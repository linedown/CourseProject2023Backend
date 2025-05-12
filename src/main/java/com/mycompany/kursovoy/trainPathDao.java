package com.mycompany.kursovoy;

import java.sql.*;
import java.util.*;

public class trainPathDao implements daoInterface<trainPath>{
    
    private static trainPathDao instPer;
    private final Connection con;
    
    public trainPathDao(Connection con){
        this.con = con;
    }
    
    public static trainPathDao getInstance(){
        if(instPer == null){
            instPer = new trainPathDao(InstanceClass.getInstance());
        }
        return instPer;
    }

    @Override
    public trainPath getData(int id) {
        try(PreparedStatement ps = con.prepareStatement("select * from train_path where path_id = ?")){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                trainPath tp = new trainPath();
                tp.setId(rs.getInt("path_id"));
                tp.setPathName(rs.getString("path_name"));
                
                return tp;
            }
        } catch(SQLException e){
            e.getMessage();
        }
        
        return null;
    }
    
    @Override
    public int insertData(trainPath tp) throws SQLException{
        int id = -1;
        try(PreparedStatement ps = con.prepareStatement("insert into train_path(path_id, path_name) values(?, ?)", Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, tp.getId());
            ps.setString(2, tp.getPathName());
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
   public int updateData(trainPath tp) throws SQLException{
        try(PreparedStatement ps = con.prepareStatement("update train_path set path_name = ? where path_id = ?")){
            ps.setString(1, tp.getPathName());
            ps.setInt(2, tp.getId());
            ps.executeUpdate();
        } catch(SQLException e){
            e.getMessage();
        }
        return tp.getId();
    }

    @Override
    public void deleteData(int id) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement("delete from train_path where path_id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.getMessage();
        }
    }

    @Override
    public List<trainPath> listData() {
        List<trainPath> listTP = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from train_path")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                trainPath tp = new trainPath();
                tp.setId(rs.getInt("path_id"));
                tp.setPathName(rs.getString("path_name"));
                listTP.add(tp);
            }
            return listTP;
            
        }catch(SQLException e){
            e.getMessage();
        }
        return null;
    }
    
}
