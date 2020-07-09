<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${libro.titolo}"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card bookbox_page">
            <img src="${pageContext.request.contextPath}/img/${libro.path}" alt="libro" height="215px" class="image" />
            <div class="book" id="product">
                <h4>
                    Titolo: ${libro.titolo}<hr>
                    Autore: ${libro.autore}<hr>
                    Prezzo: ${libro.prezzoEuro}<hr>
                    Formato: ${libro.tipo}
                    <c:if test = "${libro.numero_disponibili!=0}">
                        <hr>
                        Pezzi disponibili: ${libro.numero_disponibili}
                    </c:if>
                </h4>
            </div>
        </div>
        <div class="card buybox">
            <div class="book">
                <c:if test = "${utente == null || (utente != null && utente.admin == false)}">
            <h3 id="tempo"></h3>
                    <c:if test = "${libro.tipo == \"ebook\"}">
                        <c:if test = "${carrello== null}">
                            <a href="carrello?id=${libro.isbn}" class="button">Aggiungi al carrello</a>
                        </c:if>
                        <c:if test = "${carrello!= null && carrello.findLibrobyIsbn(libro.isbn) == false}">
                            <a href="carrello?id=${libro.isbn}" class="button">Aggiungi al carrello</a>
                        </c:if>
                    </c:if>
                    <c:if test = "${libro.tipo == \"cartaceo\"}">
                        <a href="carrello?id=${libro.isbn}" class="button">Aggiungi al carrello</a>
                    </c:if>
                    <c:if test = "${libro.tipo == \"ebook\" &&carrello!= null && carrello.findLibrobyIsbn(libro.isbn) == true}">
                    <a class="button" style="
                      pointer-events: none;
                      cursor: default;
                      background-color: gray;
                      ">Giá aggiunto al carrello!</a>
                    </c:if>
                </c:if>
                <c:if test = "${utente != null && utente.admin == false}">
            <a onclick="preferiti(this,'${libro.isbn}')" class="button" id="addpreferiti" <c:if test = "${preferiti != null}">style="display: none"</c:if>>Aggiungi ai preferiti</a>

                <a onclick="preferiti(this,'${libro.isbn}')" id="removepreferiti" class="button" <c:if test = "${preferiti == null}"> style="display: none"</c:if>>Rimuovi dai preferiti</a>
                </c:if>
                <c:if test = "${utente != null && utente.admin == true}">
                    <a href="editlibro?id=${libro.isbn}" class="button">Modifica libro</a>
                    <a href="deletelibro?id=${libro.isbn}" class="button">Elimina libro</a>
                </c:if>
            </div>
        </div>
        <div class="card bookbox descriptor">
            <h3>Descrizione</h3><hr>
            <p class="description">${libro.descrizione}</p>
        </div>
        <div class="card bookbox descriptor">
            <h3>ISBN:</h3>
            <p class="description">${libro.isbn}</p><hr>
            <h3>Anno di pubblicazione:</h3>
            <p class="description">${libro.anno_pubblicazione}</p><hr>
            <h3>Pagine:</h3>
            <p class="description">${libro.numero_pagine}</p><hr>
            <h3>Categoria/e:</h3>
            <p class="description">${libro.categoriestring}</p><br>
        </div>
    </div>

    <c:if test = "${utente == null || (utente != null && utente.admin == false)}">
    <script>
        setInterval(()=>{
            let now = new Date();
            let max = new Date();
            max.setHours(19);
            max.setMinutes(0);
            max.setSeconds(0);
            let countdown;
            let delivery_day = new Date();
            if(now.getHours()>=19) {
                max.setDate(max.getDate() + 1);
            }
            delivery_day.setDate(max.getDate() + 1);
            countdown = max.getTime()-now.getTime();
            let hours = Math.floor((countdown % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            let minutes = Math.floor((countdown % (1000 * 60 * 60)) / (1000 * 60));
            let seconds = Math.floor((countdown % (1000 * 60)) / 1000);
            if(hours<10){
                hours = "0" + hours;
            }
            if(minutes<10){
                minutes = "0" + minutes;
            }
            if(seconds<10){
                seconds = "0" + seconds;
            }
            let ora, minuto, secondo;
            if(hours==1){
                ora = " ora ";
            }
            else
                ora = " ore ";
            if(minutes==1){
                minuto = " minuto ";
            }
            else
                minuto = " minuti ";
            if(seconds==1){
                secondo = " secondo ";
            }
            else
                secondo = " secondi ";
            var months = ["gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre"];
            document.getElementById("tempo").innerHTML = "Ordina entro " + hours + ora + minutes + minuto + seconds + secondo + "per far si che venga spedito entro il " + delivery_day.getDate() + " " + months[delivery_day.getMonth()];
        },1000)

   function preferiti(val,isbn) {
        if(val.innerText == "Rimuovi dai preferiti"){
            var id=1;
        }else{
            var id=0;
        }

        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var flag = this.responseText;
                if(flag == "1"){
                    document.getElementById("removepreferiti").style.display="none";
                    document.getElementById("addpreferiti").style="";
                }else if(flag=="0"){
                    document.getElementById("addpreferiti").style.display="none";
                    document.getElementById("removepreferiti").style="";
                }
            }
        }
        xmlHttpReq.open("GET", "addremovepreferito?id=" + isbn + "&flag=" + id , true);
        xmlHttpReq.send();
    }</script>
    </c:if>


    <jsp:include page="footererightcollum.jsp"/>
