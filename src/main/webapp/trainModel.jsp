<%-- 
    Document   : trainModel
    Created on : 9 дек. 2023 г., 20:29:00
    Author     : press f
--%>

<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.kursovoy.trainModel"%>
<%@ page import="com.mycompany.kursovoy.trainModelDao"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>мОдЕлИ</title>
    </head>
    <body>
    <center>
        <h2>Модели</h2>
        
        <table>    
            <thead>
                <tr>
                    <th><b><u>modelID</u></b></th>
                    <th><b><u>modelNAME</u></b></th>
                </tr>
            </thead>
            <tbody>
                <%--@elvariable id="tm" type="com.mycompany.kursovoy.trainModel"--%>
                <%--@elvariable id="TMD" type="com.mycompany.kursovoy.trainModelDao"--%>
                <c:forEach items="${TMD.listData()}" var="tm">
                    <tr>
                        <form method="post" action="<c:url value="/trainModelChangeServlet"/>">
                            <td>
                                <input type="number" readonly name="model_id" value="${tm.id}"/>
                            </td>
                            <td>
                                <input type="text" name="model_name" value="${tm.modelName}"/>
                            </td>
                            <td>
                                <label>
                                    <input type="checkbox" readonly name="delete">
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
                        <form method="post" action="<c:url value="/trainModelChangeServlet"/>">
                            <td>
                                <input type="number" name="id"/>
                            </td>
                            <td>
                                <input type="text" name="model_name" value=""/>
                            </td>
                            <td>
                                <input type="submit" value="Сохранить">
                            </td>
                        </form>
                    </tr>
            </tbody>
        </table>
                            <h2><a href="train.jsp">=> Поезда <=</a></h2>
                            <h3><a href="trainWorker.jsp"><= Работники поездов</a> || <a href="trainPath.jsp">Пути для поездов =></a></h3>
                            <h4><a href="main.jsp">->На главную JSP страницу<-</a></h4>
    </center>
    </body>
</html>
