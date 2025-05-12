package com.mycompany.kursovoy;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener()
public class ContexClass implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("TMD", trainModelDao.getInstance());
        servletContext.setAttribute("TWD", trainWorkerDao.getInstance());
        servletContext.setAttribute("TPD", trainPathDao.getInstance());
        servletContext.setAttribute("TD", trainDao.getInstance());
    }
}

