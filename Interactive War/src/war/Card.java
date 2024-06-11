package war;

import javax.swing.ImageIcon;

/**
 * Card class - Initializes card objects, Getters, and Setters 
 * 
 * @author Travis Truong
 *
 */
public class Card {

	private String suit;
	private String rank;
	private int value;
	private ImageIcon image;
	
	
	/**
	 * Constructs a new Card with given suit, rank, and value
	 * @param suit The suit of the card
	 * @param rank The rank of the card
	 * @param value The value of the card
	 */
	public Card(String suit, String rank, int value, String imagePath) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
		this.image = new ImageIcon(imagePath);
	}
	
	
	/**
	 * Suit Getter
	 * @return The suit of the card
	 */
	public String getSuit() {
		return this.suit;
	}
	
	
	/**
	 * Rank Getter
	 * @return The rank of the card
	 */
	public String getRank() {
		return this.rank;
	}
	
	
	/**
	 * Value Getter
	 * @return The value of the card
	 */
	public int getValue() {
		return this.value;
	}
	
	
	/**
	 * Image Getter
	 * @return
	 */
	public ImageIcon getImage() {
		return this.image;
	}
	
	
	/**
	 * Suit Setter
	 * @param suit The suit of the card
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	
	/**
	 * Rank Setter
	 * @param rank The rank of the card
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
	/**
	 * Value Setter
	 * @param value The value of the card
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
