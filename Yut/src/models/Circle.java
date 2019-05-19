package models;

import java.util.ArrayList;

public class Circle extends ClickableGameObject {

    private boolean occupied;
    private int occupiedBy;
    private boolean highlighted;
    private ArrayList<Integer> nextRow;
    private ArrayList<Integer> nextColumn;

    Circle(int circleId, int row, int column, int nextRow, int nextColumn){
        setId(circleId);
        setLocation(row,column);
        this.nextRow.add(nextRow);
        this.nextColumn.add(nextColumn);
        occupied = false;
        highlighted = false;
        clickable = false;
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

    void toggleOccupying(){
        if(occupied){
            occupied =false;
        } else {
            occupied = true;
        }
    }

    boolean isHighlighted(){
        return highlighted;
    }

    void setHighlighted(){
        highlighted = true;
    }
    void resetHighlighted(){
        highlighted = false;
    }

    void setOccupiedBy(int pieceId){
        occupiedBy = pieceId;
    }

    int getOccupiedBy(){
        return occupiedBy;
    }

}
