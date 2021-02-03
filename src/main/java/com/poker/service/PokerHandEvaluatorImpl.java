package com.poker.service;

import com.poker.common.CardUtils;
import com.poker.model.Card;
import com.poker.model.EvalResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/** @author enderdincer */
@Slf4j
@Service
public class PokerHandEvaluatorImpl implements PokerHandEvaluator {

  /**
   * Processes and converts list of cards into EvalResult which holds statistical data about players
   * hand.
   *
   * @param cards list of cards, players hand
   * @return Meaningful evaluation of player hand
   */
  @Override
  public EvalResult evaluate(List<Card> cards) {

    EvalResult.EvalResultBuilder builder =
        EvalResult.builder()
            .sortedByRankDesc(CardUtils.basicSortByRank(cards, true))
            .isAllSuitsMatched(allSuitsMatched(cards))
            .rankSum(handRankSum(cards));

    findHighestRankedCard(cards).ifPresent(builder::highestRankedCard);

    checkSequence(cards)
        .ifPresent(
            card -> {
              builder.isAllInSequence(true);
              builder.highestRankedCardInSequence(card);
            });

    findRankGroups(cards)
        .ifPresent(
            rankGroups -> {
              builder.isRankGroup(true);
              builder.rankGroups(rankGroups);
            });

    return builder.build();
  }

  /**
   * It does not take into account ACE's lower value.
   *
   * @param cards
   * @return sum of the ranks in hand
   */
  private int handRankSum(List<Card> cards) {
    return cards.stream().mapToInt(Card::getRankVal).sum();
  }

  /**
   * Finds the highest rank in hand, does not take into account ACE's lower value.
   *
   * @param cards
   * @return
   */
  private Optional<Card> findHighestRankedCard(List<Card> cards) {
    return cards.stream().max(Comparator.comparing(Card::getRankVal));
  }

  /**
   * Detects a sequence if there is any. If ACE takes its lower value, highest rank in the sequence
   * will be 5
   *
   * @param cards list of cards, players hand
   * @return highest rank of the sequence
   */
  private Optional<Card> checkSequence(List<Card> cards) {

    List<Card> hand = CardUtils.basicSortByRank(cards, false);

    for (int i = 0; i < hand.size() - 1; i++) {
      if (i >= hand.size() - 2) {
        break;
      }
      if (!CardUtils.isSequentialPairByRank(hand.get(i), hand.get(i + 1))) {
        return Optional.empty();
      }
    }

    Card lastCard = hand.get(hand.size() - 1);

    if (lastCard.isAce() && CardUtils.isTwo(hand.get(0))) {
      return Optional.of(hand.get(hand.size() - 2));
    }

    return Optional.of(lastCard);
  }

  /**
   * Check if all suits are the same.
   * Required for flush type winnings.
   *
   * @param cards  list of cards, players hand
   * @return true if all suits are the same, false otherwise
   */
  private boolean allSuitsMatched(List<Card> cards) {
    String suit = cards.get(0).getSuit();
    return cards.stream().allMatch(card -> card.getSuit().toUpperCase().equals(suit));
  }

  /**
   * Finds rank groupings like pairs, three of a kinds, etc.
   *
   * Example hand: 5s 5c 7d 7c 7s
   * Example return: 2 -> [(5s, 5c)], 3 -> [(7d, 7c, 7s)]
   *
   * Example hand: 4s 4c 6d 6c Jd
   * Example return: 2 -> [(4s, 4c), (6d, 6c)]
   *
   * @param cards list of cards, players hand
   * @return rank grouping type to list of rank groups
   */
  private Optional<Map<Integer, List<List<Card>>>> findRankGroups(List<Card> cards) {
    Map<Integer, List<List<Card>>> rankGroups =
        cards.stream()
            .collect(Collectors.groupingBy(Card::getRankVal))
            .values()
            .stream()
            .filter(cardList -> cardList.size() > 1)
            .collect(Collectors.groupingBy(List::size));

    if (rankGroups.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(rankGroups);
  }
}
