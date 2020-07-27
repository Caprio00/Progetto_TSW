
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Gestisci ordini"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>Gestisci ordini</h2>
        </div>
        <c:if test="${ordini.size() == 0}">
            <div class="card">
                <h3>Non ci sono ordini</h3>
            </div>
        </c:if>
        <c:if test="${ordini.size() != 0}">
        <div class="card">
            <div class="tableorder" style="overflow-x:auto;">
                <table>
                    <tr>
                    <th>Id utente</th>
                    <th>Nome utente</th>
                    <th>Numero ordine</th>
                    <th>Totale prodotti ordinati</th>
                    <th>Totale spesa</th>
                        <th>Ulteriori dettagli</th>
                    </tr>
                    <c:forEach items="${ordini}" var="ordine">
                        <tr>
                            <td>${ordine.idutente}</td>
                            <td>${ordine.nomeutente}</td>
                            <td>${ordine.oraordine}</td>
                            <td>${ordine.quantita}</td>
                            <td>${ordine.getTotaleEuro()}</td>
                            <td><a href="dettagliordine?userid=${ordine.idutente}&id=${ordine.oraordine}">Dettagli</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        </c:if>
    </div>
    <jsp:include page="footererightcollum.jsp"/>
