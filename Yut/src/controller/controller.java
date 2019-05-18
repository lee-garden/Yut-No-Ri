package controller;

import java.util.ArrayList;

public class controller{
	
	public YutNoRiSet yutnoriset = new YutNoRiSet();

	public int playerTurn = 1;
	private static ArrayList<String> result;
	
	public int checkTurn(){
		return playerTurn;
	}
	
	public int nextTurn(){
		return playerTurn%4 + 1;
	}
	
	public boolean gameProgress(){
		int numCanMove=0;
		int catchPoint=0;
		result = new ArrayList<String>();
		String 
		
		do{
			if(catchPoint>=1){
				catchPoint--;
			}
			/*  Before Yut Roll State  */
			do{
				result.add(yutnoriset.rollyut()); //윷을 굴린 결과를 배열에 저장
				numCanMove++;
				
			}while(result=="윷"||"모");//모나 윷이 나오면 한번 더 윳을 굴린다.
			
			while(numCanMove>=1){// 움직일 수 있는 횟수가 남아있는동안
				
				/*  Choose Move Piece State  */
				choicePiece();// 움직일 말을 선택하고
				if(numCanMove>1){       //2개 이상 result가 있으면
					showMovable(result);//움직일 수 있는 칸을 보여주고
					selectMovePoint();  //움직일 circle을 정해준 후
					move();             //움직인다.
					numCanMove--;
				}
				else{
					move();	// numCanMove가 1이라면 바로 움직이면 된다.
					numCanMove--;
				}
				
				/*  Piece Moved State  */
				if(checkCatch()){
					catchPoint++; // 만약 상대 말을 잡았다면 checkPoint를 올려줌
				}
				
				if(checkGroup()){
					group();
				}
				
				if(checkArrive()){
					return checkEndGame(); // 만약 도착을 했을때 남은 말이 없다면 게임이 종료된다.
				}
			}
		}while(catchPoint>1); // checkPoint 즉, 상대 말을 잡은만큼 턴을 더 진행 할 수 있음.
		
		nextTurn(); // 위의 과정을 다 거쳤는데 게임이 안끝났다면 턴을 넘겨줌.
		return checkEndGame();
		
	}
	
	public boolean checkEndGame(){
		boolean end = false;
		if(numOfPiece<=0) {end = true;}
		return end;
	}
}



//
//void setOption(int playernum, int piecenum){
//	yutset.setOption
//}
//
//int checkTurn(){
//	return turn;
//}
//
//void rollYut(int turn){}
//void choosePiece(){}
//void showMovable(int){}
//void movePiece(){}
//boolean checkCatch(position){}
//boolean checkGroup(position){}
//boolean checkArriv(position){}
//
//int nextTurn(int turn){
//	int t = turn%(yutset.getNumOfPlayer()) + 1;
//	return t;
//}
//
//boolean checkEndGame(){}
//
//void restartGame(){}
//void endGame(){}