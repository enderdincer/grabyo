package com.poker.service;

import com.poker.model.Card;
import com.poker.model.EvalResult;

import java.util.List;

/**
 *
 * @author enderdincer
 */
public interface PokerHandEvaluator {

  EvalResult evaluate(List<Card> cards);
}
