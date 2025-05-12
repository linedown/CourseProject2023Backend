package com.mycompany.kursovoy;

import java.sql.*;
import java.util.*;

public class trainWorkerDao implements daoInterface<trainWorker>{
    
    private static trainWorkerDao instPer;
    private final Connection con;
    
    public trainWorkerDao(Connection con){
        this.con = con;
    }
    
    public static trainWorkerDao getInstance(){
        if(instPer == null){
            instPer = new trainWorkerDao(InstanceClass.getInstance());
        }
        return instPer;
    }
    
    @Override
    public int insertData(trainWorker tw) throws SQLException{
        int id = -1;
        try(PreparedStatement ps = con.prepareStatement("insert into train_worker(worker_id, worker_fio, worker_phone) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, tw.getId());
            ps.setString(2, tw.getWorkerFIO());
            ps.setString(3, tw.getWorkerPhone());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if((rs != null) && rs.next()){
                id = rs.getInt(1);
                rs.close();
            }
            
        } catch(SQLException e){
            e.getMessage();
        }
        
        return id;
    }
    
    @Override
    public int updateData(trainWorker tw) throws SQLException{
        try(PreparedStatement ps = con.prepareStatement("update train_worker set(worker_fio, worker_phone) = (?, ?) where worker_id = ?")){
            ps.setString(1, tw.getWorkerFIO());
            ps.setString(2, tw.getWorkerPhone());
            ps.setInt(3, tw.getId());
            ps.executeUpdate();
        } catch(SQLException e){
            e.getMessage();
        }
        return tw.getId();
    }

    @Override
    public trainWorker getData(int id) {
        try(PreparedStatement ps = con.prepareStatement("select * from train_worker where worker_id = ?")){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                trainWorker tw = new trainWorker();
                tw.setId(rs.getInt("worker_id"));
                tw.setWorkerFIO(rs.getString("worker_fio"));
                tw.setWorkerPhone("worker_phone");
                
                return tw;
            }
        } catch(SQLException e){
            e.getMessage();
        }
        
        return null;
    }

    @Override
    public void deleteData(int id) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement("delete from train_worker where worker_id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.getMessage();
        }
    }

    @Override
    public List<trainWorker> listData() {
        List<trainWorker> listTW = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from train_worker")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                trainWorker tw = new trainWorker();
                tw.setId(rs.getInt("worker_id"));
                tw.setWorkerFIO(rs.getString("worker_fio"));
                tw.setWorkerPhone(rs.getString("worker_phone"));
                listTW.add(tw);
            }
            return listTW;
            
        }catch(SQLException e){
            e.getMessage();
        }
        return null;
    }
}
    

