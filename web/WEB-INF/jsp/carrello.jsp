<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>
<div class="row">
    <div class="leftcolumn">
        <div class="card" id="titolo">
            <h2>Carrello</h2>
        </div>
        <c:if test = "${carrello.libro.size() == 0 || carrello == null}">
            <div class="card">
                <h3>Al momento non ci sono prodotti nel carrello</h3>
            </div>
        </c:if>
        <c:if test = "${carrello.libro.size() > 0}">
            <c:forEach items="${carrello.getLibro()}" var="libro">
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
                            <p id="prezzoProdotto${libro.getIsbn()}" class="price_view">${carrello.convertiEuro(libro.prezzo*libro.getQuantitaCarrello())} €</p>
                            <div class="quantity">
                                <input class="quantita" id="modificaQuantita${libro.getIsbn()}" type="number" value="${libro.getQuantitaCarrello()}"
                                <c:if test="${libro.getTipo().equals('ebook')}">
                                        disabled
                                </c:if>>
                                <a onclick='rimuovi("${libro.isbn}")'  class="remove_button">Rimuovi</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="card" id="totale" style="display: flow-root">
                <div class="total">
                    <p id="tot">
                        <b>Subtotale:</b> ${carrello.convertiEuro(carrello.getTotaleNetto())} €<br>
                            <b>Tasse (22%):</b> ${carrello.convertiEuro(carrello.getIva())} €<br>
                                <b>Totale netto:</b> ${carrello.convertiEuro(carrello.getTotale())} €<br>
                                    <b>Costo Spedizione:</b> ${carrello.convertiEuro(carrello.getCostoSpedizione())} €<br>
                                        <b>Totale Lordo:</b> ${carrello.convertiEuro(carrello.getTotaleLordo())} €
                    </p>
                     <c:if test = "${utente == null}">
                         <a href="login" id="paybutton">Per poter ordinare devi effettuare il login</a>
                     </c:if>
            <c:if test = "${utente != null && utente.admin == false}">
                    <a href="effettuaordine" id="paybutton">Procedi al pagamento</a>
            </c:if>
                </div>
            </div>
        </c:if>
    </div>

    <script>
        function rimuovi(isbn){
            var xmlHttpReq = new XMLHttpRequest();
            xmlHttpReq.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    console.log(this.responseText);
                    var prezzi = this.responseText.split(" ");
                    var conferma = prezzi[0];
                    var totProdotti = prezzi[6];
                    if (conferma == "ok" && totProdotti != "0") {
                        var prezzoCarrelloNetto = prezzi[1];
                        var prezzoTasse = prezzi[2];
                        var prezzoTotale = prezzi[3];
                        var prezzoSpedizione = prezzi[4];
                        var prezzoCarrelloLordo = prezzi[5];
                        var cod = "<b>Subtotale:</b> " + prezzoCarrelloNetto + " €" + "<br>\n" +
                            "<b>Tasse (22%):</b> " + prezzoTasse + " €" + "<br>\n" +
                            "<b>Totale netto:</b> " + prezzoTotale + " €" + "<br>\n" +
                            "<b>Costo Spedizione:</b> " + prezzoSpedizione + " €" + "<br>\n" +
                            "<b>Totale Lordo:</b> " + prezzoCarrelloLordo + " €";
                        $("#tot").html(cod);
                    }else if(conferma == "ok" && totProdotti == "0"){
                        $("#totale").fadeOut("normal", function() {
                            $(this).remove();
                        });
                        document.getElementById('titolo').insertAdjacentHTML('afterend', '<div class="card" id="carrellovuoto" style="display: none">\n' +
                            '                <h3>Al momento non ci sono prodotti nel carrello</h3>\n' +
                            '            </div>');
                        $("#carrellovuoto").fadeIn("slow");
                    }
                    $("#carrellonavbar").text("Carrello (" + totProdotti + ")");
                }
            }
            xmlHttpReq.open("GET", "rimuovicarrello?id=" + isbn , true);
            xmlHttpReq.send();
            $("#" + isbn).fadeOut("normal", function() {
                $(this).remove();
            });
        }

        $(document).ready(function(){




        $("input[type=\"number\"] ").change((event)=>{
            var id = (event.target.id).slice(16,event.target.id.lenght);
            var quantita = (event.target.value);
            $.ajax({
                url : "ModificaCarrello",
                data : {
                    id,
                    quantita
                },
                error:()=> console.error("errore Ajax Carrello"),
                success : (responseText)=>{
                    var prezzi = responseText.split(" ");
                    var prezzoProdottoTot = prezzi[0];
                    var prezzoCarrelloNetto = prezzi[1];
                    var prezzoTasse = prezzi[2];
                    var prezzoTotale = prezzi[3];
                    var prezzoSpedizione = prezzi[4];
                    var prezzoCarrelloLordo = prezzi[5];
                    var totProdotti = prezzi[6];
                    var disponibili = prezzi[7];
                    var cod = "<b>Subtotale:</b> " + prezzoCarrelloNetto + " €" + "<br>\n" +
                        "<b>Tasse (22%):</b> " + prezzoTasse + " €" + "<br>\n" +
                        "<b>Totale netto:</b> " + prezzoTotale + " €" + "<br>\n" +
                        "<b>Costo Spedizione:</b> " + prezzoSpedizione + " €" + "<br>\n" +
                        "<b>Totale Lordo:</b> " + prezzoCarrelloLordo + " €";
                    $("#tot").html(cod);
                    $("#prezzoProdotto" + id).text(prezzoProdottoTot + " €");
                    $("#carrellonavbar").text("Carrello (" + totProdotti + ")");

                    if(quantita.length > 8){
                        quantita =  quantita.substring(0,7);
                    }

                    if(parseInt(disponibili)<=parseInt(quantita)){
                       $("#modificaQuantita"+ id).val("" + disponibili);
                    }
                    if(quantita<=0 || quantita==""){
                        $("#modificaQuantita"+ id).val("1");
                    }
                }
            })
        })
        });
    </script>


<jsp:include page="footererightcollum.jsp"/>