package war;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck class - Initializes deck object and functions including Shuffle and Deal
 * 
 * @author Travis Truong
 *
 */
public class Deck {

	private ArrayList<Card> cards;
	
	
	/**
	 * Constructs a new Deck
	 */
	public Deck() {
		this.cards = new ArrayList<Card>();
	}
	
	
	/**
	 * Size Getter
	 * @return The size of the deck
	 */
	public int size() {
		return this.cards.size();
	}
	
	
	/**
	 * Initializes each playing card and adds it to the deck
	 */
	public void initialize() {
		String[] suits = {"Spades","Diamonds","Hearts","Clubs"}; 
		String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
		int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		
		for (String suit : suits) {
			for (int i = 0; i < ranks.length; i++) {
				Card card = new Card(suit, ranks[i], values[i], "src/war/Cards/" + ranks[i] + " of " + suit + ".png");
				this.cards.add(card);
			}
		}
	}
	
	
	/**
	 * Shuffles the deck
	 */
	public void shuffle() {
		Collections.shuffle(this.cards);
	}
	
	
	/**
	 * Deals a card from the deck
	 * @return The card on the top of the deck
	 */
	public Card dealCard() {
		shuffle();
		return this.cards.remove(0);
	}
}
