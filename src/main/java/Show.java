
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

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
// creates a JFrame containing JTabbedPane and all Player views
public class Show extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	JTabbedPane tabbedPane;
	Creature player;
	Logic logic;
	Display display;
	JPanel quitPanel;
	Stats stats;
	Inventory inventory;

	// constructor
	public Show(Logic logic) {
		super();
		this.logic = logic;
		player = (Creature) logic.creatures.elementAt(0);

		JFrame.setDefaultLookAndFeelDecorated(true);

		display = new Display(logic);
		logic.addMapDisplay(display);

		quitPanel = new QuitPanel(logic);
		stats = new Stats(logic);
		logic.addCharDisplay(stats);
		inventory = new Inventory(logic);

		initGUI();
	}

	// initialization
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				tabbedPane = new JTabbedPane();
				tabbedPane.addKeyListener(logic);

				tabbedPane.addTab("Intro", null, new Intro(), null);
				tabbedPane.setSelectedIndex(0);
				tabbedPane.addTab("Map", null, display, null);
				tabbedPane.addTab("Stats", null, stats, null);
				tabbedPane.addTab("Inventory", null, inventory, null);
				tabbedPane.addTab("Save/reload", null, quitPanel, null);
				this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
				// tabbedPane.addMouseListener(this);
			}
			pack();
			setSize(800, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void showMessage(String text) {
		System.out.println("it gets here! " + text);
		display.showMessage(text);
	}

	void updateStats() {
		display.updateStats();
	}

	void changeMap() {
		display.changeMap();
	}

	void updateMap() {
		display.updateMap();
	}

	void showText(String text) {
		display.showText(text);
	}

	int showWarning(String text) {
		return display.showWarning(text);
	}
}
