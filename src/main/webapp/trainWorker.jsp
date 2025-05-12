<%-- 
    Document   : trainWorker
    Created on : 9 дек. 2023 г., 20:30:16
    Author     : press f
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.kursovoy.trainWorker"%>
<%@ page import="com.mycompany.kursovoy.trainWorkerDao"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>РАБотникИ</title>
    </head>
    <body>
    <center><h2>Вот они. Работники!</h2>
        <table>    
            <thead>
                <tr>
                    <th><b><u>workerID</u></b></th>
                    <th><b><u>workerPhone</u></b></th>
                    <th><b><u>workerFIO</u></b></th>
                </tr>
            </thead>
            <tbody>
                <%--@elvariable id="tw" type="com.mycompany.kursovoy.trainWorker"--%>
                <%--@elvariable id="TWD" type="com.mycompany.kursovoy.trainWorkerDao"--%>
                <c:forEach items="${TWD.listData()}" var="tw">
                    <tr>
                        <form method="post" action="<c:url value="/trainWorkerChangeServlet"/>">
                            <td>
                                <input type="number" readonly name="worker_id" value="${tw.id}"/>
                            </td>
                            <td>
                                <input type="text" name="worker_phone" value="${tw.workerPhone}"/>
                            </td>
                            <td>
                                <input type="text" name="worker_fio" value="${tw.workerFIO}"/>
                            </td>
                            <td>
                                   
                                <label>
                                    <input type="checkbox" name="delete">
                                    Убрать
                                </label>
                            </td>
                            <td>
                                <input type="submit" value="Сохранить">
                            </td>
                        </form>
                    </tr>
                </c:forEach>
                    <tr>
                        <form method="post" action="<c:url value="/trainWorkerChangeServlet"/>">
                            <td>
                                <input type="number" name="id"/>
                            </td>
                            <td>
                                <input type="text" name="worker_phone" value=""/>
                            </td>
                            <td>
                                <input type="text" name="worker_fio" value=""/>
                            </td>
                            <td>
                                <input type="submit" value="Сохранить">
                            </td>
                        </form>
                    </tr>
            </tbody>
        </table>
                            <h2><a href="train.jsp">=> Поезда <=</a></h2>
                            <h3><a href="trainPath.jsp"><= Пути для поездов</a> || <a href="trainModel.jsp">Модели поездов =></a></h3>
                            <h4><a href="main.jsp">->На главную JSP страницу<-</a></h4>
    </center>
    </body>
</html>
