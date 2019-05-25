package models;

public class YutSet {
  Yut[] yutSet;
  final int YUTSETSIZE = 4;

  public YutSet(){
    yutSet = new Yut[4];
  }

  public int rollYut(){
    int cal = 0;
    for(int i = 0; i < YUTSETSIZE-1; i++){
      if(!yutSet[i].throwYut()){
        cal++;
      }
    }

    // 빽도 떤짐
    if(!yutSet[YUTSETSIZE-1].throwYut()){
      if(cal == 0){
        return -1;
      }
      cal++;
    }

    return cal;
  }

  public boolean[] getYutSetStatus(){
    boolean[] yutStatus = new boolean[4];

    for(int i = 0; i < 4; i++){
      yutStatus[i] = yutSet[i].getStatus();
    }

    return yutStatus;
  }
}
