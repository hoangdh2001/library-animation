package dohuyhoang.roundedpane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPane extends JPanel {
	private static final long serialVersionUID = 1L;
	private Color backgroundColor;
	private int cornerRadius = 15;
	private String direction = null;

	/**
	 * @param layoutManager
	 * @param radius
	 */
	public RoundedPane(LayoutManager layoutManager, int radius) {
		super(layoutManager);
		this.cornerRadius = radius;
	}

	/**
	 * @param layoutManager
	 * @param radius
	 * @param bgColor
	 */
	public RoundedPane(LayoutManager layoutManager, int radius, Color bgColor) {
		super(layoutManager);
		this.cornerRadius = radius;
		this.backgroundColor = bgColor;
	}

	/**
	 * @param radius
	 */
	public RoundedPane(int radius) {
		this.cornerRadius = radius;
	}

	/**
	 * @param radius
	 * @param bgColor
	 */
	public RoundedPane(int radius, Color bgColor) {
		this.cornerRadius = radius;
		this.backgroundColor = bgColor;
	}

	/**
	 * @param radius
	 * @param direction
	 */
	public RoundedPane(int radius, String direction) {
		this.cornerRadius = radius;
		this.direction = direction;
	}

	/**
	 *
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension arcs = new Dimension(this.cornerRadius, this.cornerRadius);
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (this.backgroundColor != null) {
			graphics.setColor(this.backgroundColor);
		} else {
			graphics.setColor(getBackground());
		}
		graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);
		graphics.setColor(getForeground());
		graphics.setColor(getBackground());

		if (this.direction != null)
			if (this.direction.equals("right"))
				graphics.fillRect(width / 2, 0, width / 2, height);
			else if (this.direction.equals("bottom"))
				graphics.fillRect(0, height / 2, width, height / 2);
			else if (this.direction.equals("left"))
				graphics.fillRect(0, 0, width / 2, height);
			else
				graphics.fillRect(0, 0, width, height / 2);
	}
}