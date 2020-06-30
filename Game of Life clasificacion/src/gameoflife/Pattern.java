/* @author jedua */
package gameoflife;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public final class Pattern {
    // Util
    public static final int CHAOS_LIFE = 0;
    public static final int STILL_LIFE = 1;
    public static final int OSCLL_LIFE = 2;
    public static final int GLIDE_LIFE = 3;
    
    // Default
    private static final int PERIODS = 10;
    private int type = CHAOS_LIFE;
    
    // Global
    private final ArrayList<ArrayList<Cell>> history; //History almacena coordenadas relativas
    private ArrayList<Cell> region;
    private Color color;
    public Point2D oldCenter;
    public Point2D center;

    // Constructor
    public Pattern(ArrayList<Cell> region){
        history = new ArrayList();
        history.add(region);
        center = new Point2D.Double();
        //color = getRandColor();
        color = Color.WHITE; // Spawned Pattern Color
        this.region = region;
        center = getCenterFromRegion(region);
    }
    
    public Pattern findNearest(ArrayList<Pattern> set){
        double minDistance = Double.MAX_VALUE;
        Pattern result = null;
        for(Pattern P : set){
            double distance = center.distance(P.center);
            if(distance <= 3 && distance < minDistance) {
                minDistance = distance;
                result = P;
            }
        }
        return result;
    }
    
    private Point2D.Double getCenterFromRegion(ArrayList<Cell> region){
        Double x = 0.0,
        y = 0.0;
        for(Cell c : region){
            x += c.x;
            y += c.y;
        }
        x /= region.size();
        y /= region.size();
        return new Point2D.Double(x,y);
    }
    
    public ArrayList<Cell> convertToRelative(ArrayList<Cell> toConvert){
        // Encontrar numero de operaciones
        int top  = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        for(Cell c : toConvert){
            if(c.x < left) left = c.x;
            if(c.y < top) top = c.y;
        }
        return moveRegion(toConvert,-left,-top);
    }
    
    // Cada que agregamos nuevos datos, checa si ya se de que tipo es.
    public void addRegion(ArrayList<Cell> region){ 
        history.add(this.region); // Graba periodo anterior en relativo
        if(history.size() > PERIODS) history.remove(0); // Mantener historial hasta 10 periodos
        center = getCenterFromRegion(this.region);
        if(region.equals(this.region)) setAs(STILL_LIFE);
        else if(history.contains(region)) setAs(OSCLL_LIFE);
        else if(isGlider()) setAs(GLIDE_LIFE);
        else setAs(CHAOS_LIFE);
        this.region = region;
    }
    private boolean filterFlag = true;
    private boolean isGlider(){
        for(ArrayList<Cell> prevRegion : history){
            // Encunetra 2 Regiones Relativamente iguales
            if(convertToRelative(prevRegion).equals(convertToRelative(region))){
                // Si su distance es 1 o mas, es glider.
                if(getCenterFromRegion(prevRegion).distance(center) >= 1) return true;
            }
        }
        return false;
    }
    
    /*
    private boolean isGlider(){
        if(history.contains(moveRegion(1,0))) return true;
        if(history.contains(moveRegion(1,1))) return true;
        if(history.contains(moveRegion(0,1))) return true;
        if(history.contains(moveRegion(-1,1))) return true;
        if(history.contains(moveRegion(-1,0))) return true;
        if(history.contains(moveRegion(-1,-1))) return true;
        if(history.contains(moveRegion(0,-1))) return true;
        if(history.contains(moveRegion(1,-1))) return true;
        return false;
    }
    */
    private ArrayList<Cell> moveRegion(int x, int y){ return moveRegion(this.region,x,y); } 
    private ArrayList<Cell> moveRegion(ArrayList<Cell> region,int xOffset,int yOffset){
        ArrayList<Cell> copy = new ArrayList<>();
        region.forEach((Cell c)->{ copy.add(new Cell(c.x,c.y)); });

        for(Cell c : copy){
            c.x += xOffset;
            c.y += yOffset;
        }
        return copy;
    }
    
    public void setAs(int type){
        this.type = type;
        switch(type){
            case CHAOS_LIFE: color = Color.GRAY; break;
            case STILL_LIFE: color = Color.GREEN; break;
            case OSCLL_LIFE: color = Color.YELLOW; break;
            case GLIDE_LIFE: color = Color.CYAN; break;
        }
    }
    // Setters & Getters
    public int getType(){ return type; }
    public void setColor(Color color){ this.color = color; }
    public Color getColor(){ return this.color; }
    public ArrayList<Cell> getRegion() { return this.region; }    
    public synchronized void addCell(Cell c){ region.add(c); }
    public synchronized void removeCell(Cell c){ region.remove(c); }
    public String centerToString() {return center.getX() + " " + center.getY(); }
    
    // Util Functions
    @Override
    public boolean equals(Object o) {
        if(o instanceof Pattern){
            Pattern toCompare = (Pattern) o;
            return this.region.equals(toCompare.getRegion());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return region.hashCode();
    }
    
    private Color getRandColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r,g,b);
    }
}
