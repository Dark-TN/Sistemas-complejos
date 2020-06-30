package gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.geom. * ;
import java.util.Random;
import javax.swing.JPanel;
//import gameoflife.LinkedList.Linkable;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements ComponentListener {
	private static int BOXSIZE = 11;
	private static double MNUM = 10;
        public boolean overlayIsEnabled = false;
	private Dimension gameBoardSize = null;
	private Graphics2D g2d;

	Color gridColor = Color.DARK_GRAY,
	outlineColor = Color.BLACK,
	fillColor = Color.BLACK;
	Random randomGenerator = new Random();
	private LinkedBlockingDeque<Cell> cells;

	public Color getGridColor() {
		return gridColor;
	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public LinkedBlockingDeque<Cell> getCells() {
		return cells;
	}

	public void setCells(LinkedBlockingDeque<Cell> cells) {
		this.cells = cells;
		repaint();
	}

	public GameBoard() {
		this(new LinkedBlockingDeque<Cell>());
	}
	public GameBoard(LinkedBlockingDeque<Cell> cells) {
		//                this.setPreferredSize(new Dimension(7225, 7225));
		this.setPreferredSize(new Dimension(6624, 6624));
		this.cells = cells;
		addComponentListener(this);
		gameBoardSize = new Dimension(600, 600);

	}

	public void randomSeed(int percent) {
		for (int i = 0; i < gameBoardSize.width; i++)
		for (int j = 0; j < gameBoardSize.height; j++)
		if (randomGenerator.nextInt(100) < percent) addCell(i, j);
	}

	public void clearWorld() {
		//cells = new LinkedBlockingDeque();
                cells.clear();
                clearLife();
	}

	public void simulate() {
		boolean[][] world = new boolean[gameBoardSize.width + 2][gameBoardSize.height + 2];
		Iterator<Cell> it = cells.iterator();
                while(it.hasNext()){
                    Cell l = it.next();
                    world[l.x + 1][l.y + 1] = true;
		};

		LinkedBlockingDeque<Cell> survivors = new LinkedBlockingDeque();
		for (int i = 1; i < world.length - 1; i++)
		for (int j = 1; j < world[0].length - 1; j++) {
			int neighbors = 0;
			if (world[i - 1][j - 1]) { neighbors++;	}
			if (world[i - 1][j]) { neighbors++; }
			if (world[i - 1][j + 1]) { neighbors++; }
			if (world[i][j - 1]) { neighbors++; }
			if (world[i][j + 1]) { neighbors++; }
			if (world[i + 1][j - 1]) { neighbors++; }
			if (world[i + 1][j]) { neighbors++; }
			if (world[i + 1][j + 1]) { neighbors++; }
			if (world[i][j]) {
				if ((neighbors == 2) || (neighbors == 3)) survivors.addFirst(new Cell(i - 1, j - 1));
			} else {
				if (neighbors == 3) survivors.addFirst(new Cell(i - 1, j - 1));
			}
		}
		cells = new LinkedBlockingDeque<Cell>();
                it = survivors.iterator();
                while(it.hasNext()){
                    Cell l = it.next();
                    cells.addFirst(new Cell(l.x, l.y));
                };
		repaint();
	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentResized(ComponentEvent e) {
		//gameBoardSize = new Dimension(getWidth() / BOXSIZE - 2, getHeight() / BOXSIZE - 2);
		//updateLinkedList();
	}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}


	private void doDrawing(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.setColor(gridColor);

		for (int i = 0; i <= gameBoardSize.width; i++)
		g2d.drawLine(((i * BOXSIZE) + BOXSIZE), BOXSIZE, ((i * BOXSIZE) + BOXSIZE), (int)(BOXSIZE + (BOXSIZE * gameBoardSize.getHeight())));

		for (int i = 0; i <= gameBoardSize.height; i++)
		g2d.drawLine(BOXSIZE, ((i * BOXSIZE) + BOXSIZE), (int)(BOXSIZE * (gameBoardSize.getWidth() + 1)), ((i * BOXSIZE) + BOXSIZE));
                
		//for (Cell l = (Cell) cells.getFirst(); l != null; l = (Cell) l.getNext()) {
                if(overlayIsEnabled){
                    for (Pattern p : stillLife) {
                        g2d.setPaint(p.getColor());
                        for(Cell l : p.getRegion()){
                            g2d.fill(new Rectangle2D.Double(l.x * BOXSIZE + 1, l.y * BOXSIZE + 1, MNUM*3+3, MNUM*3+3));
                        }
                    }
                }
                
                
                Iterator<Cell> it = cells.iterator();
                while(it.hasNext()){
                    Cell l = it.next();
                    g2d.setPaint(outlineColor);
                    g2d.draw(new Rectangle2D.Double(l.x * BOXSIZE + BOXSIZE + 1, l.y * BOXSIZE + BOXSIZE + 1, MNUM, MNUM));
                    g2d.setPaint(fillColor);
                    g2d.fill(new Rectangle2D.Double(l.x * BOXSIZE + BOXSIZE + 1, l.y * BOXSIZE + BOXSIZE + 1, MNUM, MNUM));
                }
                /*
                g2d.setPaint(Color.RED);
                for(Pattern p : stillLife){
                    g2d.fill(new Ellipse2D.Double(p.getCenterX() * BOXSIZE + BOXSIZE +1 , p.getCenterY() * BOXSIZE + BOXSIZE +1 , MNUM, MNUM));
                }
                */
                
	}
        
        // LIFE DECLARATION
        public ArrayList<Pattern> ChaosLife = new ArrayList<Pattern>();
        public ArrayList<Pattern> StillLife = new ArrayList<Pattern>();
        public ArrayList<Pattern> OscllLife = new ArrayList<Pattern>();
        public ArrayList<Pattern> GlideLife = new ArrayList<Pattern>();
        // 0 - Chaos
        // 1 - StillLife
        // 2 - Oscillators
        // 3 - Gliders
        public LinkedBlockingDeque<Pattern> stillLife = new LinkedBlockingDeque<Pattern>();
        
        public void addStillLife(Pattern shape){
            //Life[1].history.add(shape);
            //Life[1].add(shape);
            stillLife.add(shape);
            repaint();
        }
        
        public void clearLife(){
            stillLife.clear();
            repaint();
        }
        

	public void addClickedCell(MouseEvent e) {
		int col = e.getX() / BOXSIZE - 1;
		int row = e.getY() / BOXSIZE - 1;
		addCell(col, row);
	}

	public void removeClickedCell(MouseEvent e) {
		int col = e.getX() / BOXSIZE - 1;
		int row = e.getY() / BOXSIZE - 1;
		if ((col >= 0) && (col < gameBoardSize.width) && (row >= 0) && (row < gameBoardSize.height)) {
			cells.remove(new Cell(col, row));
			repaint();
		}
	}

	private void addCell(int col, int row) {
		if ((col >= 0) && (col < gameBoardSize.width) && (row >= 0) && (row < gameBoardSize.height)) {
			cells.remove(new Cell(col, row));
			cells.addFirst(new Cell(col, row));
			repaint();
		}
	}

	private void updateLinkedList() {
		//for (Cell l = (Cell) cells.getFirst(); l != null; l = (Cell) l.getNext())
                Iterator<Cell> it = cells.iterator();
                while(it.hasNext()){
                    Cell l = it.next();
                    if ((l.x > gameBoardSize.width - 1) || (l.y > gameBoardSize.height - 1)) cells.remove(new Cell(l.x, l.y));
                };
		repaint();
	}
        
	public void resizeGameBoard(int step) {
		BOXSIZE = step;
		MNUM = step - 1;
		repaint();
	}
}