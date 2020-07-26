
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Dettaglio ordine"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>Ordine n.${ordine.oraordine}</h2>
        </div>
        <div class="card">
            Prodotti ordinati in data ${ordine.data} alle ${ordine.ora}<br>
            Pagato con carta di credito <br>
            Totale prodotti ordinati:${ordine.quantita}<br>
            Costo prodotto + spedizione e iva:${ordine.getTotaleEuro()}<br>
        </div>
            <c:forEach items="${ordine.getLibri()}" var="libro">
                <div class="card" id="${libro.isbn}">
                    <div class="product_page">
                        <div class="card info_page cart">
                            <img onclick="location.href='libro?id=${libro.isbn}'" src="./img/${libro.path}" alt="libro" height="215px" class="image" />
                            <div class="product_info">
                                <p class="title">${libro.getTitolo()}</p>
                                <p class="description" id="descrizione_normale">${libro.getSdescrizione()}</p>
                                <p class="description" id="descrizione_corta">${libro.getSSDescrizione()}</p>
                            </div>
                        </div>
                        <div class="card cartbox cart">
                            <p id="prezzoProdotto${libro.getIsbn()}" class="price_view">${ordine.convertiEuro(libro.prezzo*libro.getQuantitaCarrello())} €</p>
                            Quantità:${libro.getQuantitaCarrello()}
                        </div>
                    </div>
                </div>
            </c:forEach>
    </div>
    <jsp:include page="footererightcollum.jsp"/>
