package com.poker.common;

import com.poker.model.Card;

import java.util.*;

/**
 * Simple utility for card operations.
 *
 * @author enderdincer
 */
public final class CardUtils {

  private CardUtils() {}

  public static final String ACE = "A";
  public static final int ACE_HIGH_VAL = 14;
  public static final int ACE_LOW_VAL = 1;

  public static final String KING = "K";
  public static final int KING_VAL = 13;

  public static final String QUEEN = "Q";
  public static final int QUEEN_VAL = 12;

  public static final String JACK = "J";
  public static final int JACK_VAL = 11;

  public static final String TEN = "10";
  public static final int TEN_VAL = 10;

  public static final String TWO = "2";

  public static final int ROYAL_FLUSH_VAL_SUM =
      ACE_HIGH_VAL + KING_VAL + QUEEN_VAL + JACK_VAL + TEN_VAL;

  public static final Map<String, Integer> RANK_LOOK_UP =
      CollectionUtils.mapOf(
          HashMap::new, JACK, JACK_VAL, QUEEN, QUEEN_VAL, KING, KING_VAL, ACE, ACE_HIGH_VAL);

  public static final String SPADES = "S";
  public static final String DIAMONDS = "D";
  public static final String CLUBS = "C";
  public static final String HEARTS = "H";

  public static final Set<String> SUITS =
      CollectionUtils.setOf(HashSet::new, SPADES, DIAMONDS, CLUBS, HEARTS);

  public static boolean hasAce(List<Card> cards) {
    return cards.stream().anyMatch(card -> isAce(card.getRankStr()));
  }

  public static boolean isAce(String cardStr) {
    return cardStr.substring(0, 1).toUpperCase().equals(ACE);
  }

  public static boolean isAce(Card card) {
    return card.getRankStr().toUpperCase().equals(ACE);
  }

  public static boolean isTwo(Card card) {
    return card.getRankStr().toUpperCase().equals(TWO);
  }

  public static boolean isSequentialPairByRank(Card firstCard, Card secondCard) {
    return firstCard.getRankVal() + 1 == secondCard.getRankVal();
  }

  /**
   * Sorts cards based on their rank with the specified order type. (ascending or descending)
   *
   * @param cards list of cards to be sorted
   * @param isDesc sorts in descending order if true, ascending otherwise
   * @return sorted list of cards
   */
  public static List<Card> basicSortByRank(List<Card> cards, boolean isDesc) {

    List<Card> copyCards = new ArrayList<>(cards);

    Comparator<Card> cardComparator =
        isDesc
            ? Comparator.comparing(Card::getRankVal, Comparator.reverseOrder())
            : Comparator.comparing(Card::getRankVal, Comparator.naturalOrder());

    copyCards.sort(cardComparator);

    return copyCards;
  }
}
