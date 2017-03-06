<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: petka
  Date: 26.12.2016
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />

<form action="/objectTypes/update" method="post">
    <input class="form-control"  type="text" name="id" value="${singleType.id}" readonly/>
    <input class="form-control"  type="text" name="name" placeholder="Name type" value="${singleType.name}"/>
    <select class="form-control" size="1" name="parentId">
        <option value="null">No parent</option>
        <c:forEach var="type" items="${type_list}">
            <option <c:if test="${type.id == singleType.id ? 'selected' : ''}"></c:if> value="${type.id}"><c:out value="${type.name}"/></option>
        </c:forEach>
    </select>
    <input class="form-control" type="submit" value="Update"/>
</form>

<jsp:include page="footer.jsp" />
