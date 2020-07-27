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
            <div id="image_profile">
                <c:if test="${utente.sesso == 'Femmina'}">
                <img src="img/imageedit_1_6924563966.png" height="150px"/>
                </c:if>
                <c:if test="${utente.sesso == 'Maschio'}">
                    <img src="img/imageedit_1_9058503223.png"height="150px"/>
                </c:if>
            </div>
            <ul id="profile">
                <li><b>Nome: </b>${utente.nome}</li>
                <li><b>Cognome: </b>${utente.cognome}</li>
                <li><b>Sesso: </b>${utente.sesso}</li>
                <li><b>Username: </b>${utente.username}</li>
            </ul>
        <form class="contact-container" action="changepassword"><input type="submit" value="Cambia password"></form>
        </div>
    <div class="card">
        <h2>Il mio indirizzo</h2>
        Work in progess...
    </div>
    </div>
<jsp:include page="footererightcollum.jsp"/>

