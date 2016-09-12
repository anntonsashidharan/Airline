<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: JJ
  Date: 4/13/16
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/pagestyle">
    <title>Airline1 | Login</title>
</head>
<body>
    <div id="page">
        <div id="header">
            <div id="logo"><img src="${pageContext.request.contextPath}/resources/image/logo.png"/></div>
        </div>
        <div id="content">

            <div id="loginBox">
                <form action="/login" method="post">
                    <div id="loginHeader">
                        <span><label>Login</label></span>
                    </div>
                    <c:if test="${sessionScope.errorMessage!=null}">
                        <div class="error loginLine" >${sessionScope.errorMessage}</div>
                    </c:if>
                    <div class="loginLine">
                        <label>User Name</label>
                        <input type="text" id="userName" name="userName" class="userName"/>
                    </div>
                    <div class="loginLine">
                        <label>Password</label>
                        <input type="password" id="password" name="password" class="password"/>
                    </div>
                    <div align="right" class="loginButton">
                        <input type="submit" name="submit" value="Login"/>
                    </div>
                    <div align="right" class="forgotPassword">
                        <span><a href="#">Forgot password?</a></span>
                    </div>
                </form>
            </div>

        </div>
        <div id="footer">
        </div>
    </div>
</body>
</html>
