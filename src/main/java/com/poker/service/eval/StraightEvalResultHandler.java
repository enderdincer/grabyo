package com.poker.service.eval;

import com.poker.common.CollectionUtils;
import com.poker.model.EvalResult;
import com.poker.model.PokerHandInfo;
import com.poker.model.PokerHandType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles evaluation result of the poker hand
 *
 * @author enderdincer
 */
@Service
public class StraightEvalResultHandler implements EvalResultHandler {

  @Override
  public PokerHandInfo handle(EvalResult evalResult) {
    List<Integer> straightTieBreakers =
        CollectionUtils.listOf(
            ArrayList<Integer>::new, evalResult.getHighestRankedCardInSequence().getRankVal());

    return PokerHandInfo.builder()
        .handType(PokerHandType.STRAIGHT)
        .tieBreakers(straightTieBreakers)
        .build();
  }
}
