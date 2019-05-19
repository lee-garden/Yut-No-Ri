package models;

public class Location {
    private int id;
    private int row;
    private int column;

    public int getId(){
        return id;
    }
    protected void setId(int id){
        this.id = id;
    }
    public int getRow(){
        return row;
    }
    public int getColumn() {
        return column;
    }
    protected void setLocation(int row, int column){
        this.row = row;
        this.column = column;
    }
}
