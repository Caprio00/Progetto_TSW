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
                <form enctype="multipart/form-data" method="post" id="addlibro" action="addlibroform">
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
                                        required
                                />
                            </div>
                        </div>
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
                                    required
                            />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="isbn">ISBN*</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="number"
                                    id="isbn"
                                    name="isbn"
                                    placeholder="ISBN"
                                    value="${libro.numberisbn}"
                                    required
                            />
                        </div>
                    </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="anno">Anno di pubblicazione*</label>
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
                    <div class="row">
                        <div class="col-25">
                            <label for="tipo">Formato*</label>
                        </div>
                        <div class="col-75">
                            <select name="tipo" id="tipo">
                                <option value="Cartaceo" <c:if test = "${libro != null && libro.tipo == \"Cartaceo\"}">selected</c:if>>Cartaceo</option>
                                <option value="eBook" <c:if test = "${libro != null && libro.tipo == \"eBook\"}">selected</c:if>>eBook</option>
                            </select>
                        </div>
                    </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="img">Carica copertina*</label>
                            </div>
                            <div class="col-75">
                                <input type="file"  onchange="return valida()"name="img" id="img" accept="image/*" required/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="subject" >Descrizione libro*</label>
                            </div>
                            <div class="col-75">
                  <textarea
                          id="subject"
                          name="subject"
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
                            <c:forEach items="${categorie}" var="categoria">
                            <input type="checkbox" id="${categoria.id}" name="${categoria.id}" value="${categoria.nome}">
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
    <script>$(document).ready(function(){
        if($('#tipo').val() == "eBook"){
            $('#ndisp').prop('required',false);
            $('#ndisp').prop('disabled',true);
        }else{$('#ndisp').prop('disabled',false);$('#ndisp').prop('required',true);}

        $('#tipo').change( function() {
            if($('#tipo').val() == "eBook"){
                $('#ndisp').prop('required',false);
                $('#ndisp').prop('disabled',true);
            }else{$('#ndisp').prop('disabled',false);$('#ndisp').prop('required',true);}});



                $('#addlibro').submit(function() {
                    if ($("input[type=checkbox]").is(
                        ":checked")) {
                        return true;
                    } else {
                        alert("Devi selezionare almeno una categoria");
                        $("#categoria").css("color","red");
                        return false;
                    }
                });


        function valida() {
            var fileInput =
                document.getElementById('img');

            var filePath = fileInput.value;

            var allowedExtensions =
                /(\.jpg|\.jpeg|\.png|\.gif)$/i;

            if (!allowedExtensions.exec(filePath)) {
                alert('Tipo di file non valido');
                fileInput.value = '';
                return false;
            }

        }




    });</script>
    <jsp:include page="footererightcollum.jsp"/>

