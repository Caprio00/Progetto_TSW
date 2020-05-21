
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${categoria.nome}"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>${categoria.nome}${avviso}</h2>
        </div>
        <div class="card">
            <h4>${categoria.descrizione}</h4>
        </div>
        <c:if test="${libri.size() == 0}">
        <div class="card">
            <h3>Al momento non ci sono libri in questa sezione</h3>
        </div>
        </c:if>
        <c:if test="${libri.size() > 0}">
        <c:forEach items="${libri}" var="libro">
            <div class="card bookbox">
                <img src="${pageContext.request.contextPath}/img/${libro.path}" alt="libro" height="215px" class="image" />
                <div class="book">
                    <h3>
                            ${libro.titolo}
                    </h3>
                    <h4>
                            ${libro.prezzoEuro}€
                    </h4>
                    <p class="description">${libro.descrizione}</p>
                    <a href="libro?id=${libro.isbn}">Vai alla scheda tecnica</a>
                </div>
            </div>
        </c:forEach>
        </c:if>
        <c:if test = "${libri.size() != 0}">
        <div class="card" id="indexbox">
            <a href="categoria?page=${limit-1}&id=${categoria.id}" id="back"
                    <c:set var = "limit" scope = "session" value = "limit"/>
                    <c:if test = "${limit <=1}">
                        style="
                        pointer-events: none;
                        cursor: default;
                        background-color: gray;
                        "
                    </c:if>
            >«Indietro</a>
            <h3 id="pageindex">Pagina ${limit}</h3>
            <a href="categoria?page=${limit+1}&id=${categoria.id}" id="next"
                    <c:if test = "${next != null}">
                        style="
                        pointer-events: none;
                        cursor: default;
                        background-color: gray;
                        "
                    </c:if>
            >Avanti»</a>
        </div>
        </c:if>
    </div>
    <jsp:include page="footererightcollum.jsp"/>