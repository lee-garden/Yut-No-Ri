package models;
import java.util.ArrayList;
import java.beans.*;

public class YutNoRiSet{

    public final int BACKDO = -1;
    public final int MO = 0;
    public final int DO = 1;
    public final int GEA = 2;
    public final int GIR = 3;
    public final int YUT = 4;

    public final int YUTSETSIZE = 4;

    public final String NORMALBOARD = "normal";
    public final String TWOWAYBOARD = "twoway";

    private ArrayList<ArrayList<Piece>> pieces;
    private Board board;
    private ArrayList<Yut> yutSet;

    private int[] players;

    private boolean checkCatch;

    private int numOfPlayer;
    private int numOfPiece;

    private int whichPlayerTrun;


    public YutNoRiSet() {
        yutSet = new ArrayList<Yut>();
        for(int i = 0; i < YUTSETSIZE; i++){
            if(i != YUTSETSIZE - 1) {
                yutSet.add(new Yut("Normal"));
            } else {
                yutSet.add(new Yut("BackDo"));
            }
        }
        board = new Board();

    }

    public void setOption(int numOfPlayer, int numOfPiece){
        this.numOfPiece = numOfPiece;
        this.numOfPlayer = numOfPlayer;

        players = new int[numOfPlayer];

        for(int i = 0; i < numOfPlayer; i++){
            pieces = new ArrayList<ArrayList<Piece>>();
            ArrayList<Piece> tempPiece = new ArrayList<Piece>();
            for(int j = 0; j < numOfPiece; j++){
                tempPiece.add(new Piece(i,j, i, j));
            }
            pieces.add(tempPiece);
            players[i] = 0;
        }
        whichPlayerTrun = 0;
    }

    public int rollYut(){
        int cal = 0;
        for(int i = 0; i < YUTSETSIZE-1; i++){
            if(!yutSet.get(i).throwYut()){
                cal++;
            }
        }

        if(!yutSet.get(YUTSETSIZE-1).throwYut()){
            if(cal == 0){
                return BACKDO;
            }
            cal++;
        }

        return cal;
    }

    public void showMovable(ArrayList<Integer> result,
                            int selectedPieceid){
        Piece temp = pieces.get(whichPlayerTrun).get(selectedPieceid);
        board


    }

    public void move(int playerId, int selectedPieceId, int selectedPosition){

    }

    public void endTurn(){
        whichPlayerTrun = (whichPlayerTrun + 1)%numOfPlayer;
    }

    public int getWhichPlayerTurn(){
        return whichPlayerTrun;
    }

    public boolean checkEnd(){
        for(int i : players){
            if(i == numOfPiece){
                return true;
            }
        }
        return false;
    }
}