package models;

public class Player {


    private int playerId;
    private Piece[] pieces;

    public int getPlayerId(){
        return playerId;
    }

    public Piece getPieceById(int pieceId){
        return pieces[pieceId];
    }

    public Player(int playerId, int numOfPieces){
        this.playerId = playerId;

        for(int i = 0; i < numOfPieces; i++){
            pieces[i] = new Piece(i, playerId);
        }
    }
}
