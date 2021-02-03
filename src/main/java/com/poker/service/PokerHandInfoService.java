package com.poker.service;

import com.poker.model.Card;
import com.poker.model.PokerHandInfo;

import java.util.List;

/** @author enderdincer */
public interface PokerHandInfoService {

  PokerHandInfo getHandInfo(List<Card> cards);
}
