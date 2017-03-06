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
<p>message: <c:out value="${message}"/></p>
<form action="/objectTypes" method="post">
    <input class="form-control"  type="text" name="name" placeholder="Name type"/>
    <select class="form-control" size="1" name="parentId">
        <option value="null">No parent</option>
    <c:forEach var="type" items="${type_list}">
        <option value="${type.id}"><c:out value="${type.name}"/></option>
    </c:forEach>
    </select>
    <input class="form-control" type="submit" value="Add"/>
</form>
<p><c:out value="${name}"/></p>
<table class="table">
    <caption>Type list</caption>
    <thead>
        <tr>
            <th>ID(UUID)</th>
            <th>Name</th>
            <th>Parent ID</th>
            <th>Delete</th>
            <th>Edit</th>
        </tr>
    </thead>

    <tbody>
            <c:forEach var="type" items="${type_list}">
                <tr>
                    <th><c:out value="${type.id}"/></th>
                    <th><c:out value="${type.name}"/></th>
                    <th><c:out value="${type.parentId}"/></th>
                    <th><a href="/objectTypes/del/${type.id}">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a></th>
                    <th><a href="/objectTypes/edit/${type.id}">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </a></th>
                </tr>
            </c:forEach>
    </tbody>

</table>
<jsp:include page="footer.jsp" />
