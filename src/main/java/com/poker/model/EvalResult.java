package com.poker.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Intermediate object that holds statistical information
 * that will be used to determine what type of win
 * the player has.
 *
 * For example "Flush" wins (Royal Flush, Straight Flush, Flush)
 * requires same suit type for each card in hand.
 *
 * @author enderdincer
 */
@Data
@Builder
public class EvalResult {

  private boolean isAllSuitsMatched;
  private boolean isAllInSequence;
  private Card highestRankedCardInSequence;
  private boolean isRankGroup;
  private Map<Integer, List<List<Card>>> rankGroups;
  private int rankSum;
  private Card highestRankedCard;
  private List<Card> sortedByRankDesc;
}
