package com.goosegame.domain.player;

import static java.lang.System.exit;
import static java.lang.System.out;

import com.goosegame.domain.dice.Dice;
import com.goosegame.domain.space.Box;
import com.goosegame.domain.space.FinishBox;
import com.goosegame.exception.FullBoxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Player {

  private final String name;
  private final List<Dice> dices;
  private Box box;
  private boolean winner;

  public Player(String name) {
    this.name = name;
    this.dices = Arrays.asList(new Dice(), new Dice());
  }

  public String getName() {
    return name;
  }

  public Box getBox() {
    return box;
  }

  public void setBox(Box box) {
    this.box = box;
  }

  public boolean isWinner() {
    return winner;
  }

  public void setWinner(boolean winner) {
    this.winner = winner;
  }

  public void moveBack(Box box) {
    this.box = box;
  }

  public void moveTo(Box box) throws FullBoxException {
    if (null != box.getPlayer() && !box.getPlayer().equals(this)) {
      throw new FullBoxException("WARNING: " + box.getPlayer().getName() + " is ON BOX " + box.getIndex(),
          this);
    }
    if (null != this.box) {
      this.box.player(null);
    }
    this.box = box;
    this.box.player(this);
    out.println(name + " goes to box " + box.getIndex());
    if (box instanceof FinishBox) {
      this.winner = true;
      out.println("The winner is >>>>> " + name + " <<<<<");
      exit(0);
    }
  }

  public int roll() {
    return dices.stream().parallel().mapToInt(Dice::roll).sum();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return winner == player.winner &&
        Objects.equals(name, player.name) &&
        Objects.equals(box, player.box);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, box, winner);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Player.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .toString();
  }

  public Player box(Box box) {
    this.box = box;
    return this;
  }

}