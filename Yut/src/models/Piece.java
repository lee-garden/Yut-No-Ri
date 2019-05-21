package models;
import java.util.ArrayList;


class Piece extends ClickableGameObject {

  private int ownerId;
  private boolean reachToTheFinish;
  private ArrayList<Piece> group;
  private int numOfGroupedPiece;
  private boolean outOfPan;

  private int firstRow;
  private int firstColumn;

  int getNumOfGroupedPiece(){
    return numOfGroupedPiece;
  }

  Piece(int row, int column,int ownerId, int pieceId) {

    setId(pieceId);
    this.ownerId = ownerId;
    group = new ArrayList<Piece>();
    group.add(this);
    numOfGroupedPiece = 1;

    setLocation(row, column);

    firstRow = row;
    firstColumn = column;

    reachToTheFinish = false;
    this.setClickable();
    outOfPan = true;
  }

  int getOwnerId(){
    return ownerId;
  }

  void setGone(){
    reachToTheFinish=true;
  }

  boolean isGone(){
    return reachToTheFinish;
  }

  void addGroup(Piece pieceId){
    group.add(pieceId);
    numOfGroupedPiece++;
  }

  ArrayList<Piece> getGroup(){
    return group;
  }

  void reset(){
    setLocation(firstRow, firstColumn);
    group.clear();
    group.add(this);
    outOfPan = true;
    numOfGroupedPiece = 1;
  }

  boolean getOutOfPan(){
    return outOfPan;
  }

  void setOutOfPan(){
    outOfPan = false;
  }

}
