package models;

public class ClickableGameObject {
  private int id;
  private int row;
  private int column;
  boolean clickable;

  public int getId(){
    return id;
  }
  void setId(int id){
    this.id = id;
  }
  public int getRow(){
    return row;
  }
  public int getColumn() {
    return column;
  }
  void setLocation(int row, int column){
    this.row = row;
    this.column = column;
  }

  void setClickable(){
    clickable = true;
  }
  void resetClickable(){
    clickable =false;
  }
  public boolean isClickable(){
    return clickable;
  }
}
