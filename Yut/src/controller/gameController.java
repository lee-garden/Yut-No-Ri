package controller;

import models.YutNoRiSet;

public class gameController {

    public void gameControll() {
        YutNoRiSet yutSet = new YutNoRiSet();
        eventController c = new eventController();
        //view.view v = new veiw();
        boolean checkReGame = false;

        int numofPlayer, numofPiece;

        do {
            //numofPlayer와 numofPiece를 입력받아 setOption
            yutSet.setOption(numofPlayer, numofPiece);
            while (!c.gameProgress(yutSet.getPlayerTurn(), yutSet)) {
                //게임 진행중.
                yutSet.setPlayerTurn(yutSet.getPlayerTurn() + 1 % numofPlayer);
            }
            // 재시작 할건지 끝낼건지?
            //if(재시작버튼){ checkReGame = true;}
            //else if(종료버튼) { checkReGame = false;}
        } while (checkReGame);
    }
}
