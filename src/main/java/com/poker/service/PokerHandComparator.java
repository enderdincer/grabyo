package com.poker.service;

import com.poker.PokerHand;

/**
 * @author enderdincer
 */
public interface PokerHandComparator {

    PokerHand.Result compare(PokerHand playerHand, PokerHand opponentHand);
}
