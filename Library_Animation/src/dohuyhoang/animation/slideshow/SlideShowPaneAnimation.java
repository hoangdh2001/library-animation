package dohuyhoang.animation.slideshow;

import dohuyhoang.animation.AnimationLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SlideShowPaneAnimation extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<JLabel> dsImg;
	private String[] listPath;
	private Timer tm;
	private int startTime;
	private int animationTime;
	int x = 0;
	private Rectangle rectangle;
	private String[] listTitle;
	private Color colorTitle = Color.BLACK;
	private Font fontTitle = new Font("Segoe ui", 1, 16);
	private int horizontalTextPosition = 0;
	private int verticalTextPosition = 0;

	/**
	 * @return
	 */
	public List<JLabel> getDsImg() {
		return this.dsImg;
	}

	/**
	 * @param dsImg
	 */
	public void setDsImg(List<JLabel> dsImg) {
		this.dsImg = dsImg;
	}

	/**
	 * @return
	 */
	public int getVerticalTextPosition() {
		return this.verticalTextPosition;
	}

	/**
	 * @param verticalTextPosition
	 */
	public void setVerticalTextPosition(int verticalTextPosition) {
		for (JLabel jLabel : this.dsImg) {
			jLabel.setVerticalTextPosition(verticalTextPosition);
		}
		this.verticalTextPosition = verticalTextPosition;
	}

	/**
	 * @return
	 */
	public int getHorizontalTextPosition() {
		return this.horizontalTextPosition;
	}

	/**
	 * @param horizontalTextPosition
	 */
	public void setHorizontalTextPosition(int horizontalTextPosition) {
		for (JLabel jLabel : this.dsImg) {
			jLabel.setHorizontalTextPosition(horizontalTextPosition);
		}
		this.horizontalTextPosition = horizontalTextPosition;
	}

	/**
	 * @return
	 */
	public Font getFontTitle() {
		return this.fontTitle;
	}

	/**
	 * @param fontTitle
	 */
	public void setFontTitle(Font fontTitle) {
		for (JLabel jLabel : this.dsImg) {
			jLabel.setFont(fontTitle);
		}
		this.fontTitle = fontTitle;
	}

	/**
	 * @return
	 */
	public Color getColorTitle() {
		return this.colorTitle;
	}

	/**
	 * @param colorTitle
	 */
	public void setColorTitle(Color colorTitle) {
		for (JLabel jLabel : this.dsImg) {
			jLabel.setForeground(colorTitle);
		}
		this.colorTitle = colorTitle;
	}

	/**
	 * @return
	 */
	public Rectangle getRectangle() {
		return this.rectangle;
	}

	/**
	 * @param rectangle
	 */
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	/**
	 * @return
	 */
	public int getStartTime() {
		return this.startTime;
	}

	/**
	 * @param startTime
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return
	 */
	public int getAnimationTime() {
		return this.animationTime;
	}

	/**
	 * @param animationTime
	 */
	public void setAnimationTime(int animationTime) {
		this.animationTime = animationTime;
	}

	/**
	 * @param listPath
	 * @param startTime
	 * @param animationTime
	 * @param size
	 * @param listTitle
	 */
	public SlideShowPaneAnimation(String[] listPath, int startTime, int animationTime, Rectangle size,
			String[] listTitle) {
		setLayout(null);
		this.listPath = listPath;
		setStartTime(startTime);
		setAnimationTime(animationTime);
		setRectangle(size);
		this.listTitle = listTitle;
		this.dsImg = new ArrayList<JLabel>();

		setLabel();
		addPanel();
		((JLabel) this.dsImg.get(0)).setBounds(this.rectangle.x, this.rectangle.y, this.rectangle.width,
				this.rectangle.height);
	}

	/**
	 * 
	 */
	public void start() {
		this.tm = new Timer(this.startTime, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimationLabel animation2 = new AnimationLabel(
						(JLabel) SlideShowPaneAnimation.this.dsImg.get(SlideShowPaneAnimation.this.x),
						new Rectangle(SlideShowPaneAnimation.this.rectangle.x, SlideShowPaneAnimation.this.rectangle.y,
								SlideShowPaneAnimation.this.rectangle.width,
								SlideShowPaneAnimation.this.rectangle.height),
						new Rectangle(
								-SlideShowPaneAnimation.this.rectangle.width + SlideShowPaneAnimation.this.rectangle.x,
								SlideShowPaneAnimation.this.rectangle.y, SlideShowPaneAnimation.this.rectangle.width,
								SlideShowPaneAnimation.this.rectangle.height),
						SlideShowPaneAnimation.this.animationTime);
				animation2.start();
				SlideShowPaneAnimation.this.x += 1;
				if (SlideShowPaneAnimation.this.x >= SlideShowPaneAnimation.this.listPath.length)
					SlideShowPaneAnimation.this.x = 0;
				AnimationLabel animation = new AnimationLabel(
						(JLabel) SlideShowPaneAnimation.this.dsImg.get(SlideShowPaneAnimation.this.x),
						new Rectangle(
								SlideShowPaneAnimation.this.rectangle.width - SlideShowPaneAnimation.this.rectangle.y,
								0, SlideShowPaneAnimation.this.rectangle.width,
								SlideShowPaneAnimation.this.rectangle.height),
						new Rectangle(SlideShowPaneAnimation.this.rectangle.x, SlideShowPaneAnimation.this.rectangle.y,
								SlideShowPaneAnimation.this.rectangle.width,
								SlideShowPaneAnimation.this.rectangle.height),
						SlideShowPaneAnimation.this.animationTime);
				animation.start();
			}
		});
		this.tm.start();
	}

	/**
	 * 
	 */
	public void stop() {
		this.tm.stop();
	}

	/**
	 * 
	 */
	private void setLabel() {
		for (int i = 0; i < this.listPath.length; i++) {
			JLabel label = setImage(i);
			this.dsImg.add(label);
		}
	}

	/**
	 * 
	 */
	private void addPanel() {
		for (JLabel label : this.dsImg)
			add(label);
	}

	/**
	 * @param index
	 * @return
	 */
	public JLabel setImage(int index) {
		JLabel label = new JLabel(this.listTitle[index]);
		label.setHorizontalTextPosition(this.horizontalTextPosition);
		label.setVerticalTextPosition(this.verticalTextPosition);
		label.setForeground(this.colorTitle);
		label.setFont(this.fontTitle);
		label.setBounds(0, 0, this.rectangle.width, this.rectangle.height);
		ImageIcon icon = new ImageIcon(getClass().getResource(this.listPath[index]));
		Image img1 = icon.getImage();
		Image newImg1 = img1.getScaledInstance(label.getWidth(), label.getHeight(), 4);
		ImageIcon newImc = new ImageIcon(newImg1);
		label.setIcon(newImc);
		return label;
	}
}