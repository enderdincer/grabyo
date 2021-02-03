package com.poker.service;

import com.poker.PokerHand;
import com.poker.common.CollectionUtils;
import com.poker.model.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.poker.common.CardUtils.*;

/**
 * Provides a better, more usable way for the cards in hand.
 *
 * @author enderdincer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PokerHandExtractorImpl implements PokerHandExtractor {

  @Override
  public List<Card> extract(String pokerHandStr) {

    if (Objects.isNull(pokerHandStr) || pokerHandStr.isEmpty()) {
      log.debug("Poker hand not valid {}: ", pokerHandStr);
      throw new IllegalArgumentException("Poker hand string is either null or empty");
    }

    String[] cardStrings = pokerHandStr.toUpperCase().split(StringUtils.SPACE);

    // todo validate all cardStrings are of length 2, can be length of 3 when it's 10
    // todo make sure ranks are valid, test has rank of "T" which doesn't exist

    return Arrays.stream(cardStrings).map(this::convertStr2Card).collect(Collectors.toList());
  }

  /**
   * Parses string pieces to Card object.
   *
   * @param cardStr
   * @return
   */
  private Card convertStr2Card(String cardStr) {

    String rankStr;
    String suitStr;

    if (cardStr.length() == 3) {
      rankStr = cardStr.substring(0, 2).toUpperCase();
      suitStr = cardStr.substring(2).toUpperCase();
    } else {
      rankStr = cardStr.substring(0, 1).toUpperCase();
      suitStr = cardStr.substring(1).toUpperCase();
    }

    if(!SUITS.contains(suitStr)){
      log.debug("Unknown suit type detected: {}", suitStr);
      throw new IllegalArgumentException("Unknown suit type");
    }

    Card.CardBuilder cardBuilder =
        Card.builder()
            .rankVal(getRankValue(rankStr))
            .rankStr(rankStr)
            .suit(suitStr)
            .secondaryRankVal(-1);

    if (isAce(cardStr)) {
      cardBuilder.secondaryRankVal(ACE_LOW_VAL).isAce(true);
    }

    return cardBuilder.build();
  }

  /**
   * Parses rank from rank string
   * @param rankStr
   * @return
   */
  private int getRankValue(String rankStr) {
    // if alpha numeric then simply parse to int
    if (NumberUtils.isCreatable(rankStr)) {
      return Integer.parseInt(rankStr);
    }

    if(!RANK_LOOK_UP.containsKey(rankStr)){
      log.debug("Unknown rank detected: {}", rankStr);
      throw new IllegalArgumentException("Unknown rank type");
    }

    // use look up otherwise
    return RANK_LOOK_UP.get(rankStr);
  }

  @Override
  public List<Card> extract(PokerHand pokerHand) {
    return this.extract(pokerHand.getHand());
  }
}
