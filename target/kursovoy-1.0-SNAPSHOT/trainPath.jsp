<%-- 
    Document   : trainPath
    Created on : 9 дек. 2023 г., 20:29:24
    Author     : press f
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.kursovoy.trainPath"%>
<%@ page import="com.mycompany.kursovoy.trainPathDao"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Путииииииииииии</title>
    </head>
    <body>
    <center>
        <h2>Пути поездов</h2>
        <table>    
            <thead>
                <tr>
                    <th><b><u>pathID</u></b></th>
                    <th><b><u>pathNAME</u></b></th>
                </tr>
            </thead>
            <tbody>
                <%--@elvariable id="tp" type="com.mycompany.kursovoy.trainPath"--%>
                <%--@elvariable id="TPD" type="com.mycompany.kursovoy.trainPathDao"--%>
                <c:forEach items="${TPD.listData()}" var="tp">
                    <tr>
                        <form method="post" action="<c:url value="/trainPathChangeServlet"/>">
                            <td>
                                <input type="number" readonly name="path_id" value="${tp.id}"/>
                            </td>
                            <td>
                                <input type="text" name="path_name" value="${tp.pathName}"/>
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
                        <form method="post" action="<c:url value="/trainPathChangeServlet"/>">
                            <td>
                                <input type="number" name="id"/>
                            </td>
                            <td>
                                <input type="text" name="path_name" value=""/>
                            </td>
                            <td>
                                <input type="submit" value="Сохранить">
                            </td>
                        </form>
                    </tr>
            </tbody>
        </table>
                            <h2><a href="train.jsp">=> Поезда <=</a></h2>
                            <h3><a href="trainModel.jsp"><= Модели поездов</a> || <a href="trainWorker.jsp">Работники поездов =></a></h3>
                            <h4><a href="main.jsp">->На главную JSP страницу<-</a></h4>
    </center>
    </body>
</html>
