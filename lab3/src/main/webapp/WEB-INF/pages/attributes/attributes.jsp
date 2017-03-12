<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: petka
  Date: 26.12.2016
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Attribute type</h1>

<table class="table">
    <thead>
        <tr>
            <th>ID (UUID)</th>
            <th>Name</th>
            <th>Object type</th>
            <th>Order</th>
            <th>Type</th>
            <th>Multiple</th>
            <th>Delete</th>
            <th>Edit</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="attr" items="${attrList}">
            <tr>
                <th><c:out value="${attr.id}"/></th>
                <th><c:out value="${attr.name}"/></th>
                <th><c:out value="${attr.objectTypeId}"/></th>
                <th><c:out value="${attr.orderId}"/></th>
                <th><c:out value="${attr.type}"/></th>
                <th><c:out value="${attr.multiple}"/></th>
                <th><a href="/objectTypes/del/${attrList.id}">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </a></th>
                <th><a href="/objectTypes/edit/${attrList.id}">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                </a></th>
            </tr>
        </c:forEach>
    </tbody>
</table>
