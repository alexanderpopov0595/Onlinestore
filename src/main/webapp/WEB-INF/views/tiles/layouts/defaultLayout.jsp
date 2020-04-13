<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <meta name="viewport" content="width=device-with, initial-scale=1.0"/>
    <title><tiles:getAsString name="title"/></title>
    <tiles:importAttribute name="stylesheets"/>
    <tiles:importAttribute name="javascripts"/>
    <c:forEach var="js" items="${javascripts}">
        <script src="<c:url value="/resources/js/${js}" />"></script>
    </c:forEach>
    <c:forEach var="css" items="${stylesheets}">
        <link href="<c:url value="/resources/css/${css}"/>" rel="stylesheet">
    </c:forEach>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <div class="grid-main">
        <header>
            <tiles:insertAttribute name="header" />
        </header>
        <nav>
            <tiles:insertAttribute name="nav" />
        </nav>
        <main>
            <tiles:insertAttribute name="main" />
        </main>
    </div>
</body>
</html>