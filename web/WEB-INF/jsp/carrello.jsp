<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>

<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>Carrello</h2>
        </div>
        <c:if test = "${carrello.libro.size() == 0}">
            <div class="card">
                <h3>Al momento non ci sono prodotti nel carrello</h3>
            </div>
        </c:if>
        <c:if test = "${carrello.libro.size() >= 0}">
        </c:if>
        <div class="card">
            <label for="codicesconto">Nome</label>
            <input
                    type="text"
                    id="codicesconto"
                    placeholder="Inserisci codice sconto"
                    required
            />
            <input type="submit" id="invia" value="Inserisci">
        </div>
        <div class="card">
           Totale ${carrello.totale}
        </div>
        <div class="card">
            <input type="submit" id="invia" value="Vai al pagamentos">
        </div>
    </div>

<jsp:include page="footererightcollum.jsp"/>