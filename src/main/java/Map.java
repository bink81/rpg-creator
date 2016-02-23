import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Map implements Serializable {
	private static final long serialVersionUID = 1L;
	// map parameters
	int width, height, // boundaries
			itemNumber, // amount of items on the map
			respawn, // amount of turns to reset all map items and creatures
			creatureNumber; // amount of creatures on the map
	String name, // map name
			description, // description
			filename; // source file with map design

	// array of field symbols
	byte[][] map;

	// numbers indicate which place in fields vector this type of field takes
	byte[][] icons;

	// array describing which map locations has been seen and are visible
	boolean[][] visible;

	// vectors containing map elements
	Vector<Field> fields;
	Vector<Creature> creatures;
	Vector<Item> items;

	// random generator
	Random generator;

	// reference to game main engine
	// Logic logic;

	public Map(String name, String filename, String description, int itemNumber, int creatureNumber, int respawn) {
		// initialise objects
		fields = new Vector<Field>();
		creatures = new Vector<Creature>();
		items = new Vector<Item>();
		generator = new Random();

		map = null;
		this.name = name;
		this.description = description;
		this.itemNumber = itemNumber;
		this.creatureNumber = creatureNumber;
		this.respawn = respawn;

		// read map data from a source file
		this.filename = filename;
		file2array();

	}

	// used to add player to a map
	void addPlayer(Creature player) {
		creatures.add(player);
	}

	// add all creatures
	void addCreatures(Vector<Creature> sourceCreatures, Vector<Item> sourceItems) {
		System.out.println("\nAdding creatures for map : " + name);
		// add one of each creature that are supposed to appear on the map
		for (int t = 1; t < sourceCreatures.size() - 1; t++) {
			Creature tempCreature = sourceCreatures.elementAt(t + 1);
			if (tempCreature.map.equals(name))
				addCreature(tempCreature, sourceItems);
		}
		fillCreatures(sourceCreatures, sourceItems);
	}

	void fillCreatures(Vector<Creature> sourceCreatures, Vector<Item> sourceItems) {
		int t;
		// and add more until all possible creatures are added
		while (creatures.size() < creatureNumber) {
			// use random picking
			t = generator.nextInt(sourceCreatures.size() - 1);
			Creature tempCreature = sourceCreatures.elementAt(t + 1);
			// check if creature should appear on the map
			if (tempCreature.map.equals(name) || tempCreature.map.equals("")) {
				// if so - add it
				addCreature(tempCreature, sourceItems);
			}
		}
	}

	// initialise and add single creature
	void addCreature(Creature patternCreature, Vector<Item> sourceItems) {
		int x = 0, y = 0;
		// if it has a specified location - use it
		if ((patternCreature.locationx != 0) && (patternCreature.locationy != 0)) {
			x = patternCreature.locationx;
			y = patternCreature.locationy;
		} else {
			// else - generate new location
			x = generator.nextInt(width);
			y = generator.nextInt(height);
		}
		// place creature if place is free
		if (!isFieldCrucial(x, y))
			return;
		Creature creature = new Creature(patternCreature);
		System.out.println("Added monster " + creature.race + " at :" + x + "," + y);
		creature.locationx = x;
		creature.locationy = y;
		int i;

		// simple sorting mechanism arranges creatures from fastest to slowest
		// look for a faster creature
		for (i = creatures.size() - 1; i > 0; i--) {
			Creature tempCreature = creatures.elementAt(i);
			if (tempCreature.speed > creature.speed) {
				break;
			}
		}
		// and add just behind it
		creatures.insertElementAt(creature, i + 1);

		// add items to a creature
		System.out.println("Adding items for creature : " + creature.race);
		if (creature.namedItems != null)
			for (i = 0; i < creature.namedItems.size(); i++) {
				Item item = getItem(sourceItems, creature.namedItems.elementAt(i), creature.level, creature.experience);
				if (item != null)
					creature.items.add(new Item(item));
			}
		if (creature.randomItems != null)
			for (i = 0; i < creature.randomItems.size(); i++) {
				Item item = getItem(sourceItems, "random", creature.randomItems.elementAt(i), creature.level,
						creature.experience);
				if (item != null)
					creature.items.add(new Item(item));
			}
	}

	// add map items
	void addItems(Vector<Item> sourceItems) {
		System.out.println("\nAdding items for map : " + name);
		// until enough items are added
		while (items.size() < itemNumber) {
			addItem(pickItem(sourceItems));
		}
	}

	// pick random item from all items
	Item pickItem(Vector<Item> sourceItems) {
		int t = generator.nextInt(sourceItems.size());
		Item tempItem = sourceItems.elementAt(t);
		// item should not belong to different map
		if (tempItem.map.equals(name) || tempItem.map.equals(""))
			return tempItem;
		else
			return null;
	}

	// initialise and add a single item
	void addItem(Item patternItem) {
		if (patternItem == null)
			return;
		int x, y;
		// if item has specified location, use it
		if ((patternItem.locationx != 0) && (patternItem.locationy != 0)) {
			x = patternItem.locationx;
			y = patternItem.locationy;
		} else {
			// alse - generate new
			x = generator.nextInt(width);
			y = generator.nextInt(height);
		}
		// add only if field is free
		if (!isFieldCrossable(x, y))
			return;

		Item item = new Item(patternItem);
		item.locationx = x;
		item.locationy = y;
		items.add(item);
	}

	// get item from pattern items by its name
	Item getItem(Vector<Item> sourceItems, String name, int level, int experience) {
		Item tempItem;
		// got through items
		for (int i = 0; i < sourceItems.size(); i++) {
			tempItem = sourceItems.elementAt(i);
			// if name matches
			if (tempItem.name.equals(name)) {
				System.out.println("--found : " + tempItem.name);
				// return found pattern
				return tempItem;
			}
		}
		System.out.println("couldn't find: '" + name + "'");
		return null;
	}

	// get random item (only type can be specified)
	Item getItem(Vector<Item> sourceItems, String name, String itemType, int level, int experience) {
		Item tempItem;
		int counter = 0;
		// count number of matching types
		for (int i = 0; i < sourceItems.size(); i++) {
			tempItem = sourceItems.elementAt(i);
			if (tempItem.type.equals(itemType) || itemType.equals("all"))
				counter++;
		}
		// and pick one of them
		counter = generator.nextInt(counter);
		tempItem = null;
		for (int i = 0; i < sourceItems.size() && counter > 0; i++) {
			tempItem = sourceItems.elementAt(i);
			if (tempItem.type.equals(itemType) || itemType.equals("all"))
				counter--;
		}
		if (tempItem != null)
			System.out.println("--found : " + tempItem.name);
		return tempItem;
	}

	// check if field is crucial (important not to be blocked permanently)
	boolean isFieldCrucial(int x, int y) {
		// if in boundaries
		if (x <= 0 || y <= 0 || x > width - 1 || y > height - 1)
			return false;
		Field tempField = fields.elementAt(icons[x][y]);
		if ((tempField.mobility > 0)) {
			// if not forbidden field name
			if (tempField.name.equals("teleport"))
				return false;
			if (tempField.name.equals("shop"))
				return false;
			// if not occupied by any creature
			for (int c = 0; c < creatures.size(); c++) {
				Creature tempCreature = creatures.elementAt(c);
				if ((tempCreature.locationx == x) && (tempCreature.locationy == y))
					return false;
			}
			return true;
		}
		return false;
	}

	// check if accessible (walking into)
	boolean isFieldCrossable(int x, int y) {
		// if in boundaries
		if (x <= 0 || y <= 0 || x > width - 1 || y > height - 1)
			return false;
		Field tempField = fields.elementAt(icons[x][y]);
		if ((tempField.mobility > 0)) {
			// if not occupied by any creature
			for (int c = 0; c < creatures.size(); c++) {
				Creature tempCreature = creatures.elementAt(c);
				if ((tempCreature.locationx == x) && (tempCreature.locationy == y))
					return false;
			}
			return true;
		}
		return false;
	}

	// add a new field
	public void addField(Field field, Vector<Item> sourceItems) {
		fields.add(field);
		// search whole map array for field symbol
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// if symbol matches
				if ((char) map[x][y] == field.symbol) {
					// set a field number in proper icons table field
					icons[x][y] = (byte) (fields.size() - 1);
					// add items to that place
					for (int i = 0; i < field.itemNr; i++) {
						// pick it randomly
						Item item = pickItem(sourceItems);
						if (item != null) {
							System.out.println("Adding :" + item.name + " at:" + x + "," + y);
							item.locationx = x;
							item.locationy = y;
							addItem(item);
						}
					}
				}
			}
		}

	}

	// get an image of a location
	ImageIcon getIcon(int x, int y) {
		Field tempfield = fields.elementAt(icons[x][y]);
		return tempfield.image;
	}

	// read a file into array
	public void file2array() {
		try {
			// use Vector to store lines
			Vector<String> mapLines = new Vector<String>();
			String line; // represent a line

			// file input stream initialisation
			FileReader myFile = null;
			BufferedReader buff = null;
			myFile = new FileReader(filename);
			buff = new BufferedReader(myFile);

			// set proper marker
			boolean eof = false;
			// read file until it ends
			while (!eof) {
				// read line by line
				line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					// set map width by the longest line
					if (line.length() > width)
						width = line.length();
					// every non empty line increases map height
					height++;
					// add new line to a vector
					mapLines.add(line);
				}
			}
			// initialise tables
			map = new byte[width][height];
			icons = new byte[width][height];
			// fill the map table with symbols
			for (int y = 0; y < height; y++) {
				// by reading line after line
				line = mapLines.elementAt(y);
				for (int x = 0; x < width; x++) {
					// and char after char
					while (line.length() < width)
						line += "#";
					map[x][y] = (byte) line.charAt(x);
				}
			}

			// set all places unknown
			visible = new boolean[width][height];
			for (int y = 0; y < height; y++)
				for (int x = 0; x < width; x++)
					visible[x][y] = false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// print some info about current location
	String getInfo(int x, int y) {
		String result = "";
		// if occupied by any creature
		for (int tempInt = 0; tempInt < creatures.size(); tempInt++) {
			Creature tempCreature = creatures.elementAt(tempInt);
			if ((tempCreature.locationx == x) && (tempCreature.locationy == y))
				result += " Creature: " + tempCreature.getInfo();
		}
		// if any item lying
		for (int tempInt = 0; tempInt < items.size(); tempInt++) {
			Item tempItem = items.elementAt(tempInt);
			if ((tempItem.locationx == x) && (tempItem.locationy == y))
				result += tempItem.getInfo() + ",";
		}
		return result;
	}

	// return field type for location
	Field getField(int x, int y) {
		return fields.elementAt(icons[x][y]);
	}
}
