package models;

import java.util.ArrayList;

public class Player {
    private ArrayList<Piece> pieces;
    private int pieceNumber;

    Player(int ownerId, int pieceNumber){
        this.pieceNumber = pieceNumber;
        for(int i = 0; i < pieceNumber; i++){
            pieces.add(new Piece(0, 0, ownerId, ownerId*10+i));
        }
    }

    Piece getPieceById(int id){
        if(id%10 >= pieceNumber)
            return null;

        return pieces.get(id%10);
    }

    Piece getPieceByLocation(int row, int column){
        for(Piece i : pieces){
            if(i.getRow() == row && i.getColumn() == column){
                return i;
            }
        }
        return null;
    }
}
