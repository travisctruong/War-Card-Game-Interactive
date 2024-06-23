package war;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

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
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(53, 94, 59));		
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 350, 230);
		frame.setBackground(new Color(150, 75, 0));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Notice");
		
		JLabel confirmationLabel = new JLabel("Are you sure you want to quit?");
		confirmationLabel.setFont(new Font("Onyx", Font.BOLD, 32));
		confirmationLabel.setForeground(new Color(255, 255, 255));
		confirmationLabel.setBounds(35, 11, 291, 52);
		frame.getContentPane().add(confirmationLabel);
		
		JButton leaveButton = new JButton("Yes");
		leaveButton.setBounds(72, 102, 89, 23);
		frame.getContentPane().add(leaveButton);
		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame gameFrame = GameWindow.getFrame();
				gameFrame.dispose();
				frame.dispose();
				Menu.main(null);
			}
		});
		
		JButton stayButton = new JButton("No");
		stayButton.setBounds(171, 102, 89, 23);
		frame.getContentPane().add(stayButton);
		stayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
	}
}
