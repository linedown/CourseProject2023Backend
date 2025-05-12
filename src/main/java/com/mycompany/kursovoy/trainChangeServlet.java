package com.mycompany.kursovoy;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class trainChangeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        trainDao TD = trainDao.getInstance();
        
        if(request.getParameter("train_id") == null){
            if((request.getParameter("train_name") != null) && (request.getParameter("train_destination") != null) && (request.getParameter("model_id") != null)){
                Train t = new Train();
                t.setId(Integer.parseInt(request.getParameter("id")));
                t.setTrainName(request.getParameter("train_name"));
                t.setTrainColor(request.getParameter("train_color"));
                t.setTrainElect(Boolean.parseBoolean(request.getParameter("train_electrofication")));
                t.setTrainDestination(request.getParameter("train_destination"));
                t.setModelId(Integer.parseInt(request.getParameter("model_id")));
                try{
                   TD.insertData(t);
                   this.getServletContext().getRequestDispatcher("/train.jsp").forward(request, response);
                   return;
                } catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }
            else
                response.sendError(409);
            return;
        }
        
        int id = Integer.parseInt(request.getParameter("train_id"));
        Train t = trainDao.getInstance().getData(id);
        if(t == null){
            response.sendError(405);
            return;
        }
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("delete") == null){
            t.setTrainName(request.getParameter("train_name"));
            t.setTrainColor(request.getParameter("train_color"));
            t.setTrainElect(Boolean.parseBoolean(request.getParameter("train_electrofication")));
            t.setTrainDestination(request.getParameter("train_destination"));
            t.setModelId(Integer.parseInt(request.getParameter("model_id")));
            try {
                TD.updateData(t);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else{
            try {
                TD.deleteData(t.getId());
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        
        this.getServletContext().getRequestDispatcher("/train.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
