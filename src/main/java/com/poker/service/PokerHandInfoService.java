package com.poker.service;

import com.poker.model.Card;
import com.poker.model.PokerHandInfo;

import java.util.List;

public interface PokerHandInfoService {

    PokerHandInfo getHandInfo(List<Card> cards);
}
