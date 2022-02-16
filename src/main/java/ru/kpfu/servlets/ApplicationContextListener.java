package ru.kpfu.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBHelperInterface db = new DBHelper();
        db.createTable(ApplicationParameters.USERS, User.getParameters());
        db.createTable(ApplicationParameters.PHOTOS, Photo.getParameters());
        db.createTable(ApplicationParameters.UPLOADED, User.getParametersUsersAndPhotos());
        db.createTable(ApplicationParameters.PURCHASED, User.getParametersUsersAndPhotos());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((DBHelper) sce.getServletContext().getAttribute("db")).closeConnection();
        sce.getServletContext().removeAttribute("db");
    }
}
