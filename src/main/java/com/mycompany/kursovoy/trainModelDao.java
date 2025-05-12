package com.mycompany.kursovoy;

import java.sql.*;
import java.util.*;

public class trainModelDao implements daoInterface<trainModel>{
    private static trainModelDao instPer;
    private final Connection con;
    
    public trainModelDao(Connection con){
        this.con = con;
    }
    
    public static trainModelDao getInstance(){
        if(instPer == null){
            instPer = new trainModelDao(InstanceClass.getInstance());
        }
        return instPer;
    }
    
    @Override
    public int insertData(trainModel tm) throws SQLException{
        int id = -1;
        try(PreparedStatement ps = con.prepareStatement("insert into train_model(model_id, model_name) values(?, ?)", Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, tm.getId());
            ps.setString(2, tm.getModelName());
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
    public int updateData(trainModel tm) throws SQLException{
        try(PreparedStatement ps = con.prepareStatement("update train_model set model_name = ? where model_id = ?")){
            ps.setString(1, tm.getModelName());
            ps.setInt(2, tm.getId());
            ps.executeUpdate();
        } catch(SQLException e){
            e.getMessage();
        }
        return tm.getId();
    }

    @Override
    public trainModel getData(int id) {
        try(PreparedStatement ps = con.prepareStatement("select * from train_model where model_id = ?")){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                trainModel tm = new trainModel();
                tm.setId(rs.getInt("model_id"));
                tm.setModelName(rs.getString("model_name"));
                
                return tm;
            }
        } catch(SQLException e){
            e.getMessage();
        }
        
        return null;
    }

    @Override
    public void deleteData(int id) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement("delete from train_model where model_id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.getMessage();
        }
    }

    @Override
    public List<trainModel> listData() {
        List<trainModel> listTM = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from train_model")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                trainModel tm = new trainModel();
                tm.setId(rs.getInt("model_id"));
                tm.setModelName(rs.getString("model_name"));
                listTM.add(tm);
            }
            return listTM;
            
        }catch(SQLException e){
            e.getMessage();
        }
        return null;
    }
    
}
