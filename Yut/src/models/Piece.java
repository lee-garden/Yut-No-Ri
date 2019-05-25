package models;
import java.util.ArrayList;


public class Piece extends GameObject {

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
  public int getOwnerId(){
    return ownerId;
  }

  // clickable represent whether it exist or not
  // if not clickable, the piece is finished.
  public void setGone(){
    resetClickable();
  }

  public boolean isGone(){
    return !isClickable();
  }

  // make piece on out side of the Board
  public void resetPieceToOrigin(){
    this.setLocation(defaultRow, defaultColumn);
    outOfBoard = true;
  }

  // where to put piece problem
  public boolean isOutOfBoard(){
    return outOfBoard;
  }

  public void setOutOfBoard(){
    outOfBoard = false;
  }

}
