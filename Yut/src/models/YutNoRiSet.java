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
        pieces = new ArrayList<ArrayList<Piece>>();
        for(int i = 0; i < numOfPlayer; i++){
            ArrayList<Piece> tempPiece = new ArrayList<Piece>();
            for(int j = 0; j < numOfPiece; j++){
                tempPiece.add(new Piece(0,0, i, i*10+j));
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
        board.setAllUnClickable();
        Piece tempP = pieces.get(whichPlayerTrun).get(selectedPieceid);
        Circle tempC = board.getCircleByRowCoulmn(tempP.getRow(),tempP.getColumn());

        for(int i : result){
            for(int j = 0; j < tempC.getId(); j++){
                Circle movable = board.getCircleByRowCoulmn(
                        tempC.getNextRow().get(j)*i,
                        tempC.getNextColumn().get(j)*i);
                movable.setHighlighted();
                movable.setClickable();
            }
        }
    }


    public void move(
            int playerId,
            int selectedPieceId,
            int row,
            int column){

        checkCatch = false;
        // 하이라이트 끔.
        board.setAllUnHighlight();
        Circle temp = board.getCircleByRowCoulmn(row,column);
        Piece targetPiece = pieces.get(playerId).get(selectedPieceId);

        // 이전 위치가 비었음을 알려줌
        board.getCircleByRowCoulmn(targetPiece.getRow(), targetPiece.getColumn()).toggleOccupying();
        // 현재 위치로 그룹핑된 말들을 옮김.
        for(Piece i : targetPiece.getGroup()){
            i.setLocation(row, column);
        }

        // 만약 옮긴 위치에 다른 말이 있을 때 예외 상황 처리
        int pieceId;
        if(temp.isOccupied()){
            pieceId = temp.getOccupiedBy();
            // 다른 사람의 말이 있을 때 말을 원위치하고 잡았음을 알림
            if(pieceId/10 != whichPlayerTrun){
                checkCatch = true;
                pieces.get(pieceId/10).get(pieceId%10).reset();
            }
            // 내 말일 때 그룹핑 해주고 clickable을 false로 바꿔줌.
            else {
                targetPiece.groupAdd(pieces.get(pieceId/10).get(pieceId%10));
                pieces.get(pieceId/10).get(pieceId%10).resetClickable();
            }
        }

        // 옮긴 위치의 보드를 '차지 됨' 상태로 바꿈.
        board.getCircleByRowCoulmn(row, column).setOccupiedBy(selectedPieceId);
        board.getCircleByRowCoulmn(row,column).toggleOccupying();
    }

    public void endTurn(){
        whichPlayerTrun = (whichPlayerTrun + 1)% numOfPlayer;
    }

    public int getWhichPlayerTurn(){
        return whichPlayerTrun;
    }
    public boolean checkCatch(){
        return checkCatch;
    }

    public int getMaxEnd(){
        int min = 10;
        for(int i : players){
            if(i == numOfPiece){
                return 0;
            }

            if(i < min){
                min = i;
            }
        }
        return min;
    }
}