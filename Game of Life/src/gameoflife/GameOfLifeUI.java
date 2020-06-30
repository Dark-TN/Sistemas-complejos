/**
 *
 * @author jedua
 */
package gameoflife;

import java.awt.Color;
import javax.swing.JViewport;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import javax.swing.JColorChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser. * ;
import javax.swing.JFileChooser;
import java.io. * ;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.Queue;
import java.util.Random;

public class GameOfLifeUI extends javax.swing.JFrame implements Runnable {
	private boolean running;
	private GameBoard gameBoard;
	private final MyCustomDialogs dialogs;
	private Thread game;
	private int zoom = 11;
	private int gens = 0;
	private LinkedBlockingDeque originalCells;

	public GameOfLifeUI() {
		GameOfLifeUI.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {}
		gameBoard = new GameBoard();
		dialogs = new MyCustomDialogs();
		dialogs.setSpeed(400);
		dialogs.setGenerations(0);
		dialogs.setPercentage(25);
		initComponents();
		centerViewPort();
		MouseAdapter ma = new MouseAdapter() {
			private Point origin;@Override
			public void mouseMoved(MouseEvent e) {
				origin = null;
			}@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isMiddleMouseButton(e)) origin = new Point(e.getPoint());
			}@Override
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					gameBoard.removeClickedCell(e);
					resetGens();
				}
				if (SwingUtilities.isLeftMouseButton(e)) {
					gameBoard.addClickedCell(e);
					resetGens();
				}
			}@Override
			public void mouseDragged(MouseEvent e) {
				if (origin != null) {
					JViewport viewPort = mainContent.getViewport();
					if (viewPort != null) {
						int deltaX = origin.x - e.getX();
						int deltaY = origin.y - e.getY();
						Rectangle view = viewPort.getViewRect();
						view.x += deltaX;
						view.y += deltaY;
						gameBoard.scrollRectToVisible(view);
					}
				} else if (SwingUtilities.isLeftMouseButton(e)) {
					gameBoard.addClickedCell(e);
					resetGens();
				} else if (SwingUtilities.isRightMouseButton(e)) {
					gameBoard.removeClickedCell(e);
					resetGens();
				}
			}
		};
		gameBoard.addMouseListener(ma);
		gameBoard.addMouseMotionListener(ma);
	}@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContent = new javax.swing.JScrollPane(gameBoard);
        jPanel1 = new javax.swing.JPanel();
        randGenButton = new javax.swing.JButton();
        speedButton = new javax.swing.JButton();
        genLimitButton = new javax.swing.JButton();
        centerButton = new javax.swing.JButton();
        toggleRunButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        gensLabel = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        restartButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openFile = new javax.swing.JMenuItem();
        saveFile = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        bgMenu = new javax.swing.JMenuItem();
        borderMenu = new javax.swing.JMenuItem();
        fillMenu = new javax.swing.JMenuItem();
        gridMenu = new javax.swing.JMenuItem();
        gridCheckbox = new javax.swing.JCheckBoxMenuItem();
        analizarMenu = new javax.swing.JMenu();
        runAnalisis = new javax.swing.JMenuItem();
        showOverlay = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));

        mainContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainContent.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainContent.setWheelScrollingEnabled(false);
        mainContent.getVerticalScrollBar().setUnitIncrement(16);
        mainContent.getHorizontalScrollBar().setUnitIncrement(16);
        mainContent.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                mainContentMouseWheelMoved(evt);
            }
        });
        getContentPane().add(mainContent, java.awt.BorderLayout.CENTER);

        randGenButton.setText("Random");
        randGenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randGenButtonActionPerformed(evt);
            }
        });

        speedButton.setText("Velocidad");
        speedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speedButtonActionPerformed(evt);
            }
        });

        genLimitButton.setText("<html>\n<p>Limite</p>\n<p>Generacion</p>\n</html>");
        genLimitButton.setToolTipText("");
        genLimitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genLimitButtonActionPerformed(evt);
            }
        });

        centerButton.setText("Center");
        centerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centerButtonActionPerformed(evt);
            }
        });

        toggleRunButton.setText("Inicio");
        toggleRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleRunButtonActionPerformed(evt);
            }
        });

        stepButton.setText("Step");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        gensLabel.setText("Generacion #100");

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        restartButton.setText("Reiniciar");
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(randGenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(speedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genLimitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(centerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(gensLabel)
                .addGap(18, 18, 18)
                .addComponent(stepButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toggleRunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(toggleRunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stepButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(gensLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(genLimitButton)
                    .addComponent(speedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(randGenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(centerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(restartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jMenu1.setText("Archivo");

        openFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openFile.setText("Abrir");
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        jMenu1.add(openFile);

        saveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        saveFile.setText("Guardar");
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }
        });
        jMenu1.add(saveFile);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Colores");

        bgMenu.setText("Fondo");
        bgMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bgMenuActionPerformed(evt);
            }
        });
        jMenu2.add(bgMenu);

        borderMenu.setText("Borde");
        borderMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borderMenuActionPerformed(evt);
            }
        });
        jMenu2.add(borderMenu);

        fillMenu.setText("Relleno");
        fillMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillMenuActionPerformed(evt);
            }
        });
        jMenu2.add(fillMenu);

        gridMenu.setText("Cuadricula");
        gridMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridMenuActionPerformed(evt);
            }
        });
        jMenu2.add(gridMenu);

        gridCheckbox.setSelected(true);
        gridCheckbox.setText("Mostrar Cuadricula");
        gridCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridCheckboxActionPerformed(evt);
            }
        });
        jMenu2.add(gridCheckbox);

        jMenuBar1.add(jMenu2);

        analizarMenu.setText("Analisis");

        runAnalisis.setText("Sample");
        runAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runAnalisisActionPerformed(evt);
            }
        });
        analizarMenu.add(runAnalisis);

        showOverlay.setText("Mostrar Overlay");
        showOverlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showOverlayActionPerformed(evt);
            }
        });
        analizarMenu.add(showOverlay);

        jMenuBar1.add(analizarMenu);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
	private void randGenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randGenButtonActionPerformed
		JOptionPane.showMessageDialog(this, dialogs.newRandDialog(), "Porcentaje de Semilla", JOptionPane.INFORMATION_MESSAGE);
		resetGens();
		gameBoard.randomSeed(dialogs.getPercentage());
	}//GEN-LAST:event_randGenButtonActionPerformed
	private void speedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speedButtonActionPerformed
		JOptionPane.showMessageDialog(this, dialogs.newSpeedDialog(), "Velocidad de Simulacion", JOptionPane.INFORMATION_MESSAGE);
	}//GEN-LAST:event_speedButtonActionPerformed
	private void genLimitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genLimitButtonActionPerformed
		JOptionPane.showMessageDialog(this, dialogs.newGensDialog(), "Numbero de Generaciones", JOptionPane.INFORMATION_MESSAGE);
		gensLabel.setText(String.format("Generacion #%5d%n", dialogs.getGenerations()));
	}//GEN-LAST:event_genLimitButtonActionPerformed
	private void toggleRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleRunButtonActionPerformed
		switch (evt.getActionCommand()) {
		case "Inicio":
			toggleRunButton.setText("Alto");
			game = new Thread(GameOfLifeUI.this);
			game.start();
			break;
		case "Alto":
			toggleRunButton.setText("Inicio");
			stopRunning();
		default:
			break;
		}
	}//GEN-LAST:event_toggleRunButtonActionPerformed
	private void mainContentMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_mainContentMouseWheelMoved
		if (evt.isControlDown()) {
			int notches = evt.getWheelRotation();
			int temp = zoom - notches;
			if (temp > 0 && temp < 30) {
				zoom = temp;
				gameBoard.setPreferredSize(new Dimension(zoom * 602, zoom * 602));
				gameBoard.resizeGameBoard(temp);
				mainContent.setViewportView(gameBoard);

				Point mousePos = this.getMousePosition();
				if (notches < 0) {
					Point pos = mainContent.getViewport().getViewPosition();
					int newX = (int)(mousePos.x * (1.1f - 1f) + 1.1f * pos.x);
					int newY = (int)(mousePos.y * (1.1f - 1f) + 1.1f * pos.y);
					mainContent.getViewport().setViewPosition(new Point(newX, newY));
				} else {
					Point pos = mainContent.getViewport().getViewPosition();
					int newX = (int)(mousePos.x * (0.9f - 1f) + 0.9f * pos.x);
					int newY = (int)(mousePos.y * (0.9f - 1f) + 0.9f * pos.y);
					mainContent.getViewport().setViewPosition(new Point(newX, newY));
				}
			}
		}
	}//GEN-LAST:event_mainContentMouseWheelMoved
	private void borderMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borderMenuActionPerformed
		Color color = JColorChooser.showDialog(gameBoard, "Elije color de borde", gameBoard.getOutlineColor());
		gameBoard.setOutlineColor(color);
		gameBoard.repaint();
	}//GEN-LAST:event_borderMenuActionPerformed
	private void bgMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bgMenuActionPerformed
		Color color = JColorChooser.showDialog(gameBoard, "Elije color de fondo", gameBoard.getBackground());
		gameBoard.setBackground(color);
		if (!gridCheckbox.isSelected()) gameBoard.setGridColor(color);
	}//GEN-LAST:event_bgMenuActionPerformed
	private void fillMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillMenuActionPerformed
		Color color = JColorChooser.showDialog(gameBoard, "Elije color de relleno", gameBoard.getFillColor());
		gameBoard.setFillColor(color);
		gameBoard.repaint();
	}//GEN-LAST:event_fillMenuActionPerformed
	private void gridMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gridMenuActionPerformed
		Color color = JColorChooser.showDialog(gameBoard, "Elije color de cuadricula", gameBoard.getGridColor());
		gameBoard.setGridColor(color);
		gameBoard.repaint();
	}//GEN-LAST:event_gridMenuActionPerformed
	private void gridCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gridCheckboxActionPerformed
		if (!gridCheckbox.isSelected()) gameBoard.setGridColor(gameBoard.getBackground());
		else gameBoard.setGridColor(Color.DARK_GRAY);
		gameBoard.repaint();
	}//GEN-LAST:event_gridCheckboxActionPerformed
	private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
		stepSimulate();
	}//GEN-LAST:event_stepButtonActionPerformed
	private void openFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileActionPerformed
		LinkedBlockingDeque<Cell> ll = new LinkedBlockingDeque();
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("GoL save(*.life)", "life", "text"));
		int returnVal = fc.showOpenDialog(GameOfLifeUI.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try (
			InputStream in =new FileInputStream(file);
			Reader reader = new InputStreamReader( in );
			Reader buffer = new BufferedReader(reader)) {
				int r,
				row = 0,
				col = 0;
				while ((r = buffer.read()) != -1) {
					if ((char) r == '\n') {
						row++;
						col = 0;
					}
					if ((char) r == '*') ll.addFirst(new Cell(col - 1, row));
					col++;
				}
				gameBoard.setCells(ll);
                                originalCells = ll;
			} catch(FileNotFoundException ex) {
				Logger.getLogger(GameOfLifeUI.class.getName()).log(Level.SEVERE, null, ex);
			} catch(IOException ex) {
				Logger.getLogger(GameOfLifeUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}//GEN-LAST:event_openFileActionPerformed
	private void saveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileActionPerformed
		LinkedBlockingDeque<Cell> ll = gameBoard.getCells();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar Como...");
		fileChooser.setFileFilter(new FileNameExtensionFilter("GoL save(*.life)", "life", "text"));
		int userSelection = fileChooser.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			if (fileToSave.exists()) {
				fileToSave.delete();
			}
			String file_name = fileToSave.getAbsolutePath();
			if (!file_name.endsWith(".life")) file_name += ".life";
			try (FileWriter fw = new FileWriter(file_name, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw)) {
				boolean[][] world = new boolean[602][602];
				//for (Cell l = (Cell) ll.getFirst(); l != null; l = (Cell) l.getNext()) {
                                        Iterator<Cell> it = ll.iterator();
                                        while(it.hasNext()){
                                            Cell l = it.next();
                                            world[l.x + 1][l.y + 1] = true;
                                        };
				
				for (int row = 0; row < 600; row++) {
					for (int col = 0; col < 600; col++) {
						if (world[col][row] == true) out.print('*');
						else out.print('0');
					}
					out.print('\n');
				}
			} catch(IOException e) {}
		}
	}//GEN-LAST:event_saveFileActionPerformed
	private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
		gameBoard.clearWorld();
	}//GEN-LAST:event_clearButtonActionPerformed
	private void centerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centerButtonActionPerformed
		centerViewPort();
	}//GEN-LAST:event_centerButtonActionPerformed
	private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed
		gameBoard.clearWorld();
                gameBoard.setCells(originalCells);
                resetGens();
	}//GEN-LAST:event_restartButtonActionPerformed

        
        private ArrayList<Cell> getNeighbors(Cell node, LinkedBlockingDeque<Cell> world){
            ArrayList<Cell> v = new ArrayList<Cell>();
            if(world.contains(new Cell(node.x+1,node.y))) v.add(new Cell(node.x+1,node.y));
            if(world.contains(new Cell(node.x+1,node.y+1))) v.add(new Cell(node.x+1,node.y+1));
            if(world.contains(new Cell(node.x,node.y+1))) v.add(new Cell(node.x,node.y+1));
            if(world.contains(new Cell(node.x-1,node.y+1))) v.add(new Cell(node.x-1,node.y+1));
            if(world.contains(new Cell(node.x-1,node.y))) v.add(new Cell(node.x-1,node.y));
            if(world.contains(new Cell(node.x-1,node.y-1))) v.add(new Cell(node.x-1,node.y-1));
            if(world.contains(new Cell(node.x,node.y-1))) v.add(new Cell(node.x,node.y-1));
            if(world.contains(new Cell(node.x+1,node.y-1))) v.add(new Cell(node.x+1,node.y-1));
            return v;
        }
 
    private void runAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runAnalisisActionPerformed
        stepAnalyze();
    }//GEN-LAST:event_runAnalisisActionPerformed

    private void showOverlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showOverlayActionPerformed
        gameBoard.overlayIsEnabled = showOverlay.isSelected();
    }//GEN-LAST:event_showOverlayActionPerformed
	    
    private void stepAnalyze(){
        // Copio gameBoard a world como forma de modificar world sin afectar gameBoard
        gameBoard.clearLife();
        LinkedBlockingDeque<Cell> world = new LinkedBlockingDeque<>();
        gameBoard.getCells().forEach((Cell l)->{ world.add(l); });       
        Iterator<Cell> it = world.iterator();
        while(it.hasNext()) {// Por cada celda en world
            // BFS Para enocntrar patrones
            Cell root = it.next();
            LinkedBlockingDeque<Cell> S = new LinkedBlockingDeque<>();
            Queue<Cell> Q = new LinkedList<>();
        
            S.add(root); Q.add(root);
            it.remove();
            while(!Q.isEmpty()){ 
                Cell current = Q.remove();
                ArrayList<Cell> neighbors = getNeighbors(current,world);
                for(Cell neighbor : neighbors){
                    if(!S.contains(neighbor)){
                        S.add(neighbor);
                        Q.add(neighbor);
                        world.remove(neighbor);
                    }
                }
            } // Termine analisis de una celda
            if(S.size() > 1){
                Pattern P = new Pattern(S);
                P.setColor(getRandColor());
                gameBoard.addStillLife(P);
                System.out.println(P.centerToString());
            }
            System.out.println(gameBoard.stillLife.size());
        }// Temrine analisis de todas las celdas
    }
    
        private Color getRandColor(){
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            return new Color(r,g,b);
        }
    
        private void centerViewPort() {
		Rectangle bounds = mainContent.getViewport().getViewRect();
		Dimension size = mainContent.getViewport().getViewSize();

		int x = (size.width - bounds.width) / 2;
		int y = (size.height - bounds.height) / 2;

		mainContent.getViewport().setViewPosition(new Point(x, y));
	}

	public static void main(String args[]) {
            SwingUtilities.invokeLater(() -> {
                GameOfLifeUI life = new GameOfLifeUI();
                life.setVisible(true);
            });	
	}

	@Override
	public void run() {
		//resetGens();
		if (dialogs.getGenerations() == -1) toggleRunButton.setText("Inicio");
		else running = true;
		while (running) {
			if (gens == dialogs.getGenerations() - 1) stopRunning();
			stepSimulate();
			try {
				Thread.sleep(10000 / dialogs.getSpeed());
			} catch(InterruptedException e) {}
		}
	}

	private void resetGens() {
		gens = 0;
		gensLabel.setText(String.format("Generacion #%5d%n", gens));
		originalCells = gameBoard.getCells();
	}

	private void stepSimulate() {
		gameBoard.simulate(); ++gens;
		gensLabel.setText(String.format("Generacion #%5d%n", gens));
                stepAnalyze();
	}

	private void stopRunning() {
		toggleRunButton.setText("Inicio");
		running = false;
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu analizarMenu;
    private javax.swing.JMenuItem bgMenu;
    private javax.swing.JMenuItem borderMenu;
    private javax.swing.JButton centerButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JMenuItem fillMenu;
    private javax.swing.JButton genLimitButton;
    private javax.swing.JLabel gensLabel;
    private javax.swing.JCheckBoxMenuItem gridCheckbox;
    private javax.swing.JMenuItem gridMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane mainContent;
    private javax.swing.JMenuItem openFile;
    private javax.swing.JButton randGenButton;
    private javax.swing.JButton restartButton;
    private javax.swing.JMenuItem runAnalisis;
    private javax.swing.JMenuItem saveFile;
    private javax.swing.JCheckBoxMenuItem showOverlay;
    private javax.swing.JButton speedButton;
    private javax.swing.JButton stepButton;
    private javax.swing.JButton toggleRunButton;
    // End of variables declaration//GEN-END:variables
}