/* @author jedua */
package unidim;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dialogs {
    // Defaults
	private int speed = 0,
	generations = 0,
	percentage = 0;
    
    // Constructor
	public final class SpeedDialog extends JPanel implements ChangeListener {
		JSlider slider;
		JLabel label;

		public SpeedDialog() { UIPack(); }
		protected void UIPack() {
			JPanel panel = new JPanel(new GridLayout(1, 2));
			slider = new JSlider(1, 1000, speed);
			slider.addChangeListener(this);
			label = new JLabel("Velocidad: " + speed);
			panel.add(slider);
			panel.add(label);
			add(panel);
		}
		@Override
		public void stateChanged(ChangeEvent e) {
			speed = slider.getValue();
			label.setText("Velocidad: " + speed);
		}
	}

	public final class GensDialog extends JPanel implements ChangeListener {
        JSlider slider;
		JLabel label;
        
        public GensDialog() { UIPack(); }
		protected void UIPack() {
			JPanel panel = new JPanel(new GridLayout(1, 2));
			slider = new JSlider(0, 1000, generations);
			slider.addChangeListener(this);
			label = new JLabel(generations + " Generacione(s)");
			panel.add(slider);
			panel.add(label);
			add(panel);
		}
        @Override
		public void stateChanged(ChangeEvent e) {
			generations = slider.getValue();
			label.setText(generations + " Generacione(s)");
		}
	}

	public final class RandDialog extends JPanel implements ChangeListener {
        JSlider slider;
		JLabel label;
        
        public RandDialog() { UIPack(); }
		protected void UIPack() {
			JPanel panel = new JPanel(new GridLayout(1, 2));
			slider = new JSlider(0, 100, percentage);
			slider.addChangeListener(this);
			label = new JLabel("Semilla " + percentage + "%");
			panel.add(slider);
			panel.add(label);
			add(panel);
		}
		@Override
		public void stateChanged(ChangeEvent e) {
			percentage = slider.getValue();
			label.setText("Semilla " + percentage + "%");
		}
	}
        
    public final class RulesDialog extends JPanel{
        JLabel label;

        public RulesDialog() { UIPack(); }
		protected void UIPack() {
            JPanel panel = new JPanel();
			label = new JLabel("Regla Actual: ");
            panel.add(label);
			add(panel);
		}     
	}
    
    public final class SizeDialog extends JPanel{
        JLabel label;

        public SizeDialog() { UIPack(); }
		protected void UIPack() {
            JPanel panel = new JPanel();
			label = new JLabel("Tamaño Actual: ");
            panel.add(label);
			add(panel);
		}     
	}
    
    // Setters & Getters
	public void  setGenerations(int generations) { this.generations = generations; }
    public void  setPercentage(int percentage)   { this.percentage = percentage; }
    public void  setSpeed(int speed)             { this.speed = speed; }
    
    public int   getGenerations() { return generations; }
	public int   getPercentage()  { return percentage; }
    public int   getSpeed()       { return speed; }
    
	public final SpeedDialog newSpeedDialog() { return new SpeedDialog(); }
	public final GensDialog newGensDialog()   {	return new GensDialog(); }
	public final RandDialog newRandDialog()   {	return new RandDialog(); }
    public final RulesDialog newRulesDialog() {	return new RulesDialog(); }
    public final SizeDialog newSizeDialog()  {	return new SizeDialog(); }
}