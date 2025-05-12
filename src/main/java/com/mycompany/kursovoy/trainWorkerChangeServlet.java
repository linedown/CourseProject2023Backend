package com.mycompany.kursovoy;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class trainWorkerChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        trainWorkerDao TWD = trainWorkerDao.getInstance();
        
        if(request.getParameter("worker_id") == null){
            if(request.getParameter("worker_fio") != null){
                trainWorker tw = new trainWorker();
                tw.setId(Integer.parseInt(request.getParameter("id")));
                tw.setWorkerFIO(request.getParameter("worker_fio"));
                tw.setWorkerPhone(request.getParameter("worker_phone"));
                try{
                   TWD.insertData(tw);
                   this.getServletContext().getRequestDispatcher("/trainWorker.jsp").forward(request, response);
                   return;
                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }
            else
                response.sendError(409);
            return;
        }
        
        int id = Integer.parseInt(request.getParameter("worker_id"));
        trainWorker tw = trainWorkerDao.getInstance().getData(id);
        if(tw == null){
            response.sendError(406);
            return;
        }
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("delete") == null){
            tw.setWorkerFIO(request.getParameter("worker_fio"));
            tw.setWorkerPhone(request.getParameter("worker_phone"));
            try {
                TWD.updateData(tw);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else{
            try {
                TWD.deleteData(tw.getId());
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        
        this.getServletContext().getRequestDispatcher("/trainWorker.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
