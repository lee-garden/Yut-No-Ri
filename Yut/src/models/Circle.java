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
    this.setClickable();
    occupied = false
  }

  // Unit direction vector
  public ArrayList<Integer> getNextRow(){
    return nextRow;
  }
  public ArrayList<Integer> getNextColumn(){
    return nextColumn;
  }
  public void addNextRow(int next){
    this.nextRow.add(next);
  }
  public void addNextColumn(int next){
    this.nextColumn.add(next);
  }

  // related with occupying propertys
  public boolean isOccupied(){
    return occupied;
  }
  public void setOccupied(){
    occupied = true;
  }
  public void resetOccupied(){
    occupied = false;
  }
  // manage occupying pieces
  public ArrayList<Integer> getOccupyingPieces(){
    return occupyingPieces;
  }
  public void addOccupyingPieces(int pieceId){
    occupyingPieces.add(pieceId);
  }
  public void clearOccupyingPieces(){
    occupyingPieces.clear();
  }

}
