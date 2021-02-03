package com.poker.service.eval;

import com.poker.model.EvalResult;
import com.poker.model.PokerHandInfo;

/**
 * @author enderdincer
 */
public interface EvalResultHandler {

  /**
   * Handles evaluation results based on win groups.
   *
   * @param evalResult
   * @return
   */
  PokerHandInfo handle(EvalResult evalResult);
}
