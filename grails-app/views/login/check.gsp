<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/12/2018
  Time: 3:06 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="mainLayout">
    <title></title>
</head>

<body>
<g:if test="${object}">
<g:each in="${object}" var="list">
<h1>${list["title"]}</h1>
    <h1>${list["id"]}</h1>

</g:each>
</g:if>
</body>
</html>