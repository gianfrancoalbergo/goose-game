package com.goosegame.domain.space;

import com.goosegame.domain.player.Player;
import java.util.Objects;

public class Box {

  private int index;
  private Player player;

  public Box index(int index) {
    this.index = index;
    return this;
  }

  public Box player(Player player) {
    this.player = player;
    return this;
  }

  public int getIndex() {
    return index;
  }

  public Player getPlayer() {
    return player;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Box box = (Box) o;
    return index == box.index &&
        Objects.equals(player, box.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(index, player);
  }

}
