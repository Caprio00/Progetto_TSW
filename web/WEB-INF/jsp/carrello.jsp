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
                <div class="product_page">
            <div class="card bookbox_page cart">
                <img src="img/Topolino.jpg" alt="libro" height="130px" class="image" />
                <div class="product_info">
                    <p class="title">Dio cane</p>
                    <p class="descrizione">Dio caneDio caneDio caneDio caneDio caneDio caneDio caneDio caneDio caneDio caneDio caneDio caneDio caneDio caneDio caneDio cane</p>
                </div>
        </div>
            <div class="card buybox cart">

                <p class="price_view">10€</p>
                    <div class="quantity"><input type="number" value="1" min="1"><!-- aggiungere il max dipendentemente dalla disponibilità del prodotto-->
                            <button class="remove-product">Rimuovi</button></p>
                        </div>
            </div>
            </div>
            </div>
            <div class="card" style="display: flow-root">
                <div class="total">
                    <font size="2">
                        Subtotale netto: quanto si fa pagare tua sorella per le orgie<br>
                        Tasse: perfino lo stato ne è a conoscenza e apprezza<br>
                        Costo spedizione: quando prende i pacchi grossi<br>
                       Totale completo: porta a casa un bel po' di soldi
                    </font>
                <a href="" id="paybutton">Procedi al pagamento</a>
            </div>
            </div>
        </c:if>
    </div>






<jsp:include page="footererightcollum.jsp"/>