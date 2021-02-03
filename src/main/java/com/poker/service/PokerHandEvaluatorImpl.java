package com.poker.service;

import com.poker.common.CardUtils;
import com.poker.model.Card;
import com.poker.model.EvalResult;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PokerHandEvaluatorImpl implements PokerHandEvaluator {

  @Override
  public EvalResult evaluate(List<Card> cards) {

    EvalResult.EvalResultBuilder builder =
        EvalResult.builder()
            .sortedByRankDesc(CardUtils.basicSortByRank(cards, true))
            .isAllSuitsMatched(allSuitsMatched(cards))
            .rankSum(handRankSum(cards));

    findHighestRankedCard(cards).ifPresent(builder::highestRankedCard);

    findHighestRankedCardInSequence(cards)
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

  private int handRankSum(List<Card> cards) {
    return cards.stream().mapToInt(Card::getRankVal).sum();
  }

  private Optional<Card> findHighestRankedCard(List<Card> cards) {
    return cards.stream().max(Comparator.comparing(Card::getRankVal));
  }

  private Optional<Card> findHighestRankedCardInSequence(List<Card> cards) {

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

  private boolean allSuitsMatched(List<Card> cards) {
    String suit = cards.get(0).getSuit();
    return cards.stream().allMatch(card -> card.getSuit().toUpperCase().equals(suit));
  }

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
