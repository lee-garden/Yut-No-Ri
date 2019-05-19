package models;

public class Circle extends Location{


    private String type;
    private boolean occupied;
    private boolean highlighted;

    Circle(int circleId, String type, int row, int column){
        setId(circleId);
        this.type = type;
        setLocation(row,column);
        occupied = false;
        highlighted = false;
    }

    String getType(){
        return type;
    }

    void setType(String type){
        this.type = type;
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

    public void toggleHighlight(){
        if(highlighted){
            highlighted = false;
        } else {
            highlighted = true;
        }
    }

}
