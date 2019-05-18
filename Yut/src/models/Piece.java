package models;

public class Piece {


    private int pieceId;
    private int location;

    public int getPieceId(){
        return pieceId;
    }

    public int getLocation(){
        return location;
    }

    public void getLocation(int newLocation){
        this.location = newLocation;
    }

    public Piece(int pieceId, int location){
        this.pieceId = pieceId;
        this.location = location;
    }
}
