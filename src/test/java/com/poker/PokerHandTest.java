package com.poker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.poker.PokerHand.Result;

/** Unit test for simple App. */
public class PokerHandTest {

  	@Test (expected = IllegalArgumentException.class)
  	public void newHandTestNull() {
  		new PokerHand(null);
  	}

  /** Expected: Highest ranked card wins */
  @Test
  public void highCardWin() {
    PokerHand hand1 = new PokerHand("As 2h 3c 5d 7d");
    PokerHand hand2 = new PokerHand("Kc 2s 5h Jh 7c");

    assertEquals(Result.WIN, hand1.compareWith(hand2));
    assertEquals(Result.LOSS, hand2.compareWith(hand1));

    // without ACE
    PokerHand hand3 = new PokerHand("10c 2s 5h Jh 7c");
    PokerHand hand4 = new PokerHand("7s 2h 3c 5D 6d");

    assertEquals(Result.WIN, hand3.compareWith(hand4));
    assertEquals(Result.LOSS, hand4.compareWith(hand3));

    // high card win with tie breaker
    PokerHand hand5 = new PokerHand("10s Jh 7C 5d 3d");
    PokerHand hand6 = new PokerHand("10c Js 7h 4h 2c");

    assertEquals(Result.WIN, hand5.compareWith(hand6));
    assertEquals(Result.LOSS, hand6.compareWith(hand5));
  }

  @Test
  public void onePairWin() {
    PokerHand onePairHand = new PokerHand("Js JH 3c 5d 7d");
    PokerHand highCardOnlyHand = new PokerHand("Ac 2s 5h Jh 7c");

    assertEquals(Result.WIN, onePairHand.compareWith(highCardOnlyHand));
    assertEquals(Result.LOSS, highCardOnlyHand.compareWith(onePairHand));
  }

  @Test
  public void twoPairWin() {
    PokerHand twoPairHand = new PokerHand("Js Jh 3c 3d 7d");
    PokerHand twoPairHandWithLowRank = new PokerHand("10s 10h 3c 3d 7d");

    PokerHand onePairHand = new PokerHand("10s 10h 3c 5d 7d");
    PokerHand highCardOnlyHand = new PokerHand("Ac 2s 5h Jh 7c");

    // against one pair
    assertEquals(Result.WIN, twoPairHand.compareWith(onePairHand));
    assertEquals(Result.LOSS, onePairHand.compareWith(twoPairHand));

    // against high card
    assertEquals(Result.WIN, twoPairHand.compareWith(highCardOnlyHand));
    assertEquals(Result.LOSS, highCardOnlyHand.compareWith(twoPairHand));

    // win with tie breaker
    assertEquals(Result.WIN, twoPairHand.compareWith(twoPairHandWithLowRank));
    assertEquals(Result.LOSS, twoPairHandWithLowRank.compareWith(twoPairHand));
  }

  @Test
  public void threeOfAKindWin() {
    PokerHand threeOfAKindHand = new PokerHand("3s 3h 3c 5d 7d");
    PokerHand threeOfAKindHandWithLowRank = new PokerHand("2s 2h 3c 2d 7d");

    PokerHand twoPairHand = new PokerHand("Js Jh 3c 3d 7d");
    PokerHand onePairHand = new PokerHand("10s 10h 3c 5d 7d");
    PokerHand highCardOnlyHand = new PokerHand("Ac 2s 5h Jh 7c");

    // against two pair
    assertEquals(Result.WIN, threeOfAKindHand.compareWith(twoPairHand));
    assertEquals(Result.LOSS, twoPairHand.compareWith(threeOfAKindHand));

    // against one pair
    assertEquals(Result.WIN, threeOfAKindHand.compareWith(onePairHand));
    assertEquals(Result.LOSS, onePairHand.compareWith(threeOfAKindHand));

    // against high card
    assertEquals(Result.WIN, threeOfAKindHand.compareWith(highCardOnlyHand));
    assertEquals(Result.LOSS, highCardOnlyHand.compareWith(threeOfAKindHand));

    // win with tie breaker
    assertEquals(Result.WIN, threeOfAKindHand.compareWith(threeOfAKindHandWithLowRank));
    assertEquals(Result.LOSS, threeOfAKindHandWithLowRank.compareWith(threeOfAKindHand));
  }

  @Test
  public void straightWin() {
    PokerHand straightHand = new PokerHand("6s 3h 4c 5d 7d");
    PokerHand straightHandWithLowRank = new PokerHand("6s 3h 4c 5d 2d");

    PokerHand threeOfAKindHand = new PokerHand("3s 3h 3c 5d 7d");
    PokerHand twoPairHand = new PokerHand("Js Jh 3c 3d 7d");
    PokerHand onePairHand = new PokerHand("10s 10h 3c 5d 7d");
    PokerHand highCardOnlyHand = new PokerHand("Ac 2s 5h Jh 7c");

    // against three of a kind
    assertEquals(Result.WIN, straightHand.compareWith(threeOfAKindHand));
    assertEquals(Result.LOSS, threeOfAKindHand.compareWith(straightHand));

    // against two pair
    assertEquals(Result.WIN, straightHand.compareWith(twoPairHand));
    assertEquals(Result.LOSS, twoPairHand.compareWith(straightHand));

    // against one pair
    assertEquals(Result.WIN, straightHand.compareWith(onePairHand));
    assertEquals(Result.LOSS, onePairHand.compareWith(straightHand));

    // against high card
    assertEquals(Result.WIN, straightHand.compareWith(highCardOnlyHand));
    assertEquals(Result.LOSS, highCardOnlyHand.compareWith(straightHand));

    // win with tie breaker
    assertEquals(Result.WIN, straightHand.compareWith(straightHandWithLowRank));
    assertEquals(Result.LOSS, straightHandWithLowRank.compareWith(straightHand));
  }

  @Test
  public void flushWin() {
    PokerHand flushHand = new PokerHand("Jd 3d 4d 8d 7d");
    PokerHand flushHandWithLowRank = new PokerHand("2c 3c 4c 8c 7c");

    PokerHand straightHand = new PokerHand("6s 3h 4c 5d 7d");
    PokerHand threeOfAKindHand = new PokerHand("3s 3h 3c 5d 7d");
    PokerHand twoPairHand = new PokerHand("Js Jh 3c 3d 7d");
    PokerHand onePairHand = new PokerHand("10s 10h 3c 5d 7d");
    PokerHand highCardOnlyHand = new PokerHand("Ac 2s 5h Jh 7c");

    // against straight
    assertEquals(Result.WIN, flushHand.compareWith(straightHand));
    assertEquals(Result.LOSS, straightHand.compareWith(flushHand));

    // against three of a kind
    assertEquals(Result.WIN, flushHand.compareWith(threeOfAKindHand));
    assertEquals(Result.LOSS, threeOfAKindHand.compareWith(flushHand));

    // against two pair
    assertEquals(Result.WIN, flushHand.compareWith(twoPairHand));
    assertEquals(Result.LOSS, twoPairHand.compareWith(flushHand));

    // against one pair
    assertEquals(Result.WIN, flushHand.compareWith(onePairHand));
    assertEquals(Result.LOSS, onePairHand.compareWith(flushHand));

    // against high card
    assertEquals(Result.WIN, flushHand.compareWith(highCardOnlyHand));
    assertEquals(Result.LOSS, highCardOnlyHand.compareWith(flushHand));

    // win with tie breaker
    assertEquals(Result.WIN, flushHand.compareWith(flushHandWithLowRank));
    assertEquals(Result.LOSS, flushHandWithLowRank.compareWith(flushHand));
  }

  @Test
  public void fullHouse() {
    PokerHand fullHouseHand= new PokerHand("3s 3c 3d 6c 6h");
    PokerHand fullHouseHandLowRank= new PokerHand("2s 2c 2d 4c 4h");
    PokerHand fullHouseHandLowRank2= new PokerHand("3s 3c 3d 4c 4h");

    PokerHand flushHand = new PokerHand("Jd 3d 4d 8d 7d");
    PokerHand straightHand = new PokerHand("6s 3h 4c 5d 7d");
    PokerHand threeOfAKindHand = new PokerHand("3s 3h 3c 5d 7d");
    PokerHand twoPairHand = new PokerHand("Js Jh 3c 3d 7d");
    PokerHand onePairHand = new PokerHand("10s 10h 3c 5d 7d");
    PokerHand highCardOnlyHand = new PokerHand("Ac 2s 5h Jh 7c");

    // against flush
    assertEquals(Result.WIN, fullHouseHand.compareWith(flushHand));
    assertEquals(Result.LOSS, flushHand.compareWith(fullHouseHand));

    // against straight
    assertEquals(Result.WIN, fullHouseHand.compareWith(straightHand));
    assertEquals(Result.LOSS, straightHand.compareWith(fullHouseHand));

    // against three of a kind
    assertEquals(Result.WIN, fullHouseHand.compareWith(threeOfAKindHand));
    assertEquals(Result.LOSS, threeOfAKindHand.compareWith(fullHouseHand));

    // against two pair
    assertEquals(Result.WIN, fullHouseHand.compareWith(twoPairHand));
    assertEquals(Result.LOSS, twoPairHand.compareWith(fullHouseHand));

    // against one pair
    assertEquals(Result.WIN, fullHouseHand.compareWith(onePairHand));
    assertEquals(Result.LOSS, onePairHand.compareWith(fullHouseHand));

    // against high card
    assertEquals(Result.WIN, fullHouseHand.compareWith(highCardOnlyHand));
    assertEquals(Result.LOSS, highCardOnlyHand.compareWith(fullHouseHand));

    // win with tie breaker
    assertEquals(Result.WIN, fullHouseHand.compareWith(fullHouseHandLowRank));
    assertEquals(Result.LOSS, fullHouseHandLowRank.compareWith(fullHouseHand));

    assertEquals(Result.WIN, fullHouseHand.compareWith(fullHouseHandLowRank2));
    assertEquals(Result.LOSS, fullHouseHandLowRank2.compareWith(fullHouseHand));
  }

  @Test
  public void fourOfAKind() {

    PokerHand fourOfAKindHand = new PokerHand("3s 3c 3d 3c 5h");
    PokerHand fourOfAHouseWithLowRank= new PokerHand("2s 2c 2d 2c 6h");
    PokerHand fullHouseHand= new PokerHand("3s 3c 3d 5c 5h");
    PokerHand flushHand = new PokerHand("Jd 3d 4d 8d 7d");
    PokerHand straightHand = new PokerHand("6s 3h 4c 5d 7d");
    PokerHand threeOfAKindHand = new PokerHand("3s 3h 3c 5d 7d");
    PokerHand twoPairHand = new PokerHand("Js Jh 3c 3d 7d");
    PokerHand onePairHand = new PokerHand("10s 10h 3c 5d 7d");
    PokerHand highCardOnlyHand = new PokerHand("Ac 2s 5h Jh 7c");

    // against full house
    assertEquals(Result.WIN, fourOfAKindHand.compareWith(fullHouseHand));
    assertEquals(Result.LOSS, fullHouseHand.compareWith(fourOfAKindHand));

    // against flush
    assertEquals(Result.WIN, fourOfAKindHand.compareWith(flushHand));
    assertEquals(Result.LOSS, flushHand.compareWith(fourOfAKindHand));

    // against straight
    assertEquals(Result.WIN, fourOfAKindHand.compareWith(straightHand));
    assertEquals(Result.LOSS, straightHand.compareWith(fourOfAKindHand));

    // against three of a kind
    assertEquals(Result.WIN, fourOfAKindHand.compareWith(threeOfAKindHand));
    assertEquals(Result.LOSS, threeOfAKindHand.compareWith(fourOfAKindHand));

    // against two pair
    assertEquals(Result.WIN, fourOfAKindHand.compareWith(twoPairHand));
    assertEquals(Result.LOSS, twoPairHand.compareWith(fourOfAKindHand));

    // against one pair
    assertEquals(Result.WIN, fourOfAKindHand.compareWith(onePairHand));
    assertEquals(Result.LOSS, onePairHand.compareWith(fourOfAKindHand));

    // against high card
    assertEquals(Result.WIN, fourOfAKindHand.compareWith(highCardOnlyHand));
    assertEquals(Result.LOSS, highCardOnlyHand.compareWith(fourOfAKindHand));

    // win with tie breaker
    assertEquals(Result.WIN, fourOfAKindHand.compareWith(fourOfAHouseWithLowRank));
    assertEquals(Result.LOSS, fourOfAHouseWithLowRank.compareWith(fourOfAKindHand));
  }

  @Test
  public void straightFlushWin() {

    PokerHand straightFlushHand = new PokerHand("3s 4s 6s 7s 5s");
    PokerHand straightFlushWithLowRank= new PokerHand("2d 3d 4d 5d 6d");
    PokerHand fourOfAKindHand = new PokerHand("3s 3c 3d 3c 5h");
    PokerHand fullHouseHand= new PokerHand("3s 3c 3d 5c 5h");
    PokerHand flushHand = new PokerHand("Jd 3d 4d 8d 7d");
    PokerHand straightHand = new PokerHand("6s 3h 4c 5d 7d");
    PokerHand threeOfAKindHand = new PokerHand("3s 3h 3c 5d 7d");
    PokerHand twoPairHand = new PokerHand("Js Jh 3c 3d 7d");
    PokerHand onePairHand = new PokerHand("10s 10h 3c 5d 7d");
    PokerHand highCardOnlyHand = new PokerHand("Ac 2s 5h Jh 7c");

    // against four of a kind
    assertEquals(Result.WIN, straightFlushHand.compareWith(fourOfAKindHand));
    assertEquals(Result.LOSS, fourOfAKindHand.compareWith(straightFlushHand));

    // against full house
    assertEquals(Result.WIN, straightFlushHand.compareWith(fullHouseHand));
    assertEquals(Result.LOSS, fullHouseHand.compareWith(straightFlushHand));

    // against flush
    assertEquals(Result.WIN, straightFlushHand.compareWith(flushHand));
    assertEquals(Result.LOSS, flushHand.compareWith(straightFlushHand));

    // against straight
    assertEquals(Result.WIN, straightFlushHand.compareWith(straightHand));
    assertEquals(Result.LOSS, straightHand.compareWith(straightFlushHand));

    // against three of a kind
    assertEquals(Result.WIN, straightFlushHand.compareWith(threeOfAKindHand));
    assertEquals(Result.LOSS, threeOfAKindHand.compareWith(straightFlushHand));

    // against two pair
    assertEquals(Result.WIN, straightFlushHand.compareWith(twoPairHand));
    assertEquals(Result.LOSS, twoPairHand.compareWith(straightFlushHand));

    // against one pair
    assertEquals(Result.WIN, straightFlushHand.compareWith(onePairHand));
    assertEquals(Result.LOSS, onePairHand.compareWith(straightFlushHand));

    // against high card
    assertEquals(Result.WIN, straightFlushHand.compareWith(highCardOnlyHand));
    assertEquals(Result.LOSS, highCardOnlyHand.compareWith(straightFlushHand));

    // win with tie breaker
    assertEquals(Result.WIN, straightFlushHand.compareWith(straightFlushWithLowRank));
    assertEquals(Result.LOSS, straightFlushWithLowRank.compareWith(straightFlushHand));
  }

  @Test
  public void royalFlushWin() {

    PokerHand royalFlushHand = new PokerHand("10s Qs Ks Js As");
    PokerHand royalFlushHandWithDifferentSuit = new PokerHand("10c Qc Kc Jc Ac");
    PokerHand straightFlushHand = new PokerHand("3s 4s 6s 7s 5s");
    PokerHand fourOfAKindHand = new PokerHand("3s 3c 3d 3c 5h");
    PokerHand fullHouseHand= new PokerHand("3s 3c 3d 5c 5h");
    PokerHand flushHand = new PokerHand("Jd 3d 4d 8d 7d");
    PokerHand straightHand = new PokerHand("6s 3h 4c 5d 7d");
    PokerHand threeOfAKindHand = new PokerHand("3s 3h 3c 5d 7d");
    PokerHand twoPairHand = new PokerHand("Js Jh 3c 3d 7d");
    PokerHand onePairHand = new PokerHand("10s 10h 3c 5d 7d");
    PokerHand highCardOnlyHand = new PokerHand("Ac 2s 5h Jh 7c");

    // against straight flush
    assertEquals(Result.WIN, royalFlushHand.compareWith(straightFlushHand));
    assertEquals(Result.LOSS, straightFlushHand.compareWith(royalFlushHand));

    // against four of a kind
    assertEquals(Result.WIN, royalFlushHand.compareWith(fourOfAKindHand));
    assertEquals(Result.LOSS, fourOfAKindHand.compareWith(royalFlushHand));

    // against full house
    assertEquals(Result.WIN, royalFlushHand.compareWith(fullHouseHand));
    assertEquals(Result.LOSS, fullHouseHand.compareWith(royalFlushHand));

    // against flush
    assertEquals(Result.WIN, royalFlushHand.compareWith(flushHand));
    assertEquals(Result.LOSS, flushHand.compareWith(royalFlushHand));

    // against straight
    assertEquals(Result.WIN, royalFlushHand.compareWith(straightHand));
    assertEquals(Result.LOSS, straightHand.compareWith(royalFlushHand));

    // against three of a kind
    assertEquals(Result.WIN, royalFlushHand.compareWith(threeOfAKindHand));
    assertEquals(Result.LOSS, threeOfAKindHand.compareWith(royalFlushHand));

    // against two pair
    assertEquals(Result.WIN, royalFlushHand.compareWith(twoPairHand));
    assertEquals(Result.LOSS, twoPairHand.compareWith(royalFlushHand));

    // against one pair
    assertEquals(Result.WIN, royalFlushHand.compareWith(onePairHand));
    assertEquals(Result.LOSS, onePairHand.compareWith(royalFlushHand));

    // against high card
    assertEquals(Result.WIN, royalFlushHand.compareWith(highCardOnlyHand));
    assertEquals(Result.LOSS, highCardOnlyHand.compareWith(royalFlushHand));

    // no win available with tie breaker
    assertEquals(Result.TIE, royalFlushHand.compareWith(royalFlushHandWithDifferentSuit));
    assertEquals(Result.TIE, royalFlushHandWithDifferentSuit.compareWith(royalFlushHand));
  }
}
