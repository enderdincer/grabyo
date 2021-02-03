package com.poker.provider;

import com.poker.model.EvalResult;
import com.poker.service.eval.EvalResultHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Provides the right handler for the given win group.
 *
 * @author enderdincer
 */
@Component
public class EvalResultHandlerProvider {

  private final EvalResultHandler flushHandler;
  private final EvalResultHandler straightHandler;
  private final EvalResultHandler rankGroupsHandler;
  private final EvalResultHandler highCardHandler;

  public EvalResultHandlerProvider(
      @Qualifier("flushEvalResultHandler") EvalResultHandler flushHandler,
      @Qualifier("straightEvalResultHandler") EvalResultHandler straightHandler,
      @Qualifier("rankGroupsEvalResultHandler") EvalResultHandler rankGroupsHandler,
      @Qualifier("highCardEvalResultHandler") EvalResultHandler highCardHandler) {
    this.flushHandler = flushHandler;
    this.straightHandler = straightHandler;
    this.rankGroupsHandler = rankGroupsHandler;
    this.highCardHandler = highCardHandler;
  }

  public EvalResultHandler get(EvalResult evalResult) {

    if (evalResult.isAllSuitsMatched()) {
      return this.flushHandler;
    }

    if (evalResult.isAllInSequence()) {
      return this.straightHandler;
    }

    if (evalResult.isRankGroup()) {
      return this.rankGroupsHandler;
    }

    return this.highCardHandler;
  }
}
