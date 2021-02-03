package com.poker.service;

import com.poker.PokerHand;
import com.poker.model.Card;

import java.util.List;

/**
 * @author enderdincer
 */
public interface PokerHandExtractor {

    List<Card> extract(String pokerHandStr);

    List<Card> extract(PokerHand pokerHand);
}
