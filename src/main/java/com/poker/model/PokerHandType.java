package com.poker.model;

/**
 * All possible ways to win and their hierarchies
 * in Texas Hold'em Poker
 *
 * @author enderdincer
 */
public enum PokerHandType {
  ROYAL_FLUSH(10),
  STRAIGHT_FLUSH(9),
  FOUR_OF_A_KIND(8),
  FULL_HOUSE(7),
  FLUSH(6),
  STRAIGHT(5),
  THREE_OF_A_KIND(4),
  TWO_PAIR(3),
  ONE_PAIR(2),
  HIGH_CARD(1);

  private final int hierarchy;

  PokerHandType(int hierarchy){
    this.hierarchy = hierarchy;
  }

  public int getHierarchy() {
    return this.hierarchy;
  }
}
