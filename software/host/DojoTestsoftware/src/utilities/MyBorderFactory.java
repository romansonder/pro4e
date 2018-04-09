package utilities;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class MyBorderFactory {

	public static Border createMyBorder(String title) {
		Border loweredEtched = BorderFactory.createDashedBorder(Color.BLACK);
		Border titled = BorderFactory.createTitledBorder(loweredEtched, title);
		return titled;
	}
}