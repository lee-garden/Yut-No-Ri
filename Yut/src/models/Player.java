package models;

import java.util.ArrayList;

public class Player {
  private ArrayList<ArrayList<Piece>> players;
  private int pieceNumber;
  private int playerNumber;

  Player(int playerNumber, int pieceNumber){
    this.playerNumber = playerNumber;
    this.pieceNumber = pieceNumber;
    players = new ArrayList<ArrayList<Piece>>();

    for(int i = 0; i < playerNumber; i++){
      ArrayList<Piece> pieces = new ArrayList<Piece>();
      for(int j = 0; j < pieceNumber; j++){
        pieces.add(new Piece(0,0,i,i*10+j));
      }
      players.add(pieces);
    }
  }

    Piece getPieceByPieceId(int pieceId){
    if(pieceId/10 >= playerNumber && pieceId%10 > pieceId){
      return null;
    }

    return players.get(pieceId/10).get(pieceId%10);
  }

  int getSmallestPieceLeft(){
    int min = 100;
    for(int i = 0; i < playerNumber; i++){
      int numOfGonePiece=0;
      for(Piece j : players.get(i)){
        if(j.isGone()){
          numOfGonePiece++;
        }
      }
      if(numOfGonePiece < min){
        min = numOfGonePiece;
      }
    }
    return min;
  }

  public int getWinnerPlayerId(){
    int id = -1;
    for(int i = 0; i < playerNumber; i++) {
      int numOfGonePiece = 0;
      for (Piece j : players.get(i)) {
        if (j.isGone()) {
          numOfGonePiece++;
        }
      }
      if(numOfGonePiece == 0){
        id = numOfGonePiece;
        break;
      }
    }
    return id;
  }

  Piece getPieceByLocation(int row, int column){
    for(int i = 0; i < playerNumber; i++){
      for( Piece j : players.get(i)){
        if(j.getRow() == row && j.getColumn() == column){
          return j;
        }
      }
    }
    return null;
  }

  ArrayList<Piece> getPieceArrayByPlayerId(int playerId){
    return players.get(playerId);
  }
}
