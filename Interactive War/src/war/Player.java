package war;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Player class - Initializes player objects and player actions, Getters, and Setters
 * 
 * @author Travis Truong
 *
 */
public class Player {

	private ArrayList<Card> hand;
	private ArrayList<Card> winnings;   // Cards won before being shuffled back into player's hand
	private String name;
	private int usedCards;    // Number of cards used in a round
	
	
	/**
	 * Constructs a new Player object with given name
	 * @param name The name of the player
	 */
	public Player(String name) {
		this.hand = new ArrayList<Card>();
		this.winnings = new ArrayList<Card>();
		this.name = name;
		this.usedCards = 0;
	}
	
	
	/**
	 * Copy constructor
	 * @param other
	 */
	public Player(Player other) {
		this(other.name);
	}
	
	
	/**
	 * Name Getter
	 * @return The name of the player
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Score Getter
	 * @return The number of cards a player has (in hand and in winnings)
	 */
	public int getScore() {
		return this.hand.size() + this.winnings.size();
	}
	
	
	/**
	 * Used Cards Getter
	 * @return The number of cards used in the round
	 */
	public int getUsedCards() {
		return usedCards;
	}
	
	
	/**
	 * Used Cards Setter
	 */
	public void resetUsed() {
		this.usedCards = 0;
	}
	
	
	/**
	 * Obtains a card from the top of the deck
	 * @param deck The set of playing cards
	 */
	public void drawCard(Deck deck) {
		this.hand.add(deck.dealCard());
	}
	
	
	/**
	 * Plays the card at the top of the player's hand
	 * @return The card played
	 */
	public Card playCard() {
		if (this.hand.size() == 0 && this.winnings.size() != 0) {    // Shuffles cards won back into hand when hand is empty
			transferWinnings();
		}
		this.usedCards += 1;
		return this.hand.remove(0);
	}
	
	
	/**
	 * Transfers the cards from the round into the player's winnings 
	 * @param cards Discarded cards from the round
	 */
	public void winRound(ArrayList<Card> cards) {
		for (Card card : cards) {
			this.winnings.add(card);
		}
	}
	
	
	/**
	 * Shuffles cards from player's winnings into hand
	 */
	public void transferWinnings() {
		Collections.shuffle(this.winnings);
		while (this.winnings.size() != 0) {
			this.hand.add(this.winnings.remove(0));
		}
	}
}
