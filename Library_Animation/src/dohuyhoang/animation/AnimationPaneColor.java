package dohuyhoang.animation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationPaneColor {
	private JPanel panel;
	private Color color;

	/**
	 * @return
	 */
	public JPanel getPanel() {
		return this.panel;
	}

	/**
	 * @param panel
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * @return
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @param panel
	 * @param color
	 */
	public AnimationPaneColor(JPanel panel, Color color) {
		this.panel = panel;
		this.color = color;
	}

	/**
	 * 
	 */
	public void start() {
		Timer timer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color targetColor = AnimationPaneColor.this.color;
				int changingSpeed = 5;
				Color currentColor = AnimationPaneColor.this.panel.getBackground();

				int r = currentColor.getRed();
				int g = currentColor.getGreen();
				int b = currentColor.getBlue();
				int a = currentColor.getAlpha();

				double dr = targetColor.getRed() - r;
				double dg = targetColor.getGreen() - g;
				double db = targetColor.getBlue() - b;
				double da = targetColor.getAlpha() - a;

				double norm = Math.sqrt(dr * dr + dg * dg + db * db + da * da);
				if (norm < 0.001D) {
					((Timer) e.getSource()).stop();
					return;
				}

				dr /= norm;
				dg /= norm;
				db /= norm;
				da /= norm;

				dr *= Math.min(changingSpeed, norm);
				dg *= Math.min(changingSpeed, norm);
				db *= Math.min(changingSpeed, norm);
				da *= Math.min(changingSpeed, norm);

				r = (int) (r + dr);
				g = (int) (g + dg);
				b = (int) (b + db);
				a = (int) (a + da);

				AnimationPaneColor.this.panel.setBackground(new Color(r, g, b, a));
				AnimationPaneColor.this.panel.repaint();
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
	}
}