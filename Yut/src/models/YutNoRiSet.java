/**
 * @filename YutNoRiSet.java
 * @
 * @todo 빽도 구현
 */

package models;

public class YutNoRiSet {
  private Player player;
  private Board board;
  private YutSet yutSet;

  int numOfPlayer;
  int numOfPiece;

  private int playerTurn;

  public YutNoRiSet(int numOfPlayer, int numOfPiece){
    player = new Player(numOfPlayer, numOfPiece);
    board = new Board();
    yutSet = new YutSet();
    this.numOfPlayer = numOfPlayer;
    this.numOfPiece = numOfPiece;
  }

  public int getNumOfPlayer = numOfPlayer;
  public int getNumOfPiece = numOfPiece;

  public Player getPlayer(){
    return player;
  }

  public Board getBoard(){
    return board;
  }

  public YutSet getYutSet(){
    return yutSet;
  }

  public int getPlayerTurn(){
    return playerTurn;
  }

  public void setPlayerTurn(int turn){
    this.playerTurn = turn;
  }

  public boolean tryCatch(int pieceId, int row, int column){
    Piece targetPiece = player.getPieceByPieceId(pieceId);
    Circle nextCircle = board.getCircleByLocation(row,column);
    if(nextCircle.isOccupied()){
      Piece occupyingPiece =
        player.getPieceByPieceId(nextCircle.getOccupyingPieces().get(0));
      if(occupyingPiece.getOwnerId() != targetPiece.getOwnerId()){
        occupyingPiece.resetPieceToOrigin();
        nextCircle.clearOccupyingPieces();
        return true;
      }
    }
    return false;
  }

  public void move(int pieceId, int row, int column){
    Piece targetPiece = player.getPieceByPieceId(pieceId);
    Circle lastCircle =
     board.getCircleByLocation(targetPiece.getRow(), targetPiece.getColumn());
    Circle nextCircle = board.getCircleByLocation(row,column);

    for(int i : lastCircle.getOccupyingPieces()){
      Piece movePiece = player.getPieceByPieceId(i);
      movePiece.setLocation(nextCircle.getRow(), nextCircle.getColumn());
      nextCircle.addOccupyingPieces(i);
    }
    nextCircle.setOccupied();
    lastCircle.resetOccupied();
    lastCircle.clearOccupyingPieces();
  }
}