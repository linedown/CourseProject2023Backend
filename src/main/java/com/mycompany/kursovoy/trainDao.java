package com.mycompany.kursovoy;

import java.sql.*;
import java.util.*;

public class trainDao implements daoInterface<Train> {
    
    private static trainDao instPer;
    private final Connection con;
    
    public trainDao(Connection con){
        this.con = con;
    }
    
    public static trainDao getInstance(){
        if(instPer == null){
            instPer = new trainDao(InstanceClass.getInstance());
        }
        return instPer;
    }
    
    @Override
    public Train getData(int id) {
        try(PreparedStatement ps = con.prepareStatement("select * from train where train_id = ?")){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Train t = new Train();
                t.setId(rs.getInt("train_id"));
                t.setTrainName(rs.getString("train_name"));
                t.setTrainColor(rs.getString("train_color"));
                t.setTrainElect(rs.getBoolean("train_electrofication"));
                t.setTrainDestination(rs.getString("train_destination"));
                t.setModelId(rs.getInt("model_id"));
                
                return t;
            }
        } catch(SQLException e){
            e.getMessage();
        }
        
        return null;
    }

    @Override
    public void deleteData(int id) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement("delete from train where train_id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.getMessage();
        }
    }

    @Override
    public int insertData(Train t) throws SQLException {
        int id = -1;
        try(PreparedStatement ps = con.prepareStatement("insert into train(train_id, train_name, train_color, train_electrofication, train_destination, model_id) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, t.getId());
            ps.setString(2, t.getTrainName());
            ps.setString(3, t.getTrainColor());
            ps.setBoolean(4, t.getTrainElect());
            ps.setString(5, t.getTrainDestination());
            ps.setInt(6, t.getModelId());
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
    public int updateData(Train t) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement("update train set(train_name, train_color, train_electrofication, train_destination, model_id) = (?, ?, ?, ?, ?) where train_id = ?")){
            ps.setString(1, t.getTrainName());
            ps.setString(2, t.getTrainColor());
            ps.setBoolean(3, t.getTrainElect());
            ps.setString(4, t.getTrainDestination());
            ps.setInt(5, t.getModelId());
            ps.setInt(6, t.getId());
            ps.executeUpdate();
        } catch(SQLException e){
            e.getMessage();
        }
        return t.getId();
    }

    @Override
    public List<Train> listData() {
        List<Train> listT = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from train")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Train t = new Train();
                t.setId(rs.getInt("train_id"));
                t.setTrainName(rs.getString("train_name"));
                t.setTrainColor(rs.getString("train_color"));
                t.setTrainElect(rs.getBoolean("train_electrofication"));
                t.setTrainDestination(rs.getString("train_destination"));
                t.setModelId(rs.getInt("model_id"));
                listT.add(t);
            }
            return listT;
            
        }catch(SQLException e){
            e.getMessage();
        }
        return null;
    }
}
