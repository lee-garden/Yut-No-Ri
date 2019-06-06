package controller;

import models.YutNoRiSet;
import models.RuleTable;
import models.Circle;
import models.Piece;

import java.util.ArrayList;

public class GameProcessController {

  RuleTable ruleTable;
  private static ArrayList<Integer> resultSet;
  private static ArrayList<ArrayList<Integer>> reachableLocation;

  public boolean gameProgress(int turn, models.YutNoRiSet set) {
    models.YutNoRiSet yutSet = set;
    int numCanMove = 0;
    int pieceId;
    boolean endCondition;
    int deleteResult;
    boolean catchPoint = false;

    int[] nextLocation = new int[2];

    resultSet = new ArrayList<Integer>();
    reachableLocation = new ArrayList<>();
    int result;

    do {

      /*  Before Yut Roll State  */
      do {
        result = yutSet.getYutSet().rollYut(); //윷을 굴린 결과를 배열에 저장
        resultSet.add(result);
        numCanMove++;
      } while (result == 4 || result == 0);//모나 윷이 나오면 한번 더 윳을 굴린다.

      while (numCanMove >= 1) {// 움직일 수 있는 횟수가 남아있는동안

        /*  Choose Move Piece State  */
        pieceId = choicePiece(); // 움직일 말을 선택하고

        // reachableLocaion을 받아옵니다.
        // 첫번째 인자는 circleId이고요
        // 두번째 인자는 result입니다.
        reachableLocation = getMovableCircleIds(pieceId, resultSet, set);
        set.getBoard().showMovable(reachableLocation.get(0));


        /* 입력으로 circle의 위치를 받아옵니다. */


        int chosenCicleId =
          set.getBoard().getCircleByLocation(nextLocation[0], nextLocation[1]).getId();

        for(int i = 0; i < reachableLocation.get(1).size(); i++){
          int selectedCircleId = reachableLocation.get(0).get(i);
          if(selectedCircleId == chosenCicleId){
            resultSet.remove(resultSet.indexOf(reachableLocation.get(1).get(i))); // 사용한 result를 담음 이것을 resultSet에서 삭제 해줘야함.
          }
        }

        catchPoint = set.tryCatch(pieceId, nextLocation[0], nextLocation[1]);

        set.move(pieceId, nextLocation[0], nextLocation[1]);    // numCanMove가 1이라면 바로 움직이면 된다.
        numCanMove--;
      }
    }while(catchPoint); // checkPoint 즉, 상대 말을 잡은만큼 턴을 더 진행 할 수 있음.

    endCondition = checkEndGame(set);

    return endCondition;
  }


  public boolean checkEndGame(YutNoRiSet set){
    for(int i = 0; i < set.getNumOfPlayer; i++){
      if(set.getPlayer().getLeftNumOfPieceOfPlayer(i) == 0){
        return true;
      }
    }
    return false;
  }


  // 룰에 따라서 갈 수 있는 위치의 circleId들과 그에 상응하는 result 값을 반환한다.
  ArrayList<ArrayList<Integer>> getMovableCircleIds(int pieceId,
                                                    ArrayList<Integer> result,
                                                    YutNoRiSet set){
    ArrayList<ArrayList<Integer>> movableCircle = new ArrayList<>();
    Piece targetPiece = set.getPlayer().getPieceByPieceId(pieceId);
    Circle currentCircle =
      set.getBoard().getCircleByLocation(targetPiece.getRow(), targetPiece.getColumn());

    // circle id들.
    ArrayList<Integer> ids = new ArrayList<>();
    // result 들
    ArrayList<Integer> results = new ArrayList<>();

    for(int i : result){
      int[] nextMovableCircleIds = ruleTable.getNextMoveCircleIds(currentCircle.getId(), pieceId);
      ids.add(nextMovableCircleIds[0]);
      results.add(i);
      if(nextMovableCircleIds[1] != -1){
        ids.add(nextMovableCircleIds[1]);
        results.add(i);
      }
    }

    movableCircle.add(ids);
    movableCircle.add(result);
    return movableCircle;
  }

}
