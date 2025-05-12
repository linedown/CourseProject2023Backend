package com.mycompany.kursovoy;

import java.util.List;
import java.sql.SQLException;

public interface daoInterface<T>{
    T getData(int id);
    void deleteData(int id) throws SQLException;
    int insertData(T object) throws SQLException;
    int updateData(T object) throws SQLException;
    List<T> listData();
}
