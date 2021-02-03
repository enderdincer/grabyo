# GRABYO TEST TASK

### Solution is based on the following Texas Hold'em Poker rules

Source: https://www.instructables.com/Learn-To-Play-Poker---Texas-Hold-Em-aka-Texas-Ho/

Hand Rankings  
A hand always consist of five cards. Individual cards are "ranked" as follows (high-to-low): A, K, Q, J, 10, 9, 8, 7, 6, 5, 4, 3, 2. ACE can be low, but only when part of an A-2-3-4-5 straight. Suits (Club, Diamond, Heart, Spade) have no value, so if two players have hands that are identical except for suit, then they are tied. A "Kicker" card is a high card used to break ties between hands of the same rank (ex. 2 players with "Four of a Kind", 3 K's on the board. P1 has K, 9 and P2 has K, 6. P1 with K, 9 wins with the "9 Kicker".)

Here are the "Rank of Hands" in the order of Strength with Probability of being dealt.  
  
Royal Flush- A, K, Q, J, 10, all in the same suit. 1 in 650,000  
    
Straight Flush - Five cards in sequence, all of the same suit. 1in 65,000
    
Four of a Kind- Four cards of one rank. Kicker breaks ties. 1 in 4,000
    
Full House- Three matching cards of one rank, plus Two matching cards of another rank. Higher ranking set of three wins. If two players have the same set of three, the player with the higher pair wins. 1 in 700
    
Flush- Five cards of the same suit. High card wins. 1 in 500
    
Straight- Five cards of sequential rank, but different suit. High card wins. 1 in 250
    
Three of a kind- Three cards of the same rank, plus two unmatched cards. High set wins. 1 in 50
    
Two Pair- Two cards of the same rank, plus Two cards of another rank. High pair wins. 1 in 20
    
One Pair- Two cards of the same rank, plus Three unmatched cards. High pair wins. 1 in 2 1/3
    
High Card- One card high, plus four unmatched lower ranking cards. Ace is the Highest card. Kicker breaks ties. 1 in 1

### Solution Flow (bottom-up)

1- Convert string to an object that is easy to use programmatically. (See Card object)
  
2- Since poker hand string is now list of cards it can be processed with PokerHandEvaluator.
  
3- PokerHandEvaluator returns and EvalResult that holds information about what type of winning rules players hand has. Is it sequential? Are all suit types the same?

4- Then PokerHandInfoService, by selecting the right EvalResultHandler based on the winning rules, builds a PokerHandInfo object that holds the final information about the players hand. What kind of win is it? What should be used in case of a tie?
  
5- Finally PokerHandComparator compares two hands based on the hand type (Royal Flush, Full House, etc.)




    
