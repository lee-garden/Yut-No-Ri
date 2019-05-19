package models;

import java.util.Random;

public class Yut {


    private boolean status = true;
    private String type;

    public Yut(String type){
        this.type = type;
    }

    public boolean getStatus(){
        return status;
    }

    boolean throwYut(){
        Random generate = new Random();
        status = generate.nextBoolean();
        return status;
    }

    public String getType(){
        return type;
    }
}
