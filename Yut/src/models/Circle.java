package models;

import java.util.ArrayList;

public class Circle extends ClickableGameObject {

  private boolean occupied;
  private int occupiedBy;
  private ArrayList<Integer> nextRow;
  private ArrayList<Integer> nextColumn;

  Circle(int circleId, int row, int column, int nextRow, int nextColumn){
    setId(circleId);
    setLocation(row,column);
    this.nextRow = new ArrayList<Integer>();
    this.nextColumn = new ArrayList<Integer>();
    this.nextRow.add(nextRow);
    this.nextColumn.add(nextColumn);
    occupied = false;
    clickable = true;
  }

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

  boolean isOccupied(){
    return occupied;
  }

  void setOccupied(){
    occupied = true;
  }

  void resetOccupying(){
    occupied = false;
  }

  void setOccupiedBy(int pieceId){
    occupiedBy = pieceId;
  }

  int getOccupiedBy(){
    return occupiedBy;
  }

}
