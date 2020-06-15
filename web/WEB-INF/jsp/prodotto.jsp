<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${libro.titolo}"/>
</jsp:include>
<div class="row">
    <div class="leftcolumn">
            <div class="card bookbox_page">
                <img src="${pageContext.request.contextPath}/img/${libro.path}" alt="libro" height="215px" class="image" />
                <div class="book" id="product">
                    <h3>
                        Titolo: ${libro.titolo}<hr>
                        Autore: ${libro.autore}<hr>
                        Prezzo: ${libro.prezzoEuro}€<hr>
                        Formato: ${libro.tipo}<hr>
                        <c:if test = "${libro.numero_disponibili==null}">
                            Pezzi disponibili: illimitati
                        </c:if>
                        Pezzi disponibili: ${libro.numero_disponibili}
                    </h3>
                </div>
            </div>
        <div class="card buybox">
            <div class="book">
            <h3 id="tempo"></h3>
            <a href="" class="button">Aggiungi al carrello</a>
            <a href="" class="button">Aggiungi ai preferiti</a>
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
            <p class="description">${libro.numero_pagine}</p>
        </div>
    </div>

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
            delivery_day.setDate(now.getDate() + 1);
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
            document.getElementById("tempo").innerHTML = "Ordina entro " + hours + " ore " + minutes + " minuti " + seconds +  " secondi per far si che venga spedito entro il " + delivery_day.getDate();
        },1000)

    </script>


    <jsp:include page="footererightcollum.jsp"/>
