package controller;
import views.BackGroundPanel;
//import views.MouseClick;
import views.YutGui;
import models.YutNoRiSet;

//import models.Circle;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class eventController {

    public YutGui yutGui;
    public YutNoRiSet yutSet;

    //public MouseClick mClick;

    public eventController(YutNoRiSet set, YutGui gui){
        YutNoRiSet yutSet = set;
        YutGui yutGui= gui;
        System.out.println(yutSet.getNumOfPlayer());
        System.out.println(yutSet.getNumOfPiece());
        System.out.println("짜잔");
    }

    private static ArrayList<Integer> resultSet;
    private  int chosenPicecId;

//    public void choicePiece(){
//        yutGui.midPanel..addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });

    public boolean gameProgress(int turn, models.YutNoRiSet set) {
        models.YutNoRiSet yutSet = set;
        int numCanMove=0;
        int catchPoint=0;
        resultSet = new ArrayList<Integer>();
        int result;
        boolean endCondition;

        do {
            if (catchPoint >= 1) {
                catchPoint--;
            }
            /*  Before Yut Roll State  */
            do {
                result = yutSet.rollYut(); //윷을 굴린 결과를 배열에 저장
                resultSet.add(result);
                numCanMove++;
            } while (result == 4 || result == 0);//모나 윷이 나오면 한번 더 윳을 굴린다.

            while (numCanMove >= 1) {// 움직일 수 있는 횟수가 남아있는동안

                /*  Choose Move Piece State  */
                //chosenPicecId = choicePiece(); // 움직일 말을 선택하고
                if(numCanMove>1){       //2개 이상 result가 있으면
                    //showMovable(resultSet, chosenPicecId, yutSet);//움직일 수 있는 칸을 보여주
                    //yutSet.move();             //움직인다.
                    numCanMove--;
                }
                else {
                    //yutSet.move();    // numCanMove가 1이라면 바로 움직이면 된다.
                    numCanMove--;
                }

                //yutSet.move(picId, nextLocation[0], nextLocation[1]);    // numCanMove가 1이라면 바로 움직이면 된다.
                numCanMove--;
            }
        }while(catchPoint>1); // checkPoint 즉, 상대 말을 잡은만큼 턴을 더 진행 할 수 있음.

        endCondition = checkEndGame();

        return endCondition;
    }

    public void choicePiece(int pic){
        this.chosenPicecId=pic;
    }

    public boolean checkEndGame(){
        boolean end = false;
        //if(numOfPiece<=0) {end = true;}
        return end;
    }
//    public void showMovable(ArrayList<Integer> result, int pieceId, models.YutNoRiSet set){
//        if(tempP.getOutOfPan()){
//            tempC = board.getCircleByRowCoulmn(1,1);
//        }else{
//            tempC = board.getCircleByRowCoulmn(tempP.getRow(),tempP.getColumn());
//        }
//
//        for(int i : result){
//            for(int j = 0; j < tempC.getId(); j++){
//                int nextRow =tempC.getNextRow().get(j)*i;
//                int nextColumn = tempC.getNextColumn().get(j)*i;
//
//                // 이동하려 하는 위치가 판의 영역을 넘어서는 경우 맞춰줌
//                if(nextRow < 1 || nextColumn < 1 || nextRow > 7 || nextColumn > 7){
//                    if(nextRow < 1){
//                        // 판에서 도착지 넘어서는 경우
//                        if(nextColumn< 1) {
//                            nextRow = 1;
//                            nextColumn = 1;
//                        }
//                        // 판에서 왼쪽 아래로 빠지는 경우와 왼쪽 대각선으로 빠지는 경우
//                        else{
//                            nextColumn = 7 - (1 - nextRow);
//                            nextRow = 1;
//                        }
//                    }
//                    // 판에서 위로 솓구치는 경우
//                    else if(nextRow > 7){
//                        nextColumn = nextColumn + nextRow-7;
//                        nextRow = 7;
//                    }else{
//                        // 판에서 왼쪽으로 빠지는 경우
//                        if(nextColumn > 7){
//                            nextRow = 14 - nextColumn;
//                            nextRow = 7;
//                        }
//                    }
//                }
//
//                board.addHighlightedAndClickableCircleByLocation(nextRow, nextColumn);
//            }
//        }

        //setChanged();
        //notifyObservers();
}
