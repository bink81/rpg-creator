import java.io.Serializable;

// class representing single event
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	// condition parameters
	int turn, locationx, locationy, level, experience;
	String map, repeat;

	// event rewards
	int pointsForStats, pointsForSkills;
	int health, mana, speed, strength, dexterity, intelligence, charisma;

	// information
	String text;

	// constructor
	Event(int turn, String text, String repeat, String map, int locationx, int locationy, int level, int experience,
			int pointsForStats, int pointsForSkills, int health, int mana, int speed, int strength, int dexterity,
			int intelligence, int charisma) {
		this.turn = turn;
		this.text = text;
		this.repeat = repeat;
		this.map = map;
		this.locationx = locationx;
		this.locationy = locationy;
		this.level = level;
		this.experience = experience;
		this.pointsForStats = pointsForStats;
		this.pointsForSkills = pointsForSkills;

		this.health = health;
		this.mana = mana;
		this.speed = speed;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.charisma = charisma;
	}
}
