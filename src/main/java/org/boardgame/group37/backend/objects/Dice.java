package org.boardgame.group37.backend.objects;

public class Dice {

  // Initiate variables
  private int value;

  // Constructor
  public Dice() {
    value = 1;
  }

  // Methods
  // Roll the dice
  public int roll() {
    value = (int)(Math.random() * 6) + 1;
    return value;
  }

  // Getters
  public int getValue() {
    return value;
  }
}
