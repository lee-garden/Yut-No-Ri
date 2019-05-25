package models;

public class GameObject {
  private int id;
  private int row;
  private int column;
  boolean clickable;
  private boolean changeable;

  // gameobject id, not unique
  int getId(){
    return id;
  }
  void setId(int id){
    this.id = id;
  }

  // game object location
  int getRow(){
    return row;
  }
  int getColumn() {
    return column;
  }
  void setLocation(int row, int column){
    this.row = row;
    this.column = column;
  }

  // clickable game object
  void setClickable(){
    clickable = true;
  }
  void resetClickable(){
    clickable =false;
  }
  boolean isClickable(){
    return clickable;
  }

  // related with change permission
  boolean isChangeable(){
    return changeable;
  }
  void setChangeable(){
    changeable = true;
  }
  void resetChangeable(){
    changeable = false;
  }

}
