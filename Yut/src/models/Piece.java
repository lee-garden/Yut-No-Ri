package models;
import java.util.ArrayList;


class Piece extends GameObject {

  private int ownerId;
  // Represent the piece is not on the board
  private boolean outOfBoard;
  final int defaultRow = 0;
  final int defaultColumn = 0;

  Piece(int row, int column,int ownerId, int pieceId) {

    setId(pieceId);
    this.ownerId = ownerId;
    setLocation(row, column);
    this.setClickable();
    outOfBoard = true;
  }

  // get owner id
  int getOwnerId(){
    return ownerId;
  }

  // clickable represent whether it exist or not
  // if not clickable, the piece is finished.
  void setGone(){
    resetClickable();
  }

  boolean isGone(){
    return !clickable;
  }

  // make piece on out side of the Board
  void reset(){
    this.setLocation(defaultRow, defaultColumn);
    outOfBoard = true;
  }

  // where to put piece problem
  boolean isOutOfBoard(){
    return outOfBoard;
  }

  void setOutOfBoard(){
    outOfBoard = false;
  }

}
