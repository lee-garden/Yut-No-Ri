package models;

public class Circle {


    private int circleId;
    private String type;
    private boolean occupied = false;
    private boolean highlighted = false;

    public Circle(int circleId, String type){
        this.circleId = circleId;
        this.type = type;
    }

    public int getCircleId(){
        return circleId;
    }

    public String getType(){
        return type;
    }

    public boolean isOccupied(){
        return occupied;
    }

    public void toggleOccupied(){
        if(occupied){
            occupied = false;
        } else {
            occupied = true;
        }
    }

    public boolean isHighlighted(){
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
