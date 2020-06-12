<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@taglib prefix="c"
                                         uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <meta http-equiv="X-UA-Compatible" content="IE-edge" />
    <title>BookStore -  ${param.pageTitle}</title>
    <link rel="stylesheet" href="style.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
<div class="header">
    <h1>BookStore</h1>
    <p>Tutti i libri al miglior prezzo!</p>
</div>

<div class="topnav">
    <a href="${pageContext.request.contextPath}">Home</a>
    <a href="contattaci">Contatti</a>
    <a href="">Chi siamo</a>
    <div class="dropdown">
        <button class="dropbtn">Categorie</button>
        <div class="dropdown-content">
            <c:forEach items="${categorie}" var="categoria">
                    <a href="categoria?id=<c:out value="${categoria.id}"/>"><c:out
                            value="${categoria.nome}" /></a></menuitem>
            </c:forEach>
        </div>
    </div>
    <div class="righttopnav">
        <a href="login">Accedi o iscriviti</a>
        <a href="#">Carrello (
            <c:if test = "${carrello == null}">
                0
            </c:if>
            <c:if test = "${carrello != null}">
                ${carrello}
            </c:if>
            )</a>
    </div>
</div>

