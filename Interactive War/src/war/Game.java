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
	private int playerWins;			// == 1 if Player wins, == -1 if Opponent wins (only used when either player does not have enough cards to start war) 
	
	
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
	 * @return 1 = Player wins; -1 = Opponent wins; 0 = No winner
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
	 * Gathers cards used by the players
	 * @param card1 Player 1's card
	 * @param card2 Player 2's card
	 * @param cards Set of cards played in the round
	 */
	public void assembleCards(Card card1, Card card2, ArrayList<Card> cards) {
		cards.add(card1);
		cards.add(card2);
	}
	
	
	/**
	 * Resets the number of cards each player has used in the round
	 */
	public void resetUsed() {
		this.player.resetUsed();
		this.opponent.resetUsed();
	}
	
	
	/**
	 * Plays face-up cards for each player
	 * @param cards Set of cards played in the round
	 * @return Set of cards played in the round
	 */
	public ArrayList<Card> playCards(ArrayList<Card> cards) {
		Card card1 = this.player.playCard();
		this.player.setFaceUp(card1);
		Card card2 = this.opponent.playCard();
		this.opponent.setFaceUp(card2);
		assembleCards(card1, card2, cards);
		return cards;
	}
	
	
	/**
	 * Simulates an instance of a round
	 * @param cards Set of cards played in the round
	 * @return true if game enters war phase; false otherwise
	 */
	public boolean playRound(ArrayList<Card> cards) {
		cards = playCards(cards);
		Card card1 = this.player.getFaceUp();
		Card card2 = this.opponent.getFaceUp();
		
		if (card1.getValue() > card2.getValue()) {   // Player wins round
			this.player.winRound(cards);
			System.out.println(this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beats " + this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
		}
		else if (card1.getValue() < card2.getValue()) {     // Opponent wins round
			this.opponent.winRound(cards);
			System.out.println(this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beats " + this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
		}
		else {
			System.out.println("Both players played " + card2.getRank() + "s");     
			System.out.println("\nWAR!!!");

			if (this.player.getScore() < 4) {          // Player cannot enter war
				this.opponent.winRound(cards);
				System.out.println(this.player.getName() + " has insufficient cards to play out the war");
				this.playerWins = -1;
			}
			else if (this.opponent.getScore() < 4) {          // Opponent cannot enter war
				this.player.winRound(cards);
				System.out.println(this.opponent.getName() + " has insufficient cards to play out the war");
				this.playerWins = 1;
			}
			else {
				return true;				// Enter war
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
			assembleCards(this.player.playCard(), this.opponent.playCard(), cards);   // Places face-down cards for each player
		}
		System.out.println("Both players allocate 3 face-down cards...");
	}
	
	
	/**
	 * Simulates war given the number of face-down cards
	 * @param cards Set of cards played in the round
	 * @param gambitSize Number of face-down cards
	 * @return true if game enters sub-war phase; false otherwise
	 */
	public boolean computeWar(ArrayList<Card> cards, int gambitSize) {
		cards = playCards(cards);
		Card card1 = this.player.getFaceUp();
		Card card2 = this.opponent.getFaceUp();

		if (card1.getValue() > card2.getValue()) {     // Player wins round
			this.player.winRound(cards);
			System.out.println(this.player.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beats " + this.opponent.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
			System.out.println(this.player.getName() + " wins the war and gains " + this.opponent.getUsedCards() + " cards!\n");
		}
		else if (card1.getValue() < card2.getValue()) {   // Opponent wins round
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
	
	
	/**
	 * Directs game to different war outcomes
	 * @param cards Set of cards played in the round
	 * @return 1 = Conditions for warSmall; 2 = Conditions for warAgainSmall; 3 = Conditions for warRegular
	 */
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
	 * Enters war with less than standard number of cards
	 * @param cards Set of cards played in the round
	 * @return Set of cards played in the round and number of face-down cards
	 */
	public ArrayList<Object> warSmall(ArrayList<Card> cards) {
		int gambitSize = 0;                            								// Number of face-down cards
		ArrayList<Object> returns = new ArrayList<Object>();
		
		Player losing;																// Player with lower score
		if (this.player.getScore() < 4 && this.player.getScore() > 1) {
			losing = this.player;
		}
		else {
			losing = this.opponent;
		}
		
		gambitSize = losing.getScore() - 1;
		for (int i = 0; i < gambitSize; i++) {
			assembleCards(this.player.playCard(), this.opponent.playCard(), cards);    // Places face-down cards for each player
		}
		
		System.out.println("Both players allocate " + gambitSize + " face-down cards...");
		returns.add(cards);
		returns.add(gambitSize);
		return returns;
	}
	
	
	/**
	 * Simulates sub-war where one player cannot play face-down cards
	 * @param cards Set of cards played in the round
	 * @param gambitSize Number of face-down cards from the previous round
	 * @return true if game enters sub-war phase; false otherwise
	 */
	public boolean computeWarSmall(ArrayList<Card> cards, int gambitSize) {
		Player winning;                          								// Player with higher score
		Player losing;							 								// Player with lower score 
		if (this.player.getScore() == 0) {
			winning = this.opponent;
			losing = this.player;
		}
		else {
			winning = this.player;
			losing = this.opponent;
		}
		
		for (int i = 0; i < gambitSize; i++) {   // Player with more cards places down same number of face-down cards as previous round
			cards.add(winning.playCard());
		}
		System.out.println(winning.getName() + " allocates " + gambitSize + " face-down cards...");
		Card winningCard = winning.playCard();
		winning.setFaceUp(winningCard);
		Card losingCard = losing.getFaceUp();
		cards.add(winningCard);
		
		if (winningCard.getValue() > losingCard.getValue()) {  // Winning player wins round
			winning.winRound(cards);
			System.out.println(winning.getName() + "'s " + winningCard.getRank() + " of " + winningCard.getSuit() + " beats " + losing.getName() + "'s " + losingCard.getRank() + " of " + losingCard.getSuit());
			System.out.println(winning.getName() + " wins the war and gains " + losing.getUsedCards() + " cards!\n");
		}
		else if (winningCard.getValue() < losingCard.getValue()) {   // Losing player wins round
			losing.winRound(cards);
			System.out.println(losing.getName() + "'s " + losingCard.getRank() + " of " + losingCard.getSuit() + " beats " + winning.getName() + "'s " + winningCard.getRank() + " of " + winningCard.getSuit());
			System.out.println(losing.getName() + " wins the war and gains " + winning.getUsedCards() + " cards!\n");
		}
		else {     // Enter sub-war
			System.out.println("Both players played " + winningCard.getRank() + " of " + winningCard.getSuit());
			System.out.println("\nWAR AGAIN!!!");
			return true;
		}
		
		resetUsed();
		return false;
	}
}