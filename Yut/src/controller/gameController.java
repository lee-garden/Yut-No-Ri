package controller;

//import views.MouseClick;
import views.YutGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import views.YutGui;
import  views.BackGroundPanel;


public class gameController {
    public static int playerNumber;
    public static int pieceNumber;
    public static eventController c;
    public static void main(String[] args) throws IOException {
        YutGui yutgui = new YutGui();
        models.YutNoRiSet yutSet = new models.YutNoRiSet();

        yutgui.setupStartUI();
        BackGroundPanel midP=yutgui.midPanel;

        yutgui.midPanel.enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yutgui.mainFrame.setVisible(false);
                //pieceNumber = Integer.parseInt(yutgui.midPanel.pieceNumberInput.getText());
                playerNumber = Integer.parseInt(midP.playerNumberInput.getText());
                pieceNumber = Integer.parseInt(midP.pieceNumberInput.getText());
                if (e.getSource().equals(midP.enter)) {
                    yutgui.setupYutGUI(playerNumber, pieceNumber);
                }
                yutSet.setOption(playerNumber, pieceNumber);
                yutSet.setPlayerTurn(0);
                c = new eventController(yutSet, yutgui);
                System.out.println("controller create");
                c.gameProgress(yutSet.getPlayerTurn(), yutSet);
            }
        });

        return;
    }
}




//    public void gameControll() {
//        //eventController c = new eventController();
//
//        do {
//            //numofPlayer와 numofPiece를 입력받아 setOption
//            //yutSet.setOption(numofPlayer, numofPiece);
////            while (!c.gameProgress(yutSet.getPlayerTurn(), yutSet)) {
////                //게임 진행중.
////                yutSet.setPlayerTurn(yutSet.getPlayerTurn() + 1 % numofPlayer);
////            }
////            // 재시작 할건지 끝낼건지?
//            //if(재시작버튼){ checkReGame = true;}
//            //else if(종료버튼) { checkReGame = false;}
//        } while (checkReGame);
//    }
