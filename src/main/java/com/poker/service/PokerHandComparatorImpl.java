package com.poker.service;

import com.poker.PokerHand;
import com.poker.exception.IdenticalHandException;
import com.poker.model.Card;
import com.poker.model.PokerHandInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Compares player hands depending on the PokerHandType AND tie breakers if needed
 *
 * @author enderdincer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PokerHandComparatorImpl implements PokerHandComparator {

  private final PokerHandExtractor pokerHandExtractor;
  private final PokerHandInfoService pokerHandInfoService;

  /**
   * Converts PokerHand to List of Cards. Gets PokerHandInfo to compare player hands by using list
   * of cards.
   *
   * @param playerHand results are based on this. if wins the result is a win.
   * @param opponentHand
   * @return
   */
  @Override
  public PokerHand.Result compare(PokerHand playerHand, PokerHand opponentHand) {

    if (playerHand.getHand().equals(opponentHand.getHand())) {
      log.debug("Identical hands detected.");
      throw new IdenticalHandException("Players cannot have identical hands.");
    }

    List<Card> playerCards = pokerHandExtractor.extract(playerHand);
    List<Card> opponentCards = pokerHandExtractor.extract(opponentHand);

    PokerHandInfo playerHandInfo = pokerHandInfoService.getHandInfo(playerCards);
    PokerHandInfo opponentHandInfo = pokerHandInfoService.getHandInfo(opponentCards);

    return compareHandInfos(playerHandInfo, opponentHandInfo);
  }

  /**
   * Simple comparison based PokerHandType hierarchy. Calls getTieBreakerResult if hierarchies are
   * equal.
   *
   * @param playerHandInfo
   * @param opponentHandInfo
   * @return
   */
  private PokerHand.Result compareHandInfos(
      PokerHandInfo playerHandInfo, PokerHandInfo opponentHandInfo) {

    if (playerHandInfo.getHandType().getHierarchy()
        > opponentHandInfo.getHandType().getHierarchy()) {
      return PokerHand.Result.WIN;
    }

    if (playerHandInfo.getHandType().getHierarchy()
        < opponentHandInfo.getHandType().getHierarchy()) {
      return PokerHand.Result.LOSS;
    }

    return getTieBreakerResult(playerHandInfo, opponentHandInfo);
  }

  /**
   * Iterates and compares tie breakers. If tie is not broken after the iteration returns tie.
   *
   * @param playerHandInfo
   * @param opponentHandInfo
   * @return
   */
  private PokerHand.Result getTieBreakerResult(
      PokerHandInfo playerHandInfo, PokerHandInfo opponentHandInfo) {

    for (int i = 0; i < playerHandInfo.getTieBreakers().size(); i++) {

      int playerTieBreaker = playerHandInfo.getTieBreakers().get(i);
      int opponentTieBreaker = opponentHandInfo.getTieBreakers().get(i);

      if (playerTieBreaker > opponentTieBreaker) {
        return PokerHand.Result.WIN;
      }

      if (playerTieBreaker < opponentTieBreaker) {
        return PokerHand.Result.LOSS;
      }
    }

    return PokerHand.Result.TIE;
  }
}
