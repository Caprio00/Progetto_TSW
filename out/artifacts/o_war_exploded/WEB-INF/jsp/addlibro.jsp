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
        <div class="card">
            <div class="contact-container">
                <form enctype="multipart/form-data"  action="caricalibro" method="post" id="addlibro">
                    <c:if test = "${libro.numberisbn != null}">
                    <input type="hidden" name="edit" value="${libro.numberisbn}">
                    </c:if>
                    <div class="row">
                        <div class="col-25">
                            <label for="titolo">Titolo*</label></div>
                            <div class="col-75">
                                <input
                                        type="text"
                                        id="titolo"
                                        name="titolo"
                                        placeholder="Titolo"
                                        value="${libro.titolo}"
                                        required
                                />

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="autore">Autore*</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="text"
                                    id="autore"
                                    name="autore"
                                    placeholder="Autore"
                                    value="${libro.autore}"
                                    required
                            />
                        </div>
                    </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="npage">Numero pagine*</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="number"
                                        id="npage"
                                        name="npage"
                                        placeholder="Numero pagine"
                                        value="${libro.numero_pagine}"
                                        min="1"
                                        required
                                />
                            </div>
                        </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="prezzo">Prezzo*</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="number"
                                    id="prezzo"
                                    name="prezzo"
                                    placeholder="Prezzo"
                                    value="${libro.getPrezzoEuroNo()}"
                                    step=0.01
                                    required
                            />
                        </div>
                    </div>
                    <c:if test = "${libro== null || libro.tipo == \"cartaceo\"}">
                    <div class="row">

                        <div class="col-25">
                            <label for="ndisp">Numero libri disponibili*</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="number"
                                    id="ndisp"
                                    name="ndisp"
                                    placeholder="Numero libri disponibili"
                                    value="${libro.numero_disponibili}"
                                    min="1"
                                    required
                            />
                        </div>
                    </div>
                    </c:if>
                    <c:if test = "${libro.numberisbn == null}">
                    <div class="row">
                        <div class="col-25">
                            <label id="lisbn" for="isbn">ISBN*</label>
                        </div>

                        <div class="col-75">
                            <input
                                    type="text"
                                    id="isbn"
                                    name="isbn"
                                    placeholder="ISBN"
                                    value="${libro.numberisbn}"
                                    required
                                    pattern="^[0-9]{13}$" title="L'isbn deve essere lungo 13 caratteri e contenere solo numeri"
                            />
                        </div>

                    </div>
                    </c:if>
                        <div class="row">
                            <div class="col-25">
                                <label id="lanno" for="anno">Anno di pubblicazione*</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="number"
                                        id="anno"
                                        name="anno"
                                        placeholder="Anno di pubblicazione"
                                        value="${libro.anno_pubblicazione}"
                                        required
                                />
                            </div>
                        </div>
                    <c:if test = "${libro.numberisbn == null}">
                    <div class="row">
                        <div class="col-25">
                            <label for="tipo">Formato*</label>
                        </div>
                        <div class="col-75">
                            <select name="tipo" id="tipo">
                                <option value="cartaceo" <c:if test = "${libro != null && libro.tipo == \"cartaceo\"}">selected</c:if>>Cartaceo</option>
                                <option value="ebook" <c:if test = "${libro != null && libro.tipo == \"ebook\"}">selected</c:if>>eBook</option>
                            </select>
                        </div>
                    </div>
                    </c:if>
                        <div class="row">
                            <div class="col-25">
                                <label for="img">Carica copertina <c:if test = "${libro.numberisbn != null}">(Se non vuoi modificare la copertina lascia il campo vuoto)</c:if></label>
                            </div>
                            <div class="col-75">
                                <input type="file"  onchange="valida()" name="img" id="img" accept="image/*" <c:if test = "${libro.numberisbn == null}">required</c:if>>
                                <p id="progressNumber"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="descrizione" >Descrizione libro*</label>
                            </div>
                            <div class="col-75">
                  <textarea
                          id="descrizione"
                          name="descrizione"
                          placeholder="Scrivi qui"
                          style="height: 200px;"
                          required
                  >${libro.descrizione}</textarea>
                            </div>
                        </div>
                    <div class="row">
                        <div class="col-25">
                            <label id="categoria">Categoria(Puoi anche inserirne più di una)*</label>
                        </div>
                        <div class="col-75">
                            <c:forEach items="${cecked}" var="cecked">
                                <input type="checkbox" id="${cecked.id}" name="${cecked.id}" value="${cecked.id}" checked>
                                <label for="${cecked.id}">${cecked.nome}</label><br>
                            </c:forEach>
                            <c:forEach items="${categorie}" var="categoria">
                            <input type="checkbox" id="${categoria.id}" name="${categoria.id}" value="${categoria.id}">
                                <label for="${categoria.id}">${categoria.nome}</label><br>
                            </c:forEach>
                        </div>
                    </div>
                        <div class="row">
                            <input id="contatti" type="submit" value="Pubblica"/>
                        </div>
                </form>
                <h5 class="check">I campi segnati con * sono obbligatori</h5>
            </div>
        </div>
    </div>
    <script> $(document).ready(function(){<c:if test = "${libro.numberisbn == null}">
        if($('#tipo').val() == "ebook"){
            $('#ndisp').prop('required',false);
            $('#ndisp').prop('disabled',true);
        }else{$('#ndisp').prop('disabled',false);$('#ndisp').prop('required',true);}

        $('#tipo').change( function() {
            if($('#tipo').val() == "ebook"){
                $('#ndisp').prop('required',false);
                $('#ndisp').prop('disabled',true);
            }else{$('#ndisp').prop('disabled',false);$('#ndisp').prop('required',true);}});
        </c:if>

        $('#isbn').change(function () {
            $.get({url: "ceckisbn?id=" + $("#isbn").val(), success: function(result){
                    if(result == "no"){
                        $("#isbn").css("border","2px solid red");
                        $("#lisbn").css("color","red");
                        alert("Un libro con questo isbn esiste giá");
                    }else{
                        $("#isbn").css("border","2px solid green");
                        $("#lisbn").css("color","black");
                    }
                }});

        });

        $('#anno').change(function () {
            if (parseInt($("#anno").val()) > new Date().getFullYear()) {
                $("#lanno").css("color", "red");
                $("#anno").css("border","2px solid red");
            }else{
                $("#anno").css("border","2px solid green");
                $("#lanno").css("color","black");
            }
        });


                $('#addlibro').submit(function() {
                    var errore = "Sono stati trovati i seguenti errori:\n\n";
                    if(parseInt($("#anno").val()) > new Date().getFullYear()){
                        $("#lanno").css("color", "red");
                        $("#anno").css("border","2px solid red;");
                        errore = errore + "L'anno inserito da te non é valido, l'anno deve essere inferiore alla date attuale\n";
                    }

                    if ($("input[type=checkbox]").is(
                        ":checked")) {
                    } else {
                        errore = errore + "Devi selezionare almeno una categoria\n";
                        $("#categoria").css("color","red");
                    }

                    $.get({url: "ceckisbn?id=" + $("#isbn").val(), success: function(result){
                        console.log(result);
                            if(result == "no"){
                                errore = errore + "Un libro con questo isbn esiste giá \n";
                                $("#lisbn").css("color","red");
                            }
                        }});

                    if(errore.length > 42){
                        alert(errore);
                        errore = "Sono stati trovati i seguenti errori:\n\n";
                        return false;
                    }else{
                        return true;
                    }
                });


    });


    function valida() {
        var fileInput =
            document.getElementById('img');

        var filePath = fileInput.value;

        var allowedExtensions =
            /(\.jpg|\.jpeg|\.png)$/i;

        if (!allowedExtensions.exec(filePath)) {
            alert('Tipo di file non valido');
            fileInput.value = '';
            return false;
        }

    }</script>
    <jsp:include page="footererightcollum.jsp"/>

