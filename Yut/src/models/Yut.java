package models;

import java.util.Random;

public class Yut {


  private boolean status;
  private String type;

  public Yut(String type){
    this.type = type;
    status = true;
  }

  boolean getStatus(){
    return status;
  }

  boolean throwYut(){
    Random generate = new Random();
    status = generate.nextBoolean();
    return status;
  }

  String getType(){
    return type;
  }
}
