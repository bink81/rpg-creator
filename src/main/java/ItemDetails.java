
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

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

// this simple class shows detailed item description, it is just too easy to
// comment how different Swing elements are being added
public class ItemDetails extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollItemlDescription;
	private JTextArea itemDescription;
	private JLabel valItemDamage, valItemDodge, valItemArmour, valItemToHit;
	private JLabel valItemPrice, lblItemPrice, lblItemArmour, valItemType;
	private JLabel lblItemDescription, valItemImage, lblItemImage, lblItemDamage;
	private JLabel lblItemType, valItemWeight, lblItemWeight, valItemName, lblItemName;
	private JLabel lblItemDodge, lblItemToHit, lblDetails;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	// constructor
	public ItemDetails() {
		super();
		initGUI();
	}

	// initialisation
	private void initGUI() {
		try {
			{
				// set size
				this.setPreferredSize(new java.awt.Dimension(377, 202));
				// set border
				this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				this.setLayout(null);
				{
					lblDetails = new JLabel();
					this.add(lblDetails);
					lblDetails.setFont(new java.awt.Font("Monotype Corsiva", 3, 24));

					lblDetails.setText("Details");
					lblDetails.setBounds(151, 6, 116, 28);
				}
				{
					lblItemName = new JLabel();
					this.add(lblItemName);
					lblItemName.setText("Name:");
					lblItemName.setPreferredSize(new java.awt.Dimension(36, 16));
					lblItemName.setBounds(17, 40, 347, 16);
				}
				{
					valItemName = new JLabel();
					this.add(valItemName);
					valItemName.setBounds(70, 39, 297, 16);
				}
				{
					lblItemWeight = new JLabel();
					this.add(lblItemWeight);
					lblItemWeight.setText("Weight:");
					lblItemWeight.setBounds(16, 60, 106, 16);
					lblItemWeight.setToolTipText("How heavy it is");
				}
				{
					valItemWeight = new JLabel();
					this.add(valItemWeight);
					valItemWeight.setBounds(70, 60, 114, 16);
				}
				{
					lblItemType = new JLabel();
					this.add(lblItemType);
					lblItemType.setText("Type:");
					lblItemType.setBounds(190, 118, 59, 16);
					lblItemType.setToolTipText("Shows item's purpose");
				}
				{
					valItemType = new JLabel();
					this.add(valItemType);
					valItemType.setBounds(245, 117, 119, 16);
				}
				{
					lblItemDamage = new JLabel();
					this.add(lblItemDamage);
					lblItemDamage.setText("Damage:");
					lblItemDamage.setBounds(17, 100, 109, 16);
					lblItemDamage.setToolTipText("Damage ability modyfier");
				}
				{
					valItemDamage = new JLabel();
					this.add(valItemDamage);
					valItemDamage.setBounds(70, 100, 112, 16);
				}
				{
					lblItemArmour = new JLabel();
					this.add(lblItemArmour);
					lblItemArmour.setText("Armor:");
					lblItemArmour.setBounds(190, 100, 80, 16);
					lblItemArmour.setToolTipText("Armor ability modyfier");
				}
				{
					valItemArmour = new JLabel();
					this.add(valItemArmour);
					valItemArmour.setBounds(246, 100, 119, 16);
				}
				{
					lblItemToHit = new JLabel();
					this.add(lblItemToHit);
					lblItemToHit.setText("to Hit:");
					lblItemToHit.setBounds(18, 80, 90, 16);
					lblItemToHit.setToolTipText("Aiming ability modyfier");
				}
				{
					valItemToHit = new JLabel();
					this.add(valItemToHit);
					valItemToHit.setBounds(69, 80, 112, 16);
				}
				{
					lblItemPrice = new JLabel();
					this.add(lblItemPrice);
					lblItemPrice.setText("Price:");
					lblItemPrice.setBounds(191, 60, 83, 16);
					lblItemPrice.setToolTipText("How much it is worth (in the shop)");
				}
				{
					valItemPrice = new JLabel();
					this.add(valItemPrice);
					valItemPrice.setBounds(236, 60, 136, 16);
				}
				{
					lblItemDescription = new JLabel();
					this.add(lblItemDescription);
					lblItemDescription.setText("Description:");
					lblItemDescription.setBounds(83, 119, 107, 16);
				}
				{
					itemDescription = new JTextArea();
					itemDescription.setBackground(new java.awt.Color(221, 225, 223));
					itemDescription.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
					itemDescription.setLineWrap(true);
					itemDescription.setFocusable(false);
					itemDescription.setToolTipText("All detailed item information");
				}
				{
					scrollItemlDescription = new JScrollPane(itemDescription);
					this.add(scrollItemlDescription);
					scrollItemlDescription.setBounds(78, 137, 291, 61);
					scrollItemlDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				}
				{
					lblItemImage = new JLabel();
					this.add(lblItemImage);
					lblItemImage.setBounds(18, 119, 53, 14);
					lblItemImage.setText("Image:");
				}
				{
					valItemImage = new JLabel();
					this.add(valItemImage);
					valItemImage.setBounds(14, 144, 50, 50);
				}
				{
					lblItemDodge = new JLabel();
					this.add(lblItemDodge);
					lblItemDodge.setText("Dodge:");
					lblItemDodge.setBounds(190, 80, 82, 16);
					lblItemDodge.setToolTipText("Dodge ability modyfier");
				}
				{
					valItemDodge = new JLabel();
					this.add(valItemDodge);
					valItemDodge.setBounds(246, 80, 119, 16);
				}
			}
			setPreferredSize(new Dimension(377, 202));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// display detailed information about the passed item, or clear fields if no
	// item
	void displayItem(Item chosenItem) {
		if (chosenItem == null) {
			clearDetails();
			return;
		}
		valItemName.setText(chosenItem.name);
		valItemType.setText(chosenItem.type);
		valItemWeight.setText("" + chosenItem.weight);
		valItemPrice.setText("" + chosenItem.price);
		valItemDamage.setText(chosenItem.damage);
		valItemToHit.setText(chosenItem.toHit);
		valItemArmour.setText(chosenItem.armor);
		valItemDodge.setText(chosenItem.dodge);
		valItemImage.setIcon(chosenItem.image);

		itemDescription.setText(chosenItem.description);
		// Display only those elements which are active
		if (chosenItem.health != 0)
			itemDescription.append("\nHealth change:" + chosenItem.health);
		if (chosenItem.mana != 0)
			itemDescription.append("\nMana change:" + chosenItem.mana);
		if (chosenItem.strength != 0)
			itemDescription.append("\nStrength change:" + chosenItem.strength);
		if (chosenItem.dexterity != 0)
			itemDescription.append("\nDexterity change:" + chosenItem.dexterity);
		if (chosenItem.perception != 0)
			itemDescription.append("\nPerception change:" + chosenItem.perception);
		if (chosenItem.speed != 0)
			itemDescription.append("\nSpeed change:" + chosenItem.speed);

		if (chosenItem.minLevel > 0)
			itemDescription.append("\nRequired level:" + chosenItem.minLevel);
		if (chosenItem.minStrength > 0)
			itemDescription.append("\nRequired strength:" + chosenItem.minStrength);
		if (chosenItem.minDexterity > 0)
			itemDescription.append("\nRequired dexterity:" + chosenItem.minDexterity);
		if (chosenItem.minIntelligence > 0)
			itemDescription.append("\nRequired intelligence:" + chosenItem.minIntelligence);
		if (chosenItem.minPerception > 0)
			itemDescription.append("\nRequired perception:" + chosenItem.minPerception);
		if (chosenItem.minSpeed > 0)
			itemDescription.append("\nRequired speed:" + chosenItem.minSpeed);

		// Display list of forbidden classes/professions (under development)
		if (chosenItem.forbidden.size() > 0)
			itemDescription.append("\nIt is forbidden for:");
		for (int i = 0; i < chosenItem.forbidden.size(); i++) {
			itemDescription.append(" - " + (String) chosenItem.forbidden.elementAt(i));
		}
	}

	// clear all detail fields
	void clearDetails() {
		valItemName.setText("");
		valItemType.setText("");
		valItemWeight.setText("");
		valItemPrice.setText("");
		valItemDamage.setText("");
		valItemArmour.setText("");
		valItemImage.setIcon(null);
		itemDescription.setText("");
	}
}
