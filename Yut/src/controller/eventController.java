package controller;

import models.Circle;
import models.Piece;
import models.YutNoRiSet;

import java.util.ArrayList;

public class eventController {

    private static ArrayList<Integer> resultSet;
    private static ArrayList<Circle> reachableCircle;

    public boolean gameProgress(int turn, models.YutNoRiSet set) {
        models.YutNoRiSet yutSet = set;
        int numCanMove = 0;
        int catchPoint = 0;
        int picId;
        boolean endCondition;

        int[] nextLocation = new int[2];

        resultSet = new ArrayList<Integer>();
        reachableCircle = new ArrayList<Circle>();
        int result;

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
                picId = choicePiece(); // 움직일 말을 선택하고

                reachableCircle = getReachableCircle(resultSet, picId, yutSet);
                showMovable(resultSet, picId, yutSet);
                // 어디로 갈 까~ nextLocation Movable 에서 받아옴.


                /*이동하고자 하는 Circle에 다른 말이 있는지 비었는지 판단*/
                if (set.getCircleIsOccupiedByLocation(nextLocation[0], nextLocation[1])) {
                    /*만약 상대 말을 잡았다면*/
                    if (set.getOwnerOfPieceByLocation(nextLocation[0], nextLocation[1]) != turn) {
                        set.catchPieceWithLocation(nextLocation[0], nextLocation[1]);
                        catchPoint++;
                    } else { /*내 말을 만났다면 그룹.*/
                        set.groupPieceByPieceId(nextLocation[0], nextLocation[1], picId);
                    }
                }

                yutSet.move(picId, nextLocation[0], nextLocation[1]);    // numCanMove가 1이라면 바로 움직이면 된다.
                numCanMove--;
            }
        }while(catchPoint>1); // checkPoint 즉, 상대 말을 잡은만큼 턴을 더 진행 할 수 있음.

        endCondition = checkEndGame();

        return endCondition;
    }



    public boolean checkEndGame(){
        boolean end = false;
        if(numOfPiece<=0) {end = true;}
        return end;
    }

    public ArrayList<Circle> getReachableCircle(ArrayList<Integer> result, int picId, models.YutNoRiSet set){
        ArrayList<Circle> tmp = new ArrayList<Circle>();
        int[] location = new int[2];

        location = set.getPieceLocationByPieceId(picId);

        for(int i = 0; i < result.size(); i++){
            tmp.add(set.get)
        }

        return tmp;
    }

    public void showMovable(ArrayList<Integer> result, int pieceId, models.YutNoRiSet set){
        ArrayList<ArrayList<Integer>> temp;
        int[] location = new int[2];


        if(set.getPieceIsInTheBoardByPieceId(pieceId)){
            location[0] =1;
            location[1] =1;
        }else{
            location = set.getPieceLocationByPieceId(pieceId);
        }

        temp = set.getMoveVectorByCircleLocation(location[0], location[1]);

        for(int i : result){
            for(int j = 0; j < set.getNumberOfWayCanChooseOnCircle(location[0], location[1]); j++){
                int nextRow = temp.get(0).get(j)*i;
                int nextColumn = temp.get(1).get(j)*i;

                // 이동하려 하는 위치가 판의 영역을 넘어서는 경우 맞춰줌
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

                set.setCircleClickableByLocation(nextRow, nextColumn);
            }
        }
    }
}
