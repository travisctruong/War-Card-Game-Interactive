package war;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * ConfirmationWindow Swing Class - Initializes a UI window prompting user to leave game window
 * 
 * @author Travis Truong
 *
 */
public class ConfirmationWindow {

	private JFrame frame;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmationWindow window = new ConfirmationWindow();
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
	public ConfirmationWindow() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();																// Initializes JFrame
		frame.getContentPane().setBackground(new Color(53, 94, 59));		
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 350, 230);
		frame.setBackground(new Color(150, 75, 0));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Notice");
		
		JLabel confirmationLabel = new JLabel("Are you sure you want to quit?");			// Creates screen title
		confirmationLabel.setFont(new Font("Onyx", Font.BOLD, 32));
		confirmationLabel.setForeground(new Color(255, 255, 255));
		confirmationLabel.setBounds(35, 11, 291, 52);
		frame.getContentPane().add(confirmationLabel);
		
		JButton leaveButton = new JButton("Yes");											// Initializes button to direct to main menu
		leaveButton.setBounds(72, 102, 89, 23);
		leaveButton.setForeground(new Color(60, 76, 36));
		leaveButton.setFont(new Font("Onyx", Font.PLAIN, 20));
		frame.getContentPane().add(leaveButton);
		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame gameFrame = GameWindow.getFrame();
				gameFrame.dispose();
				frame.dispose();
				Menu.main(null);
			}
		});
		
		JButton stayButton = new JButton("No");												// Initializes button to stay in game window
		stayButton.setBounds(171, 102, 89, 23);
		stayButton.setForeground(new Color(60, 76, 36));
		stayButton.setFont(new Font("Onyx", Font.PLAIN, 20));
		frame.getContentPane().add(stayButton);
		stayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
