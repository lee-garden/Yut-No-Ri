package models;

import java.util.ArrayList;
public class Board {

  public final int BOARDSIZE = 29;

  final int NORMALBOARD = 1;
  final int TWOWAYBOARD = 2;

  private ArrayList<Circle> boards;
  private ArrayList<Circle> highlightedAndClicableCircle;

  Board(){
    boards = new ArrayList<Circle>(0);
    highlightedAndClicableCircle = new ArrayList<Circle>();

    // 모퉁이 빼고 모서리 16개
    for(int i = 2; i < 7; i++){
      if(i==4) continue;
      boards.add(new Circle(NORMALBOARD, i, 7, 1, 0));
      boards.add(new Circle(NORMALBOARD, i, 1, -1, 0));
      boards.add(new Circle(NORMALBOARD, 7, i, 0, 1));
      boards.add(new Circle(NORMALBOARD, 1, i, 0, -1));
    }

    // 네 모퉁이
    // 오아
    boards.add(new Circle(NORMALBOARD, 1, 1, 1, 0));
    // 왼아
    boards.add(new Circle(NORMALBOARD, 1, 7, 0, -1));
    // 왼위
    boards.add(new Circle(TWOWAYBOARD, 7, 7, -1, 0));
    // 왼위에서 대각선으로 가는 길
    boards.get(19).addNextRow(-1);
    boards.get(19).addNextColumn(-1);
    // 오위
    boards.add(new Circle(TWOWAYBOARD, 7, 1, 0, 1));
    // 오위에서 대각선으로 가는 길
    boards.get(20).addNextRow(1);
    boards.get(20).addNextColumn(1);

    // 가운데 9칸
    // 왼쪽위에서 오른쪽 아래로 가는 칸
    for(int i = 0; i < 5; i++){
      int id = NORMALBOARD;
      if(i==2)
        id = TWOWAYBOARD;
      boards.add(new Circle(id, i+2, i+2,-1,-1));
    }
    // 오른쪽 위에서 왼쪽 아래로 가는 칸
    for(int i = 0; i < 5; i++){
      if(i==2) continue;
      boards.add(new Circle(NORMALBOARD, 6-i, i+2,-1,-1));
    }
    // 정가운데
    boards.get(23).addNextRow(1);
    boards.get(23).addNextColumn(1);

  }


  Circle getCircleByRowCoulmn(int row, int column){
    for(Circle i : boards){
      if(i.getRow() == row && i.getColumn() == column)
        return i;
    }
    return null;
  }

  void setAllUnusable(){
    for(Circle i : highlightedAndClicableCircle){
      i.resetClickable();
    }
    highlightedAndClicableCircle.clear();
  }

  void addHighlightedAndClickableCircleByLocation(int row, int column){
    Circle chosenCircle = getCircleByRowCoulmn(row, column);
    chosenCircle.setClickable();
    highlightedAndClicableCircle.add(chosenCircle);
  }

}
