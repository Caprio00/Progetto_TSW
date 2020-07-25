
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="I miei ordini"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>I miei ordini</h2>
        </div>
        <div class="card bookbox">
            <img src="img/libri.jpg" height="150px"/>
            <div class="ordinebox">
                <p>
                    <b>Ordine n.:</b> 12345<br>
                    <b>Totale:</b> 55€<br>
                    <b>Quantità prodotti:</b> 3<br>
                    <b>Acquistato in data:</b> 25/07/2020 alle 17:20<br>
                </p>
                <a href="ordine">Visualizza maggiori dettagli</a>
            </div>
        </div>
        <div class="card bookbox">
            <img src="img/libri.jpg" height="150px"/>
            <div class="ordinebox">
                <p>
                    <b>Ordine n.:</b> 12345<br>
                    <b>Totale:</b> 55€<br>
                    <b>Quantità prodotti:</b> 3<br>
                    <b>Acquistato in data:</b> 25/07/2020 alle 17:20<br>
                </p>
                <a href="ordine">Visualizza maggiori dettagli</a>
            </div>
        </div>
        <div class="card bookbox">
            <img src="img/libri.jpg" height="150px"/>
            <div class="ordinebox">
                <p>
                    <b>Ordine n.:</b> 12345<br>
                    <b>Totale:</b> 55€<br>
                    <b>Quantità prodotti:</b> 3<br>
                    <b>Acquistato in data:</b> 25/07/2020 alle 17:20<br>
                </p>
                <a href="ordine">Visualizza maggiori dettagli</a>
            </div>
        </div>
    </div>

    <!--
        <c:if test="${ordini == null || ordini.size() == 0}">
            <div class="card">
                <h3>Non ci sono ordini</h3>
            </div>
        </c:if>
        <c:if test="${ordini != null && ordini.size() > 0}">
            <c:forEach items="${ordini}" var="ordine">
                <div class="card bookbox">
                    Ordine ${ordine.oraordine}<br>Totale:${ordine.quantita}<br>Totale prodotto:${ordine.totale}<br>${ordine.data} alle ${ordine.ora}
                </div>
            </c:forEach>
        </c:if>
    </div>
    -->
    <jsp:include page="footererightcollum.jsp"/>
