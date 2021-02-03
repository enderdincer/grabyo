package com.poker.service.eval;

import com.poker.common.CollectionUtils;
import com.poker.model.EvalResult;
import com.poker.model.PokerHandInfo;
import com.poker.model.PokerHandType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles evaluation result of the poker hand
 *
 * @author enderdincer
 */
@Service
public class RankGroupsEvalResultHandler implements EvalResultHandler {

  @Override
  public PokerHandInfo handle(EvalResult evalResult) {

    boolean hasFourKinds = evalResult.getRankGroups().containsKey(4);

    if (hasFourKinds) {

      List<Integer> fourOfAKindTieBreakers =
          CollectionUtils.listOf(
              ArrayList<Integer>::new,
              evalResult.getRankGroups().get(4).get(0).get(0).getRankVal());

      return PokerHandInfo.builder()
          .handType(PokerHandType.FOUR_OF_A_KIND)
          .tieBreakers(fourOfAKindTieBreakers)
          .build();
    }

    boolean hasThreeKinds = evalResult.getRankGroups().containsKey(3);
    boolean hasPairs = evalResult.getRankGroups().containsKey(2);

    if (hasThreeKinds & hasPairs) {

      List<Integer> fullHouseTieBreakers =
          CollectionUtils.listOf(
              ArrayList<Integer>::new,
              evalResult.getRankGroups().get(3).get(0).get(0).getRankVal(),
              evalResult.getRankGroups().get(2).get(0).get(0).getRankVal());

      return PokerHandInfo.builder()
          .handType(PokerHandType.FULL_HOUSE)
          .tieBreakers(fullHouseTieBreakers)
          .build();
    }

    if (hasThreeKinds) {

      List<Integer> threeOfAKindTieBreakers =
          CollectionUtils.listOf(
              ArrayList<Integer>::new,
              evalResult.getRankGroups().get(3).get(0).get(0).getRankVal());

      return PokerHandInfo.builder()
          .handType(PokerHandType.THREE_OF_A_KIND)
          .tieBreakers(threeOfAKindTieBreakers)
          .build();
    }

    PokerHandInfo.PokerHandInfoBuilder pokerHandInfoBuilder = PokerHandInfo.builder();

    PokerHandType pairHandType =
        evalResult.getRankGroups().get(2).size() == 2
            ? PokerHandType.TWO_PAIR
            : PokerHandType.ONE_PAIR;

    List<Integer> pairsTieBreakers =
        evalResult.getRankGroups().get(2).stream()
            .map(pair -> pair.get(0).getRankVal())
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

    return pokerHandInfoBuilder.handType(pairHandType).tieBreakers(pairsTieBreakers).build();
  }
}
