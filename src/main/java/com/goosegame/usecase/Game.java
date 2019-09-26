package com.goosegame.usecase;

import com.goosegame.domain.player.Player;
import com.goosegame.exception.PlayerAlreadyExistsException;
import java.util.Set;

public interface Game {

  void addPlayer(Player player) throws PlayerAlreadyExistsException;

  Set<Player> getPlayers();

  void play();

}
