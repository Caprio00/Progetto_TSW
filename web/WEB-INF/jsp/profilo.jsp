<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Il mio profilo"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card profile">
            <h2>Il mio profilo</h2>
            <div id="image_profile">
                <c:if test="${utente.sesso == 'Femmina'}">
                <img src="img/imageedit_1_6924563966.png" height="150px"/>
                </c:if>
                <c:if test="${utente.sesso == 'Maschio'}">
                    <img src="img/imageedit_1_9058503223.png"height="150px"/>
                </c:if>
            </div>
            <ul id="profile">
                <li><b>Nome: </b>${utente.nome}</li>
                <li><b>Cognome: </b>${utente.cognome}</li>
                <li><b>Sesso: </b>${utente.sesso}</li>
                <li><b>Username: </b>${utente.username}</li>
                <li><b>Email:</b>${utente.email}</li>
            </ul>
            <a href="cancellaccount" onclick="return confirm('Sei sicuro di voler cancellare il tuo account?')"<c:if test="${utente.isAdmin()}">style="display: none" </c:if>>Cancella il mio account</a>
        </div>
    <div class="card">
        <h2>Cambia password</h2>
        <div class="contact-container" style="display: grid">
            <form  onsubmit="return formceck()">
                <div id="errorformmessage" style="display: none"><p></p></div>
                <div id="okformmessage" style="display:none;"><p>Cambio password effettuato! Al prossimo accesso ti sará chiesta la nuova password</p></div>
                <div class="row">
                    <div class="col-25">
                        <label for="expassword">Password corrente</label>
                    </div>
                    <div class="col-75">
                        <input
                                type="password"
                                id="expassword"
                                name="expassword"
                                placeholder="Password corrente"
                                onchange="confermapassword()"
                                required
                        />
                        <font color="red" size="2" style="display: none" id="errorexpassword">La password corrente non é corretta!</font>
                    </div>

                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="passwordsubmit">Password</label>
                    </div>
                    <div class="col-75">
                        <input
                                type="password"
                                id="passwordsubmit"
                                name="passwordsubmit"
                                placeholder="Password"
                                pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$" title="La password é mal formata! La password deve essere lunga 8 caratteri e al massimo 32. Deve contenere una lettere maiuscola e una minuscola. Deve contenere un numero"
                                onchange="confrontapassword()"
                                required
                        />
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="passwordsubmitconfirm">Conferma password</label>
                    </div>
                    <div class="col-75">
                        <input
                                type="password"
                                id="passwordsubmitconfirm"
                                name="passwordsubmitconfirm"
                                placeholder="Conferma password"
                                onchange="confrontapassword()"
                                required
                        />
                        <font color="red" size="2" style="display: none" id="errorpassword">I campi password e conferma password sono diversi</font>
                    </div>
                </div>
                <input id="submit" type="submit" value="Cambia password" />
            </form>
        </div>
    </div>
        <script>
            var ceckpasswordflag = false;
            var expasswordflag = false;

            function confermapassword() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        if (this.responseText == "true") {
                            document.getElementById("errorexpassword").style.display = "";
                            document.getElementById("expassword").style.border = "2px solid red";
                            expasswordflag=true;
                        } else {
                            document.getElementById("expassword").style.border = "";
                            document.getElementById("errorexpassword").style.display = "none";
                            expasswordflag=false;
                        }
                    }
                };
                xhttp.open("GET", "ceckpassword?id=" + document.getElementById("expassword").value, true);
                xhttp.send();

            }

            function confrontapassword() {
                var password = document.getElementById("passwordsubmit").value;
                var confirm = document.getElementById("passwordsubmitconfirm").value;
                if(password == confirm){
                    document.getElementById("errorpassword").style.display = "none";
                    document.getElementById("passwordsubmitconfirm").style.border = "";
                    ceckpasswordflag = false;
                }else{
                    document.getElementById("errorpassword").style.display = "";
                    document.getElementById("passwordsubmitconfirm").style.border = "2px solid red";
                    ceckpasswordflag = true;
                }
            }

            function formceck() {
                confermapassword();
                confrontapassword();
                var error = "Sono stati trovati i seguenti errori:\n\n";
                if(expasswordflag){
                    error = error + "La password corrente non é corretta!\n";
                }
                if(ceckpasswordflag){
                    error = error + "La password e la conferma password sono diverse\n";
                }
                if(error.length>44){
                    document.getElementById("errorformmessage").innerText=error;
                    document.getElementById("errorformmessage").style.display="";
                    document.getElementById("okformmessage").style.display="none";
                }else {
                        var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function () {
                            if (this.readyState == 4 && this.status == 200) {
                                if (this.responseText == "true") {
                                    document.getElementById("okformmessage").style.display = "";
                                    document.getElementById("errorformmessage").style.display="none";
                                } else {
                                    document.getElementById("errorformmessage").innerText=this.responseText;
                                    document.getElementById("errorformmessage").style.display="";
                                    document.getElementById("okformmessage").style.display="none";
                                }
                            }
                        };
                        xhttp.open("GET", "changepassword?id=" + document.getElementById("expassword").value + "&id1=" + document.getElementById("passwordsubmit").value + "&id2=" + document.getElementById("passwordsubmitconfirm").value, true);
                        xhttp.send();
                }
                return false;

            }


        </script>
    </div>
<jsp:include page="footererightcollum.jsp"/>

