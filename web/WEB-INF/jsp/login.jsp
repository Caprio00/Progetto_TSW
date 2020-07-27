<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Accedi o registrati"/>
</jsp:include>

        <div class="row verticalrow">
        <div class="card column" id="logindiv">
            <h2>Login</h2>
            <c:if test = "${errorserverlogin != null}">
                <div id="errorserver"><p>${errorserverlogin}</p></div>
            </c:if>
            <div class="contact-container">
                <form method="post" action="login">
                    <div class="row">
                        <div class="col-25">
                            <label for="username">Nome utente</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="text"
                                    id="username"
                                    name="username"
                                    placeholder="Nome utente"
                                    autocomplete="on"
                                    required
                            />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="password">Password</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="password"
                                    id="password"
                                    name="password"
                                    placeholder="Password"
                                    required
                            />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-75">
                            <input type="checkbox" id="remember" name="remember" value="yes">
                            <label for="remember">Ricordami per 30 giorni</label><br>
                        </div>
                    </div>
                        <input id="login" type="submit" value="Accedi" />
                    </div>
                </form>
            </div>
            <div class="card column" id="submitdiv">
                <h2>Registrati</h2>
                <c:if test = "${fomrerror != null}">
                <div id="errorserver"><p>${fomrerror}</p></div>
                </c:if>
                <div class="contact-container">
                    <form  onsubmit="return formceck()" action="login" method="post">
                        <div class="row">
                            <div class="col-25">
                                <label for="nome">Nome</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="text"
                                        id="nome"
                                        name="nome"
                                        placeholder="Nome"
                                        autocomplete="on"
                                        pattern="[A-Za-z]+" title="Questo campo puó contenere solo caratteri"
                                        required
                                />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="cognome">Cognome</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="text"
                                        id="cognome"
                                        name="cognome"
                                        placeholder="Cognome"
                                        autocomplete="on"
                                        pattern="[A-Za-z]+" title="Questo campo puó contenere solo caratteri"
                                        required
                                />
                            </div>
                        </div>
                            <div class="row">
                            <div class="col-25">
                                <label for="usernamesubmit">Nome utente</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="text"
                                        id="usernamesubmit"
                                        name="usernamesubmit"
                                        placeholder="Nome utente"
                                        autocomplete="on"
                                        onchange="verificausername()"
                                        required
                                />
                                <font color="red" size="2" style="display: none" id="errorusername">L'username da lei inserito é giá usato da un altro account</font>
                            </div>
                            </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="email">Email</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="email"
                                        id="email"
                                        name="email"
                                        placeholder="Email"
                                        autocomplete="on"
                                        onchange="verificaemail()"
                                        required
                                />
                                <font color="red" size="2" style="display: none" id="erroremail">L'email da lei inserita é giá usata da un altro account</font>
                            </div>
                        </div>
                            <div class="row">
                            <div class="col-25">
                                <label for="sesso">Sesso</label>
                            </div>
                            <div class="col-75">
                                <select name="sesso" id="sesso">
                                    <option value="Maschio">Maschio</option>
                                    <option value="Femmina">Femmina</option>
                                </select>
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
                                <font color="red" size="2" style="display: none" id="errorpassword">Le due password sono diverse</font>
                            </div>
                            </div>
                        <input id="submit" type="submit" value="Registrati" />
                </div>
                </div>
            <script>
                var ceckpasswordflag = false;
                var ceckusernameflag = false;
                var ceckemailflag =  false;
                function verificausername() {
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (this.readyState == 4 && this.status == 200) {
                            if (this.responseText == "true") {
                                document.getElementById("errorusername").style.display = "";
                                document.getElementById("usernamesubmit").style.border = "2px solid red";
                                ceckusernameflag=true;
                            } else {
                                document.getElementById("errorusername").style.display = "none";
                                document.getElementById("usernamesubmit").style.border = "";
                                ceckusernameflag=false;
                            }
                        }
                    };
                    xhttp.open("GET", "ceckusername?id=" + document.getElementById("usernamesubmit").value, true);
                    xhttp.send();

                }

                function verificaemail() {
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (this.readyState == 4 && this.status == 200) {
                            if (this.responseText == "true") {
                                document.getElementById("erroremail").style.display = "";
                                document.getElementById("email").style.border = "2px solid red";
                                ceckemailflag=true;
                            } else {
                                document.getElementById("email").style.border = "";
                                document.getElementById("erroremail").style.display = "none";
                                ceckemailflag=false;
                            }
                        }
                    };
                    xhttp.open("GET", "ceckemail?id=" + document.getElementById("email").value, true);
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
                    var error = "Sono stati trovati i seguenti errori:\n\n";
                    verificaemail()
                    confrontapassword();
                    verificausername();
                    if(ceckusernameflag){
                        error = error + "L'username da lei inserito é giá usato da un altro account\n";
                    }
                    if(ceckpasswordflag){
                        error = error + "La password e la conferma da lei inseriti sono diversi\n";
                    }
                    if(ceckemailflag){
                        error = error + "L'email da lei inserita é giá usata da un altro account\n";
                    }
                    console.log(error.length);
                    if(error.length > 44){
                        alert(error);
                        return false;
                    }else{
                        return true;
                    }



                }
            </script>
        </div>





<%@include file="footer.html"%>
</body>
</html>
