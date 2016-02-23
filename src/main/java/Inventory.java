import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

// class to simplify item picking/dropping
public class Inventory extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblPickedInventory, lblLyingInventory;
	private JButton btnDropAll, btnDrop, btnPickAll, btnPick;
	private JScrollPane jScrollPane1, jScrollPane2;
	private DefaultTableModel inventoryModel1, inventoryModel2;
	private JTable inventory1, inventory2;
	private Vector<Item> lyingItems;
	private Item chosenItem = null;
	private Creature player;
	private Logic logic;
	private ItemDetails itemDetails;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	// constructor
	public Inventory(Logic logic) {
		super();
		this.logic = logic;
		itemDetails = new ItemDetails();
		player = logic.creatures.elementAt(0);
		initGUI();
	}

	// it is just too easy to explain how to initialise Swing elements, even
	// more
	// that is was done by Jigloo plugin, only more complicated elements are
	// commented
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentShown(ComponentEvent evt) {
					System.out.println("this.componentShown, event=" + evt);
					updateTables();
				}
			});
			{
				lblPickedInventory = new JLabel();
				{
					// initialise JTable with a TableModel, only three columns
					// are visible
					String[] columnNames = { "Name", "Type", "Weight" };
					inventoryModel1 = new DefaultTableModel(columnNames, 0);
					inventory1 = new JTable(inventoryModel1);

					TableColumn column = inventory1.getColumnModel().getColumn(0);
					column.setPreferredWidth(240);

					inventory1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					inventory1.setFont(new java.awt.Font("Bookman Old Style", 0, 16));
					inventory1.setBorder(BorderFactory.createTitledBorder(""));
					inventory1.setModel(inventoryModel1);
					inventory1.setFocusable(false);
					inventory1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent evt) {
							inventoryMouseClicked(evt);
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane(inventory1);
					this.add(jScrollPane1, new AnchorConstraint(55, 446, 522, 11, AnchorConstraint.ANCHOR_ABS,
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(377, 459));
					jScrollPane1.setAutoscrolls(true);
				}
				{
					// button for dropping all picked items
					btnDropAll = new JButton();
					btnDropAll.setMargin(new Insets(0, 0, 0, 0));
					this.add(btnDropAll, new AnchorConstraint(461, 696, 852, 486, AnchorConstraint.ANCHOR_ABS,
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
					btnDropAll.setText("Drop all");
					btnDropAll.setPreferredSize(new java.awt.Dimension(71, 50));
					btnDropAll.setFocusable(false);
					btnDropAll.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent evt) {
							System.out.println("btnDropAll.mouseClicked, event=" + evt);
							// drop until all items are dropped
							while (player.items.size() > 0) {
								chosenItem = (Item) player.items.elementAt(0);
								dropItem(chosenItem);
							}
						}
					});
				}
				{
					// drop a single item
					btnDrop = new JButton();
					btnDrop.setMargin(new Insets(0, 0, 0, 0));
					this.add(btnDrop, new AnchorConstraint(461, 593, 852, 404, AnchorConstraint.ANCHOR_ABS,
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
					btnDrop.setText("Drop item");
					btnDrop.setPreferredSize(new java.awt.Dimension(73, 50));
					btnDrop.setFocusable(false);
					btnDrop.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent evt) {
							System.out.println("btnDrop.mouseClicked, event=" + evt);
							// only if an item is chosen
							if (chosenItem != null)
								dropItem(chosenItem);
							else
								showText("You must choose an item!");
						}
					});
				}
				{
					// button to pick all lying items
					btnPickAll = new JButton();
					btnPickAll.setMargin(new Insets(0, 0, 0, 0));
					this.add(btnPickAll, new AnchorConstraint(461, 968, 852, 704, AnchorConstraint.ANCHOR_ABS,
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
					btnPickAll.setText("Pick all");
					btnPickAll.setPreferredSize(new java.awt.Dimension(70, 50));
					btnPickAll.setFocusable(false);
					btnPickAll.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent evt) {
							System.out.println("btnPickAll.mouseClicked, event=" + evt);
							// check all map items for desireder location
							for (int i = 0; i < logic.getMap().items.size();) {
								chosenItem = (Item) logic.getMap().items.elementAt(i);
								if ((chosenItem.locationx == player.locationx)
										&& (chosenItem.locationy == player.locationy)) {
									// stop picking if eny error
									if (!pickItem(chosenItem))
										break;
								} else {
									i++;
									chosenItem = null;
								}
							}
						}
					});
				}
				{
					// pick only chosen item
					btnPick = new JButton();
					btnPick.setMargin(new Insets(0, 0, 0, 0));
					this.add(btnPick, new AnchorConstraint(461, 868, 852, 624, AnchorConstraint.ANCHOR_ABS,
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
					btnPick.setText("Pick item");
					btnPick.setPreferredSize(new java.awt.Dimension(70, 50));
					btnPick.setFocusable(false);
					btnPick.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent evt) {
							System.out.println("btnPick.mouseClicked, event=" + evt);
							// only if an item is chosen
							if (chosenItem != null)
								pickItem(chosenItem);
							else
								showText("You have to choose an item!");
						}
					});
				}

				this.add(lblPickedInventory, new AnchorConstraint(9, 828, 85, 117, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				lblPickedInventory.setText("Your inventory");
				lblPickedInventory.setFont(new java.awt.Font("Monotype Corsiva", 3, 28));
				lblPickedInventory.setPreferredSize(new java.awt.Dimension(192, 42));
			}
			{
				lblLyingInventory = new JLabel();
				{
					// initialise lying item table with a Table Model
					String[] columnNames = { "Name", "Type", "Weight" };
					inventoryModel2 = new DefaultTableModel(columnNames, 0);
					inventory2 = new JTable(inventoryModel2);

					TableColumn column = inventory2.getColumnModel().getColumn(0);
					column.setPreferredWidth(240);

					inventory2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					inventory2.setFont(new java.awt.Font("Bookman Old Style", 0, 16));
					inventory2.setBorder(BorderFactory.createTitledBorder(""));
					inventory2.setModel(inventoryModel2);
					inventory2.setDragEnabled(true);
					inventory2.setFocusable(false);
					inventory2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent evt) {
							lyingMouseClicked(evt);
						}
					});
				}
				{
					jScrollPane2 = new JScrollPane(inventory2);
					this.add(jScrollPane2, new AnchorConstraint(55, 946, 522, 402, AnchorConstraint.ANCHOR_ABS,
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
					jScrollPane2.setPreferredSize(new java.awt.Dimension(377, 169));
					jScrollPane2.setAutoscrolls(true);
				}
				this.add(lblLyingInventory, new AnchorConstraint(10, 828, 85, 497, AnchorConstraint.ANCHOR_ABS,
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				lblLyingInventory.setText("Lying inventory");
				lblLyingInventory.setFont(new java.awt.Font("Monotype Corsiva", 3, 28));
				lblLyingInventory.setPreferredSize(new java.awt.Dimension(192, 42));

				{
					// set window for item details
					itemDetails = new ItemDetails();
					this.add(itemDetails, new AnchorConstraint(236, 940, 869, 403, AnchorConstraint.ANCHOR_ABS,
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// for displaying information window
	void showText(String info) {
		JOptionPane.showMessageDialog(null, info);
	}

	// pick chosen item from picked items
	private void inventoryMouseClicked(MouseEvent evt) {
		JTable source = (JTable) evt.getSource();
		int row = evt.getY() / source.getRowHeight();
		chosenItem = (Item) player.items.elementAt(row);
		itemDetails.displayItem(chosenItem);
	}

	// pick chosen item from lying items
	private void lyingMouseClicked(MouseEvent evt) {
		JTable source = (JTable) evt.getSource();
		int row = evt.getY() / source.getRowHeight();
		chosenItem = lyingItems.elementAt(row);
		itemDetails.displayItem(chosenItem);
	}

	// update information
	void updateTables() {
		Object[] rowData;

		// clearing all rows
		for (int i = inventoryModel1.getRowCount() - 1; i >= 0; i--) {
			inventoryModel1.removeRow(i);
			inventory1.revalidate();
		}
		// add row by row to table model from player items
		rowData = new Object[inventoryModel1.getColumnCount()];
		for (int t = 0; t < player.items.size(); t++) {
			Item item = (Item) player.items.elementAt(t);
			rowData[0] = item.name;
			rowData[1] = item.type;
			rowData[2] = "" + item.weight;
			inventoryModel1.addRow(rowData);
		}

		// clearing all rows
		for (int i = inventoryModel2.getRowCount() - 1; i >= 0; i--) {
			inventoryModel2.removeRow(i);
			inventory2.revalidate();
		}
		// add row by row from map items
		lyingItems = new Vector<Item>();
		rowData = new Object[inventoryModel2.getColumnCount()];
		for (int t = 0; t < logic.getMap().items.size(); t++) {
			Item item = (Item) logic.getMap().items.elementAt(t);
			if ((item.locationx == player.locationx) && (item.locationy == player.locationy)) {
				lyingItems.add(item);
				rowData[0] = item.name;
				rowData[1] = item.type;
				rowData[2] = "" + item.weight;
				inventoryModel2.addRow(rowData);
			}
		}
		chosenItem = null;
		itemDetails.displayItem(chosenItem);
	}

	// drop chosen item
	void dropItem(Item item) {
		if (player.items.contains(item)) {
			// set new item position
			item.locationx = player.locationx;
			item.locationy = player.locationy;
			logic.getMap().items.add(item);
			player.items.remove(item);

			// get players field
			Field tempField = logic.getMap().getField(player.locationx, player.locationy);
			// if dropped in a shop, get money
			if (tempField.name.equals("shop")) {
				player.money += item.price;
				logic.display.updateStats();
			}

			updateTables();
			// if not in players inventory, it it in maps vector
		} else
			showText("This item is already lying!");
	}

	// pick an item, returns true if successful
	boolean pickItem(Item item) {
		Field tempField = logic.getMap().getField(player.locationx, player.locationy);
		// if money -> increase players money
		if (item.name.equals("money")) {
			player.money += item.price;
			logic.getMap().items.remove(item);
			updateTables();
		}
		// pick if no in shop or if have available money
		else if ((!tempField.name.equals("shop")) || (item.price <= player.money)) {
			// only if can carry more
			if (player.getFreeCapacity() < item.weight) {
				showText(" You cannot carry anymore!");
				return false;
			}
			// finalize picking
			player.items.add(item);
			logic.getMap().items.remove(item);
			updateTables();
			// pay if in the shop
			if (tempField.name.equals("shop")) {
				player.money -= item.price;
				logic.display.updateStats();
			}
		} else {
			// if not enough funds to buy
			showText("Not enough money to buy: " + item.name);
			return false;
		}
		return true;

	}
}
