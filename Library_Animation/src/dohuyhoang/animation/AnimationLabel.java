package dohuyhoang.animation;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class AnimationLabel {
	private int runTime = 200;
	private JLabel label;
	private Rectangle from;
	private Rectangle to;
	private long startTime;

	/**
	 * @return
	 */
	public JLabel getLabel() {
		return this.label;
	}

	/**
	 * @param label
	 */
	public void setLabel(JLabel label) {
		this.label = label;
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
	 * @param label
	 * @param from
	 * @param to
	 * @param runTime
	 */
	public AnimationLabel(JLabel label, Rectangle from, Rectangle to, int runTime) {
		setLabel(label);
		setFrom(from);
		setTo(to);
		setRunTime(runTime);
	}

	/**
	 * @param label
	 * @param from
	 * @param to
	 */
	public AnimationLabel(JLabel label, Rectangle from, Rectangle to) {
		setLabel(label);
		setFrom(from);
		setTo(to);
	}

	/**
	 * 
	 */
	public void start() {
		Timer timer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long duration = System.currentTimeMillis() - AnimationLabel.this.startTime;
				double progress = duration / AnimationLabel.this.runTime;
				if (progress > 1.0D) {
					progress = 1.0D;
					((Timer) e.getSource()).stop();
				}
				Rectangle target = AnimationLabel.this.calculateProgress(AnimationLabel.this.from,
						AnimationLabel.this.to, progress);
				AnimationLabel.this.label.setBounds(target);
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
	 * 
	 * @param startValue
	 * @param endValue
	 * @param fraction
	 * @return an integer value
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