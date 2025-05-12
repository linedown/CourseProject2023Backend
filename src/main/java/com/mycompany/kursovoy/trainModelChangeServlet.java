package com.mycompany.kursovoy;

import java.io.IOException;
import java.sql.*;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class trainModelChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        trainModelDao TMD = trainModelDao.getInstance();
        
        if(request.getParameter("model_id") == null){
            if(request.getParameter("model_name") != null){
                trainModel tm = new trainModel();
                tm.setId(Integer.parseInt(request.getParameter("id")));
                tm.setModelName(request.getParameter("model_name"));
                try{
                   TMD.insertData(tm);
                   this.getServletContext().getRequestDispatcher("/trainModel.jsp").forward(request, response);
                   return;
                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }
            else
                response.sendError(409);
            return;
        }
        
        int id = Integer.parseInt(request.getParameter("model_id"));
        trainModel tm = trainModelDao.getInstance().getData(id);
        if(tm == null){
            response.sendError(405);
            return;
        }
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("delete") == null){
            tm.setModelName(request.getParameter("model_name"));
            try {
                TMD.updateData(tm);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else{
            try {
                TMD.deleteData(tm.getId());
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        
        this.getServletContext().getRequestDispatcher("/trainModel.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
