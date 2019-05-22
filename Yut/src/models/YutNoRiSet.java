/**
 * @filename YutNoRiSet.java
 * @
 * @todo 리펙토링 필요, 지나친 하드 코딩임.
 */

package models;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;

public class YutNoRiSet extends Observable {

  public final int BACKDO = -1;
  public final int MO = 0;
  public final int DO = 1;
  public final int GEA = 2;
  public final int GIR = 3;
  public final int YUT = 4;

  public final int YUTSETSIZE = 4;

  private Player player;
  private Board board;
  private ArrayList<Yut> yutSet;

  public int numOfPlayer;
  public int numOfPiece;

  private int playerTurn;

  int getPlayerTurn(){
    return playerTurn;
  }

  void setPlayerTurn(int turn){
    this.playerTurn = turn;
  }

  public YutNoRiSet() {
    yutSet = new ArrayList<Yut>();
    for(int i = 0; i < YUTSETSIZE; i++){
      if(i != YUTSETSIZE - 1) {
        yutSet.add(new Yut("Normal"));
      } else {
        yutSet.add(new Yut("BackDo"));
      }
    }
    board = new Board();

  }

  public void setOption(int numOfPlayer, int numOfPiece){
    this.numOfPiece = numOfPiece;
    this.numOfPlayer = numOfPlayer;

    player = new Player(numOfPlayer, numOfPiece);
    playerTurn = 0;
  }

  public int rollYut(){
    int cal = 0;
    for(int i = 0; i < YUTSETSIZE-1; i++){
      if(!yutSet.get(i).throwYut()){
        cal++;
      }
    }

    if(!yutSet.get(YUTSETSIZE-1).throwYut()){
      if(cal == 0){
        return BACKDO;
      }
      cal++;
    }

    setChanged();
    notifyObservers();

    return cal;
  }


  // @todo 만약 piece 구분이 player id를 포함하면 바꿀 필요가 있다. + 리펙토링
  // 결과들이랑 pieceId 받아가지고 이동 가능한 위치들의 하이라이트 그거를 켜줌.
//  public void showMovable(ArrayList<Integer> result, int pieceId){
//    board.setAllUnClickable();
//    Piece tempP = player.getPieceById(pieceId);
//    Circle tempC;
//
//    if(tempP.getOutOfPan()){
//      tempC = board.getCircleByRowCoulmn(1,1);
//    }else{
//      tempC = board.getCircleByRowCoulmn(tempP.getRow(),tempP.getColumn());
//    }
//
//    for(int i : result){
//      for(int j = 0; j < tempC.getId(); j++){
//        int nextRow =tempC.getNextRow().get(j)*i;
//        int nextColumn = tempC.getNextColumn().get(j)*i;
//
//        // 이동하려 하는 위치가 판의 영역을 넘어서는 경우 맞춰줌
//        if(nextRow < 1 || nextColumn < 1 || nextRow > 7 || nextColumn > 7){
//          if(nextRow < 1){
//            // 판에서 도착지 넘어서는 경우
//            if(nextColumn< 1) {
//              nextRow = 1;
//              nextColumn = 1;
//            }
//            // 판에서 왼쪽 아래로 빠지는 경우와 왼쪽 대각선으로 빠지는 경우
//            else{
//              nextColumn = 7 - (1 - nextRow);
//              nextRow = 1;
//            }
//          }
//          // 판에서 위로 솓구치는 경우
//          else if(nextRow > 7){
//            nextColumn = nextColumn + nextRow-7;
//            nextRow = 7;
//          }else{
//            // 판에서 왼쪽으로 빠지는 경우
//            if(nextColumn > 7){
//              nextRow = 14 - nextColumn;
//              nextRow = 7;
//            }
//          }
//        }
//
//        board.addHighlightedAndClickableCircleByLocation(nextRow, nextColumn);
//      }
//    }
//
//    setChanged();
//    notifyObservers();
//  }

  // 움직일 말을 선택하여 그 말을 지정한 곳으로 옮김.
  public void move(int pieceId, int row, int column){

    // 하이라이트 끔.
    board.setAllUnusable();

    Piece targetPiece = player.getPieceByPieceId(pieceId);

    // 이전 위치가 비었음을 알려줌
    board.getCircleByRowCoulmn(targetPiece.getRow(), targetPiece.getColumn()).resetOccupying();

    // 현재 위치로 그룹핑된 말들을 옮김.
    for(Piece i : targetPiece.getGroup()){
      i.setLocation(row, column);
      // 나가 있으면 들어왔다고 상태를 바꿈.
      if(i.isOutOfPan()){
        i.setOutOfPan();
      }
      if(row == 1 && column == 1){
        i.setGone();
        i.setOutOfPan();
      }
    }

//    // 만약 옮긴 위치에 다른 말이 있을 때 예외 상황 처리
//    Piece locatedPiece;
//    if(temp.isOccupied()) {
//      locatedPiece = player.getPieceById(temp.getOccupiedBy());
//      // 다른 사람의 말이 있을 때 해당 말을 원위치하고 잡았음을 알림
//      if (locatedPiece.getOwnerId() != pieceId / 10) {
//        locatedPiece.reset();
//      }
//      // 내 말일 때 그룹핑 해주고 clickable을 false로 바꿔줌.
//      else {
//      targetPiece.addGroup(locatedPiece);
//      locatedPiece.resetClickable();
//      }
//    }

    // 옮긴 위치의 보드를 '차지 됨' 상태로 바꿈.
    board.getCircleByRowCoulmn(row, column).setOccupiedBy(pieceId);
    board.getCircleByRowCoulmn(row,column).setOccupied();
  }



  // for view to using board
  public boolean getCircleIsOccupiedByLocation(int row, int column){
    if(row < 8 && row > 0 && column > 0 && column < 8 && (column != 4 || row != 4 || (row == 4 && column == 4))){
      return board.getCircleByRowCoulmn(row, column).isOccupied();
    }
    return false;
  }

  public void setCircleOccupiedByPieceId(int row, int column, int pieceId){
    board.getCircleByRowCoulmn(row, column).setOccupiedBy(pieceId);
  }

  public boolean getCircleIsClickableByLocation(int row, int column){
    return board.getCircleByRowCoulmn(row, column).isClickable();
  }

  public void setCircleClickableByLocation(int row, int column){
    board.addHighlightedAndClickableCircleByLocation(row, column);
  }

  public int getOccupyingPieceIdByLocation(int row, int column){
    return board.getCircleByRowCoulmn(row, column).getOccupiedBy();
  }

  public ArrayList<ArrayList<Integer>> getMoveVectorByCircleLocation(int row, int column){
    ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();

    temp.add(board.getCircleByRowCoulmn(row, column).getNextRow());
    temp.add(board.getCircleByRowCoulmn(row, column).getNextColumn());

    return temp;
  }

  public int getNumberOfWayCanChooseOnCircle(int row, int column){
    return board.getCircleByRowCoulmn(row, column).getId();
  }

  // for view to using pieces
  public int[] getPieceLocationByPieceId(int pieceId){
    int[] temp = new int[2];
    temp[0] = player.getPieceByPieceId(pieceId).getRow();
    temp[1] = player.getPieceByPieceId(pieceId).getColumn();
    return temp;
  }

  public int getOwnerOfPieceByLocation(int row, int column){
    return player.getPieceByLocation(row, column).getOwnerId();
  }

  public void catchPieceWithLocation(int row, int column){
    Piece caughtPiece = player.getPieceByLocation(row, column);
    caughtPiece.reset();
  }

  public boolean getPieceIsClickableByPieceId(int pieceId){
    return player.getPieceByPieceId(pieceId).isClickable();
  }

  public int groupPieceByPieceId(int row, int column, int pieceId){
    Piece beGroupedPiece = player.getPieceByLocation(row, column);
    Piece groupingPiece = player.getPieceByPieceId(pieceId);

    if(groupingPiece.getNumOfGroupedPiece() < beGroupedPiece.getNumOfGroupedPiece()){
      Piece temp = groupingPiece;
      groupingPiece = beGroupedPiece;
      beGroupedPiece = temp;
    }

    for(Piece i : beGroupedPiece.getGroup()){
      groupingPiece.addGroup(i);
    }
    beGroupedPiece.reset();
    beGroupedPiece.setLocation(groupingPiece.getRow(), groupingPiece.getColumn());
    beGroupedPiece.setOutOfPan();
    return groupingPiece.getId();
  }

  public int getNumbOfGroupedPieceById(int pieceId){
    return player.getPieceByPieceId(pieceId).getNumOfGroupedPiece();
  }

  public void setPiecesClickableByPlayerId(int playerId){
    for(Piece i : player.getPieceArrayByPlayerId(playerId)){
      if(!i.isGone()){
        i.setClickable();
      }
    }
  }

  public void resetPiecesClickableByPlayerId(int playerId){
    for(Piece i : player.getPieceArrayByPlayerId(playerId)){
      if(!i.isGone()){
        i.resetClickable();
      }
    }
  }

  // @todo 플레이어 아이디만으로 각 피스에 접근하는 함수 추가 필요
  public boolean[] getPiecesIsInTheBoardByPlayerId(int playerId){
    boolean[] temp = new boolean[numOfPiece];
    for(int i = 0; i < numOfPiece; i++){
      temp[i] = player.getPieceArrayByPlayerId(playerId).get(i).isOutOfPan();
    }
    return temp;
  }

  public boolean getPieceIsInTheBoardByPieceId(int pieceId){
    return player.getPieceByPieceId(pieceId).isOutOfPan();
  }

  // Return Yut Status
  public boolean[] getYutSetStatus(){
    boolean[] yutStatus = new boolean[4];

    for(int i = 0; i < 4; i++){
      yutStatus[i] = yutSet.get(i).getStatus();
    }

    return yutStatus;
  }
}