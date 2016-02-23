import java.io.File;
import java.io.Serializable;

import javax.swing.ImageIcon;

// represents every different field
public class Field implements Serializable {
	private static final long serialVersionUID = 1L;
	// name and image filename (plus path)
	String name, filename;

	// some parameters
	int mobility, // if it is possible to travel here
			visibility, // if it is possible to see through
			itemNr; // how many items appear here
	char symbol; // symbol representation
	ImageIcon image; // image representation

	// variables applying only for teleports
	String targetMap; // to what map it leads
	int targetX, targetY; // to what location

	// constructor for a non-teleport
	public Field(String name, String filename, char symbol, int mobility, int visibility, int itemNr) {
		this.name = name;
		this.filename = filename;
		this.symbol = symbol;
		image = createImageIcon(filename);
		this.mobility = mobility;
		this.visibility = visibility;
		this.itemNr = itemNr;
	}

	// constructor for a teleport
	public Field(String name, String filename, char symbol, int mobility, int visibility, String targetMap, int targetX,
			int targetY, int itemNr) {
		this.name = name;
		this.filename = filename;
		this.symbol = symbol;
		image = createImageIcon(filename);
		this.mobility = mobility;
		this.visibility = visibility;

		this.targetMap = targetMap;
		this.targetX = targetX;
		this.targetY = targetY;
		this.itemNr = itemNr;

	}

	// get image from file
	protected static ImageIcon createImageIcon(String path) {
		File temp = new File(path);
		if (temp.exists())
			return new ImageIcon(path);
		else
			return null;
	}
}
