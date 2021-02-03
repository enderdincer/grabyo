package com.poker.service.eval;

import com.poker.common.CardUtils;
import com.poker.common.CollectionUtils;
import com.poker.model.EvalResult;
import com.poker.model.PokerHandInfo;
import com.poker.model.PokerHandType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Handles evaluation result of the poker hand
 *
 * @author enderdincer
 */
@Service
public class FlushEvalResultHandler implements EvalResultHandler {

  @Override
  public PokerHandInfo handle(EvalResult evalResult) {
    if (evalResult.getRankSum() == CardUtils.ROYAL_FLUSH_VAL_SUM) {
      return PokerHandInfo.builder()
          .handType(PokerHandType.ROYAL_FLUSH)
          .tieBreakers(Collections.emptyList())
          .build();
    }

    if (evalResult.isAllInSequence()) {

      List<Integer> straightFlushTieBreakers =
          CollectionUtils.listOf(
              ArrayList<Integer>::new, evalResult.getHighestRankedCardInSequence().getRankVal());

      return PokerHandInfo.builder()
          .handType(PokerHandType.STRAIGHT_FLUSH)
          .tieBreakers(straightFlushTieBreakers)
          .build();
    }

    List<Integer> flushTieBreakers =
        CollectionUtils.listOf(
            ArrayList<Integer>::new, evalResult.getHighestRankedCard().getRankVal());

    return PokerHandInfo.builder()
        .handType(PokerHandType.FLUSH)
        .tieBreakers(flushTieBreakers)
        .build();
  }
}
