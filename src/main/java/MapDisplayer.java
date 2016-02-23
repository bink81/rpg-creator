import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This code was generated using CloudGarden's Jigloo SWT/Swing GUI Builder,
 * which is free for non-commercial use. If Jigloo is being used commercially
 * (ie, by a corporation, company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo. Please visit
 * www.cloudgarden.com for details. Use of Jigloo implies acceptance of these
 * licensing terms. ************************************* A COMMERCIAL LICENSE
 * HAS NOT BEEN PURCHASED for this machine, so Jigloo or this code cannot be
 * used legally for any corporate or commercial purpose.
 * *************************************
 */

// class representing map canvas
public class MapDisplayer extends JPanel {
	private static final long serialVersionUID = 1L;
	int width, height, locationx, locationy;
	int fieldSize;
	byte[][] plane;
	Logic logic;
	ImageIcon blood;

	// initialise
	public MapDisplayer(Logic logic, int fieldSize) throws IllegalArgumentException {
		this.fieldSize = fieldSize;
		this.logic = logic;
		// set background color to black
		try {
			this.setBackground(new java.awt.Color(0, 0, 0));
			this.setDoubleBuffered(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		changeMap();

		// get blood image
		blood = createImageIcon("images/blood.gif");

	}

	// change map
	void changeMap() {
		if (logic.getMap() == null)
			throw new IllegalArgumentException("Error loading map");
		width = logic.getMap().width;
		height = logic.getMap().height;
		plane = logic.getMap().map;
	}

	// for sizing the component
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width * fieldSize, height * fieldSize);
	}

	// overridden paint method
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		ImageIcon tempIcon;

		// go through all lines and columns
		for (int y = 0; y < logic.getMap().height; y++)
			for (int x = 0; x < logic.getMap().width; x++) {
				tempIcon = logic.getMap().getIcon(x, y);
				// is field exists and is visible to player -> display
				if (tempIcon != null)
					if (logic.getMap().visible[x][y])
						tempIcon.paintIcon(this, g2d, fieldSize * x, fieldSize * y);
			}

		// display items
		for (int i = 0; i < logic.getMap().items.size(); i++) {
			Item tempItem = (Item) logic.getMap().items.elementAt(i);
			tempIcon = tempItem.getImage();
			if (tempIcon != null)
				if (logic.getMap().visible[tempItem.locationx][tempItem.locationy])
					tempIcon.paintIcon(this, g2d, fieldSize * tempItem.locationx, fieldSize * tempItem.locationy);
		}

		// display creatures
		for (int i = 0; i < logic.getMap().creatures.size(); i++) {
			Creature tempCreature = (Creature) logic.getMap().creatures.elementAt(i);
			tempIcon = tempCreature.getImage();
			if (tempIcon != null)
				// display only if Player can hear a creature
				if (logic.distance(logic.getPlayer(), tempCreature) <= logic.getPlayer().perception)
					tempIcon.paintIcon(this, g2d, fieldSize * tempCreature.locationx,
							fieldSize * tempCreature.locationy);

			// if creature was injured, display blood on it
			if (tempCreature.wasInjured())
				if (blood != null)
					blood.paintIcon(this, g2d, fieldSize * tempCreature.locationx + fieldSize / 3,
							fieldSize * tempCreature.locationy + fieldSize / 3);
		}
	}

	// create image for file
	protected ImageIcon createImageIcon(String path) {
		File temp = new File(path);
		if (temp.exists())
			return new ImageIcon(path);
		else
			return null;
	}

}