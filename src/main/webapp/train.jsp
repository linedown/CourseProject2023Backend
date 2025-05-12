<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.kursovoy.Train"%>
<%@ page import="com.mycompany.kursovoy.trainDao"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Поезда</title>
    </head>
    <body><center>
        <h1><b>Поезда: </b></h1>
        <table>    
            <thead>
                <tr>
                    <th><b><u>train_ID</u></b></th>
                    <th><b><u>train_NAME</u></b></th>
                    <th><b><u>train_COLOR</u></b></th>
                    <th><b><u>train_ELECTROFICATION</u></b></th>
                    <th><b><u>train_DESTINATION</u></b></th>
                    <th><b><u>model_ID</u></b></th>
                </tr>
            </thead>
            <tbody>
                <%--@elvariable id="t" type="com.mycompany.kursovoy.Train"--%>
                <%--@elvariable id="TD" type="com.mycompany.kursovoy.trainDao"--%>
                <c:forEach items="${TD.listData()}" var="t">
                    <tr>
                        <form method="post" action="<c:url value="/trainChangeServlet"/>">
                            <td>
                                <input type="number" readonly name="train_id" value="${t.id}"/>
                            </td>
                            <td>
                                <input type="text" name="train_name" value="${t.trainName}"/>
                            </td>
                            <td>
                                <input type="text" name="train_color" value="${t.trainColor}"/>
                            </td>
                            <td>
                                <input type="text" name="train_electrofication" value="${t.trainElect}"/>
                            </td>
                            <td>
                                <input type="text" name="train_destination" value="${t.trainDestination}"/>
                            </td>
                            <td>
                                <input type="text" name="model_id" value="${t.modelId}"/>
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
                        <form method="post" action="<c:url value="/trainChangeServlet"/>">
                            <td>
                                <input type="number" name="id"/>
                            </td>
                            <td>
                                <input type="text" name="train_name" value=""/>
                            </td>
                            <td>
                                <input type="text" name="train_color" value=""/>
                            </td>
                            <td>
                                <input type="text" name="train_electrofication" value=""/>
                            </td>
                            <td>
                                <input type="text" name="train_destination" value=""/>
                            </td>
                            <td>
                                <input type="text" name="model_id" value=""/>
                            </td>
                            <td>
                                <input type="submit" value="Сохранить">
                            </td>
                        </form>
                    </tr>
            </tbody>
        </table>
                            <h3><a href="trainWorker.jsp"><= Работники поездов</a> || <a href="trainModel.jsp">Модели поездов</a> || <a href="trainPath.jsp">Пути для поездов =></a></h3>
                            <h4><a href="main.jsp">->На главную JSP страницу<-</a></h4>
    </center>
    </body>
</html>
