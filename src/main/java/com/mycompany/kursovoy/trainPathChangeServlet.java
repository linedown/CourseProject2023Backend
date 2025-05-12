package com.mycompany.kursovoy;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class trainPathChangeServlet extends HttpServlet {   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        trainPathDao TPD = trainPathDao.getInstance();
        
        if(request.getParameter("path_id") == null){
            if(request.getParameter("path_name") != null){
                trainPath tp = new trainPath();
                tp.setId(Integer.parseInt(request.getParameter("id")));
                tp.setPathName(request.getParameter("path_name"));
                try{
                   TPD.insertData(tp);
                   this.getServletContext().getRequestDispatcher("/trainPath.jsp").forward(request, response);
                   return;
                } catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }
            else
                response.sendError(409);
            return;
        }
        
        int id = Integer.parseInt(request.getParameter("path_id"));
        trainPath tp = trainPathDao.getInstance().getData(id);
        if(tp == null){
            response.sendError(405);
            return;
        }
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("delete") == null){
            tp.setPathName(request.getParameter("path_name"));
            try {
                TPD.updateData(tp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else{
            try {
                TPD.deleteData(tp.getId());
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        
        this.getServletContext().getRequestDispatcher("/trainPath.jsp").forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
