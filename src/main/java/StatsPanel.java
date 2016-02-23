import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
// class showing a small stats panel on the bottom of map view
public class StatsPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	private JLabel lblHealth, lblMana, lblStrenght, lblDexterity, lblIntelligence, lblSpeed;
	private JLabel lblGold, lblExp, lblLevel, lblPerception, lblMap, lblTime;
	private JLabel valHealth, valMana, valStrenght, valDexterity, valIntelligence, valSpeed;
	private JLabel valGold, valExp, valLevel, valPerception;
	private JPanel panHealth, panMana, panStrenght, panDexterity, panIntelligence, panPerception;
	private JPanel panSpeed, panMapTime, panGold, panExp, panLevel;
	private Creature player;

	public StatsPanel(Creature player) {
		super();
		this.player = player;
		this.setToolTipText("Statistics are explained under 'Character' view");
		initGUI();
		updateStats();
	}

	// initialisation is too simple to comment, just Swing components and
	// setting their properties
	// flexible Box Layout is used
	private void initGUI() {
		try {
			panHealth = new JPanel();
			panHealth.setLayout(new BoxLayout(panHealth, BoxLayout.PAGE_AXIS));
			panHealth.setBorder(BorderFactory.createLineBorder(Color.black));
			lblHealth = new JLabel("Health");
			BoxLayout lblHealthLayout = new BoxLayout(lblHealth, javax.swing.BoxLayout.Y_AXIS);
			lblHealth.setLayout(lblHealthLayout);
			panHealth.add(lblHealth);
			lblHealth.setBorder(BorderFactory.createTitledBorder(""));
			lblHealth.setHorizontalTextPosition(SwingConstants.CENTER);
			lblHealth.setHorizontalAlignment(SwingConstants.CENTER);
			valHealth = new JLabel();
			panHealth.add(valHealth);
			valHealth.setHorizontalAlignment(SwingConstants.CENTER);
			valHealth.setHorizontalTextPosition(SwingConstants.CENTER);
			this.add(panHealth);

			panMana = new JPanel();
			panMana.setLayout(new BoxLayout(panMana, BoxLayout.PAGE_AXIS));
			panMana.setBorder(BorderFactory.createLineBorder(Color.black));
			lblMana = new JLabel("Mana");
			BoxLayout lblManaLayout = new BoxLayout(lblMana, javax.swing.BoxLayout.Y_AXIS);
			lblMana.setLayout(lblManaLayout);
			panMana.add(lblMana);
			lblMana.setBorder(BorderFactory.createTitledBorder(""));
			lblMana.setHorizontalTextPosition(SwingConstants.CENTER);
			lblMana.setHorizontalAlignment(SwingConstants.CENTER);
			valMana = new JLabel();
			panMana.add(valMana);
			this.add(panMana);

			panStrenght = new JPanel();
			panStrenght.setLayout(new BoxLayout(panStrenght, BoxLayout.PAGE_AXIS));
			panStrenght.setBorder(BorderFactory.createLineBorder(Color.black));
			lblStrenght = new JLabel("Strenght");
			BoxLayout lblStrenghtLayout = new BoxLayout(lblStrenght, javax.swing.BoxLayout.Y_AXIS);
			lblStrenght.setLayout(lblStrenghtLayout);
			panStrenght.add(lblStrenght);
			lblStrenght.setBorder(BorderFactory.createTitledBorder(""));
			lblStrenght.setHorizontalTextPosition(SwingConstants.CENTER);
			lblStrenght.setHorizontalAlignment(SwingConstants.CENTER);
			valStrenght = new JLabel();
			panStrenght.add(valStrenght);
			valStrenght.setHorizontalAlignment(SwingConstants.CENTER);
			valStrenght.setFocusable(false);
			valStrenght.setHorizontalTextPosition(SwingConstants.CENTER);
			this.add(panStrenght);

			panDexterity = new JPanel();
			panDexterity.setLayout(new BoxLayout(panDexterity, BoxLayout.PAGE_AXIS));
			panDexterity.setBorder(BorderFactory.createLineBorder(Color.black));
			lblDexterity = new JLabel("Dexterity");
			BoxLayout lblDexterityLayout = new BoxLayout(lblDexterity, javax.swing.BoxLayout.Y_AXIS);
			lblDexterity.setLayout(lblDexterityLayout);
			panDexterity.add(lblDexterity);
			lblDexterity.setBorder(BorderFactory.createTitledBorder(""));
			lblDexterity.setHorizontalTextPosition(SwingConstants.CENTER);
			lblDexterity.setHorizontalAlignment(SwingConstants.CENTER);
			valDexterity = new JLabel();
			panDexterity.add(valDexterity);
			this.add(panDexterity);

			panIntelligence = new JPanel();
			panIntelligence.setLayout(new BoxLayout(panIntelligence, BoxLayout.PAGE_AXIS));
			panIntelligence.setBorder(BorderFactory.createLineBorder(Color.black));
			lblIntelligence = new JLabel("Intelligence");
			BoxLayout lblIntelligenceLayout = new BoxLayout(lblIntelligence, javax.swing.BoxLayout.Y_AXIS);
			lblIntelligence.setLayout(lblIntelligenceLayout);
			panIntelligence.add(lblIntelligence);
			lblIntelligence.setBorder(BorderFactory.createTitledBorder(""));
			lblIntelligence.setHorizontalTextPosition(SwingConstants.CENTER);
			lblIntelligence.setHorizontalAlignment(SwingConstants.CENTER);
			valIntelligence = new JLabel();
			panIntelligence.add(valIntelligence);
			this.add(panIntelligence);

			panPerception = new JPanel();
			panPerception.setLayout(new BoxLayout(panPerception, BoxLayout.PAGE_AXIS));
			panPerception.setBorder(BorderFactory.createLineBorder(Color.black));
			lblPerception = new JLabel("Perception");
			BoxLayout lblPerceptionLayout = new BoxLayout(lblPerception, javax.swing.BoxLayout.Y_AXIS);
			lblPerception.setLayout(lblPerceptionLayout);
			panPerception.add(lblPerception);
			lblPerception.setBorder(BorderFactory.createTitledBorder(""));
			lblPerception.setHorizontalTextPosition(SwingConstants.CENTER);
			lblPerception.setHorizontalAlignment(SwingConstants.CENTER);
			valPerception = new JLabel();
			panPerception.add(valPerception);
			this.add(panPerception);

			panSpeed = new JPanel();
			panSpeed.setLayout(new BoxLayout(panSpeed, BoxLayout.PAGE_AXIS));
			panSpeed.setBorder(BorderFactory.createLineBorder(Color.black));
			lblSpeed = new JLabel("Speed");
			BoxLayout lblSpeedLayout = new BoxLayout(lblSpeed, javax.swing.BoxLayout.Y_AXIS);
			lblSpeed.setLayout(lblSpeedLayout);
			panSpeed.add(lblSpeed);
			lblSpeed.setBorder(BorderFactory.createTitledBorder(""));
			lblSpeed.setHorizontalTextPosition(SwingConstants.CENTER);
			lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
			valSpeed = new JLabel();
			panSpeed.add(valSpeed);
			this.add(panSpeed);

			panExp = new JPanel();
			panExp.setLayout(new BoxLayout(panExp, BoxLayout.PAGE_AXIS));
			panExp.setBorder(BorderFactory.createLineBorder(Color.black));
			lblExp = new JLabel("Experience");
			BoxLayout lblExpLayout = new BoxLayout(lblExp, javax.swing.BoxLayout.Y_AXIS);
			lblExp.setLayout(lblExpLayout);
			panExp.add(lblExp);
			lblExp.setBorder(BorderFactory.createTitledBorder(""));
			lblExp.setHorizontalTextPosition(SwingConstants.CENTER);
			lblExp.setHorizontalAlignment(SwingConstants.CENTER);
			valExp = new JLabel();
			panExp.add(valExp);
			this.add(panExp);

			panLevel = new JPanel();
			panLevel.setLayout(new BoxLayout(panLevel, BoxLayout.PAGE_AXIS));
			panLevel.setBorder(BorderFactory.createLineBorder(Color.black));
			lblLevel = new JLabel("Level");
			BoxLayout lblLevelLayout = new BoxLayout(lblLevel, javax.swing.BoxLayout.Y_AXIS);
			lblLevel.setLayout(lblLevelLayout);
			panLevel.add(lblLevel);
			lblLevel.setBorder(BorderFactory.createTitledBorder(""));
			lblLevel.setHorizontalTextPosition(SwingConstants.CENTER);
			lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
			valLevel = new JLabel();
			panLevel.add(valLevel);
			this.add(panLevel);

			panGold = new JPanel();
			panGold.setLayout(new BoxLayout(panGold, BoxLayout.PAGE_AXIS));
			panGold.setBorder(BorderFactory.createLineBorder(Color.black));
			lblGold = new JLabel("Gold");
			BoxLayout lblGoldLayout = new BoxLayout(lblGold, javax.swing.BoxLayout.Y_AXIS);
			lblGold.setLayout(lblGoldLayout);
			panGold.add(lblGold);
			lblGold.setBorder(BorderFactory.createTitledBorder(""));
			lblGold.setHorizontalTextPosition(SwingConstants.CENTER);
			lblGold.setHorizontalAlignment(SwingConstants.CENTER);
			valGold = new JLabel();
			panGold.add(valGold);
			this.add(panGold);

			panMapTime = new JPanel(new BorderLayout());
			// panMapTime.setLayout(new GridLayout(2,2));
			panMapTime.setLayout(new BoxLayout(panMapTime, BoxLayout.PAGE_AXIS));
			panMapTime.setBorder(BorderFactory.createLineBorder(Color.black));
			lblMap = new JLabel("Map:");
			BoxLayout lblMapLayout = new BoxLayout(lblMap, javax.swing.BoxLayout.Y_AXIS);
			lblMap.setLayout(lblMapLayout);
			panMapTime.add(lblMap);
			lblMap.setBorder(BorderFactory.createTitledBorder(""));
			lblMap.setHorizontalTextPosition(SwingConstants.CENTER);
			lblMap.setHorizontalAlignment(SwingConstants.CENTER);
			lblTime = new JLabel(" Turn: 0");
			panMapTime.add(lblTime);
			lblTime.setHorizontalTextPosition(SwingConstants.CENTER);
			lblTime.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(panMapTime);

			setPreferredSize(new Dimension(700, 50));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// update stats
	public void updateStats() {
		valHealth.setText("" + player.health);
		valExp.setText("" + player.experience);
		valLevel.setText("" + player.level);
		valMana.setText("" + player.mana);
		valStrenght.setText("" + player.strength);
		valDexterity.setText("" + player.dexterity);
		valSpeed.setText("" + player.speed);
		valIntelligence.setText("" + player.intelligence);
		valPerception.setText("" + player.perception);
		valGold.setText("" + player.money);
		lblMap.setText("Map: " + player.map);
	}

	// update turn label
	void updateTur(int tur) {
		lblTime.setText(" Turn: " + tur);
	}

	// update Map label
	void updateMap(int tur) {
		lblMap.setText("Map: " + player.map);
	}

	public void updateMoney(int amount) {
		valGold.setText("" + amount);
	}

}
