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
	
	
	/**
	 * Player1 Getter
	 * @return Player 1 object
	 */
	public Player getPlayer1() {
		return player1;
	}
	
	
	/**
	 * Player2 Getter
	 * @return Player 2 object
	 */
	public Player getPlayer2() {
		return player2;
	}
	
	
	/**
	 * Determines winner by viewing if a player has no cards left
	 * @return 1 = Player 1 wins; -1 = Player 2 wins; 0 = No winner
	 */
	public int determineVictor() {
		if (this.player2.getScore() == 0) {
			return 1;
		}
		else if (this.player1.getScore() == 0) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	
	/**
	 * Announces the winner
	 */
	public void declareVictor() {
		if (determineVictor() == 1) {
			System.out.println("\n\n" + this.player1.getName() + " has won the game!");
		}
		else if (determineVictor() == -1) {
			System.out.println("\n\n" + this.player2.getName() + " has won the game!");
		}
		else {
			System.out.println("\n\nERROR NO VICTOR");
		}
	}
	
	
	/**
	 * Resets the number of cards each player has used in the round
	 */
	public void resetUsed() {
		this.player1.resetUsed();
		this.player2.resetUsed();
	}
	
	
	/**
	 * Gathers cards used by the players
	 * @param card1 Player 1's card
	 * @param card2 Player 2's card
	 * @param cards Set of cards played in the round
	 */
	public void assembleCards(Card card1, Card card2, ArrayList<Card> cards) {
		cards.add(card1);
		cards.add(card2);
	}
	
	
	public ArrayList<Card> playCards() {
		Card card1 = this.player1.playCard();
		Card card2 = this.player2.playCard();
		ArrayList<Card> cards = new ArrayList<Card>();
		assembleCards(card1, card2, cards);
		return cards;
	}
	
	
	/**
	 * Simulates an instance of a round
	 */
	public void playRound(ArrayList<Card> cards) {
		Card card1 = cards.get(0);
		Card card2 = cards.get(1);
		
		if (card1.getValue() > card2.getValue()) {   // Player 1 wins round
			this.player1.winRound(cards);
			resetUsed();
			System.out.println(this.player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beat " + this.player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " (" + this.player1.getScore() + "-" + this.player2.getScore() + ")");
		}
		else if (card1.getValue() < card2.getValue()) {     // Player 2 wins round
			this.player2.winRound(cards);
			resetUsed();
			System.out.println(this.player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beat " + this.player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " (" + this.player1.getScore() + "-" + this.player2.getScore() + ")");
		}
		else {
			System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());     // Enter war
			System.out.println("\nWAR!!!");

			if ((this.player1.getScore() < 4 && this.player1.getScore() != 0) || (this.player2.getScore() < 4 && this.player2.getScore() != 0)) {
				warSmall(cards);
			}
			else if (this.player1.getScore() == 0) {          // Player 1 cannot enter war
				this.player2.winRound(cards);
				resetUsed();
				System.out.println(this.player1.getName() + " has insufficient cards to play out the war");
			}
			else if (this.player2.getScore() == 0) {          // Player 2 cannot enter war
				this.player1.winRound(cards);
				resetUsed();
				System.out.println(this.player2.getName() + " has insufficient cards to play out the war");
			}
			else {
				warRegular(cards);
			}
		}
	}
	
	
	/**
	 * Enters war with standard conditions
	 * @param cards Set of cards played in the round
	 */
	public void warRegular(ArrayList<Card> cards) {
		
		for (int i = 0; i < 3; i++) {
			assembleCards(this.player1.playCard(), this.player2.playCard(), cards);
		}
		computeWar(cards, 3);
	}
	
	
	/**
	 * Enters war with less than standard number of cards
	 * @param cards Set of cards played in the round
	 */
	public void warSmall(ArrayList<Card> cards) {
		int gambitSize = 0;                            // Number of face-down cards
		
		if (this.player1.getScore() < 4 && this.player1.getScore() > 1) {
			gambitSize = this.player1.getScore() - 1;
			for (int i = 0; i < gambitSize; i++) {
				assembleCards(this.player1.playCard(), this.player2.playCard(), cards);
			}
			computeWar(cards, gambitSize);
		}
		else if (this.player2.getScore() < 4 && this.player2.getScore() > 1) {
			gambitSize = this.player2.getScore() - 1;
			for (int i = 0; i < gambitSize; i++) {
				assembleCards(this.player1.playCard(), this.player2.playCard(), cards);
			}
			computeWar(cards, gambitSize);
		}
		else {
			computeWar(cards, gambitSize);
		}
	}
	
	
	/**
	 * Simulates war given the number of face-down cards
	 * @param cards Set of cards played in the round
	 * @param gambitSize Number of face-down cards
	 */
	public void computeWar(ArrayList<Card> cards, int gambitSize) {
		Card card1 = this.player1.playCard();
		Card card2 = this.player2.playCard();
		assembleCards(card1, card2, cards);

		if (card1.getValue() > card2.getValue()) {     // Player 1 wins round
			this.player1.winRound(cards);
			System.out.println(this.player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beat " + this.player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
			System.out.println(this.player1.getName() + " wins the war and gains " + this.player2.getUsedCards() + " cards!" + " (" + this.player1.getScore() + "-" + this.player2.getScore() + ")\n");
			resetUsed();
		}
		else if (card1.getValue() < card2.getValue()) {   // Player 2 wins round
			this.player2.winRound(cards);
			System.out.println(this.player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beat " + this.player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
			System.out.println(this.player2.getName() + " wins the war and gains " + this.player1.getUsedCards() + " cards!" + " (" + this.player1.getScore() + "-" + this.player2.getScore() + ")\n");
			resetUsed();
		}
		else {     // Enter sub-war
			System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());
			System.out.println("\nWAR AGAIN!!!");
			
			if ((this.player1.getScore() < 4 && this.player1.getScore() != 0) || (this.player2.getScore() < 4 && this.player2.getScore() != 0)) {
				warSmall(cards);
			}
			else if (this.player1.getScore() == 0 || this.player2.getScore() == 0) {
				warAgainSmall(card1, card2, cards, gambitSize);
			}
			else {
				warRegular(cards);
			}
		}
	}
		
	
	/**
	 * Enters sub-war where one player cannot play face-down cards
	 * @param card1 Player 1's card
	 * @param card2 Player 2's card
	 * @param cards Set of cards played in the round
	 * @param gambitSize Number of face-down cards from the previous round
	 */
	public void warAgainSmall(Card card1, Card card2, ArrayList<Card> cards, int gambitSize) {
		if (this.player1.getScore() == 0) {
			for (int i = 0; i < gambitSize; i++) {
				Card tempCard = this.player2.playCard();  // Player with more cards places down same number of face-down cards as previous round
				cards.add(tempCard);
			}
			card2 = this.player2.playCard();
			cards.add(card2);
			if (card1.getValue() > card2.getValue()) {  // Player 1 wins round
				this.player1.winRound(cards);
				System.out.println(this.player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beat " + this.player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
				System.out.println(this.player1.getName() + " wins the war and gains " + this.player2.getUsedCards() + " cards!" + " (" + this.player1.getScore() + "-" + this.player2.getScore() + ")\n");
				resetUsed();
			}
			else if (card1.getValue() < card2.getValue()) {   // Player 2 wins round
				this.player2.winRound(cards);
				System.out.println(this.player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beat " + this.player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
				System.out.println(this.player2.getName() + " wins the war and gains " + this.player1.getUsedCards() + " cards!" + " (" + this.player1.getScore() + "-" + this.player2.getScore() + ")\n");
				resetUsed();
			}
			else {     // Enter sub-war
				System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());
				System.out.println("\nWAR AGAIN!!!");

				warAgainSmall(card1, card2, cards, gambitSize);
			}
		}
		else if (this.player2.getScore() == 0) {
			for (int i = 0; i < gambitSize; i++) {
				Card tempCard = this.player1.playCard();
				cards.add(tempCard);
			}
			card1 = this.player1.playCard();
			if (card1.getValue() > card2.getValue()) {    // Player 1 wins round
				this.player1.winRound(cards);
				System.out.println(this.player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beat " + this.player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
				System.out.println(this.player1.getName() + " wins the war and gains " + this.player2.getUsedCards() + " cards!" + " (" + this.player1.getScore() + "-" + this.player2.getScore() + ")\n");
				resetUsed();
			}
			else if (card1.getValue() < card2.getValue()) {   // Player 2 wins round
				this.player2.winRound(cards);
				System.out.println(this.player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beat " + this.player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
				System.out.println(this.player2.getName() + " wins the war and gains " + this.player1.getUsedCards() + " cards!" + " (" + this.player1.getScore() + "-" + this.player2.getScore() + ")\n");
				resetUsed();
			}
			else {       // Enter sub-war
				System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());
				System.out.println("/nWAR AGAIN!!!");
				
				warAgainSmall(card1, card2, cards, gambitSize);
			}
		}
	}
}