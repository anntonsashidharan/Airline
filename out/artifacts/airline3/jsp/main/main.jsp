<%@ page import="com.airline.system.APPStatics" %>
<%@ page import="com.airline.domain.user.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: JJ
  Date: 4/13/16
  Time: 11:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/pagestyle">
    <title>Airline1 | Portal</title>
</head>
<body>
    <div id="page">
        <div id="header">
            <div id="logo"><img src="${pageContext.request.contextPath}/resources/image/logo.png"/></div>
            <div id="userOption">
                <div><lable><a href="/logout">Signout<a></lable></div>
                <div><lable> &nbsp;|&nbsp;   </lable></div>
                <div><lable><%=((User)session.getAttribute(APPStatics.SessionStatics.USER)).getUserName()%></lable></div>
            </div>
        </div>
        <div id="content">

            <div id="treeMenu">
                <c:set var="node" value="${sessionScope.treeMenu}" scope="request"/>
                <ul>
                    <jsp:include page="/jsp/treeNode/treeNode.jsp"/>
                </ul>
            </div>
            <div id="contentPane">
                content
            </div>

        </div>
        <div id="footer">

        </div>
    </div>
</body>
</html>
