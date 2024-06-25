package war;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;

/**
 * RulesWindow Swing Class - Initializes a UI rules window
 * 
 * @author Travis Truong
 *
 */
public class RulesWindow {

	private JFrame frame;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RulesWindow window = new RulesWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public RulesWindow() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();													// Initializes JFrame
		frame.getContentPane().setBackground(new Color(53, 94, 59));		
		frame.setBounds(100, 100, 700, 467);
		frame.setBackground(new Color(150, 75, 0));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Rules");
		
		JLabel rulesLabel = new JLabel("Rules");								// Creates rules screen title
		rulesLabel.setForeground(new Color(255, 255, 255));
		rulesLabel.setFont(new Font("Onyx", Font.BOLD, 32));
		rulesLabel.setBounds(331, 11, 300, 40);
		frame.getContentPane().add(rulesLabel);
		
		JTextArea rulesText = new JTextArea();									// Displays rules of game
		rulesText.setForeground(new Color(255, 255, 255));
		rulesText.setFont(new Font("Onyx", Font.PLAIN, 18));
		rulesText.setBackground(new Color(53, 94, 59));
		rulesText.setLineWrap(true);
		rulesText.setText("A standard deck of playing cards is shuffled and evenly distributed to two players. Every round, each player reveals their top card in unison and the player revealing the card of higher rank wins both of the cards in play. If both players reveal a card of the same rank, the game enters \"War\" where each player must then allocate 3 of their top cards as face-down cards and then reveal their fourth card. The player revealing the card of higher rank wins all cards introduced during the round. The game continues until one player has no more cards left.\r\n\r\nSpecial Conditions:\r\n\r\n     - If a player does not have enough cards to enter a war, the game ends and that player automatically loses \r\n     - If both players reveal a card of the same rank during a war, the game continuously enters another war until the             revealing cards do not match. The player revealing the card of higher rank during the final war wins all cards                  introduced during the round \r\n     - If a player has enough cards to enter a war but does not have 3 face-down cards when entering a sub-war, the                player must use the remaining cards as face-down cards and the very last card as face-up. The opponent must                 match the same number of cards used in that sub-war \r\n     - If a player has no cards remaining when entering a sub-war, the player keeps their current face-up card while the          opponent puts down the same amount of cards face-down as the previous round and reveals a new face-up card");
		rulesText.setBounds(75, 62, 542, 344);
		frame.getContentPane().add(rulesText);
		rulesText.setEditable(false);
		rulesText.setLineWrap(true);
		rulesText.setWrapStyleWord(true);
	}
}
