package main.java.org.boardgame.group37.backend.objects;

public class Dice {
  int value;
  public Dice() {
    value = 0;
  }
  public int roll() {
    value = (int)(Math.random() * 6) + 1;
    return value;
  }
}
