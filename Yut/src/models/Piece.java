package models;
import java.util.ArrayList;


class Piece extends Location{
    private int ownerId;
    private boolean reachToTheFinish;
    private ArrayList<Piece> group;

    private int firstRow;
    private int firstColumn;

    Piece(int row, int column, int owenerId, int pieceId) {

        this.ownerId = owenerId;
        setId(pieceId);

        group = new ArrayList<Piece>();
        group.add(this);

        setLocation(row, column);

        firstRow = row;
        firstColumn = column;

        reachToTheFinish = false;
    }

    public int getOwnerId(){
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

    void reset(){
        setLocation(firstRow, firstColumn);
        group.clear();
        group.add(this);
    }
}
