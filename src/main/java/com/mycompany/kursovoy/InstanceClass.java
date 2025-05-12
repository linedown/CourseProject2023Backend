package com.mycompany.kursovoy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InstanceClass {
    private static Connection connection;
    public static Connection getInstance(){
        if(connection == null){
            try{
            Class.forName("org.postgresql.Driver"); 
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/new_trains", "postgres", "reco3377");
            } catch(ClassNotFoundException | SQLException e){
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
