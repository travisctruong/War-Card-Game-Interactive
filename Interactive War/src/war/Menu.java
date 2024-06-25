package war;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menu Swing Class - Initializes a UI main menu
 * 
 * @author Travis Truong
 *
 */
public class Menu {

	private JFrame frame;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(60, 76, 36));		// Initialize JFrame
		frame.setBounds(100, 100, 1000, 600);
		frame.setBackground(new Color(150, 75, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("War - Card Game");
		
		JButton startButton = new JButton("Start Game");               // Initialize button to start game, redirects to GameWindow JFrame
		startButton.setForeground(new Color(60, 76, 36));
		startButton.setFont(new Font("Onyx", Font.PLAIN, 30));
		startButton.setBounds(385, 295, 250, 80);
		frame.getContentPane().add(startButton);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameWindow.main(null);
				frame.dispose();
			}
		});
		
		JButton rulesButton = new JButton("Rules");                    // Initialize button to display rules
		rulesButton.setForeground(new Color(60, 76, 36));
		rulesButton.setFont(new Font("Onyx", Font.PLAIN, 30));
		rulesButton.setBounds(411, 386, 200, 64);
		frame.getContentPane().add(rulesButton);
		rulesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RulesWindow.main(null);
			}
		});
		
		JButton quitButton = new JButton("Quit");                    // Initialize button to leave application
		quitButton.setForeground(new Color(60, 76, 36));
		quitButton.setFont(new Font("Onyx", Font.PLAIN, 30));
		quitButton.setBounds(411, 461, 200, 64);
		frame.getContentPane().add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		JLabel titleLabel = new JLabel("WAR");                         // Creates game title
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setBackground(new Color(150, 75, 0));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Onyx", Font.PLAIN, 110));
		titleLabel.setBounds(311, 87, 400, 100);
		frame.getContentPane().add(titleLabel);
	}
}
