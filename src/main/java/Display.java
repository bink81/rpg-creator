import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

// the class contains main graphic object shown in the 'map' tab 

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
public class Display extends javax.swing.JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	// just Swing objects...
	private JScrollPane scrollMap;
	private JTextArea textMessage;

	// map canvas object
	MapDisplayer displaymap;

	// connection to game data
	Logic logic;

	// message panel (on top)
	JPanel messagePanel;

	// statistics panel (on the bottom)
	StatsPanel statPanel;

	// size of image (height and width)
	final int FIELD_SIZE = 50;

	// constructor
	public Display(Logic logic) {
		super();
		this.logic = logic;
		initGUI(logic);
	}

	private void initGUI(Logic logic) {
		try {
			this.setLayout(new BorderLayout());

			displaymap = null;
			// new map canvas object
			try {
				displaymap = new MapDisplayer(logic, FIELD_SIZE);
				displaymap.setToolTipText("Click on the map to see details or chat");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

			// addiding listeners
			displaymap.addKeyListener(logic);
			displaymap.addMouseListener(this);

			// adding scroll
			if (displaymap != null) {
				scrollMap = new JScrollPane(displaymap);
				scrollToCenter();
			}

			// show map name
			setMessagePanel("Map name " + logic.getMap().name + "\n");

			// add split panel
			JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, messagePanel, scrollMap);
			splitPane.setDividerLocation(60);
			this.add(splitPane, BorderLayout.CENTER);

			// add stats panel
			statPanel = new StatsPanel((Creature) logic.creatures.elementAt(0));
			this.add(statPanel, BorderLayout.SOUTH);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Assumes table is contained in a JScrollPane. Scrolls the
	// cell (rowIndex, vColIndex) so that it is visible at the center of
	// viewport.
	public void scrollToCenter() {
		int Xcoor = logic.getPlayer().locationx * FIELD_SIZE;
		int Ycoor = logic.getPlayer().locationy * FIELD_SIZE;
		Rectangle r = new Rectangle(Xcoor - 300, Ycoor - 200, 700, 500);
		displaymap.scrollRectToVisible(r);
	}

	// get possible questions
	String askQuestion(Creature creature) {
		String s = (String) JOptionPane.showInputDialog(null, "You turn to " + creature.race + " " + creature.profession
				+ " " + creature.name + "\n" + "What to say ?\n", "Dialog", JOptionPane.PLAIN_MESSAGE, null,
				creature.questions, "");

		// If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			return s;
		}

		return null;
	}

	// chating method, checks if a creaure has any questions and according
	// answers
	void talk(Creature creature) {
		String question = askQuestion(creature);
		String answer = null;
		if (question != null) {

			// find answer
			if (creature.questions != null)
				for (int i = 0; i < creature.questions.length; i++) {
					if (i >= creature.answers.length)
						return;
					if (creature.questions[i].equals(question))
						answer = (String) creature.answers[i];
				}
			if (answer != null)
				JOptionPane.showMessageDialog(null, answer, "Answer", JOptionPane.PLAIN_MESSAGE);
		}
	}

	// add new text to message window
	void showMessage(String text) {
		textMessage.append(text);
	}

	// Swing focus maintance (only focussed object can generate keyboard events)
	public void focusGained(FocusEvent evt) {
		// The applet now has the input focus.
		displaymap.repaint(); // redraw
	}

	public void focusLost(FocusEvent evt) {
		// The applet has now lost the input focus.
		// focussed = false;
		displaymap.repaint(); // redraw
	}

	// repaint the map
	void updateMap() {
		displaymap.repaint();
	}

	// change displayed map
	void changeMap() {
		System.out.println("changing map");
		displaymap.changeMap();
		displaymap.repaint();
		displaymap.setPreferredSize(displaymap.getPreferredSize());
		displaymap.revalidate();
		scrollToCenter();
	}

	// show warning window
	int showWarning(String info) {
		int option = JOptionPane.showConfirmDialog(null, info, "Select please...", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			return 0;
		} else {
			return 1;
		}
	}

	// show simple informational window
	void showText(String info) {
		JOptionPane.showMessageDialog(null, info);
	}

	// update turn value
	void updateTur(int tur) {
		statPanel.updateTur(tur);
	}

	// reacts for mouse click on the map canvas
	public void mousePressed(MouseEvent evt) {
		// request that the input focus be given to the
		// canvas when the user clicks on the applet.
		displaymap.requestFocus();

		// get position
		int x = evt.getX() / FIELD_SIZE;
		int y = evt.getY() / FIELD_SIZE;

		// get clicked field
		Field tempField = logic.getMap().getField(x, y);

		// show info
		showMessage("\nCoordinates: " + x + "," + y + ", field type - " + tempField.name + "  "
				+ logic.getMap().getInfo(x, y) + ", distance from Player: "
				+ logic.distance((Creature) logic.creatures.elementAt(0), x, y));
		// look for creature
		for (int i = 0; i < logic.getMap().creatures.size(); i++) {
			Creature tempCreature = (Creature) logic.getMap().creatures.elementAt(i);
			if (tempCreature.locationx == x && tempCreature.locationy == y)
				if (logic.distance(logic.getPlayer(), x, y) == 1) {
					talk(tempCreature);
				}
		}
	}

	public void mouseEntered(MouseEvent evt) {
	} // Required by the

	public void mouseExited(MouseEvent evt) {
	} // MouseListener

	public void mouseReleased(MouseEvent evt) {
	} // interface.

	public void mouseClicked(MouseEvent evt) {
	}

	// message panel initialisaton
	public void setMessagePanel(String message) {
		messagePanel = new JPanel();
		textMessage = new JTextArea(3, 70);
		textMessage.setText("Map entered: " + logic.getMap().name);
		textMessage.setToolTipText("Shows game log");
		textMessage.setFocusable(false);
		textMessage.setLineWrap(true);
		textMessage.setEditable(false);
		// adding scroll
		JScrollPane scrollMessage = new JScrollPane(textMessage);
		scrollMessage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		messagePanel.add(scrollMessage, BorderLayout.CENTER);
	}

	// updating stats
	public void updateStats() {
		statPanel.updateStats();
	}

	// get messages content
	String getLog() {
		return textMessage.getText();
	}

	// set messages content
	void setLog(String log) {
		textMessage.setText(log);
	}

	// update money
	public void updateMoney(int amount) {
		statPanel.updateMoney(amount);
	}
}
