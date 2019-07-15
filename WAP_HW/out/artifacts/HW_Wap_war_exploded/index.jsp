<%--
  Created by IntelliJ IDEA.
  User: nev0
  Date: 7/12/2019
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"  %>

<%
  Cookie cookie = null;
  Cookie[] cookies = null;

  // Get an array of Cookies associated with the this domain
  cookies = request.getCookies();

  if( cookies != null) {
    for (int i = 0; i < cookies.length; i++) {
      if(cookies[i].getName().equals("userName")){
        request.setAttribute("user", cookies[i].getValue());
      }
    }
  }
%>

<html>
  <head>
    <title>Servlet Assignments</title>
  </head>
  <body>
    <h2>Servlet Assignment - 2</h2>
    <form action="login" method="post">
      <p>Enter your username: </p>
      <input type='text' name='username' value="<c:if test = "${user != null}"><c:out value = "${user}" /></c:if>"/>
      <p>Enter your password: </p>
      <input type='password' name='password'/><br><br>
      <label>Remember me: </label>
      <input type='checkbox' name='remember' value='checked' <c:if test = "${user != null}"><c:out value = "checked"/></c:if>/><br><br>
      <input type='submit' value='Login'/>
    </form>

    <p><strong>${message}</strong></p>
    <p>Hello, If you need help click the link <a href='support'>cs tech support</a></p>
  </body>
</html>
