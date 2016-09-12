<%--
  Created by IntelliJ IDEA.
  User: JJ
  Date: 4/13/16
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/pagestyle">
    <title>Airline1 | Update password</title>
</head>
<body>
    <div id="page">
        <div id="header">
            <div id="logo"><img src="${pageContext.request.contextPath}/resources/image/logo.png"/></div>
        </div>
        <div id="content">

            <div id="changePasswordBox">
                <form action="/updatePassword" method="post">
                    <div id="changePasswordHeader">
                        <span><label>Update Password</label></span>
                    </div>
                    <c:if test="${sessionScope.errorMessage!=null}">
                        <div class="error changePasswordLine" >${sessionScope.errorMessage}</div>
                    </c:if>
                    <div class="changePasswordLine">
                        <label>Old password</label>
                        <input type="password" id="oldpassword" name="oldpassword" class="userName"/>
                    </div>
                    <div class="changePasswordLine">
                        <label>New password</label>
                        <input type="password" id="newpassword" name="newpassword" class="password"/>
                    </div>
                    <div class="changePasswordLine">
                        <label>Confirm password</label>
                        <input type="password" id="confirmpassword" name="confirmpassword" class="password"/>
                    </div>
                    <div align="right" class="updatePasswordButton">
                        <input type="submit" name="submit" value="Update"/>
                    </div>
                </form>
            </div>

        </div>
        <div id="footer">

        </div>
    </div>
</body>
</html>
