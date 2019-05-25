package models;

import java.util.ArrayList;

public class Circle extends GameObject {

  private boolean occupied;
  // Holding id of pieces which occupying the circle
  private ArrayList<Integer> occupyingPieces;

  // Vector of reachable direction.
  // Use ArrayList type for multi direction circle
  private ArrayList<Integer> nextRow;
  private ArrayList<Integer> nextColumn;

  Circle(int circleId, int row, int column, int nextRow, int nextColumn){
    setId(circleId);
    setLocation(row,column);
    this.occupyingPieces = new ArrayList<Integer>();
    this.nextRow = new ArrayList<Integer>();
    this.nextColumn = new ArrayList<Integer>();
    this.nextRow.add(nextRow);
    this.nextColumn.add(nextColumn);
    occupied = false;
    clickable = true;
  }

  // Unit direction vector
  ArrayList<Integer> getNextRow(){
    return nextRow;
  }
  ArrayList<Integer> getNextColumn(){
    return nextColumn;
  }
  void addNextRow(int next){
    this.nextRow.add(next);
  }
  void addNextColumn(int next){
    this.nextColumn.add(next);
  }

  // related with occupying propertys
  boolean isOccupied(){
    return occupied;
  }
  void setOccupied(){
    occupied = true;
  }
  void resetOccupied(){
    occupied = false;
  }
  // manage occupying pieces
  ArrayList<Integer> getOccupyingPieces(){
    return occupyingPieces;
  }
  void addOccupyingPieces(int pieceId){
    occupyingPieces.add(pieceId);
  }
  void deleteOccupyingPieces(){
    occupyingPieces.clear();
  }

}
