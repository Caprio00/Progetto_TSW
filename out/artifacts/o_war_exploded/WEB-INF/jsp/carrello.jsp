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
        <c:if test = "${carrello.libro.size() == 0 || carrello == null}">
            <div class="card">
                <h3>Al momento non ci sono prodotti nel carrello</h3>
            </div>
        </c:if>
        <c:if test = "${carrello.libro.size() > 0}">
            <c:forEach items="${carrello.getLibro()}" var="libro">
            <div class="card">
                <h4 id="title_mobile">${libro.getTitolo()}</h4>
            <div class="product_page">
                <div class="card info_page cart">
                    <img src="./img/${libro.path}" alt="libro" height="215px" class="image" />
                    <div class="product_info">
                        <p class="title">${libro.getTitolo()}</p>
                        <p class="descrizione">${libro.getDescrizione()}</p>
                    </div>
                </div>
                <div class="card cartbox cart">
                    <p id="prezzoProdotto${libro.getIsbn()}" class="price_view">${carrello.convertiEuro(libro.prezzo*libro.getQuantitaCarrello())} €</p>
                        <div class="quantity">
                            <input id="modificaQuantita${libro.getIsbn()}" type="text" value="${libro.getQuantitaCarrello()}">
                            <a href="RimuoviCarrelloServlet?id=${libro.isbn}"  class="remove_button">Rimuovi</a>
                        </div>
                </div>
                </div>
                </div>
            </c:forEach>
            <div class="card" style="display: flow-root">
                <div class="total">
                    <p id="tot">
                        Subtotale: ${carrello.convertiEuro(carrello.getTotaleNetto())} €<br>
                        Tasse (22%): ${carrello.convertiEuro(carrello.getIva())} €<br>
                        Totale netto: ${carrello.convertiEuro(carrello.getTotale())} €<br>
                        Costo Spedizione: ${carrello.convertiEuro(carrello.getCostoSpedizione())} €<br>
                        Totale Lordo: ${carrello.convertiEuro(carrello.getTotaleLordo())} €
                    </p>
                    <a href="" id="paybutton">Procedi al pagamento</a>
                </div>
            </div>
        </c:if>
    </div>

    <script>
$("input").change((event)=>{
    let id = (event.target.id).slice(16,event.target.id.lenght);
    let quantita = (event.target.value);
    console.log(event.target.id);
    console.log(quantita);
    let idPrezzo = "prezzoProdotto" + id;
    $.ajax({
        url : "ModificaCarrello",
        data : {
            id,
            quantita
        },
    error:()=> console.error("errore Ajax Carrello"),
    success : (responseText)=>{
            let prezzi = responseText.split(" ");
            let prezzoProdottoTot = prezzi[0];
            let prezzoCarrelloNetto = prezzi[1];
            let prezzoTasse = prezzi[2];
            let prezzoTotale = prezzi[3];
            let prezzoSpedizione = prezzi[4];
            let prezzoCarrelloLordo = prezzi[5];
            let totProdotti = prezzi[6];
            let disponibili = prezzi[7];
            let cod = "Subtotale: " + prezzoCarrelloNetto + " €" + "<br>\n" +
                      "Tasse (22%): " + prezzoTasse + " €" + "<br>\n" +
                      "Totale netto: " + prezzoTotale + " €" + "<br>\n" +
                      "Costo Spedizione: " + prezzoSpedizione + " €" + "<br>\n" +
                      "Totale Lordo: " + prezzoCarrelloLordo + " €";
            $("#tot").html(cod);
            $("#prezzoProdotto" + id).text(prezzoProdottoTot + " €");
            $("#carrellonavbar12345").text("Carrello (" + totProdotti + ")");
            //console.log(quantita, disponibili);
            if(quantita<=0){
                $("#modificaQuantita"+ id).val("1");
            }
            if(disponibili<=quantita){
                $("#modificaQuantita"+ id).val("" + disponibili);
            }
        }
    })
})
    </script>


<jsp:include page="footererightcollum.jsp"/>