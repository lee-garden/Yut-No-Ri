package models;
import java.util.ArrayList;


class Piece extends ClickableGameObject {

    private int ownerId;
    private boolean reachToTheFinish;
    private ArrayList<Piece> group;
    private boolean outOfPan;

    private int firstRow;
    private int firstColumn;

    Piece(int row, int column,int ownerId, int pieceId) {

        setId(pieceId);
        this.ownerId = ownerId;
        group = new ArrayList<Piece>();
        group.add(this);

        setLocation(row, column);

        firstRow = row;
        firstColumn = column;

        reachToTheFinish = false;
        this.setClickable();
        outOfPan = true;
    }

    int getOwnerId(){
        return ownerId;
    }

    void setGone(){
        reachToTheFinish=true;
    }

    boolean isGone(){
        return reachToTheFinish;
    }

    void groupAdd(Piece pieceId){
        group.add(pieceId);
    }

    ArrayList<Piece> getGroup(){
        return group;
    }

    void reset(){
        setLocation(firstRow, firstColumn);
        group.clear();
        group.add(this);
        outOfPan = true;
    }

    boolean getOutOfPan(){
        return outOfPan;
    }

    void setOutOfPan(){
        outOfPan = false;
    }

}
