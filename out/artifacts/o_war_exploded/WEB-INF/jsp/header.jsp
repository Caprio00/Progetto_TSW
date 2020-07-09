<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@taglib prefix="c"
                                         uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <meta http-equiv="X-UA-Compatible" content="IE-edge" />
    <meta name="format-detection" content="telephone=no">
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
    <a href="descrizione">Chi siamo</a>
    <div class="dropdown">
        <button class="dropbtn">Categorie</button>
        <div class="dropdown-content">
            <c:forEach items="${categorie}" var="categoria">
                    <a href="categoria?id=<c:out value="${categoria.id}"/>"><c:out
                            value="${categoria.nome}" /></a>
            </c:forEach>
        </div>
    </div>
    <div class="righttopnav">
        <c:if test = "${utente != null}">
        <div class="dropdown">
            <button class="dropbtn">Ciao, ${utente.nome}</button>
            <div class="dropdown-content">
                <a href="profilo"/>I miei dati</a>
                <c:if test = "${utente.admin == false}">
                    <a href=""/>I miei ordini</a>
                    <a href="preferiti"/>I miei libri preferiti</a>
                </c:if>

                <c:if test = "${utente.admin == true}">
                    <a href="editlibro"/>Aggiungi libro</a>
                    <a href="editcategoria">Aggiungi categoria</a>
                    <a href=""/>Aggiungi codice sconto</a>
                    <a href=""/>Gestisci ordini utenti</a>
                </c:if>
                <a href="esci"/>Esci</a>
            </div>
        </div>
        </c:if>
        <c:if test = "${utente == null}">
        <a href="login">Accedi o iscriviti</a>
        </c:if>
        <a href="carrello" id="carrellonavbar">Carrello(<c:if test = "${carrello == null}">0</c:if><c:if test = "${carrello != null}">${carrello.totprodotti}</c:if>)</a>
    </div>
</div>

