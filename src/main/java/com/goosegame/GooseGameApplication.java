package com.goosegame;

import static java.lang.System.err;

import com.goosegame.domain.player.Player;
import com.goosegame.exception.PlayerAlreadyExistsException;
import com.goosegame.usecase.Game;
import com.goosegame.usecase.GameImpl;

public class GooseGameApplication {

  public static void main(String[] args) {
    Game game = new GameImpl();
    try {
      game.addPlayer(new Player("Alaska"));
      game.addPlayer(new Player("Kentucky"));
      game.addPlayer(new Player("Bianca"));
      game.addPlayer(new Player("Gianfry"));
    } catch (PlayerAlreadyExistsException e) {
      err.print("An unexpected error occurred " + e);
      System.exit(1);
    }
    game.play();
  }

}
