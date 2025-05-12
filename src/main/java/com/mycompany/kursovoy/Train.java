package com.mycompany.kursovoy;

public class Train {
    private int id;
    private String trainName;
    private String trainColor;
    private boolean trainElect;
    private String trainDestination;
    private int modelId;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setTrainName(String trainName){
        this.trainName = trainName;
    }
    
    public String getTrainName(){
        return trainName;
    }
    
    public void setTrainColor(String trainColor){
        this.trainColor = trainColor;
    }
    
    public String getTrainColor(){
        return trainColor;
    }
    
    public void setTrainElect(boolean trainElect){
        this.trainElect = trainElect;
    }
    
    public boolean getTrainElect(){
        return trainElect;
    }
    
    public void setTrainDestination(String trainDestination){
        this.trainDestination = trainDestination;
    }
    
    public String getTrainDestination(){
        return trainDestination;
    }
    
    public void setModelId(int modelId){
        this.modelId = modelId;
    }
    
    public int getModelId(){
        return modelId;
    }
}
