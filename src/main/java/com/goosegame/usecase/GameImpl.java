package com.goosegame.usecase;

import static java.lang.System.out;

import com.goosegame.domain.board.Board;
import com.goosegame.domain.player.Player;
import com.goosegame.domain.space.Box;
import com.goosegame.domain.space.BridgeBox;
import com.goosegame.domain.space.GooseBox;
import com.goosegame.exception.FullBoxException;
import com.goosegame.exception.PlayerAlreadyExistsException;
import java.util.HashSet;
import java.util.Set;

public class GameImpl implements Game {

  private Set<Player> players = new HashSet<>();
  private Board board = new Board();

  @Override
  public Set<Player> getPlayers() {
    return players;
  }

  @Override
  public void addPlayer(Player player) throws PlayerAlreadyExistsException {
    final boolean added = players.add(player);
    if (!added) {
      throw new PlayerAlreadyExistsException(player.getName() + " already exists");
    } else {
      out.println("players: " + players);
    }
  }

  @Override
  public void play() {
    Box[] boxes = board.getBoxes();
    do {
      players.forEach(player -> {
        final Box currentBox = player.getBox();
        int rollNum = player.roll();
        final int position = prettyIndex(currentBox);
        out.println(
            player.getName() + (position > 0 ? " is on box " + position : " STARTS the game ")
                + " > He plays with 2 dices .. " + rollNum);
        int to = CalculatorHelper.normalize(player, rollNum, position);
        Box box = boxes[to];
        if (box instanceof GooseBox || box instanceof BridgeBox) {
          out.println(
              player.getName() + " goes on the " + (box instanceof GooseBox ? "Goose" : "Bridge")
                  + " box " + to + " > SKIP " + rollNum
                  + " boxes forward");
          to = CalculatorHelper.normalize(player, rollNum, to);
          box = boxes[to];
        }
        try {
          player.moveTo(box);
        } catch (FullBoxException ex) {
          player.moveBack(currentBox);
          out.println(ex.getMessage());
          out.println(
              "WARNING: " + ex.getPlayer().getName() + " GOES BACK to " + prettyIndex(ex
                  .getPlayer()
                  .getBox()));
        }
      });
    } while (players.stream().anyMatch(player -> !player.isWinner()));
  }

  private int prettyIndex(Box box) {
    if (null == box) {
      return 0;
    }
    return box.getIndex();
  }

}