import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
public class QuitPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */

	// GUI parent.
	Logic logic;
	private JButton saveAndQuit, quitWithoutSaving, btnLoad;
	private JLabel end;
	private JFileChooser fc; // Automatic file dialog.

	// Constructor creates all elements

	public QuitPanel(Logic logic) {
		super();
		this.logic = logic;

		// New file dialog.
		fc = new JFileChooser();

		initGUI();
	}

	private void initGUI() {
		try {

			setPreferredSize(new Dimension(400, 300));
			// Add some space.
			this.add(Box.createRigidArea(new Dimension(0, 24)));

			// Add the button, set up font and orientation.
			{
				end = new JLabel();
				this.add(end);
				end.setIcon(createImageIcon("images/end.jpe"));
				{
					saveAndQuit = new JButton();
					saveAndQuit.setMargin(new Insets(0, 0, 0, 0));
					end.add(saveAndQuit);
					saveAndQuit.setText("Save and quit");
					saveAndQuit.setBounds(30, 253, 129, 73);
					saveAndQuit.setFocusable(false);
					saveAndQuit.setFont(new java.awt.Font("Gungsuh", 1, 14));
					saveAndQuit.setBackground(new java.awt.Color(192, 192, 192));
					saveAndQuit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("btnSaveAndQuit.actionPerformed, event=" + evt);
							// int returnVal = fc.showOpenDialog(null);
							quit(evt);
						}
					});
				}
				{
					quitWithoutSaving = new JButton();
					quitWithoutSaving.setMargin(new Insets(0, 0, 0, 0));
					end.add(quitWithoutSaving);
					quitWithoutSaving.setText("Quit (no save!)");
					quitWithoutSaving.setBounds(176, 253, 129, 73);
					quitWithoutSaving.setFocusable(false);
					quitWithoutSaving.setFont(new java.awt.Font("Gungsuh", 1, 14));
					quitWithoutSaving.setBackground(new java.awt.Color(192, 192, 192));
					quitWithoutSaving.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							quit(evt);
						}
					});
				}

				{
					btnLoad = new JButton();
					btnLoad.setMargin(new Insets(0, 0, 0, 0));
					end.add(btnLoad);
					btnLoad.setText("Load");
					btnLoad.setBounds(600, 253, 129, 73);
					btnLoad.setFocusable(false);
					btnLoad.setFont(new java.awt.Font("Gungsuh", 1, 14));
					btnLoad.setBackground(new java.awt.Color(192, 192, 192));
					btnLoad.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							load();
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// end application, but save first if desired
	public void quit(ActionEvent e) {

		// Check if file was really saved before quitting.
		if (e.getSource() == saveAndQuit)
			if (!save())
				return;
		// Check for affirmation to quit the application.
		Object[] options = { "Yes", "Cancel" };
		int n = JOptionPane.showOptionDialog(null, "Do you really want to exit?", "Last warning",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		// Quit application.
		if (n == 0)
			System.exit(0);
	}

	/**
	 * Open save dialog and if operation approved-save data. If not-show
	 * appropriate message.
	 */
	public boolean save() {
		// Open a file dialog.
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			// Save file.
			try {
				if (logic.saveFile(file.getPath())) {
					JOptionPane.showMessageDialog(null, "File \"" + file.getName() + "\" saved");
					return true;
				}
				// Check for errors.
			} catch (IOException f) {
				System.err.println(f);
				JOptionPane.showMessageDialog(null, f.getMessage(), "File error", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException f) {
				System.err.println(f);
				JOptionPane.showMessageDialog(null, f.getMessage(), "File error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}

	/**
	 * Open load dialog and if operation approved-save data. If not-show
	 * appriopriate message.
	 */
	public void load() {
		// Open a file dialog.
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			// Load the data from a file.
			try {
				if (logic.openFile(file.getPath()))
					JOptionPane.showMessageDialog(null, "File \"" + file.getName() + "\" loaded");
				// If error occures:
			} catch (IOException f) {
				JOptionPane.showMessageDialog(null, f.getMessage(), "File error", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException f) {
				JOptionPane.showMessageDialog(null, f.getMessage(), "File error", JOptionPane.ERROR_MESSAGE);
			}
		}
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
