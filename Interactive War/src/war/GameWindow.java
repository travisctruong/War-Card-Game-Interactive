package war;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JTextField;

public class GameWindow extends JFrame {

	private static JFrame frame;
	private boolean flag;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
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
	public GameWindow() {
		initialize();
	}
	
	public void showCards(Game playWar, JLabel playerCardLabel, JLabel opponentCardLabel) {
		ImageIcon playerCard1 = playWar.getPlayer().getCardInPlay().getImage();
		ImageIcon playerCard2 = playWar.getOpponent().getCardInPlay().getImage();
		
		Image scaleCard1 = playerCard1.getImage();
		Image modifiedCard1 = scaleCard1.getScaledInstance(241, 277, java.awt.Image.SCALE_SMOOTH);
		playerCard1 = new ImageIcon(modifiedCard1);
		playerCardLabel.setIcon(playerCard1);
		
		Image scaleCard2 = playerCard2.getImage();
		Image modifiedCard2 = scaleCard2.getScaledInstance(241, 277, java.awt.Image.SCALE_SMOOTH);
		playerCard2 = new ImageIcon(modifiedCard2);
		opponentCardLabel.setIcon(playerCard2);
	}
	
	public void checkWinner(Game playWar, JButton playCardButton) {
		int end = playWar.declareVictor();
		if (end == 1 || end == -1) {
			JLabel winnerLabel = new JLabel();
			winnerLabel.setText("WINNER");
			winnerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			winnerLabel.setVerticalAlignment(SwingConstants.CENTER);
			winnerLabel.setFont(new Font("Onyx", Font.PLAIN, 60));
			frame.getContentPane().add(winnerLabel);
			
			if (end == 1) {
				winnerLabel.setBounds(70, 450, 131, 38);
				winnerLabel.setForeground(Color.GREEN);
			}
			else if (end == -1) {
				winnerLabel.setBounds(790, 450, 131, 38);
				winnerLabel.setForeground(Color.RED);
			}
			
			playCardButton.setVisible(false);
			JButton menuButton= new JButton("Main Menu");
			menuButton.setForeground(new Color(60, 76, 36));
			menuButton.setFont(new Font("Onyx", Font.PLAIN, 30));
			menuButton.setBounds(414, 409, 165, 58);
			frame.getContentPane().add(menuButton);
			menuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConfirmationWindow.main(null);
				}
			});
		}
	}
	
	public static JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(60, 76, 36));		
		frame.setBounds(100, 100, 1000, 600);
		frame.setBackground(new Color(150, 75, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("War - Card Game");
		
		ImageIcon quit = new ImageIcon("src/war/Icon/Exit Icon.png");
		Image scaleQuit = quit.getImage();
		Image modifiedQuit = scaleQuit.getScaledInstance(33, 33, java.awt.Image.SCALE_SMOOTH);
		quit = new ImageIcon(modifiedQuit);
		JButton quitButton = new JButton(quit);
		quitButton.setBounds(900,11,33,33);
		frame.getContentPane().add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		ImageIcon rules = new ImageIcon("src/war/Icon/Info Icon.png");
		Image scaleRules = rules.getImage();
		Image modifiedRules = scaleRules.getScaledInstance(33, 33, java.awt.Image.SCALE_SMOOTH);
		rules = new ImageIcon(modifiedRules);
		JButton rulesButton = new JButton(rules);
		rulesButton.setBounds(943,11,33,33);
		frame.getContentPane().add(rulesButton);
		rulesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RulesWindow.main(null);
			}
		});
		
		JButton startButton = new JButton("Start Game");
		startButton.setForeground(new Color(60, 76, 36));
		startButton.setFont(new Font("Onyx", Font.PLAIN, 30));
		startButton.setBounds(414, 409, 165, 58);
		frame.getContentPane().add(startButton);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.getContentPane().remove(startButton);
				
				Game playWar = new Game();
				playWar.startGame();
				
				JLabel playerCardLabel = new JLabel();
				playerCardLabel.setBounds(11,69,241,277);
				playerCardLabel.setBackground(new Color(255,255,255));
				frame.getContentPane().add(playerCardLabel);
				
				JLabel playerLabel = new JLabel();
				playerLabel.setText("Player's Card");
				playerLabel.setBounds(70, 350, 131, 38);
				playerLabel.setForeground(Color.GREEN);
				playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
				playerLabel.setVerticalAlignment(SwingConstants.CENTER);
				playerLabel.setFont(new Font("Onyx", Font.PLAIN, 20));
				frame.getContentPane().add(playerLabel);
				
				JLabel opponentCardLabel = new JLabel();
				opponentCardLabel.setBounds(734,69,241,277);
				opponentCardLabel.setBackground(new Color(255,255,255));
				frame.getContentPane().add(opponentCardLabel);
				
				JLabel opponentLabel = new JLabel();
				opponentLabel.setText("Opponent's Card");
				opponentLabel.setBounds(790, 350, 131, 38);
				opponentLabel.setForeground(Color.RED);
				opponentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				opponentLabel.setHorizontalAlignment(SwingConstants.CENTER);
				opponentLabel.setVerticalAlignment(SwingConstants.CENTER);
				opponentLabel.setFont(new Font("Onyx", Font.PLAIN, 20));
				frame.getContentPane().add(opponentLabel);
				
				JLabel scoreLabel = new JLabel();
				scoreLabel.setText("Score: " + playWar.getPlayer().getScore() + "-" + playWar.getOpponent().getScore());
				scoreLabel.setForeground(new Color(255, 255, 255));
				scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
				scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
				scoreLabel.setFont(new Font("Onyx", Font.PLAIN, 40));
				scoreLabel.setBounds(369, 12, 252, 50);
				frame.getContentPane().add(scoreLabel);
				
				JTextArea dialogueTextArea = new JTextArea();
				dialogueTextArea.setEditable(false);
				PrintStream printStream = new PrintStream(new RedirectOutput(dialogueTextArea));
				System.setOut(printStream);
				
				JScrollPane dialogueScrollPane = new JScrollPane(dialogueTextArea);
				dialogueScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				dialogueScrollPane.setBounds(279,73,428,311);
				frame.getContentPane().add(dialogueScrollPane);
				
				JButton playWarButton = new JButton("Play War");
				playWarButton.setForeground(new Color(60, 76, 36));
				playWarButton.setFont(new Font("Onyx", Font.PLAIN, 30));
				playWarButton.setBounds(414, 409, 165, 58);
				frame.getContentPane().add(playWarButton);
				playWarButton.setVisible(false);
				
				JButton playCardButton = new JButton("Play Card");
				playCardButton.setForeground(new Color(60, 76, 36));
				playCardButton.setFont(new Font("Onyx", Font.PLAIN, 30));
				playCardButton.setBounds(414, 409, 165, 58);
				frame.getContentPane().add(playCardButton);
				playCardButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ArrayList<Card> cards = new ArrayList<Card>();
						flag = playWar.playRound(cards);
						
						showCards(playWar, playerCardLabel, opponentCardLabel);
						
						if (flag) {
							int gambitSize = 3;
							playWarButton.setVisible(true);
								
							playWarButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									while (flag) {
										int direct = playWar.startSubWar(cards);
										if (direct == 1) {
											ArrayList<Object> returns = playWar.warSmall(cards);
											ArrayList<Card> cards = (ArrayList<Card>) returns.get(0);
											int gambitSize = (int) returns.get(1);
											flag = playWar.computeWar(cards, gambitSize);
										}
										else if (direct == 2) {
											flag = playWar.warAgainSmall(playWar.getPlayer().getCardInPlay(), playWar.getOpponent().getCardInPlay(), cards, gambitSize);
										}
										else {
											playWar.warRegular(cards);
											flag = playWar.computeWar(cards, gambitSize);
										}
										showCards(playWar, playerCardLabel, opponentCardLabel);
									}
									scoreLabel.setText("Score: " + playWar.getPlayer().getScore() + "-" + playWar.getOpponent().getScore());
									playWarButton.setVisible(false);
									checkWinner(playWar, playCardButton);
								}
							});							
						}
						
						scoreLabel.setText("Score: " + playWar.getPlayer().getScore() + "-" + playWar.getOpponent().getScore());
						
						frame.revalidate();
						frame.repaint();
						checkWinner(playWar, playCardButton);
					}
				});

				frame.revalidate();
				frame.repaint();
				
				
			}
		});
	}
}
