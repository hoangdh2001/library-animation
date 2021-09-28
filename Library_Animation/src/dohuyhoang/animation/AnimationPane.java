package dohuyhoang.animation;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationPane {
	private int runTime = 200;
	private JPanel panel;
	private Rectangle from;
	private Rectangle to;
	private long startTime;

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
	public Rectangle getFrom() {
		return this.from;
	}

	/**
	 * @param from
	 */
	public void setFrom(Rectangle from) {
		this.from = from;
	}

	/**
	 * @return
	 */
	public Rectangle getTo() {
		return this.to;
	}

	/**
	 * @param to
	 */
	public void setTo(Rectangle to) {
		this.to = to;
	}

	/**
	 * @return
	 */
	public int getRunTime() {
		return this.runTime;
	}

	/**
	 * @param runTime
	 */
	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}

	/**
	 * @param panel
	 * @param from
	 * @param to
	 * @param runTime
	 */
	public AnimationPane(JPanel panel, Rectangle from, Rectangle to, int runTime) {
		setPanel(panel);
		setFrom(from);
		setTo(to);
		setRunTime(runTime);
	}

	/**
	 * @param panel
	 * @param from
	 * @param to
	 */
	public AnimationPane(JPanel panel, Rectangle from, Rectangle to) {
		setPanel(panel);
		setFrom(from);
		setTo(to);
	}

	/**
	 * 
	 */
	public void start() {
		Timer timer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long duration = System.currentTimeMillis() - AnimationPane.this.startTime;
				double progress = duration / AnimationPane.this.runTime;
				if (progress > 1.0D) {
					progress = 1.0D;
					((Timer) e.getSource()).stop();
				}
				Rectangle target = AnimationPane.this.calculateProgress(AnimationPane.this.from, AnimationPane.this.to,
						progress);
				AnimationPane.this.panel.setBounds(target);
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		this.startTime = System.currentTimeMillis();
		timer.start();
	}

	/**
	 * @param startBounds
	 * @param targetBounds
	 * @param progress
	 * @return
	 */
	public Rectangle calculateProgress(Rectangle startBounds, Rectangle targetBounds, double progress) {
		Rectangle bounds = new Rectangle();

		if ((startBounds != null) && (targetBounds != null)) {
			bounds.setLocation(calculateProgress(startBounds.getLocation(), targetBounds.getLocation(), progress));
			bounds.setSize(calculateProgress(startBounds.getSize(), targetBounds.getSize(), progress));
		}

		return bounds;
	}

	/**
	 * @param startPoint
	 * @param targetPoint
	 * @param progress
	 * @return
	 */
	public Point calculateProgress(Point startPoint, Point targetPoint, double progress) {
		Point point = new Point();

		if ((startPoint != null) && (targetPoint != null)) {
			point.x = calculateProgress(startPoint.x, targetPoint.x, progress);
			point.y = calculateProgress(startPoint.y, targetPoint.y, progress);
		}

		return point;
	}

	/**
	 * @param startValue
	 * @param endValue
	 * @param fraction
	 * @return
	 */
	public int calculateProgress(int startValue, int endValue, double fraction) {
		int value = 0;
		int distance = endValue - startValue;
		value = (int) Math.round(distance * fraction);
		value += startValue;

		return value;
	}

	/**
	 * @param startSize
	 * @param targetSize
	 * @param progress
	 * @return
	 */
	public Dimension calculateProgress(Dimension startSize, Dimension targetSize, double progress) {
		Dimension size = new Dimension();

		if ((startSize != null) && (targetSize != null)) {
			size.width = calculateProgress(startSize.width, targetSize.width, progress);
			size.height = calculateProgress(startSize.height, targetSize.height, progress);
		}

		return size;
	}
}