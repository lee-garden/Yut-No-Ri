/**
 * @todo 리펙토링 필요, 지나친 하드 코딩임.
 */

package models;
import java.util.ArrayList;

import java.beans.*;
import java.util.Observable;

public class YutNoRiSet extends Observable {

    public final int BACKDO = -1;
    public final int MO = 0;
    public final int DO = 1;
    public final int GEA = 2;
    public final int GIR = 3;
    public final int YUT = 4;

    public final int YUTSETSIZE = 4;

    private ArrayList<ArrayList<Piece>> pieces;
    private Board board;
    private ArrayList<Yut> yutSet;

    private int[] players;

    private boolean checkCatch;

    public int numOfPlayer;
    public int numOfPiece;

    private int whichPlayerTurn;



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
            players[i] = numOfPiece;
        }
        whichPlayerTurn = 0;
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

        setChanged();
        notifyObservers();

        return cal;
    }

    // 결과들이랑 pieceId 받아가지고 이동 가능한 위치들의 하이라이트 그거를 켜줌.
    public void showMovable(ArrayList<Integer> result, int selectedPieceId){
        board.setAllUnClickable();
        Piece tempP = pieces.get(selectedPieceId/10).get(selectedPieceId%10);
        Circle tempC;
        if(tempP.getOutOfPan()){
            tempC = board.getCircleByRowCoulmn(1,1);
        }else{
            tempC = board.getCircleByRowCoulmn(tempP.getRow(),tempP.getColumn());
        }

        for(int i : result){
            for(int j = 0; j < tempC.getId(); j++){
                int nextRow =tempC.getNextRow().get(j)*i;
                int nextColumn = tempC.getNextColumn().get(j)*i;

                // 영역을 넘어섬
                if(nextRow < 1 || nextColumn < 1 || nextRow > 7 || nextColumn > 7){
                    if(nextRow < 1){
                        // 판에서 도착지 넘어서는 경우
                        if(nextColumn< 1) {
                            nextRow = 1;
                            nextColumn = 1;
                        }
                        // 판에서 왼쪽 아래로 빠지는 경우와 왼쪽 대각선으로 빠지는 경우
                        else{
                            nextColumn = 7 - (1 - nextRow);
                            nextRow = 1;
                        }
                    }
                    // 판에서 위로 솓구치는 경우
                    else if(nextRow > 7){
                        nextColumn = nextColumn + nextRow-7;
                        nextRow = 7;
                    }else{
                        // 판에서 왼쪽으로 빠지는 경우
                        if(nextColumn > 7){
                            nextRow = 14 - nextColumn;
                            nextRow = 7;
                        }
                    }
                }

                Circle movable = board.getCircleByRowCoulmn(nextRow, nextColumn);
                movable.setHighlighted();
                movable.setClickable();
            }
        }

        setChanged();
        notifyObservers();
    }


    // 움직일 말을 선택하여 그 말을 지정한 곳으로 옮김.
    public void move(
            int selectedPieceId,
            int row,
            int column){

        checkCatch = false;
        // 하이라이트 끔.
        board.setAllUnHighlight();

        Circle temp = board.getCircleByRowCoulmn(row,column);
        Piece targetPiece = pieces.get(selectedPieceId/10).get(selectedPieceId);

        // 이전 위치가 비었음을 알려줌
        board.getCircleByRowCoulmn(targetPiece.getRow(), targetPiece.getColumn()).toggleOccupying();
        // 현재 위치로 그룹핑된 말들을 옮김.
        for(Piece i : targetPiece.getGroup()){
            i.setLocation(row, column);
            // 나가 있으면 들어왔다고 상태를 바꿈.
            if(i.getOutOfPan()){
                i.setOutOfPan();
            }
            if(row == 1 && column == 1){
                i.setGone();
                players[selectedPieceId/10]--;
            }
        }

        // 만약 옮긴 위치에 다른 말이 있을 때 예외 상황 처리
        Piece tempPiece;
        if(temp.isOccupied()){
            tempPiece = getPieceById(temp.getOccupiedBy());
            // 다른 사람의 말이 있을 때 말을 원위치하고 잡았음을 알림
            if(tempPiece.getOwnerId() != selectedPieceId/10){
                checkCatch = true;
                tempPiece.reset();
            }
            // 내 말일 때 그룹핑 해주고 clickable을 false로 바꿔줌.
            else {
                targetPiece.groupAdd(tempPiece);
                tempPiece.resetClickable();
            }
        }

        // 옮긴 위치의 보드를 '차지 됨' 상태로 바꿈.
        board.getCircleByRowCoulmn(row, column).setOccupiedBy(selectedPieceId);
        board.getCircleByRowCoulmn(row,column).toggleOccupying();

        setChanged();
        notifyObservers();
    }

    public void endTurn(){
        whichPlayerTurn = (whichPlayerTurn + 1)% numOfPlayer;
    }

    public int getWhichPlayerTurn(){
        return whichPlayerTurn;
    }
    public boolean checkCatch(){
        return checkCatch;
    }

    public int checkEnd(){
        int min = 10;
        for(int i : players){
            if(i <= 0){
                return 0;
            }

            if(i < min){
                min = i;
            }
        }
        return min;
    }

    Piece getPieceById(int pieceId){
        return pieces.get(pieceId/10).get(pieceId%10);
    }
}