/* @author jedua */
package gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLabelLocation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LifeChart extends javax.swing.JFrame {
    private final XYSeries data = new XYSeries("Celdas Activas");
    private final XYSeries mean = new XYSeries("Promedio");
    private final XYSeries variance = new XYSeries("Varianza");
    private final XYSeries stdDev = new XYSeries("Dev Std");
    private double xCount;
    private int size;
    
    public LifeChart(int size) {
        super("Estadisticas");
        this.size = size;
        xCount = 0.0;
        initComponents();
        initChart();
    }
    
    private void initChart(){
        getContentPane().add(createDemoPanel(),BorderLayout.CENTER);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        activeCellsPanel = new javax.swing.JPanel();
        activeCellsLabelText = new javax.swing.JLabel();
        activeCellsLabelNum = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(32767, 0));
        meanPanel = new javax.swing.JPanel();
        meanLabelText = new javax.swing.JLabel();
        meanLabelNum = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(32767, 0));
        variancePanel = new javax.swing.JPanel();
        varianceLabelText = new javax.swing.JLabel();
        varianceLabelNum = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(32767, 0));
        devStdPanel = new javax.swing.JPanel();
        devStdLabelText = new javax.swing.JLabel();
        devStdLabelNum = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exportXLS = new javax.swing.JMenuItem();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(670, 450));
        setPreferredSize(new java.awt.Dimension(670, 450));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        activeCellsPanel.setBackground(java.awt.Color.white);
        activeCellsPanel.setLayout(new java.awt.BorderLayout());

        activeCellsLabelText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        activeCellsLabelText.setForeground(new java.awt.Color(0, 0, 255));
        activeCellsLabelText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activeCellsLabelText.setText("Celdas Activas");
        activeCellsLabelText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        activeCellsLabelText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        activeCellsLabelText.setPreferredSize(new java.awt.Dimension(115, 22));
        activeCellsPanel.add(activeCellsLabelText, java.awt.BorderLayout.CENTER);

        activeCellsLabelNum.setBackground(java.awt.Color.white);
        activeCellsLabelNum.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        activeCellsLabelNum.setForeground(java.awt.Color.blue);
        activeCellsLabelNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activeCellsLabelNum.setText("0");
        activeCellsLabelNum.setFocusable(false);
        activeCellsLabelNum.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        activeCellsPanel.add(activeCellsLabelNum, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(activeCellsPanel);
        jPanel1.add(filler1);

        meanPanel.setBackground(java.awt.Color.white);
        meanPanel.setLayout(new java.awt.BorderLayout());

        meanLabelText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        meanLabelText.setForeground(java.awt.Color.orange);
        meanLabelText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meanLabelText.setText("Promedio");
        meanLabelText.setPreferredSize(new java.awt.Dimension(115, 22));
        meanPanel.add(meanLabelText, java.awt.BorderLayout.PAGE_START);

        meanLabelNum.setBackground(java.awt.Color.white);
        meanLabelNum.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        meanLabelNum.setForeground(java.awt.Color.orange);
        meanLabelNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meanLabelNum.setText("0");
        meanLabelNum.setFocusable(false);
        meanLabelNum.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        meanPanel.add(meanLabelNum, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(meanPanel);
        jPanel1.add(filler2);

        variancePanel.setBackground(java.awt.Color.white);
        variancePanel.setLayout(new java.awt.BorderLayout());

        varianceLabelText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        varianceLabelText.setForeground(java.awt.Color.green);
        varianceLabelText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        varianceLabelText.setText("Varianza");
        varianceLabelText.setPreferredSize(new java.awt.Dimension(115, 22));
        variancePanel.add(varianceLabelText, java.awt.BorderLayout.CENTER);

        varianceLabelNum.setBackground(java.awt.Color.white);
        varianceLabelNum.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        varianceLabelNum.setForeground(java.awt.Color.green);
        varianceLabelNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        varianceLabelNum.setText("0");
        varianceLabelNum.setFocusable(false);
        varianceLabelNum.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        variancePanel.add(varianceLabelNum, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(variancePanel);
        jPanel1.add(filler3);

        devStdPanel.setBackground(java.awt.Color.white);
        devStdPanel.setLayout(new java.awt.BorderLayout());

        devStdLabelText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        devStdLabelText.setForeground(java.awt.Color.red);
        devStdLabelText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        devStdLabelText.setText("DevStd");
        devStdLabelText.setPreferredSize(new java.awt.Dimension(115, 22));
        devStdPanel.add(devStdLabelText, java.awt.BorderLayout.PAGE_START);

        devStdLabelNum.setBackground(java.awt.Color.white);
        devStdLabelNum.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        devStdLabelNum.setForeground(java.awt.Color.red);
        devStdLabelNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        devStdLabelNum.setText("0");
        devStdLabelNum.setFocusable(false);
        devStdLabelNum.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        devStdPanel.add(devStdLabelNum, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(devStdPanel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        fileMenu.setText("Archivo");

        exportXLS.setText("Exportar (.xls)");
        fileMenu.add(exportXLS);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   public void resetChart(){
        xCount = 0.0;
        data.clear();
        mean.clear();
        variance.clear();
        stdDev.clear();
    }

    private ChartPanel createDemoPanel() {
        JFreeChart jfreechart = ChartFactory.createXYLineChart(
            null, "X", "Y", createDataset(),
            PlotOrientation.VERTICAL, true, true, false);
        NumberAxis axis2 = new NumberAxis("Varianza");
        
        jfreechart.removeLegend();
        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
        
        xyPlot.setRangeAxis(1, axis2);
        xyPlot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        xySeriesCollection.addSeries(variance);

        xyPlot.setDataset(1,xySeriesCollection);
        xyPlot.mapDatasetToRangeAxis(1, 1);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setSeriesPaint(0,Color.green);
        renderer2.setSeriesShapesVisible(0, false);
        
        xyPlot.setRenderer(1, renderer2);
        
        //xyPlot.setDomainCrosshairVisible(true);        
        //xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        renderer.setSeriesPaint(1, Color.red);
        renderer.setSeriesPaint(2, Color.yellow);
        
        
        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setLabelLocation(AxisLabelLocation.HIGH_END);
        domain.setLabel("Generación");
        domain.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setLabel(null);
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        //domain.setVerticalTickLabels(true);
        return new ChartPanel(jfreechart);
    }

    private XYDataset createDataset() {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        xySeriesCollection.addSeries(data);
        xySeriesCollection.addSeries(stdDev);
        xySeriesCollection.addSeries(mean);
        return xySeriesCollection;
    }
    
    public void updateCount(Object count){
        try{
            if(count != null){
                xCount++;
                data.add(xCount, (int)count);
                Statistics stats = new Statistics(data.toArray()[1]);
                mean.add(xCount,stats.getMean());
                variance.add(xCount,stats.getVariance());
                stdDev.add(xCount,stats.getStdDev());
                               
                activeCellsLabelNum.setText(""+(int)count);
                meanLabelNum.setText(""+(double)Math.round(stats.getMean() * 100) / (size*size));
                varianceLabelNum.setText(""+(double)Math.round(stats.getVariance() * 100) / 100);
                devStdLabelNum.setText(""+(double)Math.round(stats.getStdDev() * 100) / 100);
                
            }
        }catch(Exception e){}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activeCellsLabelNum;
    private javax.swing.JLabel activeCellsLabelText;
    private javax.swing.JPanel activeCellsPanel;
    private javax.swing.JLabel devStdLabelNum;
    private javax.swing.JLabel devStdLabelText;
    private javax.swing.JPanel devStdPanel;
    private javax.swing.JMenuItem exportXLS;
    private javax.swing.JMenu fileMenu;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel meanLabelNum;
    private javax.swing.JLabel meanLabelText;
    private javax.swing.JPanel meanPanel;
    private javax.swing.JLabel varianceLabelNum;
    private javax.swing.JLabel varianceLabelText;
    private javax.swing.JPanel variancePanel;
    // End of variables declaration//GEN-END:variables
}
