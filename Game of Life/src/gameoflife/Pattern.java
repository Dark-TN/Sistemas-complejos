/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;
import java.util.Iterator;
import java.util.ArrayList;
import java.awt.Color;
import java.util.concurrent.LinkedBlockingDeque;
/**
 *
 * @author jedua
 */
public class Pattern {
    private static int PERIODS = 10;
    private ArrayList<LinkedBlockingDeque<Cell>> history;
    private LinkedBlockingDeque<Cell> region;
    private Color color;
    private int type = 0;
    private double center_x;
    private double center_y;
    
    public Pattern(LinkedBlockingDeque<Cell> region){
        this.region = region;
        for(Cell c : region){
            this.center_x += c.x;
            this.center_y += c.y;
        }
        this.center_x /= region.size();
        this.center_y /= region.size();
    }

    public void setColor(Color color){ this.color = color; }
    public Color getColor(){ return this.color; }
    public void addRegion(LinkedBlockingDeque<Cell> region){ 
        if(type != 0) { // Si aun no identifico que tipo de patron soy...
            this.history.add(this.region); // Graba periodo
            //guessType() // ?
        }
        this.region = region;
    }
    public LinkedBlockingDeque<Cell> getRegion() {
        return this.region;
    }
    public synchronized void addCell(Cell c){ region.add(c); }
    public synchronized void removeCell(Cell c){ region.remove(c); }
    public String centerToString() {
			return center_x + " " + center_y;
		}
    public double getCenterX(){ return this.center_x; }
    public double getCenterY(){ return this.center_y; }
    
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
}
