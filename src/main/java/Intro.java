import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

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
// This tab containing few Swing elements
public class Intro extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane;
	private JLabel image;
	private JButton btnInfo, story;
	private JLabel title;
	private String instructions = "          Welcome to the simple RPG Creator viewer\n\n"
			+ "Click on the tab above to switch to different views\n\n"
			+ "Use numerical keys as direction that your hero should move or attack\n\n" + "          ‘7’(up-left)"
			+ "         ‘8’ (up)" + "        ‘9’(up-right)\n" + "          ‘4’(left)" + "               ‘5’(wait)"
			+ "      ‘6’(right)\n" + "          ‘1’(down-left)" + "    ‘2’(down)" + "   ‘3’(down-right)\n\n" +

	"Additionally, 	\n - ‘>’ key is used to enter passages (field with a name “teleport”)\n"
			+ " - ‘p’ picks the first item at player location\n\n\n"
			+ "                                      Enjoy the game...\n\n\n"
			+ "PS. Game configuration is in folder 'conf'";

	private String info = "" + "Version:" + "         1.0\n" + "Created by:" + "    Robert Marzeta\n";

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	// constructor
	public Intro() {
		super();
		initGUI();
	}

	// Initialisation contains only a few Swing elements
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			{
				jScrollPane = new JScrollPane();
				this.add(jScrollPane);
				{
					image = new JLabel();
					jScrollPane.setViewportView(image);
					image.setIcon(createImageIcon("images/label.jpg"));
					{
						title = new JLabel();
						image.add(title);
						title.setText("RPG creator");
						title.setBounds(281, 70, 344, 51);
						title.setFont(new java.awt.Font("Monotype Corsiva", 1, 28));
						title.setBorder(BorderFactory.createTitledBorder(""));
						title.setFocusable(false);
					}
					{
						story = new JButton();
						image.add(story);
						story.setText("Click to read the instructions");
						story.setBounds(324, 142, 257, 30);
						story.setFocusable(false);
						// click handler
						story.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("story.actionPerformed, event=" + evt);
								// show information window
								JOptionPane.showMessageDialog(null, instructions);

							}
						});

					}

					{
						btnInfo = new JButton();
						image.add(btnInfo);
						btnInfo.setText("About");
						btnInfo.setBounds(768, 19, 68, 30);
						btnInfo.setFocusable(false);
						btnInfo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("btnInfo.actionPerformed, event=" + evt);
								JOptionPane.showMessageDialog(null, info);
							}
						});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
