import java.io.File;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.ImageIcon;

// class describing single item (supports both pattern and initialised) 
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	// item information parameters
	String name, type, description, filename, material;
	int price, weight;

	// Randomised item properties
	String toHit, damage, armor, dodge, strprice, strweight;

	// sets an item location
	String map;
	int locationx, locationy;

	// item can have bonuses to different stats
	int health, mana, strength, dexterity, intelligence, perception, speed;

	// those bonuses can vary from different item initialisation (even from same
	// pattern)
	// because pattern contains only string expressions
	String strhealth, strmana, strstrength, strdexterity, strintelligence, strperception, strspeed;

	// requirements for using
	int minLevel, minStrength, minDexterity, minIntelligence, minPerception, minSpeed;

	// forbid some professions/races from using (under development)
	Vector<String> forbidden;

	// contains image
	ImageIcon image;

	// dice reference
	Dice dice;

	// constructor for initialising an item (materialising)
	public Item(Item item) {
		dice = item.dice;

		// item description parameters
		name = item.name;
		filename = item.filename;
		type = item.type;
		description = item.description;
		image = item.image;

		// randomized once values
		weight = dice.throwDice(item.strweight);
		price = dice.throwDice(item.strprice);

		// (under development)
		forbidden = item.forbidden;

		// location parameters
		locationx = item.locationx;
		locationy = item.locationy;
		map = item.map;

		// fighting parameters
		toHit = item.toHit;
		damage = item.damage;
		dodge = item.dodge;
		armor = item.armor;

		// minimal requirements
		minLevel = item.minLevel;
		minStrength = item.minStrength;
		minDexterity = item.minDexterity;
		minIntelligence = item.minIntelligence;
		minPerception = item.minPerception;
		minSpeed = item.minSpeed;

		// statisctics modifications
		health = dice.throwDice(item.strhealth);
		mana = dice.throwDice(item.strmana);
		strength = dice.throwDice(item.strstrength);
		dexterity = dice.throwDice(item.strdexterity);
		intelligence = dice.throwDice(item.strintelligence);
		perception = dice.throwDice(item.strperception);
		speed = dice.throwDice(item.strspeed);
	}

	// money contructor
	public Item(int amount) {
		name = "money";
		price = amount;
		image = createImageIcon("images/money.gif");
	}

	// pattern item contructor
	public Item(Dice dice, String name, String filename, String type, String description, String strweight,
			String strprice, String material, String map, int locationx, int locationy, String toHit, String damage,
			String dodge, String armor, String strhealth, String strmana, String strstrength, String strdexterity,
			String strintelligence, String strperception, String strspeed, int minLevel, int minStrength,
			int minDexterity, int minIntelligence, int minPerception, int minSpeed) {

		this.dice = dice;

		// item description parameters
		this.name = name;
		this.filename = filename;
		this.type = type;
		this.description = description;
		this.strweight = strweight;
		this.strprice = strprice;
		this.material = material;

		// location parameters
		this.map = map;
		this.locationx = locationx;
		this.locationy = locationy;

		// fighting parameters
		this.toHit = toHit;
		this.damage = damage;
		this.dodge = dodge;
		this.armor = armor;

		// creation expresions
		this.strhealth = strhealth;
		this.strmana = strmana;
		this.strstrength = strstrength;
		this.strdexterity = strdexterity;
		this.strintelligence = strintelligence;
		this.strperception = strperception;
		this.strspeed = strspeed;

		// minimal requirements
		this.minLevel = minLevel;
		this.minStrength = minStrength;
		this.minDexterity = minDexterity;
		this.minIntelligence = minIntelligence;
		this.minPerception = minPerception;
		this.minSpeed = minSpeed;

		// (under development)
		forbidden = new Vector<String>();

		// get image
		image = createImageIcon(filename);
	}

	// get Item information
	String getInfo() {
		return name + " " + type + " " + weight + " " + strength + " " + mana + " " + dexterity + " " + perception + " "
				+ speed;
	}

	// add a new position to forbidden vector
	void addForbidden(String name) {
		forbidden.add(name);
	}

	// return item image
	ImageIcon getImage() {
		return image;
	}

	// create image
	protected static ImageIcon createImageIcon(String path) {
		File temp = new File(path);
		if (temp.exists())
			return new ImageIcon(path);
		else
			return null;
	}
}
