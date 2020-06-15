<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="Contattaci"/>
</jsp:include>
<div class="card ricerca_mobile">
  <%@include file="search.html"%>
</div>
<div class="row">
      <div class="leftcolumn">
        <div class="card">
          <h2>Contattaci</h2>
        </div>
        <div class="card">
          <div class="contact-container">
            <h3>Inviaci una mail</h3>
            <form action="/action_page.php">
              <div class="row">
                <div class="col-25">
                  <label for="firstname">Nome*</label>
                </div>
                <div class="col-75">
                  <input
                    type="text"
                    id="firstname"
                    name="firstname"
                    placeholder="Il tuo nome"
                    required
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-25">
                  <label for="lastname">Cognome*</label>
                </div>
                <div class="col-75">
                  <input
                    type="text"
                    id="lastname"
                    name="lastname"
                    placeholder="Il tuo cognome"
                    required
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-25">
                  <label for="email">Email*</label>
                </div>
                <div class="col-75">
                  <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="La tua mail"
                    required
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-25">
                  <label for="subject">Descrivi il problema*</label>
                </div>
                <div class="col-75">
                  <textarea
                    id="subject"
                    name="subject"
                    placeholder="Scrivi qui"
                    style="height: 200px;"
                  ></textarea>
                </div>
              </div>
              <div class="row">
                <input id="contatti" type="submit" value="Invia messaggio" />
              </div>
            </form>
            <h5 class="check">I campi segnati con * sono obbligatori</h5>
          </div>
        </div>
          <div class="card">
            <h3>Chiamaci a telefono</h3>
            <div class="row">
              <a href="tel:+390123456789" class="button" id="call"> Chiama +39.0123.456789! </a>
            </div>
            <h5 class="check">I centrali sono attivi dal lunedì al venerdi dalle 9:00 alle 18:00</h5>
          </div>
        </div>
        <jsp:include page="footererightcollum.jsp"/>
