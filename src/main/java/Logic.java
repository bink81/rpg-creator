import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Vector;

// main class containing all game logic and also handling keyboard events
public class Logic implements KeyListener {
	// vectors storing all game data:
	Vector<Map> maps; // maps
	Vector<Creature> creatures; // patterns of creatures
	Vector<Item> items; // patterns of items
	Vector<Event> events; // possible events
	Vector<Skill> skills; // possible skills (under construction)

	Display display; // class for map displaying
	Stats stats; // class for personal statistics display
	int turn = 0;
	Random generator;
	Map currentMap; // current map
	Dice dice; // Dice object

	// constructor
	public Logic(Dice dice) {
		this.dice = dice;

		// initialising vectors
		maps = new Vector<Map>();
		creatures = new Vector<Creature>();
		items = new Vector<Item>();
		events = new Vector<Event>();
		generator = new Random();
		skills = new Vector<Skill>();
	}

	// set a new map
	void setMap(String name, int x, int y) {
		// get a map by its name (go through all maps to find it)
		for (int i = 0; i < maps.size(); i++) {
			Map tempMap = maps.elementAt(i);
			if (tempMap.name.equals(name)) {
				// if map exists
				System.out.println("Map found: " + tempMap.name);
				if (display != null)
					display.showText("Map entered: " + tempMap.name);
				currentMap = tempMap;
				// set player on it
				getPlayer().map = name;
				getPlayer().locationx = x;
				getPlayer().locationy = y;
				if (display != null)
					display.changeMap();
				// check neighbourhood
				lookAround();
				return;
			}
		}
		System.out.println("Map not found: " + name);
	}

	// sets direct map display object (just for faster and simpler displaying
	// operations)
	void addMapDisplay(Display display) {
		this.display = display;
	}

	// the same with character
	void addCharDisplay(Stats stats) {
		this.stats = stats;
	}

	// get current map
	public Map getMap() {
		return currentMap;
	}

	// add a new map
	void addMap(Map map) {
		// add player
		map.addPlayer(creatures.elementAt(0));
		// and create all map creatures
		map.addCreatures(creatures, items);
		// create all map items
		map.addItems(items);
		// finally, add map to a vector
		maps.add(map);
	}

	// add skill to vector
	void addSkill(Skill skill) {
		skills.add(skill);
	}

	// add player to vector
	void addPlayer(Creature creature) {
		Item tempItem = null;
		// initialise new creature as player
		Creature player = new Creature(creature);
		// add all specified items
		System.out.println("Adding items for creature : " + creature.race);
		if (creature.namedItems != null)
			for (int i = 0; i < creature.namedItems.size(); i++) {
				String name = creature.namedItems.elementAt(i);
				for (int t = 0; t < items.size(); t++) {
					tempItem = items.elementAt(t);
					// if name matches
					if (tempItem.name.equals(name)) {
						System.out.println("--found : " + tempItem.name);
						// return found pattern
						break;
					} else
						tempItem = null;
				}
				if (tempItem != null)
					player.items.add(new Item(tempItem));
			}
		// add all random items
		if (creature.randomItems != null)
			for (int i = 0; i < creature.randomItems.size(); i++) {
				String itemType = creature.randomItems.elementAt(i);
				int counter = 0;
				// count number of matching types
				for (int t = 0; t < items.size(); t++) {
					tempItem = items.elementAt(t);
					if (tempItem.type.equals(itemType) || itemType.equals("all"))
						counter++;
				}
				// and pick one of them
				counter = generator.nextInt(counter);
				tempItem = null;
				for (int t = 0; t < items.size() && counter > 0; t++) {
					tempItem = items.elementAt(t);
					if (tempItem.type.equals(itemType) || itemType.equals("all"))
						counter--;
				}
				if (tempItem != null) {
					System.out.println("--found : " + tempItem.name);
					player.items.add(new Item(tempItem));
				}

			}
		// add player to a vector
		creatures.add(player);
	}

	// all pattern creatures to vectors
	void addCreature(Creature creature) {
		// if vector is empty -> player is added
		if (creatures.size() == 0)
			addPlayer(creature);
		else
			creatures.add(creature);
	}

	// add events to vector
	void addEvent(Event event) {
		events.add(event);
	}

	// add pattern items
	void addItem(Item item) {
		items.add(item);
	}

	// attack neighboring creature
	void attack(Creature attacker, Creature defender) {
		// attacket gets angry
		defender.attitude = "hostile";

		// check ability to hit
		int toHit = attacker.getToHit();

		// check ability to avoid hit
		int dodge = defender.getDodge();

		// if hit succesful
		if (toHit > dodge) {
			// count damage coused
			int damage = attacker.getDamage();

			// count armor
			int armour = defender.getArmor();

			// if damage coused
			if (damage > armour) {
				display.showMessage(" " + attacker.race + " / " + attacker.profession + "(" + toHit + ")" + " hits "
						+ defender.race + " / " + defender.profession + "(" + dodge + ")" + " for: " + (damage - armour)
						+ "(" + damage + "-" + armour + "), ");
				// finalize attack
				defender.doDamage(damage - armour);
			} else
				// damage was fully absorbed
				display.showMessage(" " + attacker.race + " " + attacker.profession + "(" + toHit + ")" + " hits "
						+ defender.race + " / " + defender.profession + "(" + dodge + ")" + " for 0 " + "(" + damage
						+ "-" + armour + "), ");
			// attacker missed
		} else
			display.showMessage(" " + attacker.race + " / " + attacker.profession + "(" + toHit + ")" + " misses "
					+ defender.race + " / " + defender.profession + "(" + dodge + ")" + ", ");

		// if target killed
		if (defender.health < 1) {
			display.showMessage("  -- it dies!  ");
			// get experience
			attacker.addExperience(defender.level * defender.experience);
			// if target had money - it drops to the ground
			if (defender.money > 0) {
				Item moneyItem = new Item(defender.money);
				moneyItem.locationx = defender.locationx;
				moneyItem.locationy = defender.locationy;
				getMap().items.add(moneyItem);
			}
			// all possesed item drop also
			for (int i = 0; i < defender.items.size(); i++) {
				Item tempItem = defender.items.elementAt(i);
				tempItem.locationx = defender.locationx;
				tempItem.locationy = defender.locationy;
				getMap().items.add(tempItem);
			}
			// creature is removed
			getMap().creatures.removeElement(defender);
		}
		// update player statistics
		display.updateStats();
	}

	// hostile creature will follow Player when they spot him, instead of moving
	// randomly
	void follow(Creature creature, Creature target) {
		int action = 5;

		// got into players direction
		if (creature.locationx < target.locationx)
			action++;
		else if (creature.locationx > target.locationx)
			action--;
		if (creature.locationy < target.locationy)
			action -= 3;
		else if (creature.locationy > target.locationy)
			action += 3;

		// if player not in neighbourhood, go into his direction
		if (move(creature, action))
			return;
		else {
			// try to avoid obstacle
			action = 5;
			if (creature.locationx < target.locationx)
				action++;
			else if (creature.locationx > target.locationx)
				action--;
			if (move(creature, action))
				return;
			else {
				action = 5;
				if (creature.locationy < target.locationy)
					action -= 3;
				else if (creature.locationy > target.locationy)
					action += 3;
				if (move(creature, action))
					return;
			}
		}
	}

	// move action move selected creature (if a move in selected direction is
	// possible)
	// return true if creature moved, otherwise - false
	boolean move(Creature creature, int action) {
		// check if proper action chosen
		if (!((action > 0 && action < 5) || (action > 5 && action < 10)))
			return false;
		creature.direction = (byte) action;
		// get creature's position
		int y = creature.locationy;
		int x = creature.locationx;
		// check desired position
		if (action == 1 || action == 2 || action == 3)
			y++;
		if (action == 7 || action == 8 || action == 9)
			y--;
		if (action == 1 || action == 4 || action == 7)
			x--;
		if (action == 3 || action == 6 || action == 9)
			x++;

		// check if desired field is free and if creature can move
		if ((getMap().isFieldCrossable(x, y)) && creature.canMove()) {
			if (creature == creatures.elementAt(0))
				display.showMessage("You walk. ");
			creature.locationx = x;
			creature.locationy = y;
			return true;
		} else {
			if (creature == getPlayer())
				display.showMessage("You cannot go there. ");
			Creature tempCreature = isFieldOccupied(x, y);

			// if place is occupied by another creature
			if (((tempCreature == getPlayer()) && creature.attitude.equals("hostile"))
					|| (creature == getPlayer()) && (tempCreature != null)) {
				// if targer creature is peacefull, show warning
				if (tempCreature.attitude.equals("peaceful") && tempCreature != getPlayer()) {
					if (display.showWarning("Are you really going to attack a peacefull beeing?") == 0)
						attack(creature, tempCreature);
					// else attack it
				} else
					attack(creature, tempCreature);
				return true;
			} else
				return false;
		}
	}

	// excuted by every keyboard event, goes through whole turn
	void Action(int action) {
		Field tempField = getMap().getField(getPlayer().locationx, getPlayer().locationy);
		Event event;

		// are we moving?
		if (action > 0 && action < 10) {
			display.showMessage("\n");
			boolean player_moved = false;
			move(getPlayer(), action);
			// or are we waiting?
			if (action == 5)
				display.showMessage("You wait.");
			else {
				player_moved = true;
				lookAround();
			}
			// anyway, move all creatures
			moveCreatures();
			if (player_moved)
				display.scrollToCenter();
			display.updateMap();
		} else
		// ----- are we pressing '>' key?
		if ((action == 14) && (tempField.name.equals("teleport"))) {
			System.out.println("Passage");
			int number = findMap(tempField.targetMap);
			// check if the teleport points to an existing map
			if (number != -1) {
				System.out.println(
						"You move to map :" + tempField.targetMap + " " + tempField.targetX + " " + tempField.targetY);
				// if so, go to it!
				setMap(tempField.targetMap, tempField.targetX, tempField.targetY);
				display.changeMap();
				display.showMessage("\nEntering " + getMap().name + "\n");
			}
			// are we picking anything?
		} else if (action == 'p' - 48) {
			display.showMessage("\nSearching... ");
			// check location of all items on the map
			for (int i = 0; i < getMap().items.size(); i++) {
				Item tempItem = getMap().items.elementAt(i);
				// if the same as players, pick it
				if ((tempItem.locationx == getPlayer().locationx) && (tempItem.locationy == getPlayer().locationy)) {
					display.showMessage(" Picking: ");
					display.showMessage(tempItem.name + ", ");
					// if item == money, add it to the amount
					if (tempItem.name.equals("money")) {
						getPlayer().money += tempItem.price;
						getMap().items.removeElementAt(i);
						// update the displayed amount
						display.updateStats();
						// clear the picked amount
						display.updateMap();
					} else {
						// if its not money and we are not in a shop
						// or we have enough money to buy it
						if ((!tempField.name.equals("shop")) || (tempItem.price <= getPlayer().money)) {
							if (getPlayer().getFreeCapacity() < tempItem.weight) {
								display.showMessage(" You cannot carry anymore!");
								break;
							}
							display.showMessage(" --picked");
							// pay if in the shop
							if (tempField.name.equals("shop"))
								getPlayer().money -= tempItem.price;
							// add new item to players items
							getPlayer().items.add(tempItem);
							// and remove it from the map
							getMap().items.removeElementAt(i);
							display.updateMoney(getPlayer().money);
							display.updateMap();
							break;
						} else
							display.showMessage(" --Not enough money!");
					}

					// stats.updateInventory();
				}
			}
		}
		// check if we are alive
		if (getPlayer().health <= 0) {
			display.showWarning("You died - game over\nDo you want to load last save?");
			System.out.println("---YOU DIED!!");
			System.exit(0);
		}
		// events checking
		if ((action > 0 && action < 10) || action == 14 || action == 'p' - 48) {
			// increase turn number, first turn starts from 1!
			turn++;
			// go through all events checking if we match any
			for (int i = 0; i < events.size(); i++) {
				event = events.elementAt(i);
				// check all condtitions
				if ((event.turn == turn || event.turn == 0) && (event.map.equals(getMap().name) || event.map.equals(""))
						&& ((event.locationx == getPlayer().locationx || event.locationx == 0)
								&& (event.locationy == getPlayer().locationy || event.locationy == 0))
						&& (event.experience <= getPlayer().experience || event.experience == 0))
					// if yes - execute event
					executeEvent(event);
			}
		}
	}

	// execute event
	void executeEvent(Event event) {
		System.out.println("Executing event:" + event.text);
		// display window with info
		if (!event.text.equals(""))
			display.showText(event.text);
		Creature player = creatures.elementAt(0);
		// increase stats (if possible)
		player.health += event.health;
		player.mana += event.mana;
		player.speed += event.speed;
		player.strength += event.strength;
		player.dexterity += event.dexterity;
		player.intelligence += event.intelligence;
		player.level = event.level;
		player.bonusStats += event.pointsForStats;
		player.bonusSkills += event.pointsForSkills;
		display.updateStats();
		stats.updateStats();
		// remove event if neccesary
		if (!event.repeat.equals("yes"))
			events.remove(event);
	}

	// move all creatures
	void moveCreatures() {
		// go through all map creatures
		for (int i = 1; i < getMap().creatures.size(); i++) {
			Creature tempCreature = getMap().creatures.elementAt(i);
			// speed implementation
			tempCreature.turCredits += tempCreature.speed;
			if (tempCreature.turCredits > getPlayer().speed)
				do {
					if ((distance(tempCreature, getPlayer()) < tempCreature.perception)
							&& (tempCreature.attitude.equals("hostile")))
						follow(tempCreature, getPlayer());
					else
						move(tempCreature, generator.nextInt(8) + 1);
					tempCreature.turCredits -= getPlayer().speed;
				} while (tempCreature.turCredits > getPlayer().speed);
		}

	}

	// returns a creature from a location
	Creature isFieldOccupied(int x, int y) {
		// go through the creatures
		for (int i = 0; i < getMap().creatures.size(); i++) {
			Creature tempCreature = getMap().creatures.elementAt(i);
			if ((tempCreature.locationx == x) && (tempCreature.locationy == y))
				return tempCreature;
		}
		return null;
	}

	// return the first creature from all (player)
	Creature getPlayer() {
		return creatures.elementAt(0);
	}

	// algorithm to define which places are visible
	void lookAround() {
		// make players location visible
		getMap().visible[getPlayer().locationx][getPlayer().locationy] = true;
		// lounch recurence method
		recurrenceSearch(getPlayer().perception, getPlayer().locationx, getPlayer().locationy);
	}

	// main method for players looking around
	void recurrenceSearch(int iteration, int x, int y) {
		// important ending condition!
		if (iteration-- <= 0)
			return;
		// check the nearest neighbourhood
		for (int currentX = -1; currentX <= 1; currentX++)
			for (int currentY = -1; currentY <= 1; currentY++) {
				// necessary conditions
				if (currentX == 0 && currentY == 0)
					continue;
				if ((getPlayer().locationx - x) * currentX > 0)
					continue;
				if ((getPlayer().locationy - y) * currentY > 0)
					continue;
				if ((getPlayer().locationx - x != 0) && y == 0)
					continue;
				if ((getPlayer().locationy - y != 0) && x == 0)
					continue;
				if ((distance(getPlayer(), x, y) > 2) && ((x != getPlayer().locationx && currentX == 0)
						|| (y != getPlayer().locationy && currentY == 0)))
					continue;
				// make a place visible
				getMap().visible[x + currentX][y + currentY] = true;
				if (getMap().getField(x + currentX, y + currentY).visibility > 0)
					recurrenceSearch(iteration, x + currentX, y + currentY);
			}
		return;
	}

	// count the distance between two creatures
	int distance(Creature creature1, Creature creature2) {
		int distance = 0;
		// just Pitagoras ;)
		distance = (int) Math.sqrt(Math.pow(creature1.locationx - creature2.locationx, 2)
				+ Math.pow(creature1.locationy - creature2.locationy, 2));
		return distance;
	}

	// count the distance between a creature and a position
	int distance(Creature creature1, int x, int y) {
		int distance = 0;
		// just Pitagoras ;)
		distance = (int) Math.sqrt(Math.pow(creature1.locationx - x, 2) + Math.pow(creature1.locationy - y, 2));
		return distance;
	}

	// find a map by a name
	int findMap(String name) {
		Map tempMap;
		for (int i = 0; i < maps.size(); i++) {
			tempMap = maps.elementAt(i);
			if (tempMap.name.equals(name)) {
				return i;
			}
		}
		return -1;
	}

	// keyboard event method - all keys
	public void keyTyped(KeyEvent evt) {

		char ch = evt.getKeyChar(); // The character typed.

		Action(ch - 48);

		display.updateTur(turn);
	}

	// keyboard event method (just arrow keys)
	public void keyPressed(KeyEvent evt) {
		int key = evt.getKeyCode(); // keyboard code for the key that was
									// pressed

		if (key == KeyEvent.VK_LEFT)
			Action(4);
		else if (key == KeyEvent.VK_RIGHT)
			Action(6);
		else if (key == KeyEvent.VK_UP)
			Action(8);
		else if (key == KeyEvent.VK_DOWN)
			Action(2);
		display.updateTur(turn);
	} // end keyPressed()

	public void keyReleased(KeyEvent evt) {
		// empty method, required by the KeyListener Interface
	}

	/**
	 * Opens a save file dialog and saves data if save approved
	 */
	public boolean saveFile(String filename) throws IOException {
		try {
			// Open a stream.
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));

			// Serialization is required!
			out.writeObject(skills);
			System.out.println("Saved :" + skills);
			out.writeObject(events);
			System.out.println("Saved :" + events);
			out.writeObject(items);
			System.out.println("Saved :" + items);
			out.writeObject(creatures);
			System.out.println("Saved :" + creatures);
			out.writeObject(maps);
			System.out.println("Saved :" + maps);
			out.writeObject(display.getLog());

			// Close a stream.
			out.close();
		} catch (IOException e2) {
			throw new IOException("Unable to save to: " + filename);
			// Possibly the file is used by other application.
		}
		return true;
	}

	/**
	 * Opens a load file dialog and loads data if load approved
	 */
	public boolean openFile(String filename) throws IOException {
		try {

			// Open input stream.
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

			// Read all flights and reservations.
			skills = ((Vector) in.readObject());
			events = ((Vector) in.readObject());
			items = ((Vector) in.readObject());
			creatures = ((Vector) in.readObject());
			maps = ((Vector) in.readObject());
			display.setLog((String) in.readObject());
			setMap(getPlayer().map, getPlayer().locationx, getPlayer().locationy);
			display.updateStats();

			// Close stream.
			in.close();
		} catch (IOException e1) {
			// When IO system error.
			throw new IOException("Unable to open file :" + filename);
		} catch (ClassNotFoundException e2) {
			// When wrong type of data in a file.
			throw new IOException("Wrong input file format");
		}
		return true;
	}
}
