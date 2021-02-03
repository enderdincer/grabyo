package com.poker.service.eval;

import com.poker.model.Card;
import com.poker.model.EvalResult;
import com.poker.model.PokerHandInfo;
import com.poker.model.PokerHandType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles evaluation result of the poker hand
 *
 * @author enderdincer
 */
@Service
public class HighCardEvalResultHandler implements EvalResultHandler {


  /**
   * Builds PokerHandInfo for the "Highest ranked card wins" case.
   * This type of winning requires all cards in hand as tie breakers
   * since players can 4 identical ranks.
   *
   * @param evalResult
   * @return
   */
  @Override
  public PokerHandInfo handle(EvalResult evalResult) {

    List<Integer> tieBreakers =
        evalResult.getSortedByRankDesc().stream()
            .map(Card::getRankVal)
            .collect(Collectors.toList());

    return PokerHandInfo.builder()
        .handType(PokerHandType.HIGH_CARD)
        .tieBreakers(tieBreakers)
        .build();
  }
}
