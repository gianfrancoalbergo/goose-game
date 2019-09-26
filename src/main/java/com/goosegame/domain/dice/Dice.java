package com.goosegame.domain.dice;

import java.util.Random;

public class Dice {

  private static final Random random = new Random();

  public int roll() {
    return random
        .ints(1, 1, 7)
        .findFirst()
        .getAsInt();
  }

}