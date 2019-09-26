package com.goosegame;

import static org.junit.Assert.fail;

import com.goosegame.domain.player.Player;
import com.goosegame.exception.PlayerAlreadyExistsException;
import com.goosegame.usecase.Game;
import com.goosegame.usecase.GameImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameImplTest {

  private Game game;
  private Player player1;
  private Player player2;

  @Before
  public void setUp() {
    game = new GameImpl();
    player1 = new Player("pippo");
    player2 = new Player("pluto");
  }

  @Test(expected = PlayerAlreadyExistsException.class)
  public void shouldAddTwoPlayer() throws PlayerAlreadyExistsException {
    game.addPlayer(player1);
    game.addPlayer(player2);
    game.addPlayer(player1);
  }

  @Test
  public void shouldAddOnePlayer() {
    try {
      game.addPlayer(player1);
      game.addPlayer(player2);
    } catch (PlayerAlreadyExistsException e) {
      fail();
    }
    Assert.assertEquals(2, game.getPlayers().size());
  }

}