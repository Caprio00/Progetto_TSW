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
            <div class="card">
            <div class="card bookbox_page cart">
                <img src="${pageContext.request.contextPath}/img/Topolino.jpg" alt="libro" height="215px" class="image" />
                <div class="book" id="product">
                    <h4>
                        Titolo: Dio cane<hr>
                        Autore: Gesu bambino<hr>
                        Formato: Cristo in croce
                        <c:if test = "${libro.numero_disponibili!=0}">
                            <hr>
                            Pezzi disponibili: gli anni di hitler
                        </c:if>
                    </h4>
                </div>
        </div>
            <div class="card buybox cart">
                <div class="book">
                    <h4>
                    Prezzo: quanto si fa pagare tua sorella<hr>
                    Quantità: <input type="number" value="1" min="1"><!-- aggiungere il max dipendentemente dalla disponibilità del prodotto-->
                    <button class="remove-product">Rimuovi</button><hr>
                    Subtotale prodotto: più o meno quanto tua madre sull'autostrada
                    </h4>
                </div>
            </div>
            </div>
            <div class="card book">
                <div class="total">
                    <h4>
                        Subtotale netto: quanto si fa pagare tua sorella per le orgie<hr>
                        Tasse: perfino lo stato ne è a conoscenza e apprezza<hr>
                        Costo spedizione: quando prende i pacchi grossi<hr>
                       Totale completo: porta a casa un bel po' di soldi
                    </h4>
                <a href="" id="checkout">Procedi al pagamento</a>
            </div>
            </div>
        </c:if>
    </div>






<jsp:include page="footererightcollum.jsp"/>