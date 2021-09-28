package dohuyhoang.animation.slideshow;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SlideShowPane extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel pic;
	private Timer tm;
	private int ImageNumber = 0;
	private Dimension dimension;
	private ArrayList<Image> listImg;
	private int thoiGian;
	private ArrayList<String> listToolTip;

	/**
	 * @return
	 */
	public JLabel getPic() {
		return this.pic;
	}

	/**
	 * @param pic
	 */
	public void setPic(JLabel pic) {
		this.pic = pic;
	}

	/**
	 * @return
	 */
	public ArrayList<Image> getListImg() {
		return this.listImg;
	}

	/**
	 * @param listImg
	 */
	public void setListImg(ArrayList<Image> listImg) {
		this.listImg = listImg;
	}

	/**
	 * @return
	 */
	public Dimension getDimension() {
		return this.dimension;
	}

	/**
	 * @param dimension
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return
	 */
	public int getThoiGian() {
		return this.thoiGian;
	}

	/**
	 * @param thoiGian
	 */
	public void setThoiGian(int thoiGian) {
		this.thoiGian = thoiGian;
	}

	/**
	 * @return
	 */
	public int getImageNumber() {
		return this.ImageNumber;
	}

	/**
	 * @param imageNumber
	 */
	public void setImageNumber(int imageNumber) {
		this.ImageNumber = imageNumber;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getListToolTip() {
		return this.listToolTip;
	}

	/**
	 * @param listToolTip
	 */
	public void setListToolTip(ArrayList<String> listToolTip) {
		this.listToolTip = listToolTip;
	}

	/**
	 * @param listImg
	 * @param dimension
	 * @param thoiGian
	 */
	public SlideShowPane(ArrayList<Image> listImg, Dimension dimension, int thoiGian) {
		setListImg(listImg);
		setDimension(dimension);
		setThoiGian(thoiGian);
		buidSlideShowPane();
	}

	/**
	 * @param listImg
	 * @param dimension
	 * @param thoiGian
	 * @param listToolTip
	 */
	public SlideShowPane(ArrayList<Image> listImg, Dimension dimension, int thoiGian, ArrayList<String> listToolTip) {
		setListImg(listImg);
		setDimension(dimension);
		setThoiGian(thoiGian);
		setListToolTip(listToolTip);
		buidSlideShowPane();
	}

	public SlideShowPane() {
	}

	/**
	 * 
	 */
	private void buidSlideShowPane() {
		this.pic = new JLabel();
		this.pic.setBounds(0, 0, this.dimension.width, this.dimension.height);
		setImageSize(0);

		this.tm = new Timer(this.thoiGian, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SlideShowPane.this.ImageNumber += 1;
				if (SlideShowPane.this.ImageNumber >= SlideShowPane.this.listImg.size())
					SlideShowPane.this.ImageNumber = 0;
				SlideShowPane.this.setImageSize(SlideShowPane.this.ImageNumber);
			}
		});
		add(this.pic);
		setLayout(null);
	}

	/**
	 * 
	 */
	public void start() {
		this.tm.start();
	}

	/**
	 * 
	 */
	public void stop() {
		this.tm.stop();
	}

	/**
	 * @param index
	 */
	public void setImageSize(int index) {
		Image img = (Image) this.listImg.get(index);
		Image newImage = img.getScaledInstance(this.pic.getWidth(), this.pic.getHeight(), 4);
		ImageIcon newImgIcon = new ImageIcon(newImage);
		this.pic.setIcon(newImgIcon);
		if (this.listToolTip != null)
			this.pic.setToolTipText((String) this.listToolTip.get(index));
	}
}