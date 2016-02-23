import java.io.File;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.ImageIcon;

// class representing a single creature
public class Creature implements Serializable {
	private static final long serialVersionUID = 1L;

	// general parameters
	String name, pathAndFilenamePrefix, race, profession, description, attitude, move;

	// battle parameters
	String damage, toHit, armour, dodge, strMoney, map;

	// personal info
	int level, mana, speed, health, experience, turCredits, money;
	int strength, dexterity, intelligence, perception;
	int bonusStats, bonusSkills;

	// map position
	int locationx, locationy, direction = 4;

	// used for displaying blood (when injured)
	boolean injured = false;

	// to indicate if a creature is visible
	boolean visible = false;

	// contains images
	ImageIcon left, right, down, up, upright, upleft, downright, downleft;

	// all items the creature has
	Vector<Item> items;

	// names of items that are created when new creature is created
	Vector<String> namedItems, randomItems;

	// equipped items
	Item head, chest, leftHand, rightHand, back, belt, shoes, gloves, shoulders, ranged, ring1, ring2, ring3, ring4;

	Dice dice;

	// possible dialogs
	Object[] questions, answers;

	// this constructor is for making an individual creature from a pattern
	public Creature(Creature creature) {
		this.dice = creature.dice;
		this.name = creature.name;
		this.pathAndFilenamePrefix = creature.pathAndFilenamePrefix;
		this.race = creature.race;
		this.profession = creature.profession;
		this.description = creature.description;
		this.experience = creature.experience;

		this.level = 1;
		this.speed = creature.speed;
		this.health = creature.health;
		this.mana = creature.mana;
		this.strength = creature.strength;
		this.dexterity = creature.dexterity;
		this.intelligence = creature.intelligence;
		this.perception = creature.perception;

		this.damage = creature.damage;
		this.toHit = creature.toHit;
		this.armour = creature.armour;
		this.dodge = creature.dodge;

		this.attitude = creature.attitude;
		this.move = creature.move;
		this.money = dice.throwDice(creature.strMoney);

		this.map = creature.map;
		this.locationx = creature.locationx;
		this.locationy = creature.locationy;

		this.left = creature.left;
		this.right = creature.right;
		this.up = creature.up;
		this.down = creature.down;
		this.upleft = creature.upleft;
		this.downleft = creature.downleft;
		this.upright = creature.upright;
		this.downright = creature.downright;
		turCredits = 0;

		// create items from desired items
		items = new Vector<Item>();
		namedItems = new Vector<String>();
		if (namedItems != null)
			for (int i = 0; i < creature.namedItems.size(); i++)
				namedItems.add(creature.namedItems.elementAt(i));
		randomItems = new Vector<String>();
		if (randomItems != null)
			for (int i = 0; i < creature.randomItems.size(); i++)
				randomItems.add(creature.randomItems.elementAt(i));

		// create dialogs
		questions = new Object[creature.questions.length];
		for (int i = 0; i < creature.questions.length; i++)
			questions[i] = creature.questions[i];

		answers = new Object[creature.answers.length];
		for (int i = 0; i < creature.answers.length; i++)
			answers[i] = creature.answers[i];

	}

	// this constructor is only for creatures
	public Creature(Dice dice, String name, String pathAndFilenamePrefix,

			// for variable description - look above
			String race, String profession, String description, int experience, int speed, int health, int mana,
			int strength, int dexterity, int intelligence, int perception, String damage, String toHit, String armour,
			String dodge, String attitude, String move, String strMoney, String map, int locationx, int locationy,
			Vector<String> namedItems, Vector<String> randomItems, Vector<String> questions, Vector<String> answers) {
		this.dice = dice;
		this.name = name;
		this.pathAndFilenamePrefix = pathAndFilenamePrefix;
		this.race = race;
		this.profession = profession;
		this.description = description;
		this.experience = experience;

		level = 1;
		this.speed = speed;
		this.health = health;
		this.mana = mana;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.perception = perception;

		this.damage = damage;
		this.toHit = toHit;
		this.armour = armour;
		this.dodge = dodge;
		this.move = move;
		this.strMoney = strMoney;
		this.attitude = attitude;
		this.map = map;
		this.locationx = locationx;
		this.locationy = locationy;

		// create items that every creature has
		this.namedItems = new Vector<String>();
		for (int i = 0; i < namedItems.size(); i++) {
			this.namedItems.add(namedItems.elementAt(i));
		}
		this.randomItems = new Vector<String>();
		for (int i = 0; i < randomItems.size(); i++) {
			this.randomItems.add(randomItems.elementAt(i));
		}

		// create dialogs
		this.questions = new Object[questions.size()];
		for (int i = 0; i < questions.size(); i++)
			this.questions[i] = questions.elementAt(i);
		this.answers = new Object[answers.size()];
		for (int i = 0; i < answers.size(); i++)
			this.answers[i] = answers.elementAt(i);

		// reseting speed technique
		turCredits = 0;

		// loading all images, if sth wrong with any, try to load a default one
		// instead
		System.out.println("base found " + createImageIcon(pathAndFilenamePrefix + ".gif"));
		left = createImageIcon(pathAndFilenamePrefix + "_left.gif");
		if (left == null)
			left = createImageIcon(pathAndFilenamePrefix + ".gif");
		else
			System.out.println("left found" + left);
		right = createImageIcon(pathAndFilenamePrefix + "_right.gif");
		if (right == null)
			right = createImageIcon(pathAndFilenamePrefix + ".gif");
		else
			System.out.println("right found");
		up = createImageIcon(pathAndFilenamePrefix + "_up.gif");
		if (up == null)
			up = createImageIcon(pathAndFilenamePrefix + ".gif");
		else
			System.out.println("up found");
		down = createImageIcon(pathAndFilenamePrefix + "_down.gif");
		if (down == null)
			down = createImageIcon(pathAndFilenamePrefix + ".gif");
		else
			System.out.println("down found");
		upleft = createImageIcon(pathAndFilenamePrefix + "_upleft.gif");
		if (upleft == null)
			upleft = createImageIcon(pathAndFilenamePrefix + ".gif");
		else
			System.out.println("upleft found");
		downleft = createImageIcon(pathAndFilenamePrefix + "_downleft.gif");
		if (downleft == null)
			downleft = createImageIcon(pathAndFilenamePrefix + ".gif");
		else
			System.out.println("downleft found");
		upright = createImageIcon(pathAndFilenamePrefix + "_upright.gif");
		if (upright == null)
			upright = createImageIcon(pathAndFilenamePrefix + ".gif");
		else
			System.out.println("upright found");
		downright = createImageIcon(pathAndFilenamePrefix + "_downright.gif");
		if (downright == null)
			downright = createImageIcon(pathAndFilenamePrefix + ".gif");
		else
			System.out.println("downright found");

		items = new Vector();
	}

	// add item to creature
	void addItem(Item item) {
		items.add(item);
	}

	// check if the creature can move
	boolean canMove() {
		if (move.equals("stay"))
			return false;
		return true;
	}

	// get free capacity (how much more it can carry)
	public int getFreeCapacity() {
		int sum = 0;
		for (int i = 0; i < items.size(); i++) {
			Item tempItem = items.elementAt(i);
			sum += tempItem.weight;
		}
		return strength * 10 - sum;
	}

	// get the weight of all carried items
	int getLoad() {
		int sum = 0;
		for (int i = 0; i < items.size(); i++) {
			Item item = items.elementAt(i);
			sum += item.weight;
		}
		return sum;
	}

	// add experience
	public void addExperience(int amount) {
		int raceBonus = 0;
		// humans learn quicker than other races
		if (race.equals("human"))
			raceBonus += 10;

		experience += amount + raceBonus * amount / 100;
	}

	// checks and resets beeing injured mark
	boolean wasInjured() {
		if (injured) {
			injured = false;
			return true;
		}
		return false;
	}

	// count damage
	public int getDamage() {
		// count dice plus strength(stronger creatures hit harder)
		int sum = dice.throwDice(damage) + strength;

		// check if any item contributes
		sum += addItemDamage(head);
		sum += addItemDamage(chest);
		sum += addItemDamage(leftHand);
		sum += addItemDamage(rightHand);
		sum += addItemDamage(back);
		sum += addItemDamage(belt);
		sum += addItemDamage(shoes);
		sum += addItemDamage(gloves);
		sum += addItemDamage(shoulders);
		sum += addItemDamage(ranged);
		sum += addItemDamage(ring1);
		sum += addItemDamage(ring2);
		sum += addItemDamage(ring3);
		sum += addItemDamage(ring4);

		// particular classes have bonusses
		if (profession.equals("warrior"))
			sum += 10;
		if (profession.equals("thief"))
			sum += 5;

		// same with races
		if (race.equals("dragon"))
			sum += 10;
		if (race.equals("orc"))
			sum += 5;

		return sum;
	}

	// get single items damage
	int addItemDamage(Item item) {
		if (item != null)
			if (!item.damage.equals(""))
				return dice.throwDice(item.damage);
		return 0;
	}

	// count a chance to hit a target
	public int getToHit() {
		int sum = 0;
		// dexterity makes aiming easier
		sum = dexterity + dice.throwDice(toHit);

		// add equipped items factors
		sum += addItemToHit(head);
		sum += addItemToHit(chest);
		sum += addItemToHit(leftHand);
		sum += addItemToHit(rightHand);
		sum += addItemToHit(back);
		sum += addItemToHit(belt);
		sum += addItemToHit(shoes);
		sum += addItemToHit(gloves);
		sum += addItemToHit(shoulders);
		sum += addItemToHit(ranged);
		sum += addItemToHit(ring1);
		sum += addItemToHit(ring2);
		sum += addItemToHit(ring3);
		sum += addItemToHit(ring4);

		return sum;
	}

	// get single item toHit parameter
	int addItemToHit(Item item) {
		if (item != null)
			if (!item.toHit.equals(""))
				return dice.throwDice(item.toHit);
		return 0;
	}

	// check if any damage coused
	void doDamage(int value) {
		if (value > 0) {
			health -= value;
			injured = true;
		}
		return;
	}

	// get armor skill
	public int getArmor() {
		int sum = dice.throwDice(armour);

		// add equipped items armor factors
		sum += addItemArmour(head);
		sum += addItemArmour(chest);
		sum += addItemArmour(leftHand);
		sum += addItemArmour(rightHand);
		sum += addItemArmour(back);
		sum += addItemArmour(belt);
		sum += addItemArmour(shoes);
		sum += addItemArmour(gloves);
		sum += addItemArmour(shoulders);
		sum += addItemArmour(ranged);
		sum += addItemArmour(ring1);
		sum += addItemArmour(ring2);
		sum += addItemArmour(ring3);
		sum += addItemArmour(ring4);

		// hard skinned races get bonuses
		if (race.equals("dragon"))
			sum += 10;

		return sum;
	}

	// get single item armor
	int addItemArmour(Item item) {
		if (item != null)
			if (!item.armor.equals(""))
				return dice.throwDice(item.armor);
		return 0;
	}

	// get dodge skill
	public int getDodge() {
		// dexterity hepls to avoid attacks
		int sum = dexterity + dice.throwDice(dodge);

		// add equipped items factors
		sum += addItemDodge(head);
		sum += addItemDodge(chest);
		sum += addItemDodge(leftHand);
		sum += addItemDodge(rightHand);
		sum += addItemDodge(back);
		sum += addItemDodge(belt);
		sum += addItemDodge(shoes);
		sum += addItemDodge(gloves);
		sum += addItemDodge(shoulders);
		sum += addItemDodge(ranged);
		sum += addItemDodge(ring1);
		sum += addItemDodge(ring2);
		sum += addItemDodge(ring3);
		sum += addItemDodge(ring4);

		// some bonuses from professions and
		if (profession.equals("thief"))
			sum += 10;
		if (profession.equals("warrior"))
			sum += 5;
		if (race.equals("elf"))
			sum += 10;

		return sum;
	}

	// get single item dodge
	int addItemDodge(Item item) {
		if (item != null)
			if (!item.dodge.equals(""))
				return dice.throwDice(item.dodge);
		return 0;
	}

	// get images for every direction
	ImageIcon getImage() {
		ImageIcon temp = null;
		switch (direction) {
		case 9:
			temp = upright;
			break;
		case 8:
			temp = up;
			break;
		case 7:
			temp = upleft;
			break;
		case 6:
			temp = right;
			break;
		case 5:
			temp = right;
			break;
		case 4:
			temp = left;
			break;
		case 3:
			temp = downright;
			break;
		case 2:
			temp = down;
			break;
		case 1:
			temp = downleft;
			break;
		}
		return temp;

	}

	// get creature info
	String getInfo() {
		String temp = race + "," + profession + ",HP:" + health + ",attitude: " + attitude + ", movable: " + move
				+ ", money: " + money + "$, ";
		if (items.size() > 0)
			temp += "Items possesed: ";
		for (int i = 0; i < items.size(); i++) {
			Item tempItem = items.elementAt(i);
			temp += tempItem.name + ", ";
		}
		return temp + "; ";
	}

	// create images
	protected static ImageIcon createImageIcon(String path) {
		File temp = new File(path);
		if (temp.exists())
			return new ImageIcon(path);
		else
			return null;
	}
}
