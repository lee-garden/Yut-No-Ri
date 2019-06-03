/**
 * @filename YutNoRiSet.java
 * @
 * @todo 빽도 구현
 */

package models;

import java.util.ArrayList;

public class YutNoRiSet {

  public final int BACKDO = 5;
  public final int MO = 0;
  public final int DO = 1;
  public final int GEA = 2;
  public final int GIR = 3;
  public final int YUT = 4;

  final int YUTSETSIZE = 4;

  private Player player;
  private Board board;
  private ArrayList<Yut> yutSet;

  int numOfPlayer;
  int numOfPiece;

  private int playerTurn;

  public int getPlayerTurn(){
    return playerTurn;
  }

  public void setPlayerTurn(int turn){
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

    // 빽도 떤짐
    if(!yutSet.get(YUTSETSIZE-1).throwYut()){
      if(cal == 0){
        return BACKDO;
      }
      cal++;
    }

    return cal;
  }

  // Return Yut Status
  public boolean[] getYutSetStatus(){
    boolean[] yutStatus = new boolean[4];

    for(int i = 0; i < 4; i++){
      yutStatus[i] = yutSet.get(i).getStatus();
    }

    return yutStatus;
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

  // @todo 다음 위치의 친구들을 컨트롤러와 move 중 누가 없앨지 생각해야함.
  // 움직일 말을 선택하여 그 말을 지정한 곳으로 옮김.
  public void move(int pieceId, int row, int column){

    // 하이라이트 끔.
    board.setAllUnuChangeable();


    // 이전 위치의 Circle에 필요한 조작 함.
    Piece targetPiece = player.getPieceByPieceId(pieceId);

    int lastRow = targetPiece.getRow();
    int lastColumn = targetPiece.getColumn();

    if(targetPiece.isOutOfBoard()){
      lastRow = 1;
      lastColumn = 1;
    }

    // 이전 위치가 비었음을 알려줌
    board.getCircleByRowColumn(lastRow, lastColumn).resetOccupied();
    board.getCircleByRowColumn(lastRow, lastColumn).deleteOccupyingPieces();


    // 현재 위치로 그룹핑된 말들을 옮김.
    ArrayList<Integer> temp = board.getCircleByRowColumn(row,column).getOccupyingPieces();
    for(int i : temp){
      player.getPieceByPieceId(i).setLocation(row, column);
    }

    // 옮긴 위치의 보드를 '차지 됨' 상태로 바꿈.
    board.getCircleByRowColumn(row, column).addOccupyingPieces(pieceId);
    board.getCircleByRowColumn(row,column).setOccupied();
  }



  // Use of board
  public boolean isCircleOccupied(int row, int column){
    try{
      return board.getCircleByRowColumn(row, column).isOccupied();
    } catch (NullPointerException e){
      /*error handling code*/
    }
    return false;
  }
  public void addPiece2OccupyingCircle(int pieceId, int row, int column){
    try {
      board.getCircleByRowColumn(row, column).addOccupyingPieces(pieceId);
    } catch (NullPointerException e){
      /*error handling code*/
    }
  }
  public int getNumberOfOccupyingPieces(int row, int column){
    try{
      return board.getCircleByRowColumn(row, column).getOccupyingPieces().size();
    } catch(NullPointerException e){
      /* error handling */
    }
    return -1;
  }
  public ArrayList<Integer> getOccupyingPieceIds(int row, int column){
    try{
      return board.getCircleByRowColumn(row, column).getOccupyingPieces();
    } catch (NullPointerException e){
      /*error handling code*/
    }
    return null;
  }

  public boolean isCircleClickable(int row, int column){
    try {
      return board.getCircleByRowColumn(row, column).isClickable();
    } catch (NullPointerException e){
      /*error handling code*/
    }
    return false;
  }

  public boolean isCircleChangeable(int row, int column){
    try{
      return board.getCircleByRowColumn(row, column).isChangeable();
    } catch(NullPointerException e) {
      /*error handling code*/
      return false;
    }
  }
  public void setCircleChangeable(int row, int column){
    try {
      board.setCircleChangeable(row, column);
    } catch (NullPointerException e){
      /*error handling code*/
    }
  }

  public int getNumberOfVectorsOfCircle(int row, int column){
    try{
      return board.getCircleByRowColumn(row, column).getId();
    } catch (NullPointerException e){
      /*error handling code*/
    }
    return -1;
  }
  public ArrayList<ArrayList<Integer>> getMoveVectorOfCircle(int row, int column){
    try{
      ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
      temp.add(board.getCircleByRowColumn(row, column).getNextRow());
      temp.add(board.getCircleByRowColumn(row, column).getNextColumn());
      return temp;

    } catch (NullPointerException e){
      /*error handling code*/
    }

    return null;
  }



  // Use of piece
  public int[] getPieceLocationByPieceId(int pieceId){
    try{
      int[] temp = new int[2];
      temp[0] = player.getPieceByPieceId(pieceId).getRow();
      temp[1] = player.getPieceByPieceId(pieceId).getColumn();
      return temp;
    } catch (NullPointerException e){
      /*error handling code*/
    }
    return null;
  }

  public int getOwnerOfPieceByLocation(int row, int column){
    try{
      return player.getPieceByLocation(row, column).getOwnerId();
    } catch (NullPointerException e){
      /*error handling code*/
    }
    return -1;
  }
  public int getOwnerOfPieceByPieceId(int pieceId){
    try{
      return player.getPieceByPieceId(pieceId).getOwnerId();
    } catch (NullPointerException e){
      /*error handling code*/
    }
    return -1;
  }

  public void catchPiece(int row, int column){
    try{
      player.getPieceByLocation(row, column).reset();
    } catch (NullPointerException e){
      /*error handling code*/
    }
  }

  public boolean isPieceFinish(int pieceId){
    try{
      return player.getPieceByPieceId(pieceId).isGone();
    } catch (NullPointerException e){
      /*error handling code*/
    }
    return false;
  }

  // modify pieces to changeable without pieces that finished
  public void setPiecesChangeable(int playerId){
    for(Piece i : player.getPieceArrayByPlayerId(playerId)){
      if(i!=null && !i.isGone()){
        i.setChangeable();
      }
    }
  }
  public void setPiecesUnChangeable(int playerId){
    for(Piece i : player.getPieceArrayByPlayerId(playerId)){
      if(i != null && !i.isGone()){
        i.resetChangeable();
      }
    }
  }

  public boolean isPieceChangeable(int pieceId){
    try{
      return player.getPieceByPieceId(pieceId).isChangeable();
    } catch (NullPointerException e){
      /* error handling*/
    }
    return false;
  }

  // @todo 플레이어 아이디만으로 각 피스에 접근하는 함수 추가 필요
  // 뷰에서 플레이어 판위에 없을때 보여주려고?했음
  public int howManyPiecesIsOutOfBoards(int playerId){
    try {
      int num = 0;
      for (int i = 0; i < numOfPiece; i++) {
        Piece temp =  player.getPieceArrayByPlayerId(playerId).get(i);
        if(temp.isOutOfBoard() && !temp.isGone()){
          num++;
        }
      }
      return num;
    } catch (NullPointerException e){
      /*error handling code*/
    }

    return -1;
  }
  // 뷰에서 직접 하나하나 보면 쓸 수 있음.
  public boolean isPieceOutOfBoard(int pieceId){
    try {
      return player.getPieceByPieceId(pieceId).isOutOfBoard();
    } catch (NullPointerException e){
      /*error handling code*/
    }
    return false;
  }

  public int getNumOfPiece() {
    return numOfPiece;
  }
  public int getNumOfPlayer(){
    return numOfPlayer;
  }
}