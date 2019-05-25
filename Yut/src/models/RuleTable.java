package models;

public class RuleTable {
  int[][][] nextMoveTable;
  public RuleTable(){
    nextMoveTable = new int[29][6][2];

    for(int i = 0; i < 20; i++){
      for(int j = 0; j < 6; j++){
        if(i == 0 && j == 0){
          nextMoveTable[i][j][0] = 1;
        } else if(i+j >= 20){
          nextMoveTable[i][j][0] = 0;
        } else {
            nextMoveTable[i][j][0] = j;
        }
        nextMoveTable[i][j][1] = -1;
      }
    }
    nextMoveTable[5][0][1] = -1;
    nextMoveTable[5][1][1] = 25;
    nextMoveTable[5][2][1] = 26;
    nextMoveTable[5][3][1] = 22;
    nextMoveTable[5][4][1] = 27;
    nextMoveTable[5][5][1] = 28;

    nextMoveTable[10][0][1] = -1;
    nextMoveTable[10][1][1] = 24;
    nextMoveTable[10][2][1] = 23;
    nextMoveTable[10][3][1] = 22;
    nextMoveTable[10][4][1] = 21;
    nextMoveTable[10][5][1] = 20;

    for(int i = 0; i < 9; i++){
      for(int j = 0; j < 6; j++){
        nextMoveTable[i+20][j][1] = -1;
      }
    }

    nextMoveTable[20][0][0] = 21;
    nextMoveTable[20][1][0] = 0;
    nextMoveTable[20][2][0] = 0;
    nextMoveTable[20][3][0] = 0;
    nextMoveTable[20][4][0] = 0;
    nextMoveTable[20][5][0] = 0;

    nextMoveTable[21][0][0] = 22;
    nextMoveTable[21][1][0] = 20;
    nextMoveTable[21][2][0] = 0;
    nextMoveTable[21][3][0] = 0;
    nextMoveTable[21][4][0] = 0;
    nextMoveTable[21][5][0] = 0;

    nextMoveTable[22][0][0] = 23;
    nextMoveTable[22][1][0] = 21;
    nextMoveTable[22][2][0] = 20;
    nextMoveTable[22][3][0] = 0;
    nextMoveTable[22][4][0] = 0;
    nextMoveTable[22][5][0] = 0;
    nextMoveTable[22][0][1] = 26;
    nextMoveTable[22][1][1] = 27;
    nextMoveTable[22][2][1] = 28;
    nextMoveTable[22][3][1] = 15;
    nextMoveTable[22][4][1] = 16;
    nextMoveTable[22][5][1] = 17;

    nextMoveTable[23][0][0] = 24;
    nextMoveTable[23][1][0] = 22;
    nextMoveTable[23][2][0] = 21;
    nextMoveTable[23][3][0] = 20;
    nextMoveTable[23][4][0] = 0;
    nextMoveTable[23][5][0] = 0;

    nextMoveTable[24][0][0] = 10;
    nextMoveTable[24][1][0] = 23;
    nextMoveTable[24][2][0] = 22;
    nextMoveTable[24][3][0] = 21;
    nextMoveTable[24][4][0] = 20;
    nextMoveTable[24][5][0] = 0;

    nextMoveTable[25][0][0] = 5;
    nextMoveTable[25][1][0] = 26;
    nextMoveTable[25][2][0] = 22;
    nextMoveTable[25][3][0] = 27;
    nextMoveTable[25][4][0] = 28;
    nextMoveTable[25][5][0] = 15;

    nextMoveTable[26][0][0] = 25;
    nextMoveTable[26][1][0] = 22;
    nextMoveTable[26][2][0] = 27;
    nextMoveTable[26][3][0] = 28;
    nextMoveTable[26][4][0] = 15;
    nextMoveTable[26][5][0] = 16;

    nextMoveTable[27][0][0] = 22;
    nextMoveTable[27][1][0] = 28;
    nextMoveTable[27][2][0] = 15;
    nextMoveTable[27][3][0] = 16;
    nextMoveTable[27][4][0] = 17;
    nextMoveTable[27][5][0] = 18;

    nextMoveTable[28][0][0] = 27;
    nextMoveTable[28][1][0] = 15;
    nextMoveTable[28][2][0] = 16;
    nextMoveTable[28][3][0] = 17;
    nextMoveTable[28][4][0] = 18;
    nextMoveTable[28][5][0] = 19;
  }

  public int[] getNextMoveCircleIds(int circleId, int result){
    try{
      return nextMoveTable[circleId][result];
    } catch (NullPointerException e){
      /* ecceotion handling*/
    }

    return null;
  }
}
