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
</head>

<body>
<div class="header">
    <h1>BookStore</h1>
    <p>Tutti i libri al miglior prezzo!</p>
</div>

<div class="topnav">
    <a href="${pageContext.request.contextPath}">Home</a>
    <a href="contatti.html">Contatti</a>
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
        <a href="login.html">Accedi o iscriviti</a>
        <a href="#">Carrello(200)</a>
    </div>
</div>

