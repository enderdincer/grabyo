package com.poker.exception;

/**
 * Although two players can have the same ranking or same winning type they cannot have
 * identical hands (%100 match).
 *
 * @author enderdincer
 */
public class IdenticalHandException extends RuntimeException {

  public IdenticalHandException(String message) {
    super(message);
  }
}
