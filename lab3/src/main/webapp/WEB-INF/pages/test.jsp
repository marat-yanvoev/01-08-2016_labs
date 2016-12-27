<%@ page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.*" %>
<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Hello world from JSP</title></head>
<body>
<h1>Hello my web app</h1>
<p><%= Integer.parseInt(UUID.randomUUID().toString())%></p>
<%
  InitialContext ctx;
  DataSource ds;
  Connection conn = null;
  Statement stmt;
  ResultSet rs;

  try {
    ctx = new InitialContext();
    //ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MySQLDataSource");
    ds = (DataSource) ctx.lookup("jdbc/MySQLDataSource");
    conn = ds.getConnection();
    stmt = conn.createStatement();
    rs = stmt.executeQuery("SELECT * FROM catalog ORDER BY id_catalog");
    while(rs.next()) {
%>
<h3>id_products: <%= rs.getString("id_catalog") %></h3>
<h3>Name: <%= rs.getString("name") %></h3>
<%
     }
}
catch (SQLException se) {
%>
<%= se.getMessage() %>
<%
}
catch (NamingException ne) {
%>
<%= ne.getMessage() %>
<%
  }

  try {
    conn.close();
  } catch (SQLException e) {
    e.printStackTrace();
  }
%>
</body>
</html>