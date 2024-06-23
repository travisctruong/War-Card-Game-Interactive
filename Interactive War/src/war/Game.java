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
	private Player player;
	private Player opponent;
	private int playerWins;
	
	
	/**
	 * Constructs a new Game object
	 */
	public Game() {
		this.deck = new Deck();
		this.player = new Player("Player");
		this.opponent = new Player("Opponent");
		this.playerWins = 0;
	}
	
	
	/**
	 * Starts the game and initializes, shuffles and deals the entire playing deck
	 */
	public void startGame() {
		this.deck.initialize();
		this.deck.shuffle();
		
		while (deck.size() > 0) {
			this.player.drawCard(this.deck);
			this.opponent.drawCard(this.deck);
		}
	}
	
	
	/**
	 * Player Getter
	 * @return Player object
	 */
	public Player getPlayer() {
		return player;
	}
	
	
	/**
	 * Opponent Getter
	 * @return Opponent object
	 */
	public Player getOpponent() {
		return opponent;
	}
	
	
	/**
	 * Determines winner by viewing if a player has no cards left
	 * @return 1 = Player wins; -1 = Opponent wins; 0 = No winner
	 */
	public int determineVictor() {
		if (this.opponent.getScore() == 0 || this.playerWins == 1) {
			return 1;
		}
		else if (this.player.getScore() == 0 || this.playerWins == -1) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	
	/**
	 * Announces the winner
	 */
	public int declareVictor() {
		if (determineVictor() == 1) {
			System.out.println("\n\n" + this.player.getName() + " has won the game!");
			return 1;
		}
		else if (determineVictor() == -1) {
			System.out.println("\n\n" + this.opponent.getName() + " has won the game!");
			return -1;
		}
		return 0;
	}
	
	
	/**
	 * Resets the number of cards each player has used in the round
	 */
	public void resetUsed() {
		this.player.resetUsed();
		this.opponent.resetUsed();
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
	
	
	public ArrayList<Card> playCards(ArrayList<Card> cards) {
		Card card1 = this.player.playCard();
		this.player.setCardInPlay(card1);
		Card card2 = this.opponent.playCard();
		this.opponent.setCardInPlay(card2);
		assembleCards(card1, card2, cards);
		return cards;
	}
	
	
	/**
	 * Simulates an instance of a round
	 */
	public boolean playRound(ArrayList<Card> cards) {
		cards = playCards(cards);
		Card card1 = this.player.getCardInPlay();
		Card card2 = this.opponent.getCardInPlay();
		
		if (card1.getValue() > card2.getValue()) {   // Player 1 wins round
			this.player.winRound(cards);
			System.out.println(this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beats " + this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
		}
		else if (card1.getValue() < card2.getValue()) {     // Player 2 wins round
			this.opponent.winRound(cards);
			System.out.println(this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beats " + this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
		}
		else {
			System.out.println("Both players played " + card2.getRank() + "s");     // Enter war
			System.out.println("\nWAR!!!");

			if (this.player.getScore() < 4) {          // Player 1 cannot enter war
				this.opponent.winRound(cards);
				System.out.println(this.player.getName() + " has insufficient cards to play out the war");
				this.playerWins = -1;
			}
			else if (this.opponent.getScore() < 4) {          // Player 2 cannot enter war
				this.player.winRound(cards);
				System.out.println(this.opponent.getName() + " has insufficient cards to play out the war");
				this.playerWins = 1;
			}
			else {
				return true;
			}
		}
		resetUsed();
		return false;
	}
	
	
	/**
	 * Enters war with standard conditions
	 * @param cards Set of cards played in the round
	 */
	public void warRegular(ArrayList<Card> cards) {
		
		for (int i = 0; i < 3; i++) {
			assembleCards(this.player.playCard(), this.opponent.playCard(), cards);
		}
		System.out.println("Both players allocate 3 face-down cards...");
	}
	
	
	/**
	 * Enters war with less than standard number of cards
	 * @param cards Set of cards played in the round
	 */
	public ArrayList<Object> warSmall(ArrayList<Card> cards) {
		int gambitSize = 0;                            // Number of face-down cards
		ArrayList<Object> returns = new ArrayList<Object>();
		
		if (this.player.getScore() < 4 && this.player.getScore() > 1) {
			gambitSize = this.player.getScore() - 1;
			for (int i = 0; i < gambitSize; i++) {
				assembleCards(this.player.playCard(), this.opponent.playCard(), cards);
			}
		}
		else if (this.opponent.getScore() < 4 && this.opponent.getScore() > 1) {
			gambitSize = this.opponent.getScore() - 1;
			for (int i = 0; i < gambitSize; i++) {
				assembleCards(this.player.playCard(), this.opponent.playCard(), cards);
			}
		}
		System.out.println("Both players allocate " + gambitSize + " face-down cards...");
		returns.add(cards);
		returns.add(gambitSize);
		return returns;
	}
	
	
	/**
	 * Simulates war given the number of face-down cards
	 * @param cards Set of cards played in the round
	 * @param gambitSize Number of face-down cards
	 */
	public boolean computeWar(ArrayList<Card> cards, int gambitSize) {
		cards = playCards(cards);
		Card card1 = this.player.getCardInPlay();
		Card card2 = this.opponent.getCardInPlay();

		if (card1.getValue() > card2.getValue()) {     // Player 1 wins round
			this.player.winRound(cards);
			System.out.println(this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beats " + this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
			System.out.println(this.player.getName() + " wins the war and gains " + this.opponent.getUsedCards() + " cards!\n");
		}
		else if (card1.getValue() < card2.getValue()) {   // Player 2 wins round
			this.opponent.winRound(cards);
			System.out.println(this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beats " + this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
			System.out.println(this.opponent.getName() + " wins the war and gains " + this.player.getUsedCards() + " cards!\n");
		}
		else {     // Enter sub-war
			System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());
			System.out.println("\nWAR AGAIN!!!");
			return true;
		}
		resetUsed();
		return false;
	}
	
	
	public int startSubWar(ArrayList<Card> cards) {
		if ((this.player.getScore() < 4 && this.player.getScore() != 0) || (this.opponent.getScore() < 4 && this.opponent.getScore() != 0)) {
			return 1;
		}
		else if (this.player.getScore() == 0 || this.opponent.getScore() == 0) {
			return 2;
		}
		else {
			return 3;
		}
	}
	
	/**
	 * Enters sub-war where one player cannot play face-down cards
	 * @param card1 Player 1's card
	 * @param card2 Player 2's card
	 * @param cards Set of cards played in the round
	 * @param gambitSize Number of face-down cards from the previous round
	 */
	public boolean warAgainSmall(Card card1, Card card2, ArrayList<Card> cards, int gambitSize) {
		if (this.player.getScore() == 0) {
			for (int i = 0; i < gambitSize; i++) {
				Card tempCard = this.opponent.playCard();  // Player with more cards places down same number of face-down cards as previous round
				cards.add(tempCard);
			}
			System.out.println("Opponent allocates " + gambitSize + " face-down cards...");
			card2 = this.opponent.playCard();
			this.opponent.setCardInPlay(card2);
			cards.add(card2);
			if (card1.getValue() > card2.getValue()) {  // Player 1 wins round
				this.player.winRound(cards);
				System.out.println(this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beats " + this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
				System.out.println(this.player.getName() + " wins the war and gains " + this.opponent.getUsedCards() + " cards!\n");
			}
			else if (card1.getValue() < card2.getValue()) {   // Player 2 wins round
				this.opponent.winRound(cards);
				System.out.println(this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beats " + this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
				System.out.println(this.opponent.getName() + " wins the war and gains " + this.player.getUsedCards() + " cards!\n");
			}
			else {     // Enter sub-war
				System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());
				System.out.println("\nWAR AGAIN!!!");
				return true;
			}
			resetUsed();
			return false;
		}
		else if (this.opponent.getScore() == 0) {
			for (int i = 0; i < gambitSize; i++) {
				Card tempCard = this.player.playCard();
				cards.add(tempCard);
			}
			System.out.println("Player allocates " + gambitSize + " face-down cards...");
			card1 = this.player.playCard();
			this.player.setCardInPlay(card1);
			cards.add(card1);
			if (card1.getValue() > card2.getValue()) {    // Player 1 wins round
				this.player.winRound(cards);
				System.out.println(this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beats " + this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
				System.out.println(this.player.getName() + " wins the war and gains " + this.opponent.getUsedCards() + " cards!\n");
			}
			else if (card1.getValue() < card2.getValue()) {   // Player 2 wins round
				this.opponent.winRound(cards);
				System.out.println(this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beats " + this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
				System.out.println(this.opponent.getName() + " wins the war and gains " + this.player.getUsedCards() + " cards!\n");
			}
			else {       // Enter sub-war
				System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());
				System.out.println("/nWAR AGAIN!!!");
				return true;
			}
		}
		resetUsed();
		return false;
	}
}