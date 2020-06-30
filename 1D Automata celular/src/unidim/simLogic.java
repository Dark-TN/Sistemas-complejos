package unidim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class simLogic extends javax.swing.JPanel {
    private static int BOXSIZE = 11;
	private static double MNUM = 10;
    private Graphics2D g2d;
    private LinkedBlockingDeque<Cell> grid;
    public Dimension gameBoardSize;
    private int rule;
    private boolean rules[];
   
    public simLogic(int size){ this(new LinkedBlockingDeque<Cell>(),size); }
    public simLogic(LinkedBlockingDeque<Cell> grid, int size) { 
        this.grid = grid;
        gameBoardSize = new Dimension(size, 1); // Always starts with 1 height
    }

    public void simulate() {
        boolean[][] world = new boolean[gameBoardSize.width + 1][gameBoardSize.height + 2];
        for(Cell c : grid) world[c.x][c.y] = true;
        
        for (int i = 0; i < gameBoardSize.width; i++) {
            
            // Ring Behaviour
            Cell prev = new Cell((i==0)?gameBoardSize.width-1:i-1,gameBoardSize.height-1);
            Cell next = new Cell((i==gameBoardSize.width-1)?0:i+1,gameBoardSize.height-1);
            Cell actual = new Cell(i,gameBoardSize.height-1);
            repaint();
            //try{Thread.sleep(50);}catch(Exception e){}
            Cell newGen = new Cell(i,gameBoardSize.height);
        
            // Simulate according to rules
                 if(  grid.contains(prev) &&  grid.contains(actual) &&  grid.contains(next) && rules[0]) grid.add(newGen);
            else if(  grid.contains(prev) &&  grid.contains(actual) && !grid.contains(next) && rules[1]) grid.add(newGen);
            else if(  grid.contains(prev) && !grid.contains(actual) &&  grid.contains(next) && rules[2]) grid.add(newGen);
            else if(  grid.contains(prev) && !grid.contains(actual) && !grid.contains(next) && rules[3]) grid.add(newGen);
            else if( !grid.contains(prev) &&  grid.contains(actual) &&  grid.contains(next) && rules[4]) grid.add(newGen);
            else if( !grid.contains(prev) &&  grid.contains(actual) && !grid.contains(next) && rules[5]) grid.add(newGen);
            else if( !grid.contains(prev) && !grid.contains(actual) &&  grid.contains(next) && rules[6]) grid.add(newGen);
            else if( !grid.contains(prev) && !grid.contains(actual) && !grid.contains(next) && rules[7]) grid.add(newGen);
                 
            repaint();
        }
        gameBoardSize.height++;
    }
    
    private void doDrawing(Graphics g) {
        g2d = (Graphics2D) g;
		
        // Cuadricula
        //g2d.setColor(gridColor);
        for (int i = 0; i <= gameBoardSize.width; i++)
            g2d.drawLine(((i * BOXSIZE) + BOXSIZE), BOXSIZE, ((i * BOXSIZE) + BOXSIZE), (int)(BOXSIZE + (BOXSIZE * gameBoardSize.getHeight())));
		for (int i = 0; i <= gameBoardSize.height; i++)
            g2d.drawLine(BOXSIZE, ((i * BOXSIZE) + BOXSIZE), (int)(BOXSIZE * (gameBoardSize.getWidth() + 1)), ((i * BOXSIZE) + BOXSIZE));
		                             
        // Celdas Activas
        for(Cell l : grid){
            //g2d.setPaint(outlineColor);
            g2d.draw(new Rectangle2D.Double(l.x * BOXSIZE + BOXSIZE + 1, l.y * BOXSIZE + BOXSIZE + 1, MNUM, MNUM));
            //g2d.setPaint(fillColor);
            g2d.fill(new Rectangle2D.Double(l.x * BOXSIZE + BOXSIZE + 1, l.y * BOXSIZE + BOXSIZE + 1, MNUM, MNUM));
        }
        /* //DEBUG CURSOR
        g2d.setPaint(Color.RED);
        g2d.draw(new Ellipse2D.Double(actual.x * BOXSIZE + BOXSIZE + 1, actual.y * BOXSIZE + BOXSIZE + 1, MNUM, MNUM));
        */
    }
    
    public void setRule(int rule){
        this.rule = rule;
        this.rules = new boolean[8];
        String str = Integer.toBinaryString(this.rule);
        char[] chr = str.toCharArray();
        int div = 8 - chr.length;

        //for (int i = 0; i < div; i++) rules[i] = false;
        for (int i = 0; i < chr.length; i++)
            rules[i + div] = (chr[i] == '1');
        
        System.out.println(""+rules[0]+rules[1]+rules[2]+rules[3]+rules[4]+rules[5]+rules[6]+rules[7]);
    }
    
    public int getRule(){ return this.rule; }
    public int getBoardSize(){ return this.gameBoardSize.width; }
    
    public void randomSeed(int percent){
        clearWorld();
		for (int i = 0; i < gameBoardSize.width; i++)
		for (int j = 0; j < gameBoardSize.height; j++)
		if (new Random().nextInt(100) < percent) addCell(i, j);
    }
    
    public void clearWorld() {
        grid.clear();
        gameBoardSize.height = 1;
        repaint();
    }
    
    public void addClickedCell(MouseEvent e) {
		int col = e.getX() / BOXSIZE - 1;
		addCell(col, 0);
	}

	public void removeClickedCell(MouseEvent e) {
		int col = e.getX() / BOXSIZE - 1;
		if ((col >= 0) && (col < gameBoardSize.width)) {
			grid.remove(new Cell(col, 0));
			repaint();
		}
	}
    
	private void addCell(int col, int row) {
		if ((col >= 0) && (col < gameBoardSize.width) && (row >= 0) && (row < gameBoardSize.height)) {
			grid.remove(new Cell(col, row));
			grid.add(new Cell(col, row));
			repaint();
		}
	}
    
    public void setBoardSize(int size) {
        gameBoardSize = new Dimension(size,1); 
        for(Cell l : grid){
            if ((l.x > gameBoardSize.width - 1) || (l.y > gameBoardSize.height - 1)) grid.remove(new Cell(l.x, l.y));
		}
		repaint();
    }

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
}
