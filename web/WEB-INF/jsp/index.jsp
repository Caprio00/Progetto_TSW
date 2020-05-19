<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
  <div class="card ricerca_mobile">
    <h3>Cerca un libro</h3>
    <div class="search-container">
      <form>
        <input type="search" placeholder="Scrivi qui" required>
        <input type="submit" value="Cerca">
      </form>
    </div>
  </div>
  <div class="row">
    <div class="leftcolumn">
        <c:forEach items="${prodotti}" var="libro">
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


      <div class="card" id="indexbox">
        <a href="changepage?page=${limit-1}" id="back"
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
        <a href="changepage?page=${limit+1}" id="next"
                <c:if test = "${next != null}">
                  style="
                  pointer-events: none;
                  cursor: default;
                  background-color: gray;
                  "
                </c:if>
        >Avanti»</a>
      </div>

    </div>
    <jsp:include page="rightcollum.jsp">
      <jsp:param name="pageTitle" value="Home"/>
    </jsp:include>
  </div>
<%@include file="footer.html"%>
</body>

</html>