package com.poker.service;

import com.poker.factory.EvalResultHandlerFactory;
import com.poker.model.Card;
import com.poker.model.EvalResult;
import com.poker.model.PokerHandInfo;
import com.poker.service.eval.EvalResultHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author enderdincer
 */
@Service
@RequiredArgsConstructor
public class PokerHandInfoServiceImpl implements PokerHandInfoService {

  private final PokerHandEvaluator pokerHandEvaluator;
  private final EvalResultHandlerFactory evalResultHandlerFactory;

  @Override
  public PokerHandInfo getHandInfo(List<Card> cards) {
    EvalResult handEvalResult = pokerHandEvaluator.evaluate(cards);

    EvalResultHandler handler = evalResultHandlerFactory.get(handEvalResult);

    return handler.handle(handEvalResult);
  }
}
