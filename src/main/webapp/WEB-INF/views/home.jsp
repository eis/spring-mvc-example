<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@
    page session="false"
%><!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="<c:url value="/resources/style.css" />" rel="stylesheet"/>
</head>
<body>
    <h1>Hello world!</h1>

    <p>The time on the server is ${serverTime}.</p>
</body>
</html>
