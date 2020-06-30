/* @author jedua */
package gameoflife;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameOfLifeUI extends JFrame implements Runnable {
    //Default
    private int zoom = 11;
	private int gens = 0;
    private int size = 100;
        
    //Global
	private boolean running;
	private GameBoard gameBoard;
	private final Dialogs dialogs;
	private Thread game;
	private LinkedBlockingDeque originalCells;
    private final LifeChart chart;
    
    //Constructors
	public GameOfLifeUI() {
        // Variables
        int ratio = (int)(size * 11.04);
        
        // Look and Feel
		GameOfLifeUI.setDefaultLookAndFeelDecorated(true);
		try { UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {}
        
        // Initialization
		gameBoard = new GameBoard(size);
        gameBoard.setPreferredSize(new Dimension(ratio,ratio));
		dialogs = new Dialogs();
		dialogs.setSpeed(400);
		dialogs.setGenerations(0);
		dialogs.setPercentage(25);
        gameBoard.setRules("2333");
		initComponents();
		centerViewPort();
        chart = new LifeChart(size);
        
        // Listeners
		gameBoard.addMouseListener(ma);
		gameBoard.addMouseMotionListener(ma);
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
    private void toggleRun(){
        //switch (evt.getActionCommand()) {
        switch(toggleRunButton.getActionCommand()){
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
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statsPanel = new javax.swing.JPanel();
        chaosLifeLabelNum = new javax.swing.JLabel();
        stillLifeLabelNum = new javax.swing.JLabel();
        oscllLifeLabelNum = new javax.swing.JLabel();
        glideLifeLabelNum = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        mainContent = new javax.swing.JScrollPane(gameBoard);
        mainJPanel = new javax.swing.JPanel();
        randGenButton = new javax.swing.JButton();
        speedButton = new javax.swing.JButton();
        genLimitButton = new javax.swing.JButton();
        centerButton = new javax.swing.JButton();
        toggleRunButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        gensLabel = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        restartButton = new javax.swing.JButton();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openFile = new javax.swing.JMenuItem();
        saveFile = new javax.swing.JMenuItem();
        genRand = new javax.swing.JMenuItem();
        sizeChange = new javax.swing.JMenuItem();
        colorsMenu = new javax.swing.JMenu();
        gridCheckbox = new javax.swing.JCheckBoxMenuItem();
        borderMenu = new javax.swing.JMenuItem();
        gridMenu = new javax.swing.JMenuItem();
        bgMenu = new javax.swing.JMenuItem();
        fillMenu = new javax.swing.JMenuItem();
        analizarMenu = new javax.swing.JMenu();
        openGraph = new javax.swing.JMenuItem();
        showOverlay = new javax.swing.JCheckBoxMenuItem();
        showChaos = new javax.swing.JCheckBoxMenuItem();
        showCenter = new javax.swing.JCheckBoxMenuItem();
        debugMenu = new javax.swing.JMenu();
        debug_toggleRun = new javax.swing.JMenuItem();
        debug_stepSimulate = new javax.swing.JMenuItem();
        debug_stepAnalize = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));

        statsPanel.setLayout(new java.awt.GridLayout(1, 7));

        chaosLifeLabelNum.setText("Sin Identificar: 0");
        statsPanel.add(chaosLifeLabelNum);
        statsPanel.setVisible(false);

        stillLifeLabelNum.setText("StillLife: 0");
        statsPanel.add(stillLifeLabelNum);

        oscllLifeLabelNum.setText("Osciladores: 0");
        statsPanel.add(oscllLifeLabelNum);

        glideLifeLabelNum.setText("Gliders: 0");
        statsPanel.add(glideLifeLabelNum);
        statsPanel.add(filler1);
        statsPanel.add(filler2);
        statsPanel.add(filler3);

        getContentPane().add(statsPanel, java.awt.BorderLayout.PAGE_START);

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

        randGenButton.setText("Reglas");
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

        javax.swing.GroupLayout mainJPanelLayout = new javax.swing.GroupLayout(mainJPanel);
        mainJPanel.setLayout(mainJPanelLayout);
        mainJPanelLayout.setHorizontalGroup(
            mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainJPanelLayout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gensLabel)
                .addGap(18, 18, 18)
                .addComponent(stepButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toggleRunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        mainJPanelLayout.setVerticalGroup(
            mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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

        getContentPane().add(mainJPanel, java.awt.BorderLayout.PAGE_END);

        fileMenu.setText("Archivo");

        openFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openFile.setText("Abrir");
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        fileMenu.add(openFile);

        saveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveFile.setText("Guardar");
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }
        });
        fileMenu.add(saveFile);

        genRand.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        genRand.setText("Generar Random");
        genRand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genRandActionPerformed(evt);
            }
        });
        fileMenu.add(genRand);

        sizeChange.setText("Cambiar Tamaño");
        sizeChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeChangeActionPerformed(evt);
            }
        });
        fileMenu.add(sizeChange);

        mainMenuBar.add(fileMenu);

        colorsMenu.setText("Colores");

        gridCheckbox.setSelected(true);
        gridCheckbox.setText("Mostrar Cuadricula");
        gridCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridCheckboxActionPerformed(evt);
            }
        });
        colorsMenu.add(gridCheckbox);

        borderMenu.setText("Borde");
        borderMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borderMenuActionPerformed(evt);
            }
        });
        colorsMenu.add(borderMenu);

        gridMenu.setText("Cuadricula");
        gridMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridMenuActionPerformed(evt);
            }
        });
        colorsMenu.add(gridMenu);

        bgMenu.setText("Fondo");
        bgMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bgMenuActionPerformed(evt);
            }
        });
        colorsMenu.add(bgMenu);

        fillMenu.setText("Relleno");
        fillMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillMenuActionPerformed(evt);
            }
        });
        colorsMenu.add(fillMenu);

        mainMenuBar.add(colorsMenu);

        analizarMenu.setText("Analisis");

        openGraph.setText("Grafica");
        openGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openGraphActionPerformed(evt);
            }
        });
        analizarMenu.add(openGraph);

        showOverlay.setText("Mostrar Overlay");
        showOverlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showOverlayActionPerformed(evt);
            }
        });
        analizarMenu.add(showOverlay);

        showChaos.setText("Mostrar Caos");
        showChaos.setEnabled(false);
        showChaos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showChaosActionPerformed(evt);
            }
        });
        analizarMenu.add(showChaos);

        showCenter.setText("Mostrar Centros");
        showCenter.setEnabled(false);
        showCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCenterActionPerformed(evt);
            }
        });
        analizarMenu.add(showCenter);

        mainMenuBar.add(analizarMenu);

        debugMenu.setText("DEBUG");

        debug_toggleRun.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        debug_toggleRun.setText("Start/Stop");
        debug_toggleRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debug_toggleRunActionPerformed(evt);
            }
        });
        debugMenu.add(debug_toggleRun);

        debug_stepSimulate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        debug_stepSimulate.setText("Step Simulate");
        debug_stepSimulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debug_stepSimulateActionPerformed(evt);
            }
        });
        debugMenu.add(debug_stepSimulate);

        debug_stepAnalize.setText("StepAnalize");
        debug_stepAnalize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debug_stepAnalizeActionPerformed(evt);
            }
        });
        debugMenu.add(debug_stepAnalize);

        mainMenuBar.add(debugMenu);

        setJMenuBar(mainMenuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
	private void randGenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randGenButtonActionPerformed
        String val = JOptionPane.showInputDialog(this, dialogs.newRulesDialog(), gameBoard.getRules());
        if((val != null) && (!val.isEmpty())){
            gameBoard.setRules(val);
        }
	}//GEN-LAST:event_randGenButtonActionPerformed
	private void speedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speedButtonActionPerformed
		JOptionPane.showMessageDialog(this, dialogs.newSpeedDialog(), "Velocidad de Simulacion", JOptionPane.INFORMATION_MESSAGE);
	}//GEN-LAST:event_speedButtonActionPerformed
	private void genLimitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genLimitButtonActionPerformed
		JOptionPane.showMessageDialog(this, dialogs.newGensDialog(), "Numbero de Generaciones", JOptionPane.INFORMATION_MESSAGE);
		gensLabel.setText(String.format("Generacion #%5d%n", dialogs.getGenerations()));
	}//GEN-LAST:event_genLimitButtonActionPerformed
	private void toggleRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleRunButtonActionPerformed
		toggleRun();
	}//GEN-LAST:event_toggleRunButtonActionPerformed
	private void mainContentMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_mainContentMouseWheelMoved
		if (evt.isControlDown()) {
			int notches = evt.getWheelRotation();
			int temp = zoom - notches;
			if (temp > 0 && temp < 30) {
				zoom = temp;
				gameBoard.setPreferredSize(new Dimension(zoom * (gameBoard.getBoardSize() + 2), zoom * (gameBoard.getBoardSize() + 2)));
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
        resetGens();
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
			}
            catch(FileNotFoundException ex) {}
            catch(IOException ex) {}
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
                Iterator<Cell> it = ll.iterator();
                while(it.hasNext()){
                    Cell l = it.next();
                    world[l.x + 1][l.y + 1] = true;
                }				
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
        chart.resetChart();
	}//GEN-LAST:event_clearButtonActionPerformed
	private void centerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centerButtonActionPerformed
		centerViewPort();
	}//GEN-LAST:event_centerButtonActionPerformed
	private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed
        gameBoard.clearWorld();
        gameBoard.setCells(originalCells);
        resetGens();
        if(showOverlay.isSelected()) stepAnalyze();
	}//GEN-LAST:event_restartButtonActionPerformed
 
    private void debug_stepAnalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debug_stepAnalizeActionPerformed
        stepAnalyze();
        gameBoard.repaint();
    }//GEN-LAST:event_debug_stepAnalizeActionPerformed

    private void showOverlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showOverlayActionPerformed
        statsPanel.setVisible(showOverlay.isSelected());
        gameBoard.overlayIsEnabled = showOverlay.isSelected();
        showChaos.setEnabled(showOverlay.isSelected());
        showCenter.setEnabled(showOverlay.isSelected());
        if(showOverlay.isSelected()) stepAnalyze();
        gameBoard.repaint();
    }//GEN-LAST:event_showOverlayActionPerformed

    private void showChaosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showChaosActionPerformed
        gameBoard.showChaos = showChaos.isSelected();
        gameBoard.repaint();
    }//GEN-LAST:event_showChaosActionPerformed

    private void genRandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genRandActionPerformed
        
        JOptionPane.showMessageDialog(this, dialogs.newRandDialog(), "Porcentaje de Semilla", JOptionPane.INFORMATION_MESSAGE);
        resetGens();
        gameBoard.randomSeed(dialogs.getPercentage());
    }//GEN-LAST:event_genRandActionPerformed

    private void openGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openGraphActionPerformed
        chart.setVisible(true);
        chart.requestFocus();
    }//GEN-LAST:event_openGraphActionPerformed

    private void showCenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCenterActionPerformed
        gameBoard.showCenter = showCenter.isSelected();
        gameBoard.repaint();
    }//GEN-LAST:event_showCenterActionPerformed

    private void sizeChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeChangeActionPerformed
        int newSize = Integer.parseInt(JOptionPane.showInputDialog(this, dialogs.newSizeDialog(), gameBoard.getBoardSize()));
        gameBoard.setBoardSize(newSize);
        centerViewPort();
    }//GEN-LAST:event_sizeChangeActionPerformed

    private void debug_toggleRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debug_toggleRunActionPerformed
        toggleRun();
    }//GEN-LAST:event_debug_toggleRunActionPerformed

    private void debug_stepSimulateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debug_stepSimulateActionPerformed
        stepSimulate();
    }//GEN-LAST:event_debug_stepSimulateActionPerformed
	
    private void stepSimulate() {
		gameBoard.simulate(); ++gens;
		gensLabel.setText(String.format("Generacion #%5d%n", gens));
        if(showOverlay.isSelected()) stepAnalyze();
        chart.updateCount(gameBoard.getCells().size());
	}
    
    private void stepAnalyze(){
        // Copio gameBoard a world como forma de modificar world sin afectar gameBoard
        ArrayList<Pattern> newData = new ArrayList<>();
        ArrayList<Pattern> oldData = (ArrayList<Pattern>)gameBoard.computedCells.clone();
        LinkedBlockingDeque<Cell> world = new LinkedBlockingDeque<>();
        gameBoard.getCells().forEach((Cell l)->{ world.add(l); });
        Iterator<Cell> it = world.iterator();
        while(it.hasNext()) {// Por cada celda en world
            // BFS Para enocntrar patrones
            Cell root = it.next();
            ArrayList<Cell> S = new ArrayList<>();
            Queue<Cell> Q = new LinkedBlockingDeque<>();
            S.add(root); Q.add(root);
            it.remove();
            while(!Q.isEmpty()){ 
                Cell current = Q.remove();
                ArrayList<Cell> neighbors = getNeighbors(current,world);
                neighbors.stream().filter((neighbor) -> (!S.contains(neighbor))).map((neighbor) -> {
                    S.add(neighbor);
                    return neighbor;
                }).map((neighbor) -> {
                    Q.add(neighbor);
                    return neighbor;
                }).forEachOrdered((neighbor) -> {
                    world.remove(neighbor);
                });
            } // Termine analisis de una celda
            if(S.size() > 1){
                Pattern P = new Pattern(S);
                Pattern nearest = P.findNearest(oldData);
                if(nearest != null){ // Si encuentro centros cercanos, es una evolucion.
                    nearest.addRegion(S);
                    newData.add(nearest);
                    oldData.remove(nearest);
                }else newData.add(P); // Si no, es nuevo objeto
            }
        }// Temrine analisis de todas las celdas
        gameBoard.computedCells = newData;
        // Report Results to Labels
        int chaos=0,still=0,oscll=0,glide=0;
        for(Pattern p : newData){
            switch(p.getType()){
                case Pattern.CHAOS_LIFE: chaos++; break;
                case Pattern.STILL_LIFE: still++; break;
                case Pattern.OSCLL_LIFE: oscll++; break;
                case Pattern.GLIDE_LIFE: glide++; break;
            }
        }
        stillLifeLabelNum.setText("StillLife: "+still);
        chaosLifeLabelNum.setText("Sin Identificar:"+chaos);
        oscllLifeLabelNum.setText("Osciladores: "+oscll);
        glideLifeLabelNum.setText("Gliders: "+glide);
        
    }
        
    private void centerViewPort() {
		Rectangle bounds = mainContent.getViewport().getViewRect();
		Dimension dim = mainContent.getViewport().getViewSize();

		int x = (dim.width - bounds.width) / 2;
		int y = (dim.height - bounds.height) / 2;

		mainContent.getViewport().setViewPosition(new Point(x, y));
	}

	private void resetGens() {
		gens = 0;
		gensLabel.setText(String.format("Generacion #%5d%n", gens));
		originalCells = gameBoard.getCells();
        chart.resetChart();
        gameBoard.computedCells.clear();
	}

	private void stopRunning() {
		toggleRunButton.setText("Inicio");
		running = false;
	}
    
    private ArrayList<Cell> getNeighbors(Cell node, LinkedBlockingDeque<Cell> world){
        ArrayList<Cell> v = new ArrayList<>();
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu analizarMenu;
    private javax.swing.JMenuItem bgMenu;
    private javax.swing.JMenuItem borderMenu;
    private javax.swing.JButton centerButton;
    private javax.swing.JLabel chaosLifeLabelNum;
    private javax.swing.JButton clearButton;
    private javax.swing.JMenu colorsMenu;
    private javax.swing.JMenu debugMenu;
    private javax.swing.JMenuItem debug_stepAnalize;
    private javax.swing.JMenuItem debug_stepSimulate;
    private javax.swing.JMenuItem debug_toggleRun;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fillMenu;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JButton genLimitButton;
    private javax.swing.JMenuItem genRand;
    private javax.swing.JLabel gensLabel;
    private javax.swing.JLabel glideLifeLabelNum;
    private javax.swing.JCheckBoxMenuItem gridCheckbox;
    private javax.swing.JMenuItem gridMenu;
    private javax.swing.JScrollPane mainContent;
    private javax.swing.JPanel mainJPanel;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem openFile;
    private javax.swing.JMenuItem openGraph;
    private javax.swing.JLabel oscllLifeLabelNum;
    private javax.swing.JButton randGenButton;
    private javax.swing.JButton restartButton;
    private javax.swing.JMenuItem saveFile;
    private javax.swing.JCheckBoxMenuItem showCenter;
    private javax.swing.JCheckBoxMenuItem showChaos;
    private javax.swing.JCheckBoxMenuItem showOverlay;
    private javax.swing.JMenuItem sizeChange;
    private javax.swing.JButton speedButton;
    private javax.swing.JPanel statsPanel;
    private javax.swing.JButton stepButton;
    private javax.swing.JLabel stillLifeLabelNum;
    private javax.swing.JButton toggleRunButton;
    // End of variables declaration//GEN-END:variables
    
    // Mouse Adapter
    MouseAdapter ma = new MouseAdapter() {
        private Point origin;
        @Override public void mouseMoved(MouseEvent e) { origin = null; }
        @Override public void mousePressed(MouseEvent e) { if (SwingUtilities.isMiddleMouseButton(e)) origin = new Point(e.getPoint());	}
        @Override
		public void mouseReleased(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				gameBoard.removeClickedCell(e);
				resetGens();
			}
			if (SwingUtilities.isLeftMouseButton(e)) {
				gameBoard.addClickedCell(e);
				resetGens();
			}
		}
        @Override
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
}