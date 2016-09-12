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
    <link rel="stylesheet" href="/resources/javascript/jquery-ui.min.css">
    <script src="/resources/javascript/jquery.js"></script>
    <script src="/resources/javascript/jquery-ui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">
    <script>
        $(document).ready(function () {
            if ($('input[type=radio][name=tripType]').val() == 'oneway') {
                $("#departDateDownTrip").css("display", "none");
            } else if ($('input[type=radio][name=tripType]').val() == 'round') {
                $("#departDateDownTrip").css("display", "block");
            }

            $("#departDateUpTrip").datepicker({

            });
            $("#arrivalDateUpTrip").datepicker({

            });

            $("#sourceAirport").autocomplete({
                source: $.map(<%=session.getAttribute("airportList")%>, function (value, key) {
                    return {
                        label: value,
                        value: key
                    };
                })
            });

            $("#destinationAirport").autocomplete({
                source: $.map(<%=session.getAttribute("airportList")%>, function (value, key) {
                    return {
                        label: value,
                        value: key
                    };
                })
            });
        });
    </script>

</head>
<body>
<div id="page">
    <div id="header">
        <div id="logo"><img src="${pageContext.request.contextPath}/resources/image/logo.png"/></div>
        <div id="userOption">
            <div><lable><a href="/logout">Signout<a></lable></div>
            <div><lable> &nbsp;|&nbsp;   </lable></div>
        </div>
    </div>
    <div id="content">

        <%--<div id="treeMenu">--%>
            <%--<c:set var="node" value="${sessionScope.treeMenu}" scope="request"/>--%>
            <%--<ul>--%>
                <%--<jsp:include page="/jsp/treeNode/treeNode.jsp"/>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <div id="contentPane">
            <div>
                <div>From : </div>
                <div><input type="text" id="sourceAirport" name="sourceAirport"/></div>
            </div>
            <div>
                <div>To : </div>
                <div><input type="text" id="destinationAirport" name="destinationAirport"/></div>
            </div>
            <div class="line3">
                <div>Depart Date : </div>
                <fmt:formatDate value="${sessionScope.upTripDepartureDate}"
                                var="formattedUpDepartureDate"
                                pattern="MM/dd/yyyy"></fmt:formatDate>
                <div class="col1"><input type="text" name="departDateUpTrip" id="departDateUpTrip"
                                         placeholder="Departure Date" value="${formattedUpDepartureDate}"/></div>
                <fmt:formatDate value="${sessionScope.downTripDepartureDate}"
                                var="formattedDownDepartDate"
                                pattern="MM/dd/yyyy"></fmt:formatDate>
                <div>Arrival Date : </div>
                <div class="col2"><input type="text" name="arrivalDateUpTrip" id="arrivalDateUpTrip"
                                         placeholder="Arrival Date" style="display: none" value="${formattedDownDepartDate}"/></div>
            </div>

            <div class="line3">
                <div>Depart Time : </div>
                <div class="col1"><input type="text" name="departTimeUpTripHour" id="departTimeUpTripHour"
                                         placeholder="Departure Date" value="${formattedUpDepartureDate}"/></div>
                <div class="col1"><input type="text" name="departTimeUpTripMinute" id="departTimeUpTripMinute"
                                         placeholder="Departure Date" value="${formattedUpDepartureDate}"/></div>
            </div>



            <div class="line3">
                <div>Arrival Time : </div>
                <div class="col1"><input type="text" name="arrivalTimeUpTripHour" id="arrivalTimeUpTripHour"
                                         placeholder="Departure Date" value="${formattedUpDepartureDate}"/></div>
                <div class="col1"><input type="text" name="arrivalTimeUpTripMinute" id="arrivalTimeUpTripMinute"
                                         placeholder="Departure Date" value="${formattedUpDepartureDate}"/></div>
            </div>

            <div>
                <div>Aircraft : </div>
                <div><input type="text" id="aircraftNumber" name="aircraftNumber"/></div>
            </div>

            <div>
                <div>Number Of Economy Seat : </div>
                <div><input type="text" id="numberOfEconomySeat" name="numberOfEconomySeat"/></div>
            </div>
            <div>
                <div>Number Of Business Seat : </div>
                <div><input type="text" id="numberOfBusinessSeat" name="numberOfBusinessSeat"/></div>
            </div>
            <div>
                <div>Number Of First Class Seat : </div>
                <div><input type="text" id="numberOfFirstClassSeat" name="numberOfFirstClassSeat"/></div>
            </div>

            <div>Economy Fair</div>
            <div>
                <div>Adult : </div>
                <div><input type="text" id="econAdultRate" name="econAdultRate"/></div>
            </div>
            <div>
                <div>Chld : </div>
                <div><input type="text" id="econChildRate" name="econChildRate"/></div>
            </div>
            <div>
                <div>Adult : </div>
                <div><input type="text" id="econInfantRate" name="econInfantRate"/></div>
            </div>
            <div>Business Fair</div>
            <div>
                <div>Adult : </div>
                <div><input type="text" id="busAdultRate" name="busAdultRate"/></div>
            </div>
            <div>
                <div>Chld : </div>
                <div><input type="text" id="busChildRate" name="busChildRate"/></div>
            </div>
            <div>
                <div>Adult : </div>
                <div><input type="text" id="busInfantRate" name="busInfantRate"/></div>
            </div>
            <div>First Class Fair</div>
            <div>
                <div>Adult : </div>
                <div><input type="text" id="fcAdultRate" name="fcAdultRate"/></div>
            </div>
            <div>
                <div>Chld : </div>
                <div><input type="text" id="fcChildRate" name="fcChildRate"/></div>
            </div>
            <div>
                <div>Adult : </div>
                <div><input type="text" id="fcInfantRate" name="fcInfantRate"/></div>
            </div>
        </div>


    </div>
    <div id="footer">

    </div>
</div>
</body>
</html>
