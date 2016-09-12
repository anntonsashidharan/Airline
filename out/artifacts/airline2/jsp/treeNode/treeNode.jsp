<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${node!=null}">
    <c:forEach var="node" items="${node}">
            <li><a href="${node.href}">${node.displayName}</a></li>
            <c:set var="node" value="${node.subMenu}" scope="request"/>
                <ul>
                    <jsp:include page="treeNode.jsp"/>
                </ul>
    </c:forEach>
</c:if>