package war;
import java.util.ArrayList;

/**
 * Game class - Initializes a game object and game conditions
 * 
 * @author Travis Truong
 *
 */
public class Game {

	private Deck deck;
	private Player player1;
	private Player player2;
	
	
	/**
	 * Constructs a new Game object
	 */
	public Game() {
		this.deck = new Deck();
		this.player1 = new Player("Player 1");
		this.player2 = new Player("Player 2");
	}
	
	
	/**
	 * Starts the game and initializes, shuffles and deals the entire playing deck
	 */
	public void startGame() {
		this.deck.initialize();
		this.deck.shuffle();
		
		while (deck.size() > 0) {
			this.player1.drawCard(this.deck);
			this.player2.drawCard(this.deck);
		}
	}
}