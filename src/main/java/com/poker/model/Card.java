package com.poker.model;

import lombok.Builder;
import lombok.Data;

/**
 * Model object that represents a card in
 * typical 52 card deck with no Joker cards.
 *
 * @author enderdincer
 */
@Data
@Builder
public class Card {

  private int rankVal;
  private boolean isAce;
  private int secondaryRankVal;
  private String rankStr;
  private String suit;
}
