package gameoflife;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class MyCustomDialogs {
	private int speed = 0,
	generations = 0,
	percentage = 0;

	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getGenerations() {
		return generations;
	}
	public void setGenerations(int generations) {
		this.generations = generations;
	}

	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public SpeedDialog newSpeedDialog() {
		return new SpeedDialog();
	}
	public GensDialog newGensDialog() {
		return new GensDialog();
	}
	public RandDialog newRandDialog() {
		return new RandDialog();
	}

	class SpeedDialog extends JPanel implements ChangeListener {
		JSlider slider;
		JLabel label;

		public SpeedDialog() {
			UIPack();
		}
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

	class GensDialog extends SpeedDialog {@Override
		protected void UIPack() {
			JPanel panel = new JPanel(new GridLayout(1, 2));
			slider = new JSlider(0, 1000, generations);
			slider.addChangeListener(this);
			label = new JLabel(generations + " Generacione(s)");
			panel.add(slider);
			panel.add(label);
			add(panel);
		}@Override
		public void stateChanged(ChangeEvent e) {
			generations = slider.getValue();
			label.setText(generations + " Generacione(s)");
		}
	}

	class RandDialog extends SpeedDialog {@Override
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
}