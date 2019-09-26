package com.goosegame.domain.board;

import com.goosegame.domain.space.Box;
import com.goosegame.domain.space.BridgeBox;
import com.goosegame.domain.space.FinishBox;
import com.goosegame.domain.space.GooseBox;

public class Board {

  private Box[] boxes = new Box[64];

  public Board() {
    for (int i = 5; i < 63; i += 9) {
      boxes[i] = new GooseBox().index(i);
    }
    for (int i = 9; i < 63; i += 9) {
      boxes[i] = new GooseBox().index(i);
    }
    boxes[6] = new BridgeBox().index(6);
    for (int i = 1; i < 63; i++) {
      final Box box = boxes[i];
      if (box == null) {
        boxes[i] = new Box().index(i);
      }
    }
    boxes[63] = new FinishBox().index(63);
  }

  public Box[] getBoxes() {
    return boxes;
  }

}
