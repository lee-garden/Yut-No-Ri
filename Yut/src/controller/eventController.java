package controller;
//import views.MouseClick;
import models.Circle;
import models.Piece;
import views.YutGui;
import models.YutNoRiSet;

//import models.Circle;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class eventController {
    public YutGui yutGui;
    public YutNoRiSet yutSet;
    public int flag = 0;
    public int currentTurn = 0;
    public int numCanMove = 0;
    public int catchPoint = 0;
    public int chosenPiece;
    //public MouseClick mClick;
    private static ArrayList<Integer> resultSet;

    public eventController(YutNoRiSet set, YutGui gui){
        YutNoRiSet yutSet = set;
        yutSet.setPlayerTurn(0);
        YutGui yutGui= gui;
        System.out.println(yutSet.getNumOfPlayer());
        System.out.println(yutSet.getNumOfPiece());
        System.out.println("짜잔");
    }

    public void gameProgress(int turn, models.YutNoRiSet set) {
        System.out.println("progress call");
        models.YutNoRiSet yutSet = set;

        resultSet = new ArrayList<Integer>(); // player가 윷을 던진 결과들을 저장

        boolean endCondition;
        /*BEFORE YUT ROLL STATE  FLAG = 0
        *윷 또는 모가 나오기 전까지는 윷던지기 버튼을 계속 누를 수 있다.
        * */
        yutGui.yutBtn.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                currentTurn = yutSet.getPlayerTurn();
                System.out.println("hi");
                int result;
                if(flag == 0){ //BEFORE YUT ROLL STATE 일 때만 윷 던지기가 동작 하도록.
                    result = yutSet.rollYut();
                    numCanMove++;
                    System.out.println(result);
                    resultSet.add(result); //나온 결과를 순서대로 resultSet에 저장 해준다.
                    if(result != 4 && result != 0){ // 윷이나 모가 아니라면 befoer yut roll state에서 빠져나와 BEFORE SELECT PIECE STATE 로 들어가게 된다.
                        flag = 1;
                    }
                }
            }
        });

        /*BEFORE SELECT PIECE STATE FLAG = 1
        * 윷을 던진 후 이동할 말을 선택하고 말을 선택하면 그 말이 어디로 갈 수 있는지 보여준다.
        * */
        // player는 아직 판에 올려지지 않은 남은 말들 중 하나를 선택 할 수있다.
        // 현재 턴인 플레이어의 말만 선택 할 수 있어야 한다.
        yutGui.(아직 판에 올려지지 않은 piece).addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                if(flag == 1){ // BEFORE SELECT PIECE STATE 일 때만 동작 하도록
                    chosenPiece = ; // 눌려진 버튼으로 Piece id를 받아온다.
                    //먼저 어디로 이동 할 지 보여준다.
                    showMovable(resultSet, chosenPiece, yutSet);
                    flag=2;
                }
            }
        });

        /*second click시 해제 ? */

        //player는 판 위에 올려져있는 말들 중 하나를 선택 할 수 있다.
        yutGui.(판 위에 있는 piece).addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                if(flag == 1){ // BEFORE SELECT PIECE STATE 일 때만 동작 하도록
                    //먼저 어디로 이동 할 지 보여준다.
                    showMovable(resultSet, chosenPicecId, yutSet);
                    flag=2;
                }
            }
        });

        /*CHOICE PIECE STATE FLAG = 2
         * 말이 선택 되었으면 ShowMovable에 의해 나온 결과에 따라 움직여 주면 된다.
         * */
        yutGui.(highlighting 된 circle중 하나).addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                int[] moveLocation;
                if(flag == 2) { // CHOICE PIECE STATE 일 때만 동작 하도록
                    moveLocation = yutSet.getLocationByCircle(chosenPiece); // 이동할 수 있는 circle을 선택하면 circle을 이용해 row, col을 받아오는 getLocationByCircle 함수 필요.

                    switch (checkMovingPosition(chosenPiece, moveLocation[0], moveLocation[1])) {
                        case 1: // 아무것도 없으면
                            break;
                        case 2: // 본인말이 있으면 grouping
                            // grouping 하고
                            break;
                        case 3: // 상대 말을 잡았으면
                            yutSet.catchPiece(moveLocation[0], moveLocation[1]);
                            catchPoint++;
                            break;
                    }

                    /*arrival 조건을 판단하여 게임이 끝났는지 계속 진행되는지 판단해줌*/


                    //게임 계속 진행된다면
                    yutSet.move(chosenPiece, moveLocation[0], moveLocation[1]); // 선택한 circle로 piece를 이동시킴.
                    //선택한 result를 resultSet에서 delete시켜야함.
                    numCanMove--;

                    /*Decision*/
                    if (numCanMove >= 1 && catchPoint == 0) { // 아직 움직일 수 있는 횟수가 남았고 잡은 말이 없다면 BEFORE SELECT PIECE STATE로 변경함
                        flag = 1;
                    } else if (numCanMove == 0 && catchPoint == 0) { // 움직일수 있는 횟수가 없고 잡은 말이 없다면 턴을 종료하고 다음 player에게 턴을 넘김.
                        yutSet.setPlayerTurn((yutSet.getPlayerTurn() % yutSet.getNumOfPlayer()) + 1);
                        flag = 0;
                    } else if (catchPoint > 0) { // 상대 말을 잡았다면 BEFORE YUT ROLL STATE로 변경하여 다시 윷을 던질 수 있게 함.
                        catchPoint--;
                        flag = 0;
                    }
                }
            }
        });
    }
    /* game progress 종료*/


    /* 움직이고자 하는 Circle에 뭔가 있는지 살펴본다 아무것도 없으면 1, 본인것이 있으면 2, 상대것이 있으면 3 반환*/
    public int checkMovingPosition(int pieceId, int row, int col) {
        int currentPlayer = yutSet.getOwnerOfPieceByPieceId(pieceId); //현재 말을 움직이는 플레이어

        int ownerOfPositionedPiece = yutSet.getOwnerOfPieceByLocation(row, col); //움직이고자 하는 위치에 있는 말의 플레이어

        if(ownerOfPositionedPiece == -1){ // 아무것도 없으면 1 반환
            return 1;
        } else if (currentPlayer == ownerOfPositionedPiece){ // 본인것이 있으면 2 반환
            return  2;
        } else if(currentPlayer != ownerOfPositionedPiece) { // 둘다 아니면 상대 말이 있다.
            return 3;
        }

        return -1;
    }

    public boolean checkEndGame(){
        boolean end = false;
        //if(numOfPiece<=0) {end = true;}
        return end;
    }

    public void showMovable(ArrayList<Integer> result, int pieceId, models.YutNoRiSet set) {
        Piece
        Circle tempC = new Circle();
        if (tempP.getOutOfPan()) {
            tempC = board.getCircleByRowCoulmn(1, 1);
        } else {
            tempC = board.getCircleByRowCoulmn(tempP.getRow(), tempP.getColumn());
        }

        for (int i : result) {
            for (int j = 0; j < tempC.getId(); j++) {
                int nextRow = tempC.getNextRow().get(j) * i;
                int nextColumn = tempC.getNextColumn().get(j) * i;

                // 이동하려 하는 위치가 판의 영역을 넘어서는 경우 맞춰줌
                if (nextRow < 1 || nextColumn < 1 || nextRow > 7 || nextColumn > 7) {
                    if (nextRow < 1) {
                        // 판에서 도착지 넘어서는 경우
                        if (nextColumn < 1) {
                            nextRow = 1;
                            nextColumn = 1;
                        }
                        // 판에서 왼쪽 아래로 빠지는 경우와 왼쪽 대각선으로 빠지는 경우
                        else {
                            nextColumn = 7 - (1 - nextRow);
                            nextRow = 1;
                        }
                    }
                    // 판에서 위로 솓구치는 경우
                    else if (nextRow > 7) {
                        nextColumn = nextColumn + nextRow - 7;
                        nextRow = 7;
                    } else {
                        // 판에서 왼쪽으로 빠지는 경우
                        if (nextColumn > 7) {
                            nextRow = 14 - nextColumn;
                            nextRow = 7;
                        }
                    }
                }

                board.addHighlightedAndClickableCircleByLocation(nextRow, nextColumn);
            }
        }
    }
        //setChanged();
        //notifyObservers();
}

//
//       do {
//            if (catchPoint >= 1) {
//                catchPoint--;
//            }
//            /*  Before Yut Roll State  */
//            do {
//                yutGui.yutBtn.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        // controller에서 윷 던졌음을 알리기
//                        // System.out.print("hihi");
//                    }
//                });
//                result = yutSet.rollYut(); //윷을 굴린 결과를 배열에 저장
//                resultSet.add(result);
//                numCanMove++;
//            } while (result == 4 || result == 0);//모나 윷이 나오면 한번 더 윳을 굴린다.
//
//            while (numCanMove >= 1) {// 움직일 수 있는 횟수가 남아있는동안
//
//                /*  Choose Move Piece State  */
//                //chosenPicecId = choicePiece(); // 움직일 말을 선택하고
//                if(numCanMove>1){       //2개 이상 result가 있으면
//                    //showMovable(resultSet, chosenPicecId, yutSet);//움직일 수 있는 칸을 보여주
//                    //yutSet.move();             //움직인다.
//                    numCanMove--;
//                }
//                else {
//                    //yutSet.move();    // numCanMove가 1이라면 바로 움직이면 된다.
//                    numCanMove--;
//                }
//
//                //yutSet.move(picId, nextLocation[0], nextLocation[1]);    // numCanMove가 1이라면 바로 움직이면 된다.
//                numCanMove--;
//            }
//        }while(catchPoint>1); // checkPoint 즉, 상대 말을 잡은만큼 턴을 더 진행 할 수 있음.
//
//        endCondition = checkEndGame();
//
//        return endCondition;