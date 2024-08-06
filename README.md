# War-Card-Game-Interactive
Interactive implementation of the card game War

Rules:
A standard deck of playing cards is shuffled and evenly distributed to two players. Every round, each player reveals their top card in unison and the player revealing the card of higher rank wins both of the cards in play. If both players reveal a card of the same rank, the game enters "War" where each player must then allocate 3 of their top cards as face-down cards and then reveal their fourth card. The player revealing the card of higher rank wins all cards introduced during the round. The game continues until one player has no more cards left. 

  Special Conditions:
    - If a player does not have enough cards to enter a war, the game ends and that player automatically loses
    - If both players reveal a card of the same rank during a war, the game continuously enters another war until the revealing cards do not match. The player revealing the card of higher rank during the final war wins all cards introduced during the round.
    - If a player has enough cards to enter a war but does not have 3 face-down cards when entering a sub-war, the player must use the remaining cards as face-down cards and the very last card as face-up. The opponent must match the same number of cards used in that sub-war.
    - If a player has no cards remaining when entering a sub-war, the player keeps their current face-up card while the opponent puts down the same amount of cards face-down as the previous round and reveals a new face-up card.

Special conditions in comply with https://www.youtube.com/watch?v=7lY5eOrrvyA&list=PLEFF1F8917C486FF8&index=7&ab_channel=ExpertVillageLeafGroup
