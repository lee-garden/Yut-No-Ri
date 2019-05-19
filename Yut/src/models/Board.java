package models;

import java.util.ArrayList;
import models.YutNoRiSet;
public class Board {

    public final int BOARDSIZE = 29;

    public final String NORMALBOARD = "normal";
    public final String TWOWAYBOARD = "twoway";

    private ArrayList<Circle> boards;

    Board(){
        int id = 0;

        for(int i = 1; i <= 7; i+=6){
            for(int j = 1; j < 7; j+=6){
                boards.add(new Circle(id, NORMALBOARD, i,j));
            }
        }
        for(int i = 0; i < 5; i++){
            boards.add(new Circle(id, NORMALBOARD, i+2, i+2));
            id++;
        }
        for(int i = 0; i < 5; i++){
            if(i==3) continue;
            boards.add(new Circle(id, NORMALBOARD, 6-i, i+2));
            id++;
        }

        boards.get(5).setType(TWOWAYBOARD);
        boards.get(11).setType(TWOWAYBOARD);
        boards.get(23).setType(TWOWAYBOARD);
    }

    String getBoardTypeById(int index){
        return boards.get(index).getType();
    }
    public boolean getBoardIsHighlighted(int index){
        return boards.get(index).isHighlighted();
    }

    int getRowById(int id){
        return boards.get(id).getRow();
    }
    int getColumnById(int id){
        return boards.get(id).getColumn();
    }

    void setLocationById(int id, int row, int column){
        boards.get(id).setLocation(row,column);
    }

    Integer getCircleIdByRowCoulmn(int row, int column){
        for(Circle i : boards){
            if(i.getRow() == row && i.getColumn() == column)
                return i.getId();
        }
        return null;
    }
}
