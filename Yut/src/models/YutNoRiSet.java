package models;
import java.util.ArrayList;
import java.beans.*;

public class YutNoRiSet{

    final int BACKDO = -1;
    final int MO = 0;
    final int DO = 1;
    final int GEA = 2;
    final int GIR = 3;
    final int YUT = 4;

    public Player[] players;
    public Circle[] boards;
    public Yut[] yutSet;

    public YutNoRiSet(int numOfPlayer, int numOfPiece){
        for(int i = 0; i < numOfPlayer; i++){
            players[i] = new Player(i, numOfPiece);
        }

        for(int i = 0; i < 4; i++){
            if(i != 3) {
                yutSet[i] = new Yut("Normal");
            } else {
                yutSet[i] = new Yut("BackDo");
            }
        }
    }

    public int rollYut(){
        int cal = 0;
        for(int i = 0; i < 3; i++){
            if(!yutSet[i].throwYut()){
                cal++;
            }
        }

        if(!yutSet[3].throwYut()){
            if(cal == 0){
                return BACKDO;
            }
            cal++;
        }

        return cal;
    }
}