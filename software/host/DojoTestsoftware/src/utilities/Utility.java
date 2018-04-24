package utilities;

import java.awt.Container;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

public class Utility {

	private static Container container = new Container();

	public static Image loadImage(String strBild) {
		MediaTracker tracker = new MediaTracker(container);
		Image img = (new ImageIcon(strBild)).getImage();
		tracker.addImage(img, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException ex) {
			System.out.println("Can not load image: " + strBild);
		}
		return img;
	}

	public static Image loadResourceImage(String strBild) {
		MediaTracker tracker = new MediaTracker(container);
		Image img = new ImageIcon(Utility.class.getResource("/images" + "/" + strBild)).getImage();
		tracker.addImage(img, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException ex) {
			System.out.println("Can not load image: " + strBild);
		}
		return img;
	}

	public static ImageIcon loadResourceIcon(String strBild) {
		MediaTracker tracker = new MediaTracker(container);
		ImageIcon icon = new ImageIcon(Utility.class.getResource("/icons" + "/" + strBild));
		tracker.addImage(icon.getImage(), 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException ex) {
			System.out.println("Can not load image: " + strBild);
		}
		return icon;
	}
}