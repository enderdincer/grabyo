package com.poker.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * To be compared with itself by PokerHandType or tie breakers.
 * @author enderdincer
 */
@Data
@Builder
public class PokerHandInfo {

  private PokerHandType handType;
  private List<Integer> tieBreakers;
}
