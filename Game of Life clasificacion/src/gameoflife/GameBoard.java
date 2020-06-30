/* @author jedua */
package gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements ComponentListener {
    // Util
    Random randomGenerator = new Random();
    
    // Canvas
	private static int BOXSIZE = 11;
	private static double MNUM = 10;
    private Graphics2D g2d;
    
    // Analyzer Config
    public boolean overlayIsEnabled = false;
    public boolean showChaos = false;
    public boolean showCenter = false;
    
    // Default Values
    private final int rules[] = {2,3,3,3};
	Color gridColor = Color.DARK_GRAY,
	outlineColor = Color.BLACK,
	fillColor = Color.BLACK;
    
	// Globals
    private Dimension gameBoardSize;
    private LinkedBlockingDeque<Cell> cells; // Current Cell Drawing
    public ArrayList<Pattern> computedCells = new ArrayList<>(); // Background Cells for Analize Function
    // 0 - Chaos - RED
    // 1 - StillLife - GREEN
    // 2 - Oscillators - YELLOW
    // 3 - Gliders - BLUE

    // Constructors            
	public GameBoard(int size) { this(new LinkedBlockingDeque<Cell>(),size); }
	public GameBoard(LinkedBlockingDeque<Cell> cells,int size) {
		this.cells = cells;
		//addComponentListener(this);
		gameBoardSize = new Dimension(size,size);
	}

	public void randomSeed(int percent) {
        clearWorld();
		for (int i = 0; i < gameBoardSize.width; i++)
		for (int j = 0; j < gameBoardSize.height; j++)
		if (randomGenerator.nextInt(100) < percent) addCell(i, j);
	}

	public void clearWorld() {
        cells.clear();
        computedCells.clear();
        repaint();
	}

	public void simulate() {
		boolean[][] world = new boolean[gameBoardSize.width + 2][gameBoardSize.height + 2];
		Iterator<Cell> it = cells.iterator();
        while(it.hasNext()){
            Cell l = it.next();
            world[l.x + 1][l.y + 1] = true;
		}

		LinkedBlockingDeque<Cell> survivors = new LinkedBlockingDeque();
		for (int i = 1; i < world.length - 1; i++){
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
                if (world[i][j]) { // Si esta vivo
                    if ((neighbors >= rules[0]) && (neighbors <= rules[1])) survivors.addFirst(new Cell(i - 1, j - 1));
                } else {
                    if ((neighbors >= rules[2]) && (neighbors <= rules[3])) survivors.addFirst(new Cell(i - 1, j - 1));
                }
            }
        }
		cells = new LinkedBlockingDeque<>();
        it = survivors.iterator();
        while(it.hasNext()){
            Cell l = it.next();
            cells.addFirst(new Cell(l.x, l.y));
        }
		repaint();
	}

	private void doDrawing(Graphics g) {
		g2d = (Graphics2D) g;
		
        // Cuadricula
        g2d.setColor(gridColor);
        for (int i = 0; i <= gameBoardSize.width; i++)
            g2d.drawLine(((i * BOXSIZE) + BOXSIZE), BOXSIZE, ((i * BOXSIZE) + BOXSIZE), (int)(BOXSIZE + (BOXSIZE * gameBoardSize.getHeight())));
		for (int i = 0; i <= gameBoardSize.height; i++)
            g2d.drawLine(BOXSIZE, ((i * BOXSIZE) + BOXSIZE), (int)(BOXSIZE * (gameBoardSize.getWidth() + 1)), ((i * BOXSIZE) + BOXSIZE));
		               
		// Color de Analisis de fondo
        if(overlayIsEnabled){
            computedCells.stream().filter((p) -> (showChaos || (p.getType() != Pattern.CHAOS_LIFE))).map((p) -> {
                g2d.setPaint(p.getColor());
                return p;
            }).forEachOrdered((p) -> {
                p.getRegion().forEach((l) -> {
                    g2d.fill(new Rectangle2D.Double(l.x * BOXSIZE + 1, l.y * BOXSIZE + 1, MNUM*3+3, MNUM*3+3));
                });
            });
        }
                
        // Celdas Activas
        Iterator<Cell> it = cells.iterator();
        while(it.hasNext()){
            Cell l = it.next();
            g2d.setPaint(outlineColor);
            g2d.draw(new Rectangle2D.Double(l.x * BOXSIZE + BOXSIZE + 1, l.y * BOXSIZE + BOXSIZE + 1, MNUM, MNUM));
            g2d.setPaint(fillColor);
            g2d.fill(new Rectangle2D.Double(l.x * BOXSIZE + BOXSIZE + 1, l.y * BOXSIZE + BOXSIZE + 1, MNUM, MNUM));
        }
                
        // Centros
        if(overlayIsEnabled && showCenter){
            g2d.setPaint(Color.RED);
            computedCells.forEach((p) -> {
                g2d.fill(new Ellipse2D.Double(p.center.getX()* BOXSIZE + BOXSIZE +1 , p.center.getY() * BOXSIZE + BOXSIZE +1 , MNUM, MNUM));
            });
        }
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
        Iterator<Cell> it = cells.iterator();
        while(it.hasNext()){
            Cell l = it.next();
            if ((l.x > gameBoardSize.width - 1) || (l.y > gameBoardSize.height - 1)) cells.remove(new Cell(l.x, l.y));
		}
		repaint();
	}

	public void resizeGameBoard(int step) {
		BOXSIZE = step;
		MNUM = step - 1;
		repaint();
	}

    public void setRules(String rules){
        int i = 0;
        for(String item : rules.split("")){
            this.rules[i++] = Integer.parseInt(item);
        }
    }
    // Setters & Getters
    public String getRules(){ return ""+rules[0]+rules[1]+rules[2]+rules[3]; }
    public Color getGridColor() {return gridColor; }
	public Color getOutlineColor() { return outlineColor; }
	public Color getFillColor() { return fillColor; }
	public void setGridColor(Color gridColor) { this.gridColor = gridColor; }
	public void setOutlineColor(Color outlineColor) { this.outlineColor = outlineColor; }
	public void setFillColor(Color fillColor) { this.fillColor = fillColor; }
	public LinkedBlockingDeque<Cell> getCells() { return cells; }
	public void setCells(LinkedBlockingDeque<Cell> cells) {	this.cells = cells; repaint(); }
    public int getBoardSize(){ return this.gameBoardSize.height; } 
    public void setBoardSize(int size) { gameBoardSize = new Dimension(size,size); updateLinkedList(); }
    
    // Overridables
    @Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentResized(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
}