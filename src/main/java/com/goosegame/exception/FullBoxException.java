package com.goosegame.exception;

import com.goosegame.domain.player.Player;

public class FullBoxException extends Exception {

  private Player player;

  public FullBoxException(String message) {
    super(message);
  }

  public FullBoxException(String message, Player player) {
    super(message);
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }

}

