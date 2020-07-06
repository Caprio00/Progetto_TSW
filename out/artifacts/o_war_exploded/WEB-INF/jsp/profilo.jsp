<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Il mio profilo"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card profile">
            <h2>Il mio profilo</h2>
            <ul id="profile">
                <li>Nome:${utente.nome}</li>
                <li>Cognome:${utente.cognome}</li>
                <li>Sesso:${utente.sesso}</li>
                <li>Username:${utente.username}</li>
            </ul>
        <form class="contact-container" action="changepassword"><input type="submit" value="Cambia password"></form>
        </div>
    <div class="card">
        <h2>Il mio indirizzo</h2>
        Work in progess...
    </div>
    </div>
<jsp:include page="footererightcollum.jsp"/>

