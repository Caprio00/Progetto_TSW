<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Pagamento"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>

<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>Pagamento</h2>
        </div>
        <div class="card">
            <div class="payment-container">
            <div class="row" id="payment">
                <form>
                    <div class="col-50">
                       <h3>Dati del destinatario</h3>
                        <label for="name">Nome e Cognome*</label>
                        <input
                                type="text"
                                id="name"
                                name="name"
                                placeholder="Nome e cognome del destinatario"
                                required
                        />
                        <label for="email">Email*</label>
                        <input
                                type="email"
                                id="email"
                                name="email"
                                placeholder="La tua mail"
                                required
                        />
                        <label for="telefono">Telefono</label>
                        <input
                                type="telefono"
                                id="telefono"
                                name="telefono"
                                placeholder="Il tuo numero di telefono"
                                pattern="^(\((00|\+)39\)|(00|\+)39)?(38[890]|34[4-90]|36[680]|33[13-90]|32[89]|35[01]|37[019])\d{6,7}$"
                                title="Numero di telefono malformato"
                        />
                    </div>
                    <div class="row">
                        <div class="col-50 right">
                            <h3> Indirizzo di consegna</h3>
                            <div class="col-50">
                                <div class="address">
                                    <label for="regione">Regione</label>
                                    <select name="regione" id="regione" class="address_form">
                                        <option value=""> Seleziona la regione</option>
                                    </select>
                                </div>
                                <div class="address">
                                    <label for="provincia">Provincia</label>
                                    <select name="provincia" id="provincia" class="address_form">
                                        <option value=""> Seleziona la provincia</option>
                                    </select>
                                </div>
                                <div class="address">
                                    <label for="citta">Città</label>
                                    <select name="citta" id="citta" class="address_form">
                                        <option value=""> Seleziona la citta</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-50 right">
                                <div class="address">
                                    <label for="cap">CAP</label>
                                    <input
                                            type="number"
                                            id="cap"
                                            name="cap"
                                            placeholder="12345"
                                            required
                                    />
                                </div>
                                <div class="address">
                                    <label for="indirizzo">Via</label>
                                    <input
                                            type="text"
                                            id="indirizzo"
                                            name="indirizzo"
                                            placeholder="Via Roma"
                                            required
                                    />
                                </div>
                                <div class="address">
                                    <label for="civico">Numero civico</label>
                                    <input
                                            type="number"
                                            id="civico"
                                            name="civico"
                                            placeholder="0"
                                            required
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
            </div>
        </div>
        <div class="card">
            <div class="row">
                <div class="payment-container" id="card">
                    <h3>Dati per il pagamento</h3>
                <div class="col-50">
                <label for="cardnumber">Numero carta</label>
                <input
                        type="text"
                        id="cardnumber"
                        name="cardnumber"
                        placeholder="1111-2222-3333-4444"
                        required
                />
                    <label for="cvv">CVV</label>
                    <input
                            type="number"
                            id="cvv"
                            name="cvv"
                            placeholder="123"
                            required
                    />
                </div>
                    <div class="col-50 right">

                        <label for="cardname">Nome intestatario carta</label>
                        <input
                                type="text"
                                id="cardname"
                                name="cardname"
                                placeholder="Mario Rossi"
                                required
                        />
                <label for="scadenza">Data di scadenza</label>
                <input
                        type="month"
                        id="scadenza"
                        name="scadenza"
                        min="2020-08"
                        value="2021-01"
                        required
                />
                    </div>
            </div>
        </div>
        </div>
        <div class="card">
            <div class="cart" style="display: flow-root">
                <div class="payment-container">
                <h3>Completamento Ordine</h3>
                <p>
                    <b>Subtotale:</b> ${carrello.convertiEuro(carrello.getTotaleNetto())} €<br>
                    <b>Tasse (22%):</b> ${carrello.convertiEuro(carrello.getIva())} €<br>
                    <b>Totale netto:</b> ${carrello.convertiEuro(carrello.getTotale())} €<br>
                    <b>Costo Spedizione:</b> ${carrello.convertiEuro(carrello.getCostoSpedizione())} €<br>
                    <b>Totale Lordo:</b> ${carrello.convertiEuro(carrello.getTotaleLordo())} €
                </p>
                <input type="submit" value="Completa il pagamento"></input>
                </div>
            </div>
        </div>
    </div>
    </form>


    <jsp:include page="footererightcollum.jsp"/>
