<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${titolo}"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html" %>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card profile">
            <h2>${titolo}</h2>
        </div>
        <div class="card" id="viewerrore" <c:if test="${errore == null}">style="display: none"</c:if>>
        <div id="errorserver"><p id="erroreoutput">${errore}</p></div>
        </div>
        <div class="card">
            <div class="contact-container">
                <form method="post" action="editcategoria">
                    <c:if test = "${categoria != null}"><input type="hidden" name="id" value="${categoria.id}"/></c:if>
                    <div class="row">
                        <div class="col-25">
                            <label for="titolo">Nome categoria*</label></div>
                        <div class="col-75">
                            <input
                                    type="text"
                                    id="titolo"
                                    name="titolo"
                                    onchange="ceckCategoria()"
                                    placeholder="Categoria"
                                    value="${categoria.nome}"
                                    required
                            />

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="descrizione" >Descrizione*</label>
                        </div>
                        <div class="col-75">
                  <textarea
                          id="descrizione"
                          name="descrizione"
                          placeholder="Scrivi qui"
                          style="height: 200px;"
                          required
                  >${categoria.descrizione}</textarea>
                        </div>
                    </div>
                    <div class="row">
                        <input  type="submit" id="submitform" value="Pubblica"/>
                    </div>
                </form>
                <h5 class="check">I campi segnati con * sono obbligatori</h5>
            </div>
        </div>
    </div>
    <script>
        function ceckCategoria() {
            var titolo = document.getElementById("titolo").value;
            <c:if test = "${categoria != null}">if(titolo != "${categoria.nome}") {</c:if>
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        if (this.responseText == "true") {
                            document.getElementById("erroreoutput").innerText = "Una categoria con questo nome esiste gia!";
                            document.getElementById("viewerrore").style = "";
                            document.getElementById("submitform").style.pointerEvents = "none";
                            document.getElementById("submitform").style.cursor = "default"
                            document.getElementById("submitform").style.background = "gray";
                        } else {
                            document.getElementById("viewerrore").style.display = "none";
                            document.getElementById("erroreoutput").innerText = "";
                            document.getElementById("submitform").style = "";
                        }
                    }
                };
                xhttp.open("GET", "cecknomecategoria?titolo=" + titolo, true);
                xhttp.send();
                <c:if test = "${categoria != null}">}</c:if>
        }
    </script>
    <jsp:include page="footererightcollum.jsp"/>

