package com.goosegame;

import static org.junit.Assert.assertTrue;

import com.goosegame.domain.dice.Dice;
import org.junit.Test;

public class DiceTest {

  @Test
  public void shoulReturnAValueBetween_1_6() {
    final int value = new Dice().roll();
    System.out.println(value);
    assertTrue(1 <= value && value <= 6);
  }

}
