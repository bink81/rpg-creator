import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

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

public class Stats extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel title, burden, image, bonus;
	private JPanel stats;
	private JLabel hp, mana, race, name, strength, dexterity, intelligence, perception, speed;
	private JLabel valName, valStrength, valProfession, valRace, valLevel, valGold;
	private JLabel valMana, valHP, valDexterity, valIntelligence, valPerception, valSpeed, lblGold, jLabel1, valBonus,
			valWeight;

	private JButton btnUse, btnDrink, btnEat, btnRead, btnSpell, btnDrop;
	private JButton btnHP, btnMana, btnStr, btnDex, btnInt, btnPer, btnSpeed, btnMissileWeapon, btnHead, btnRight,
			btnLeft, btnChest, btnGloves, btnShoes, btnBack, btnBelt, btnRing1, btnRing2, btnRing3, btnRing4,
			btnShoulders;

	private ItemDetails itemDetails;
	private JTable inventory;
	private JScrollPane jScrollPane1;
	private DefaultTableModel inventoryModel;
	private Creature player;
	private Item chosenItem;
	private Logic logic;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */

	// constructor
	public Stats(Logic logic) {
		super();
		this.logic = logic;
		player = logic.getPlayer();
		initGUI();
		updateStats();
	}

	// initialization of many Swing graphic elements
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentShown(ComponentEvent evt) {
					System.out.println("this.componentShown, event=" + evt);
					updateTable();
				}
			});

			itemDetails = new ItemDetails();
			this.add(itemDetails, new AnchorConstraint(322, 940, 869, 393, AnchorConstraint.ANCHOR_ABS,
					AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
			itemDetails.setPreferredSize(new java.awt.Dimension(382, 203));
			itemDetails.setToolTipText("Show item details");
			{
				btnDrop = new JButton();
				btnDrop.setMargin(new Insets(0, 0, 0, 0));
				this.add(btnDrop, new AnchorConstraint(404, 460, 770, 304, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				btnDrop.setText("Drop");
				btnDrop.setFocusable(false);
				btnDrop.setFont(new java.awt.Font("Gungsuh", 1, 16));
				btnDrop.setPreferredSize(new java.awt.Dimension(64, 58));
				btnDrop.setToolTipText("Sets an item on the ground");
				btnDrop.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnDropActionPerformed(evt);
					}
				});
			}
			{
				btnSpell = new JButton();
				btnSpell.setMargin(new Insets(0, 0, 0, 0));
				this.add(btnSpell, new AnchorConstraint(340, 460, 664, 304, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				btnSpell.setText("Spell");
				btnSpell.setFocusable(false);
				btnSpell.setFont(new java.awt.Font("Gungsuh", 1, 16));
				btnSpell.setPreferredSize(new java.awt.Dimension(64, 58));
				btnSpell.setToolTipText("Opens a spell book (under development)");
				btnSpell.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						showText("This funcion is still under development...");
					}
				});
			}

			{
				btnUse = new JButton();
				btnUse.setMargin(new Insets(0, 0, 0, 0));
				this.add(btnUse, new AnchorConstraint(469, 461, 879, 304, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				btnUse.setText("Use");
				btnUse.setFocusable(false);
				btnUse.setFont(new java.awt.Font("Gungsuh", 1, 16));
				btnUse.setPreferredSize(new java.awt.Dimension(64, 58));
				btnUse.setToolTipText("Only potions, books and scrolls can be used");
				btnUse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						use("tool");
					}
				});
			}
			{
				btnDrink = new JButton();
				btnDrink.setMargin(new Insets(0, 0, 0, 0));
				this.add(btnDrink, new AnchorConstraint(210, 461, 447, 304, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				btnDrink.setText("Drink");
				btnDrink.setFocusable(false);
				btnDrink.setFont(new java.awt.Font("Gungsuh", 1, 16));
				btnDrink.setPreferredSize(new java.awt.Dimension(64, 58));
				btnDrink.setToolTipText("Drinks a chosen (drinkable) iem.");
				btnDrink.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						use("potion");
					}
				});
			}
			{
				btnRead = new JButton();
				btnRead.setMargin(new Insets(0, 0, 0, 0));
				this.add(btnRead, new AnchorConstraint(146, 461, 340, 304, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				btnRead.setText("Read");
				btnRead.setFocusable(false);
				btnRead.setFont(new java.awt.Font("Gungsuh", 1, 16));
				btnRead.setPreferredSize(new java.awt.Dimension(64, 58));
				btnRead.setToolTipText("Reads a chosen (readable item)");
				btnRead.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						use("script");
					}
				});
			}
			{
				btnEat = new JButton();
				btnEat.setMargin(new Insets(0, 0, 0, 0));
				this.add(btnEat, new AnchorConstraint(275, 460, 555, 304, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				btnEat.setText("Eat");
				btnEat.setFocusable(false);
				btnEat.setFont(new java.awt.Font("Gungsuh", 1, 16));
				btnEat.setPreferredSize(new java.awt.Dimension(64, 58));
				btnEat.setToolTipText("Eats a chosen (eatable) item");
				btnEat.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						use("food");
					}
				});

			}
			{
				valWeight = new JLabel();
				this.add(valWeight, new AnchorConstraint(36, 900, 110, 720, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				valWeight.setPreferredSize(new java.awt.Dimension(60, 30));
				valWeight.setFont(new java.awt.Font("Dialog", 1, 14));
			}
			{
				burden = new JLabel();
				this.add(burden, new AnchorConstraint(37, 755, 107, 542, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				burden.setText("Total weight [ kg ]:");
				burden.setPreferredSize(new java.awt.Dimension(171, 30));
				burden.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
				burden.setToolTipText(
						"Player can not carry too much! The amount is influenced by strength level (multiplied by 10).");
			}
			{
				valGold = new JLabel();
				this.add(valGold, new AnchorConstraint(37, 581, 107, 465, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				valGold.setPreferredSize(new java.awt.Dimension(60, 30));
				valGold.setAutoscrolls(true);
				valGold.setFont(new java.awt.Font("Dialog", 1, 14));
			}
			{
				lblGold = new JLabel();
				this.add(lblGold, new AnchorConstraint(38, 629, 129, 409, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				lblGold.setText("Gold:");
				lblGold.setPreferredSize(new java.awt.Dimension(63, 30));
				lblGold.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
				lblGold.setToolTipText("Gold can be used to buy useful equipment");
			}
			{
				valBonus = new JLabel();
				this.add(valBonus, new AnchorConstraint(519, 313, 995, 232, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				valBonus.setPreferredSize(new java.awt.Dimension(84, 30));
			}
			{
				bonus = new JLabel();
				this.add(bonus, new AnchorConstraint(520, 255, 997, 139, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				bonus.setText("Bonus points:");
				bonus.setPreferredSize(new java.awt.Dimension(103, 30));
				bonus.setToolTipText("Point can be distributed among Player stats");
			}
			{
				stats = new JPanel();
				GridBagLayout statsLayout = new GridBagLayout();
				stats.setLayout(statsLayout);
				statsLayout.columnWeights = new double[] { 0.1, 0.1, 0.1 };
				statsLayout.columnWidths = new int[] { 7, 5, 3 };
				statsLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
				statsLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7 };
				GridBagConstraints c = new GridBagConstraints();
				// GridLayout jPanel1Layout = new GridLayout(0, 3);
				this.add(stats, new AnchorConstraint(314, 366, 962, 14, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				stats.setPreferredSize(new java.awt.Dimension(270, 213));

				// stats.setLayout(jPanel1Layout);
				stats.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				{
					name = new JLabel();
					c.gridx = 0;
					c.gridy = 0;
					stats.add(name, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					name.setText("Name/Level");
					name.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
					name.setToolTipText("Level represents Players game progress.");
					valName = new JLabel();
					c.gridx = 1;
					c.gridy = 0;
					stats.add(valName, c);
					valName.setHorizontalTextPosition(SwingConstants.CENTER);
					valName.setHorizontalAlignment(SwingConstants.CENTER);
					valLevel = new JLabel();
					c.gridx = 2;
					c.gridy = 0;
					stats.add(valLevel, c);
				}
				{
					race = new JLabel();
					c.gridx = 0;
					c.gridy = 1;
					stats.add(race, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					race.setText("Race / Class");
					race.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
					race.setPreferredSize(new java.awt.Dimension(92, 23));
					race.setToolTipText("Choosing proper race and class can dramatically change game strategy");
					valRace = new JLabel();
					c.gridx = 1;
					c.gridy = 1;
					stats.add(valRace, c);
					valRace.setHorizontalTextPosition(SwingConstants.CENTER);
					valRace.setHorizontalAlignment(SwingConstants.CENTER);
					valProfession = new JLabel();
					c.gridx = 2;
					c.gridy = 1;
					stats.add(valProfession, c);

				}
				{
					hp = new JLabel();
					c.gridx = 0;
					c.gridy = 2;
					stats.add(hp, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					hp.setText("Hit Points:");
					hp.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
					hp.setToolTipText("Represents Player's health.When it gets to 0 - Player dies");
					valHP = new JLabel();
					c.gridx = 1;
					c.gridy = 2;
					stats.add(valHP, c);
					valHP.setHorizontalTextPosition(SwingConstants.CENTER);
					valHP.setHorizontalAlignment(SwingConstants.CENTER);
					btnHP = new JButton();
					btnHP.setText("Add");
					c.gridx = 2;
					c.gridy = 2;
					stats.add(btnHP, c);
					btnHP.setPreferredSize(new java.awt.Dimension(88, 23));
					btnHP.setFocusable(false);
					btnHP.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnStats(evt);
						}
					});

				}

				{
					mana = new JLabel("Mana:");
					c.gridx = 0;
					c.gridy = 3;
					stats.add(mana, c);
					mana.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
					mana.setToolTipText("Represents magic powers");
					valMana = new JLabel();
					c.gridx = 1;
					c.gridy = 3;
					stats.add(valMana, c);
					valMana.setHorizontalTextPosition(SwingConstants.CENTER);
					valMana.setHorizontalAlignment(SwingConstants.CENTER);
					btnMana = new JButton();
					btnMana.setText("Add");
					c.gridx = 2;
					c.gridy = 3;
					stats.add(btnMana, c);
					btnMana.setPreferredSize(new java.awt.Dimension(88, 23));
					btnMana.setFocusable(false);
					btnMana.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnStats(evt);
						}
					});

				}
				{
					strength = new JLabel();
					c.gridx = 0;
					c.gridy = 4;
					stats.add(strength, c);
					strength.setText("Strength:");
					strength.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
					strength.setToolTipText("Increases making damage");
					valStrength = new JLabel();
					c.gridx = 1;
					c.gridy = 4;
					stats.add(valStrength, c);
					valStrength.setHorizontalTextPosition(SwingConstants.CENTER);
					valStrength.setHorizontalAlignment(SwingConstants.CENTER);
					btnStr = new JButton();
					btnStr.setText("Add");
					c.gridx = 2;
					c.gridy = 4;
					stats.add(btnStr, c);
					btnStr.setPreferredSize(new java.awt.Dimension(88, 23));
					btnStr.setFocusable(false);
					btnStr.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnStats(evt);
						}
					});
				}
				{
					dexterity = new JLabel();
					c.gridx = 0;
					c.gridy = 5;
					stats.add(dexterity, c);
					dexterity.setText("Dexterity:");
					dexterity.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
					dexterity.setToolTipText("Influences ability to succesfully hit and avoid beeing hit");
					valDexterity = new JLabel();
					c.gridx = 1;
					c.gridy = 5;
					stats.add(valDexterity, c);
					valDexterity.setHorizontalTextPosition(SwingConstants.CENTER);
					valDexterity.setHorizontalAlignment(SwingConstants.CENTER);
					btnDex = new JButton();
					c.gridx = 2;
					c.gridy = 5;
					stats.add(btnDex, c);
					btnDex.setText("Add");
					btnDex.setPreferredSize(new java.awt.Dimension(88, 23));
					btnDex.setFocusable(false);
					btnDex.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnStats(evt);
						}
					});

				}
				{
					intelligence = new JLabel();
					c.gridx = 0;
					c.gridy = 6;
					stats.add(intelligence, c);
					intelligence.setText("Intelligence:");
					intelligence.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
					intelligence.setToolTipText("Represents ability to use complicated items and magic");
					valIntelligence = new JLabel();
					c.gridx = 1;
					c.gridy = 6;
					stats.add(valIntelligence, c);
					valIntelligence.setHorizontalTextPosition(SwingConstants.CENTER);
					valIntelligence.setHorizontalAlignment(SwingConstants.CENTER);
					btnInt = new JButton();
					c.gridx = 2;
					c.gridy = 6;
					stats.add(btnInt, c);
					btnInt.setText("Add");
					btnInt.setPreferredSize(new java.awt.Dimension(88, 23));
					btnInt.setFocusable(false);
					btnInt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnStats(evt);
						}
					});

				}
				{
					perception = new JLabel();
					c.gridx = 0;
					c.gridy = 7;
					stats.add(perception, c);
					perception.setText("Perception:");
					perception.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
					perception.setToolTipText("Increases a seeing and hearing range");
					valPerception = new JLabel();
					c.gridx = 1;
					c.gridy = 7;
					stats.add(valPerception, c);
					valPerception.setHorizontalTextPosition(SwingConstants.CENTER);
					valPerception.setHorizontalAlignment(SwingConstants.CENTER);
					btnPer = new JButton();
					c.gridx = 2;
					c.gridy = 7;
					stats.add(btnPer, c);
					btnPer.setText("Add");
					btnPer.setPreferredSize(new java.awt.Dimension(88, 23));
					btnPer.setFocusable(false);
					btnPer.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnStats(evt);
						}
					});
				}
				{
					speed = new JLabel();
					c.gridx = 0;
					c.gridy = 8;
					stats.add(speed, c);
					speed.setText("Speed:");
					speed.setFont(new java.awt.Font("Monotype Corsiva", 1, 18));
					speed.setToolTipText("Decides how often can Player make action (comparing to other creatures).");
					valSpeed = new JLabel();
					c.gridx = 1;
					c.gridy = 8;
					stats.add(valSpeed, c);
					valSpeed.setHorizontalTextPosition(SwingConstants.CENTER);
					valSpeed.setHorizontalAlignment(SwingConstants.CENTER);
					btnSpeed = new JButton();
					c.gridx = 2;
					c.gridy = 8;
					stats.add(btnSpeed, c);
					btnSpeed.setText("Add");
					btnSpeed.setPreferredSize(new java.awt.Dimension(88, 23));
					btnSpeed.setFocusable(false);
					btnSpeed.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnStats(evt);
						}
					});

				}

			}
			{
				image = new JLabel();
				this.add(image, new AnchorConstraint(25, 368, 567, 14, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				image.setIcon(createImageIcon("images/person.JPG"));
				image.setPreferredSize(new java.awt.Dimension(273, 287));
				image.setToolTipText("Click a body part to equip selected item");
				{
					btnMissileWeapon = new JButton();
					AnchorLayout btnMissileWeaponLayout = new AnchorLayout();
					btnMissileWeapon.setLayout(btnMissileWeaponLayout);
					image.add(btnMissileWeapon);
					btnMissileWeapon.setMargin(new Insets(0, 0, 0, 0));
					btnMissileWeapon.setBounds(207, 25, 44, 47);
					btnMissileWeapon.setIgnoreRepaint(true);
					btnMissileWeapon.setContentAreaFilled(false);
					btnMissileWeapon.setFocusable(false);
					btnMissileWeapon.setToolTipText("Place for missile weapon");
					btnMissileWeapon.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnHead = new JButton();
					AnchorLayout btnHeadLayout = new AnchorLayout();
					btnHead.setLayout(btnHeadLayout);
					image.add(btnHead);
					btnHead.setMargin(new Insets(0, 0, 0, 0));
					btnHead.setBounds(119, 2, 44, 47);
					btnHead.setIgnoreRepaint(true);
					btnHead.setContentAreaFilled(false);
					btnHead.setActionCommand("head");
					btnHead.setFocusable(false);
					btnHead.setToolTipText("Place for head cover");
					btnHead.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnRight = new JButton();
					AnchorLayout btnRightLayout = new AnchorLayout();
					btnRight.setLayout(btnRightLayout);
					image.add(btnRight);
					btnRight.setMargin(new Insets(0, 0, 0, 0));
					btnRight.setBounds(8, 17, 44, 47);
					btnRight.setIgnoreRepaint(true);
					btnRight.setContentAreaFilled(false);
					btnRight.setFocusable(false);
					btnRight.setToolTipText("Place for hand used items");
					btnRight.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnLeft = new JButton();
					AnchorLayout btnLeftLayout = new AnchorLayout();
					btnLeft.setLayout(btnLeftLayout);
					image.add(btnLeft);
					btnLeft.setMargin(new Insets(0, 0, 0, 0));
					btnLeft.setBounds(170, 133, 44, 47);
					btnLeft.setIgnoreRepaint(true);
					btnLeft.setContentAreaFilled(false);
					btnLeft.setFocusable(false);
					btnLeft.setToolTipText("Place for hand used items");
					btnLeft.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnGloves = new JButton();
					AnchorLayout btnGlovesLayout = new AnchorLayout();
					btnGloves.setLayout(btnGlovesLayout);
					image.add(btnGloves);
					btnGloves.setMargin(new Insets(0, 0, 0, 0));
					btnGloves.setBounds(221, 132, 44, 47);
					btnGloves.setIgnoreRepaint(true);
					btnGloves.setContentAreaFilled(false);
					btnGloves.setFocusable(false);
					btnGloves.setToolTipText("Place for gloves");
					btnGloves.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}

				{
					btnChest = new JButton();
					AnchorLayout btnChestLayout = new AnchorLayout();
					btnChest.setLayout(btnChestLayout);
					image.add(btnChest);
					btnChest.setMargin(new Insets(0, 0, 0, 0));
					btnChest.setBounds(119, 54, 44, 47);
					btnChest.setIgnoreRepaint(true);
					btnChest.setContentAreaFilled(false);
					btnChest.setFocusable(false);
					btnChest.setToolTipText("Place for chest cover");
					btnChest.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnShoes = new JButton();
					AnchorLayout btnShoesLayout = new AnchorLayout();
					btnShoes.setLayout(btnShoesLayout);
					image.add(btnShoes);
					btnShoes.setMargin(new Insets(0, 0, 0, 0));
					btnShoes.setBounds(124, 239, 44, 47);
					btnShoes.setIgnoreRepaint(true);
					btnShoes.setContentAreaFilled(false);
					btnShoes.setFocusable(false);
					btnShoes.setToolTipText("Place for shoes");
					btnShoes.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnBelt = new JButton();
					AnchorLayout btnBeltLayout = new AnchorLayout();
					btnBelt.setLayout(btnBeltLayout);
					image.add(btnBelt);
					btnBelt.setMargin(new Insets(0, 0, 0, 0));
					btnBelt.setBounds(120, 105, 44, 47);
					btnBelt.setIgnoreRepaint(true);
					btnBelt.setContentAreaFilled(false);
					btnBelt.setFocusable(false);
					btnBelt.setToolTipText("Place for belt");
					btnBelt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnBack = new JButton();
					AnchorLayout btnBackLayout = new AnchorLayout();
					btnBack.setLayout(btnBackLayout);
					image.add(btnBack);
					btnBack.setMargin(new Insets(0, 0, 0, 0));
					btnBack.setBounds(207, 78, 44, 47);
					btnBack.setIgnoreRepaint(true);
					btnBack.setContentAreaFilled(false);
					btnBack.setFocusable(false);
					btnBack.setToolTipText("Place for back cover");
					btnBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnShoulders = new JButton();
					AnchorLayout btnShouldersLayout = new AnchorLayout();
					btnShoulders.setLayout(btnShouldersLayout);
					image.add(btnShoulders);
					btnShoulders.setMargin(new Insets(0, 0, 0, 0));
					btnShoulders.setBounds(59, 17, 44, 47);
					btnShoulders.setIgnoreRepaint(true);
					btnShoulders.setContentAreaFilled(false);
					btnShoulders.setFocusable(false);
					btnShoulders.setToolTipText("Place for a shoulder/arm cover");
					btnShoulders.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnRing1 = new JButton();
					AnchorLayout btnRing1Layout = new AnchorLayout();
					btnRing1.setLayout(btnRing1Layout);
					image.add(btnRing1);
					btnRing1.setMargin(new Insets(0, 0, 0, 0));
					btnRing1.setBounds(8, 82, 44, 47);
					btnRing1.setIgnoreRepaint(true);
					btnRing1.setContentAreaFilled(false);
					btnRing1.setFocusable(false);
					btnRing1.setToolTipText("Place for a ring");
					btnRing1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnRing2 = new JButton();
					AnchorLayout btnRing2Layout = new AnchorLayout();
					btnRing2.setLayout(btnRing2Layout);
					image.add(btnRing2);
					btnRing2.setMargin(new Insets(0, 0, 0, 0));
					btnRing2.setBounds(18, 134, 44, 47);
					btnRing2.setIgnoreRepaint(true);
					btnRing2.setContentAreaFilled(false);
					btnRing2.setFocusable(false);
					btnRing2.setToolTipText("Place for a ring");
					btnRing2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnRing3 = new JButton();
					AnchorLayout btnRing3Layout = new AnchorLayout();
					btnRing3.setLayout(btnRing3Layout);
					image.add(btnRing3);
					btnRing3.setMargin(new Insets(0, 0, 0, 0));
					btnRing3.setBounds(26, 186, 44, 47);
					btnRing3.setIgnoreRepaint(true);
					btnRing3.setContentAreaFilled(false);
					btnRing3.setFocusable(false);
					btnRing3.setToolTipText("Place for a ring");
					btnRing3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
				{
					btnRing4 = new JButton();
					AnchorLayout btnRing4Layout = new AnchorLayout();
					btnRing4.setLayout(btnRing4Layout);
					image.add(btnRing4);
					btnRing4.setMargin(new Insets(0, 0, 0, 0));
					btnRing4.setBounds(39, 238, 44, 47);
					btnRing4.setIgnoreRepaint(true);
					btnRing4.setContentAreaFilled(false);
					btnRing4.setFocusable(false);
					btnRing4.setToolTipText("Place for a ring");
					btnRing4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnEquip(evt);
						}
					});
				}
			}
			{
				title = new JLabel();
				this.add(title, new AnchorConstraint(-10, 319, 59, 79, AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				title.setText("Your character");
				title.setFont(new java.awt.Font("Monotype Corsiva", 3, 28));
				title.setPreferredSize(new java.awt.Dimension(192, 42));
			}
			{
				jLabel1 = new JLabel();
				{
					String[] columnNames = { "Name", "Type", "Weight" };
					inventoryModel = new DefaultTableModel(columnNames, 0);
					inventory = new JTable(inventoryModel);

					TableColumn column = inventory.getColumnModel().getColumn(0);
					column.setPreferredWidth(240);

					inventory.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					// inventory.setPreferredSize(new java.awt.Dimension(425,
					// 228));
					inventory.setFont(new java.awt.Font("Bookman Old Style", 0, 16));
					inventory.setBorder(BorderFactory.createTitledBorder(""));
					inventory.setModel(inventoryModel);
					inventory.setDragEnabled(true);
					inventory.setFocusable(false);
					inventory.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent evt) {
							inventoryMouseClicked(evt);
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane(inventory);
					this.add(jScrollPane1, new AnchorConstraint(72, 946, 522, 395, AnchorConstraint.ANCHOR_ABS,
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(384, 246));
					jScrollPane1.setAutoscrolls(true);
				}
				this.add(jLabel1, new AnchorConstraint(-7, 828, 85, 465, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				jLabel1.setText("Your inventory");
				jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 3, 28));
				jLabel1.setPreferredSize(new java.awt.Dimension(192, 42));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// updates player stats
	public void updateStats() {
		valName.setText("" + player.name);
		valRace.setText("" + player.race);
		valProfession.setText("" + player.profession);
		valHP.setText("" + player.health);
		// valExp.setText(""+player.experience);
		valLevel.setText("" + player.level);
		valWeight.setText("" + player.getLoad());
		valMana.setText("" + player.mana);
		valStrength.setText("" + player.strength);
		valDexterity.setText("" + player.dexterity);
		valSpeed.setText("" + player.speed);
		valIntelligence.setText("" + player.intelligence);
		valGold.setText("" + player.money);
		valPerception.setText("" + player.perception);
		valBonus.setText("" + player.bonusStats);

		// enables increase stat buttons if any free points
		if (player.bonusStats < 1) {
			btnHP.setEnabled(false);
			btnMana.setEnabled(false);
			btnStr.setEnabled(false);
			btnDex.setEnabled(false);
			btnInt.setEnabled(false);
			btnPer.setEnabled(false);
			btnSpeed.setEnabled(false);
		} else {
			btnHP.setEnabled(true);
			btnMana.setEnabled(true);
			btnStr.setEnabled(true);
			btnDex.setEnabled(true);
			btnInt.setEnabled(true);
			btnPer.setEnabled(true);
			btnSpeed.setEnabled(true);
		}
	}

	// handles adding clicking at one of increase stat buttons
	// adds one to proper feature and decreases amount of free points
	private void btnStats(ActionEvent evt) {
		if (evt.getSource() == btnHP) {
			player.health++;
			player.bonusStats--;
		}
		if (evt.getSource() == btnMana) {
			player.mana++;
			player.bonusStats--;
		}
		if (evt.getSource() == btnStr) {
			player.strength++;
			player.bonusStats--;
		}
		if (evt.getSource() == btnDex) {
			player.dexterity++;
			player.bonusStats--;
		}
		if (evt.getSource() == btnInt) {
			player.intelligence++;
			player.bonusStats--;
		}
		if (evt.getSource() == btnSpeed) {
			player.speed++;
			player.bonusStats--;
		}
		if (evt.getSource() == btnPer) {
			player.perception++;
			player.bonusStats--;
		}
		updateStats();
	}

	// tries to equip item
	private void btnEquip(ActionEvent evt) {
		// checks if any item chosen
		if (chosenItem == null) {
			showText("You must choose an item!");
			return;
		}
		// try to equip item on a given place
		if (evt.getSource() == btnHead)
			player.head = equip("head", player.head, (JButton) evt.getSource());
		else if (evt.getSource() == btnChest)
			player.chest = equip("chest", player.chest, (JButton) evt.getSource());
		else if (evt.getSource() == btnBack)
			player.back = equip("back", player.back, (JButton) evt.getSource());
		else if (evt.getSource() == btnBelt)
			player.belt = equip("belt", player.belt, (JButton) evt.getSource());
		else if (evt.getSource() == btnShoes)
			player.shoes = equip("shoes", player.shoes, (JButton) evt.getSource());
		else if (evt.getSource() == btnMissileWeapon)
			player.ranged = equip("ranged", player.ranged, (JButton) evt.getSource());
		else if (evt.getSource() == btnGloves)
			player.gloves = equip("gloves", player.gloves, (JButton) evt.getSource());
		else if (evt.getSource() == btnShoulders)
			player.shoulders = equip("shoulders", player.shoulders, (JButton) evt.getSource());
		else if (evt.getSource() == btnLeft) {
			// check if the second hand holds an item
			if (player.rightHand != chosenItem)
				player.leftHand = equip("hand", player.leftHand, (JButton) evt.getSource());
			else
				showText("That item is already equipped - right hand");
		} else if (evt.getSource() == btnRight) {
			if (player.leftHand != chosenItem)
				player.rightHand = equip("hand", player.rightHand, (JButton) evt.getSource());
			else
				showText("That item is already equipped - left hand");
			// checks if any other ring slot contains the new item
		} else if (evt.getSource() == btnRing1) {
			if ((player.ring2 != chosenItem) && (player.ring3 != chosenItem) && (player.ring4 != chosenItem))
				player.ring1 = equip("ring", player.ring1, (JButton) evt.getSource());
			else
				showText("That item is already equipped!");
		} else if (evt.getSource() == btnRing2) {
			if ((player.ring1 != chosenItem) && (player.ring3 != chosenItem) && (player.ring4 != chosenItem))
				player.ring2 = equip("ring", player.ring2, (JButton) evt.getSource());
			else
				showText("That item is already equipped!");
		} else if (evt.getSource() == btnRing3) {
			if ((player.ring2 != chosenItem) && (player.ring1 != chosenItem) && (player.ring4 != chosenItem))
				player.ring3 = equip("ring", player.ring3, (JButton) evt.getSource());
			else
				showText("That item is already equipped!");
		} else if (evt.getSource() == btnRing4) {
			if ((player.ring2 != chosenItem) && (player.ring3 != chosenItem) && (player.ring1 != chosenItem))
				player.ring4 = equip("ring", player.ring4, (JButton) evt.getSource());
			else
				showText("That item is already equipped!");
		}
		updateStats();
	}

	// try to equip an item in the specified place
	Item equip(String type, Item place, JButton button) {
		// check place is free to equip
		if (place == null) {
			// check if item type matches
			if (!chosenItem.type.equals(type)) {
				showText("Wrong item type!");
				return null;
			} else {
				// check all minimal requirements
				if (!checkRequirements())
					return null;

				button.setIcon(chosenItem.image);
				System.out.println("equipped");
				// modify all Player stats
				player.health += chosenItem.health;
				player.mana += chosenItem.mana;
				player.strength += chosenItem.strength;
				player.dexterity += chosenItem.dexterity;
				player.perception += chosenItem.perception;
				player.speed += chosenItem.speed;
				return chosenItem;
			}
		} else {
			// if clicking on an equipped place, dis-equip item
			System.out.println("dis-equipped");
			button.setIcon(null);

			// subtract Players stats
			player.health -= chosenItem.health;
			player.mana -= chosenItem.mana;
			player.strength -= chosenItem.strength;
			player.dexterity -= chosenItem.dexterity;
			player.perception -= chosenItem.perception;
			player.speed -= chosenItem.speed;
			return null;
		}
	}

	// check if Player matches all requirements
	boolean checkRequirements() {
		if (chosenItem.minLevel > player.level) {
			showText("Your level is too small!");
			return false;
		}
		if (chosenItem.minStrength > player.strength) {
			showText("Your strength is too small!");
			return false;
		}
		if (chosenItem.minDexterity > player.dexterity) {
			showText("Your dexterity is too small!");
			return false;
		}
		if (chosenItem.minIntelligence > player.intelligence) {
			showText("Your intelligence is too small!");
			return false;
		}
		if (chosenItem.minPerception > player.perception) {
			showText("Your perception is too small!");
			return false;
		}
		if (chosenItem.minSpeed > player.speed) {
			showText("Your speed is too small!");
			return false;
		}

		// if everything matches
		return true;
	}

	// use way of using items according
	void use(String type) {
		// check place is free to equip
		if (chosenItem == null) {
			showText("You must choose an item!");
			return;
		}

		// check if item type matches
		if (!chosenItem.type.equals(type)) {
			showText("Wrong item type! Only " + type + " is allowed");
			return;
		}

		// check all minimal requirements
		if (!checkRequirements())
			return;

		// modify all Player stats
		player.health += chosenItem.health;
		player.mana += chosenItem.mana;
		player.strength += chosenItem.strength;
		player.dexterity += chosenItem.dexterity;
		player.perception += chosenItem.perception;
		player.speed += chosenItem.speed;

		// remove item
		inventoryModel.removeRow(player.items.indexOf(chosenItem));
		player.items.remove(chosenItem);
		chosenItem = null;
		itemDetails.clearDetails();
		updateStats();
	}

	// show information window
	void showText(String info) {
		JOptionPane.showMessageDialog(null, info);
	}

	// set chosen item and display info
	private void inventoryMouseClicked(MouseEvent evt) {
		int row = evt.getY() / inventory.getRowHeight();
		chosenItem = (Item) player.items.elementAt(row);
		itemDetails.displayItem(chosenItem);
	}

	// drop an item
	private void btnDropActionPerformed(ActionEvent evt) {
		// check if any item chosen
		if (chosenItem != null) {
			// check if unequipped
			if ((player.head == chosenItem) || (player.chest == chosenItem) || (player.back == chosenItem)
					|| (player.back == chosenItem) || (player.gloves == chosenItem) || (player.shoulders == chosenItem)
					|| (player.ranged == chosenItem) || (player.shoes == chosenItem) || (player.ring1 == chosenItem)
					|| (player.ring2 == chosenItem) || (player.ring3 == chosenItem) || (player.ring4 == chosenItem))
				showText("You must unequip the item");
			else {
				// finalise dropping
				chosenItem.locationx = player.locationx;
				chosenItem.locationy = player.locationy;
				logic.getMap().items.add(chosenItem);
				inventoryModel.removeRow(player.items.indexOf(chosenItem));
				player.items.remove(chosenItem);
				chosenItem = null;
				itemDetails.clearDetails();
			}
		} else
			showText("You must select an item!");
	}

	// update all tables
	void updateTable() {
		Object[] rowData;

		// clear table
		for (int i = inventoryModel.getRowCount() - 1; i >= 0; i--) {
			inventoryModel.removeRow(i);
			inventory.revalidate();
		}
		// fill the table again
		rowData = new Object[inventoryModel.getColumnCount()];
		for (int t = 0; t < player.items.size(); t++) {
			Item item = (Item) player.items.elementAt(t);
			rowData[0] = item.name;
			rowData[1] = item.type;
			rowData[2] = "" + item.weight;
			inventoryModel.addRow(rowData);
		}
		updateStats();
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
