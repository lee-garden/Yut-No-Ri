package models;

import java.util.Random;

public class Yut {


  private boolean status;
  private String type;

  Yut(String type){
    this.type = type;
    status = true;
  }

  public boolean getStatus(){
    return status;
  }

  public boolean throwYut(){
    Random generate = new Random();
    status = generate.nextBoolean();
    return status;
  }

  public String getType(){
    return type;
  }
}
