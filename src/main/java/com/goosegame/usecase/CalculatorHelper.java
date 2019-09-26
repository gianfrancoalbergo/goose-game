package com.goosegame.usecase;

import static java.lang.System.out;

import com.goosegame.domain.player.Player;

class CalculatorHelper {

  private CalculatorHelper() {}

  static int normalize(Player player, int value, int to) {
    to = value + to;
    if (to > 63) {
      out.println(player.getName() + " goes beyond the finish line [" + to + "]");
      return 63 - (to - 63);
    }
    return to;
  }

}
