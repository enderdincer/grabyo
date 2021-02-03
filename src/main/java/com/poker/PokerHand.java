package com.poker;

import com.poker.common.SpringContext;
import com.poker.service.PokerHandComparator;

import java.util.Objects;

/** @author grabyo */
public class PokerHand {

  private final String hand;

  public enum Result {
    WIN,
    LOSS,
    TIE;
  }

  public PokerHand(String hand) {
    if (Objects.isNull(hand)) {
      throw new IllegalArgumentException("Poker hand string cannot be null.");
    }
    this.hand = hand;
  }

  public String getHand() {
    return hand;
  }

  public Result compareWith(PokerHand hand) {
    return SpringContext.getBean(PokerHandComparator.class).compare(this, hand);
  }
}
