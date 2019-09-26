package com.goosegame.domain.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.goosegame.domain.space.Box;
import com.goosegame.exception.FullBoxException;
import org.junit.Test;

public class PlayerTest {

  @Test
  public void shouldGoBack() {
    Box box1 = new Box().index(5);
    Box box2 = new Box().index(10).player(new Player("Kentucky"));

    Player alaska = new Player("Alaska").box(box1);
    try {
      alaska.moveTo(box2);
    } catch (FullBoxException e) {
      assertEquals(box1, alaska.getBox());
    }
  }

  @Test(expected =FullBoxException.class)
  public void shouldGoToOccupiedBox() throws FullBoxException {
    Box box1 = new Box().index(5);
    Box box2 = new Box().index(10).player(new Player("Kentucky"));

    Player alaska = new Player("Alaska").box(box1);
    alaska.moveTo(box2);
  }

  @Test
  public void shouldGoTo() {
    Box box1 = new Box().index(5);
    Box box2 = new Box().index(10);

    Player alaska = new Player("Alaska").box(box1);
    try {
      alaska.moveTo(box2);
    } catch (FullBoxException e) {
      fail();
    }
    assertEquals(box2, alaska.getBox());
  }

}