import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

// fists class containing Main method
// main task is to load data from XML file and use it to initialise other objects

public class Start {
	// Common dice representation
	private Dice dice;

	public Start() {
	}

	// 'Swing way' of running a window
	private void startInANewThead() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowWorld();
			}
		});
	}

	// start creating
	private void createAndShowWorld() {
		// only dice initialisation
		dice = new Dice();

		// initialise main logic object
		Logic logic = new Logic(dice);
		// and load all data to it
		loadFiles(logic);

		// set map according to player location
		System.out.println("Trying to set map: ");
		logic.setMap(logic.getPlayer().map, logic.getPlayer().locationx, logic.getPlayer().locationy);

		// initialise Graphical User Interface
		Show GUI = new Show(logic);
		GUI.setVisible(true);
		// set starting size
		GUI.setSize(800, 600);
	}

	// there are 5 XML files to load
	// after solving how to process a XML file, getting data is pure mechanical
	// activity therefore only first file loading is being explained
	public void loadFiles(Logic gameLogic) {
		loadItems(gameLogic);
		loadCreatures(gameLogic);
		loadMaps(gameLogic);
		loadEvents(gameLogic);
		loadSkills(gameLogic);
	}

	// loading possible skills (under development)
	public void loadSkills(Logic logic) {
		String name, desc; // simpler skill information
		int valuePerPoint; // how many skill points needed for one point
							// increase
							// (for balancing the skill)
		Skill skill = null;
		try {
			// get main node
			// file has to be in 'conf' directory and have proper root and
			// element name
			NodeList listOfSkills = checkRootAndGetList("conf/", "skills.xml", "skills", "skill");
			// get amount of elements
			int totalSkills = listOfSkills.getLength();
			System.out.println("\n*****Total number of skills: " + totalSkills);

			// and process all of them
			for (int s = 0; s < totalSkills; s++) {

				// check if node is valid
				Node tempSkillNode = listOfSkills.item(s);
				if (tempSkillNode.getNodeType() == Node.ELEMENT_NODE) {

					// get temporary element
					Element skillElement = (Element) tempSkillNode;

					// get element value (String) by elements name
					name = getElementValue(skillElement, "name");
					System.out.println("skill name : " + name);

					// some of the elements are being immediately processed to
					// get value
					valuePerPoint = dice.throwDice(getElementValue(skillElement, "valuePerPoint"));
					System.out.println("valuePerPoint : " + valuePerPoint);

					// get element value (String) by elements name
					desc = getElementValue(skillElement, "desc");
					System.out.println("desc : " + desc);

					// create a new object from loaded data
					skill = new Skill(name, valuePerPoint, desc);
				}
				// and add it to logic object
				logic.addSkill(skill);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	// load possible events
	public void loadEvents(Logic logic) {
		String text, repeat, map;
		int turn, locationx, locationy, level, experience, pointsForStats, pointsForSkills;
		int strength, mana, health, intelligence, perception, dexterity, speed;
		Event event = null;
		try {
			// get main node
			// file has to be in 'conf' directory and have proper root and
			// element name
			NodeList listOfEvents = checkRootAndGetList("conf/", "events.xml", "events", "event");
			int totalEvents = listOfEvents.getLength();
			System.out.println("\n*****Total number of events: " + totalEvents);

			for (int s = 0; s < totalEvents; s++) {

				Node tempEventNode = listOfEvents.item(s);
				if (tempEventNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eventElement = (Element) tempEventNode;

					// ----
					turn = dice.throwDice(getElementValue(eventElement, "turn"));
					System.out.println("turn : " + turn);

					// -------
					text = getElementValue(eventElement, "text");
					System.out.println("event text : " + text);

					// -------
					map = getElementValue(eventElement, "map");
					System.out.println("map : " + map);

					// -------
					repeat = getElementValue(eventElement, "repeat");
					System.out.println("repeat : " + repeat);

					// ----
					locationx = dice.throwDice(getElementValue(eventElement, "locationx"));
					if (locationx > 0)
						System.out.println("locationx : " + locationx);

					// ----
					locationy = dice.throwDice(getElementValue(eventElement, "locationy"));
					if (locationy > 0)
						System.out.println("locationy : " + locationy);

					// ----
					level = dice.throwDice(getElementValue(eventElement, "level"));
					if (level > 0)
						System.out.println("level : " + level);

					// ----
					experience = dice.throwDice(getElementValue(eventElement, "experience"));
					if (experience > 0)
						System.out.println("experience : " + experience);

					// ----
					pointsForStats = dice.throwDice(getElementValue(eventElement, "pointsForStats"));
					if (pointsForStats > 0)
						System.out.println("pointsForStats : " + pointsForStats);

					// ----
					pointsForSkills = dice.throwDice(getElementValue(eventElement, "pointsForSkills"));
					if (pointsForSkills > 0)
						System.out.println("pointsForSkills : " + pointsForSkills);

					// manually modifiable attributes:
					// ----
					health = dice.throwDice(getElementValue(eventElement, "health"));
					if (health > 0)
						System.out.println("health : " + health);

					// ----
					mana = dice.throwDice(getElementValue(eventElement, "mana"));
					if (mana > 0)
						System.out.println("mana : " + mana);

					// ----
					speed = dice.throwDice(getElementValue(eventElement, "speed"));
					if (speed > 0)
						System.out.println("speed : " + speed);

					// ----
					strength = dice.throwDice(getElementValue(eventElement, "strength"));
					if (strength > 0)
						System.out.println("strength : " + strength);

					// ----
					dexterity = dice.throwDice(getElementValue(eventElement, "dexterity"));
					if (dexterity > 0)
						System.out.println("dexterity : " + dexterity);

					// ----
					intelligence = dice.throwDice(getElementValue(eventElement, "intelligence"));
					if (intelligence > 0)
						System.out.println("intelligence : " + intelligence);

					// ----
					perception = dice.throwDice(getElementValue(eventElement, "perception"));
					if (perception > 0)
						System.out.println("perception : " + perception);

					event = new Event(turn, text, repeat, map, locationx, locationy, level, experience, pointsForStats,
							pointsForSkills, health, mana, speed, strength, dexterity, intelligence, perception);

				}
				logic.addEvent(event);
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	// -------- load maps
	public void loadMaps(Logic logic) {
		String mapName, mapFilename, mapDescription, fieldName, fieldFilename, targetMap = "";
		int fieldMobility, fieldVisibility, mapMobility, mapVisibility, targetX = 1, targetY = 1;
		int creatureNumber, respawn, mapItemNumber, fieldItemNr;
		char fieldSymbol;
		Map map = null;
		try {
			NodeList listOfMaps = checkRootAndGetList("conf/", "maps.xml", "maps", "map");
			int totalMaps = listOfMaps.getLength();
			System.out.println("\n****Total number of maps : " + totalMaps);

			for (int s = 0; s < totalMaps; s++) {

				Node tempMapNode = listOfMaps.item(s);
				if (tempMapNode.getNodeType() == Node.ELEMENT_NODE) {

					Element mapElement = (Element) tempMapNode;

					// -------
					mapName = getElementValue(mapElement, "name");
					System.out.println("\nMap name : " + mapName);

					// -------
					mapFilename = getElementValue(mapElement, "filename");
					System.out.println("Filename : " + mapFilename);

					// ----
					mapDescription = getElementValue(mapElement, "description");
					System.out.println("description : " + mapDescription);

					try {
						mapVisibility = Integer.valueOf(getElementValue(mapElement, "visibility")).intValue();
					} catch (NumberFormatException E) {
						mapVisibility = 100;
					}
					System.out.println("Map visibility : " + mapVisibility);

					try {
						mapItemNumber = Integer.valueOf(getElementValue(mapElement, "itemNumber")).intValue();
					} catch (NumberFormatException E) {
						mapItemNumber = 0;
					}
					System.out.println("mapItem number : " + mapItemNumber);

					try {
						creatureNumber = Integer.valueOf(getElementValue(mapElement, "creatureNumber")).intValue();
					} catch (NumberFormatException E) {
						creatureNumber = 0;
					}
					System.out.println("Creature number : " + creatureNumber);

					try {
						respawn = Integer.valueOf(getElementValue(mapElement, "respawn")).intValue();
					} catch (NumberFormatException E) {
						respawn = 0;
					}
					System.out.println("respawn time : " + respawn);

					try {
						mapMobility = Integer.valueOf(getElementValue(mapElement, "mobility")).intValue();
					} catch (NumberFormatException E) {
						mapMobility = 100;
					}
					System.out.println("Map mobility : " + mapMobility);

					map = new Map(mapName, mapFilename, mapDescription, mapItemNumber, creatureNumber, respawn);

					// get another parent and read all fields
					NodeList fieldList = mapElement.getElementsByTagName("fields");
					Element fieldsElement = (Element) fieldList.item(0);
					NodeList listOfFields = fieldsElement.getElementsByTagName("field");

					int totalFields = listOfFields.getLength();
					System.out.println("Total number of fields : " + totalFields);

					for (int t = 0; t < totalFields; t++) {
						Node tempFieldNode = listOfFields.item(t);
						if (tempFieldNode.getNodeType() == Node.ELEMENT_NODE) {

							Element fieldElement = (Element) tempFieldNode;

							// -------
							fieldName = getElementValue(fieldElement, "name");
							System.out.println("Field name : " + fieldName);

							if (fieldName.equals("teleport")) {
								targetMap = getElementValue(fieldElement, "targetMap");
								System.out.println("targetMap : " + targetMap);
								try {
									targetX = Integer.valueOf(getElementValue(fieldElement, "targetX")).intValue();
								} catch (NumberFormatException E) {
									System.out.println(E);
									targetX = 1;
									;
								}
								System.out.println("targetX : " + targetX);

								try {
									targetY = Integer.valueOf(getElementValue(fieldElement, "targetY")).intValue();
								} catch (NumberFormatException E) {
									System.out.println(E);
									targetY = 1;
								}
								System.out.println("targetY : " + targetY);
							}

							// -------
							if (fieldName.equals("SPACE"))
								fieldSymbol = ' ';
							else {
								fieldSymbol = getElementValue(fieldElement, "symbol").charAt(0);
							}
							System.out.println("Field symbol : '" + fieldSymbol + "'");

							// -------
							fieldFilename = getElementValue(fieldElement, "filename");
							System.out.println("Field filename : " + fieldFilename);

							// -------
							try {
								fieldMobility = Integer.valueOf(getElementValue(fieldElement, "mobility")).intValue();
							} catch (NumberFormatException E) {
								// System.out.println(E);
								fieldMobility = mapMobility;
							}
							System.out.println("Field mobility : " + fieldMobility);

							// -------
							try {
								fieldVisibility = Integer.valueOf(getElementValue(fieldElement, "visibility"))
										.intValue();
							} catch (NumberFormatException E) {
								// System.out.println(E);
								fieldVisibility = mapVisibility;
							}
							System.out.println("Field visibility : " + fieldVisibility);

							// -------
							try {
								fieldItemNr = Integer.valueOf(getElementValue(fieldElement, "fieldItemsNr")).intValue();
							} catch (NumberFormatException E) {
								System.out.println(E);
								fieldItemNr = 0;
							}
							System.out.println("fieldItemNr : " + fieldItemNr);

							Field newField;
							if (fieldName.equals("teleport"))
								newField = new Field(fieldName, fieldFilename, fieldSymbol, fieldMobility,
										fieldVisibility, targetMap, targetX, targetY, fieldItemNr);
							else
								newField = new Field(fieldName, fieldFilename, fieldSymbol, fieldMobility,
										fieldVisibility, fieldItemNr);

							map.addField(newField, logic.items);
						}
					}

				} // end of reading map clause
				logic.addMap(map);
			} // end of for loop with s var

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	// -------- load creature patterns
	public void loadCreatures(Logic logic) {
		String name, filenamePrefix, description, itemName, itemClass, race;
		String profession, damage, toHit, armour, dodge, attitude, move, money, map;
		String dialogQuestion, dialogAnswer;
		int experience, speed, health, mana, strength, dexterity, intelligence, perception, locationx, locationy;
		Creature creature = null;

		try {
			NodeList listOfCreatures = checkRootAndGetList("conf/", "creatures.xml", "creatures", "creature");
			int totalCreatures = listOfCreatures.getLength();
			System.out.println("\n****Total number of Creatures: " + totalCreatures);

			for (int s = 0; s < totalCreatures; s++) {
				Vector<String> namedItems = new Vector<String>();
				Vector<String> randomItems = new Vector<String>();
				Vector<String> questions = new Vector<String>();
				Vector<String> answers = new Vector<String>();

				Node tempCreatureNode = listOfCreatures.item(s);
				if (tempCreatureNode.getNodeType() == Node.ELEMENT_NODE) {

					Element creatureElement = (Element) tempCreatureNode;

					// -------
					name = getElementValue(creatureElement, "name");
					System.out.println("\ncreature name : " + name);

					// -------
					filenamePrefix = getElementValue(creatureElement, "filenamePrefix");
					System.out.println("Filename prefix : " + filenamePrefix);

					// ----
					description = getElementValue(creatureElement, "description");
					System.out.println("description : " + description);

					// ----
					experience = dice.throwDice(getElementValue(creatureElement, "experience"));
					System.out.println("experience : " + experience);

					// ----
					race = getElementValue(creatureElement, "race");
					System.out.println("race : " + race);

					// ----
					profession = getElementValue(creatureElement, "profession");
					System.out.println("profession : " + profession);

					// ----
					money = getElementValue(creatureElement, "money");
					System.out.println("money : " + money);

					// ----
					speed = dice.throwDice(getElementValue(creatureElement, "speed"));
					System.out.println("speed : " + speed);

					// ----
					health = dice.throwDice(getElementValue(creatureElement, "health"));
					System.out.println("health : " + health);

					// ----
					mana = dice.throwDice(getElementValue(creatureElement, "mana"));
					if (mana > 0)
						System.out.println("mana : " + mana);

					// ----
					strength = dice.throwDice(getElementValue(creatureElement, "strength"));
					if (strength > 0)
						System.out.println("strength : " + strength);

					// ----
					dexterity = dice.throwDice(getElementValue(creatureElement, "dexterity"));
					if (dexterity > 0)
						System.out.println("dexterity : " + dexterity);

					// ----
					intelligence = dice.throwDice(getElementValue(creatureElement, "intelligence"));
					if (intelligence > 0)
						System.out.println("intelligence : " + intelligence);

					// ----
					perception = dice.throwDice(getElementValue(creatureElement, "perception"));
					if (perception > 0)
						System.out.println("perception : " + perception);

					// ----
					damage = getElementValue(creatureElement, "damage");
					System.out.println("damage : " + damage);

					// ----
					toHit = getElementValue(creatureElement, "toHit");
					System.out.println("toHit : " + toHit);

					// ----
					armour = getElementValue(creatureElement, "armour");
					System.out.println("armour : " + armour);

					// ----
					dodge = getElementValue(creatureElement, "dodge");
					System.out.println("dodge : " + dodge);

					// ----
					attitude = getElementValue(creatureElement, "attitude");
					System.out.println("attitude : " + attitude);

					// ----
					move = getElementValue(creatureElement, "move");
					System.out.println("move : " + move);

					// ----
					map = getElementValue(creatureElement, "map");
					System.out.println("map : " + map);

					// ----
					locationx = dice.throwDice(getElementValue(creatureElement, "locationx"));
					System.out.println("locationx : " + locationx);

					// ----
					locationy = dice.throwDice(getElementValue(creatureElement, "locationy"));
					System.out.println("locationy : " + locationy);

					// ------
					NodeList itemsList = creatureElement.getElementsByTagName("items");
					Element itemsElement = (Element) itemsList.item(0);
					if (itemsElement != null) {
						NodeList listOfItems = itemsElement.getElementsByTagName("item");

						int totalItems = listOfItems.getLength();
						System.out.println("Total number of items : " + totalItems);

						for (int t = 0; t < totalItems; t++) {
							Node tempItemNode = listOfItems.item(t);
							if (tempItemNode.getNodeType() == Node.ELEMENT_NODE) {

								Element itemElement = (Element) tempItemNode;

								// -------
								itemName = getElementValue(itemElement, "itemName");
								if (itemName.equals("random")) {
									itemClass = getElementValue(itemElement, "class");
									System.out.println("Items random class : " + itemClass);
									randomItems.add(itemClass);
									// item = new
									// Item(logic.getItem(itemName,itemClass,level,experience));
								} else {
									System.out.println("Item name : " + itemName);
									// item = new
									// Item(logic.getItem(itemName,level,experience));
									namedItems.add(itemName);
								}
							}
						}
					}

					// ------
					NodeList dialogsList = creatureElement.getElementsByTagName("dialogs");
					Element dialogsElement = (Element) dialogsList.item(0);
					if (dialogsElement != null) {
						NodeList listOfDialogs = dialogsElement.getElementsByTagName("dialog");

						int totalDialogs = listOfDialogs.getLength();
						System.out.println("Total number of dialogs : " + totalDialogs);

						for (int t = 0; t < totalDialogs; t++) {
							Node tempDialogNode = listOfDialogs.item(t);
							if (tempDialogNode.getNodeType() == Node.ELEMENT_NODE) {

								Element dialogElement = (Element) tempDialogNode;

								// -------
								dialogQuestion = getElementValue(dialogElement, "question");
								questions.add(dialogQuestion);
								System.out.println("dialogQuestion : " + dialogQuestion);

								// -------
								dialogAnswer = getElementValue(dialogElement, "answer");
								answers.add(dialogAnswer);
								System.out.println("dialogAnswer : " + dialogAnswer);
							}
						}
					}

					creature = new Creature(dice, name, filenamePrefix, race, profession, description, experience,
							speed, health, mana, strength, dexterity, intelligence, perception, damage, toHit, armour,
							dodge, attitude, move, money, map, locationx, locationy, namedItems, randomItems, questions,
							answers);
				} // end of if clause
				logic.addCreature(creature);
				System.out.println(creature.getInfo());
			} // end of for loop with s var

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	// load all item patterns
	public void loadItems(Logic logic) {
		String name, filename, description, type, toHit, damage, armor, dodge, material, map;
		int locationx, locationy;
		String weight, price, health, mana, speed, strength, perception, dexterity, intelligence;
		int minStrength, minLevel, minDexterity, minIntelligence, minSpeed, minPerception;
		Item item = null;
		try {
			NodeList listOfItems = checkRootAndGetList("conf/", "items.xml", "items", "item");
			int totalCreatures = listOfItems.getLength();
			System.out.println("\n*****Total number of items: " + totalCreatures);

			for (int s = 0; s < totalCreatures; s++) {

				Node tempItemNode = listOfItems.item(s);
				if (tempItemNode.getNodeType() == Node.ELEMENT_NODE) {

					Element itemElement = (Element) tempItemNode;

					// -------
					name = getElementValue(itemElement, "name");
					System.out.println("\nitem name : " + name);

					// -------
					filename = getElementValue(itemElement, "filename");
					System.out.println("item filename : " + filename);

					// ----
					description = getElementValue(itemElement, "description");
					System.out.println("description : " + description);

					// ----
					type = getElementValue(itemElement, "type");
					System.out.println("type : " + type);

					// ----
					weight = getElementValue(itemElement, "weight");
					System.out.println("weight : " + weight);

					// ----
					map = getElementValue(itemElement, "map");
					System.out.println("map : " + map);

					// ----
					locationx = dice.throwDice(getElementValue(itemElement, "locationx"));
					System.out.println("locationx : " + locationx);

					// ----
					locationy = dice.throwDice(getElementValue(itemElement, "locationy"));
					System.out.println("locationy : " + locationy);

					// ----
					price = getElementValue(itemElement, "price");
					System.out.println("price : " + price);

					// ----
					material = getElementValue(itemElement, "material");
					System.out.println("material : " + material);

					// ----
					toHit = getElementValue(itemElement, "toHit");
					System.out.println("toHit : " + toHit);

					// ----
					damage = getElementValue(itemElement, "damage");
					System.out.println("damage : " + damage);

					// ----
					armor = getElementValue(itemElement, "armor");
					System.out.println("armor : " + armor);

					// ----
					dodge = getElementValue(itemElement, "dodge");
					System.out.println("dodge : " + dodge);

					// manually modifiable attributes:
					// ----
					health = getElementValue(itemElement, "health");
					System.out.println("health : " + health);

					// ----
					mana = getElementValue(itemElement, "mana");
					System.out.println("mana : " + mana);

					// ----
					speed = getElementValue(itemElement, "speed");
					System.out.println("speed : " + speed);

					// ----
					strength = getElementValue(itemElement, "strength");
					System.out.println("strength : " + strength);

					// ----
					dexterity = getElementValue(itemElement, "dexterity");
					System.out.println("dexterity : " + dexterity);

					// ----
					intelligence = getElementValue(itemElement, "intelligence");
					System.out.println("intelligence : " + intelligence);

					// ----
					perception = getElementValue(itemElement, "perception");
					System.out.println("perception : " + perception);

					// ----
					minStrength = dice.throwDice(getElementValue(itemElement, "minStrength"));
					if (minStrength > 0)
						System.out.println("minStrength : " + minStrength);

					// ----
					minDexterity = dice.throwDice(getElementValue(itemElement, "minDexterity"));
					if (minDexterity > 0)
						System.out.println("minDexterity : " + minDexterity);

					// ----
					minIntelligence = dice.throwDice(getElementValue(itemElement, "minIntelligence"));
					if (minIntelligence > 0)
						System.out.println("minIntelligence : " + minIntelligence);

					// ----
					minPerception = dice.throwDice(getElementValue(itemElement, "minPerception"));
					if (minPerception > 0)
						System.out.println("minPerception : " + minPerception);

					// ----
					minSpeed = dice.throwDice(getElementValue(itemElement, "minSpeed"));
					if (minSpeed > 0)
						System.out.println("minSpeed : " + minSpeed);

					// ----
					minLevel = dice.throwDice(getElementValue(itemElement, "minLevel"));
					if (minLevel > 0)
						System.out.println("minLevel : " + minLevel);

					item = new Item(dice, name, filename, type, description, weight, price, material, map, locationx,
							locationy, toHit, damage, dodge, armor, health, mana, strength, dexterity, intelligence,
							perception, speed, minLevel, minStrength, minDexterity, minIntelligence, minPerception,
							minSpeed);

					NodeList forbiddenList = itemElement.getElementsByTagName("forbiddenFor");
					Element forbiddenElement = (Element) forbiddenList.item(0);
					if (forbiddenElement != null) {
						NodeList listOfForbidden = forbiddenElement.getElementsByTagName("name");

						int totalForbidden = listOfForbidden.getLength();
						System.out.println("Total number of forbidden : " + totalForbidden);
						String forbidden;
						for (int t = 0; t < totalForbidden; t++) {
							Node tempNode = listOfForbidden.item(t);
							if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

								Element tempElement = (Element) tempNode;

								NodeList textList = tempElement.getChildNodes();
								forbidden = textList.item(0).getNodeValue().trim();

								System.out.println("Forbidden name : " + forbidden);

								item.addForbidden(forbidden);
							}
						}
					}

				} // end of if clause
				logic.addItem(item);
			} // end of for loop with s var

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	// get element value by element name
	String getElementValue(Element element, String elementName) {
		NodeList mapNameList = element.getElementsByTagName(elementName);
		Element mapNameElement = (Element) mapNameList.item(0);
		if (mapNameElement == null)
			return "";
		NodeList textMNList = mapNameElement.getChildNodes();
		return textMNList.item(0).getNodeValue().trim();
	}

	// get nodelist (root node) from a file with a given path
	NodeList checkRootAndGetList(String path, String fileName, String rootName, String elementName) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(path + fileName));

			// normalize text representation
			doc.getDocumentElement().normalize();
			String tempstr = doc.getDocumentElement().getNodeName();
			if (!tempstr.equals(rootName)) {
				System.out.println("Root element of the " + fileName + " is not " + rootName + "'!");
				return null;
			}
			return doc.getElementsByTagName(elementName);
		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());
			return null;
		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();
			return null;
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

	// starting method
	public static void main(String[] args) {
		new Start().startInANewThead();
	}
}
