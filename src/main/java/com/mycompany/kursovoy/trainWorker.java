package com.mycompany.kursovoy;

public class trainWorker {
    private int id;
    private String workerFIO;
    private String workerPhone;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setWorkerFIO(String workerFIO){
        this.workerFIO = workerFIO;
    }
    
    public String getWorkerFIO(){
        return workerFIO;
    }
    
    public void setWorkerPhone(String workerPhone){
        this.workerPhone = workerPhone;
    }
    
    public String getWorkerPhone(){
        return workerPhone;
    }
}
