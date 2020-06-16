<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Chi siamo"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>Chi siamo</h2>
        </div>
        <div class="card description">
            <h4>I libri sono la nostra attività storica: dalla narrativa italiana e straniera alla saggistica, dalla poesia ai classici, dai libri per bambini e ragazzi ai grandi volumi illustrati e ai cataloghi d’arte; pubblichiamo testi scolastici per ogni grado di istruzione e settore di insegnamento, libri di carta e e-book, edizioni di pregio e tascabili.
                Completamento naturale della nostra attività di editori di libri è la catena di librerie, un network di 575 store su tutto il territorio nazionale.
                Siamo editori di magazine, di carta e digitali: brand di successo che portano nel mondo lo stile italiano, anche grazie alle edizioni internazionali delle nostre testate più note.</h4>
        </div>
    </div>
    <jsp:include page="footererightcollum.jsp"/>
