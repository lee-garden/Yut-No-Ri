package models;

public class GameObject {
  private int id;
  private int row;
  private int column;
  private boolean clickable;
  private boolean changeable;

  // gameobject id, not unique
  public int getId(){
    return id;
  }
  public void setId(int id){
    this.id = id;
  }

  // game object location
  public int getRow(){
    return row;
  }
  public int getColumn() {
    return column;
  }
  public void setLocation(int row, int column){
    this.row = row;
    this.column = column;
  }

  // clickable game object
  public void setClickable(){
    clickable = true;
  }
  public void resetClickable(){
    clickable =false;
  }
  public boolean isClickable(){
    return clickable;
  }

  // related with change permission
  public boolean isChangeable(){
    return changeable;
  }
  public void setChangeable(){
    changeable = true;
  }
  public void resetChangeable(){
    changeable = false;
  }

}
