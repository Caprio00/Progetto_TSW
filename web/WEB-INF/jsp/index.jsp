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
        <c:forEach items="${prodotti}" var="prodotto">
      <div class="card bookbox">
          <img src="https://source.unsplash.com/category/nature" alt="nature" height="215px" class="image" />
          <div class="book">
            <h3>
              ${prodotto.nome}
            </h3>
            <h4>
                ${prodotto.prezzoEuro}€
            </h4>
            <p class="description">${prodotto.descrizione}</p>
            <a href="prodotto?id=${prodotto.id}">Vai alla scheda tecnica</a>
          </div>
      </div>
        </c:forEach>


      <div class="card" id="indexbox">
        <a href="" id="back">«Indietro</a>
        <h3 id="pageindex">Pagina 1</h5>
          <a href="" id="next">Avanti»</a>
      </div>

    </div>
    <jsp:include page="rightcollum.jsp">
      <jsp:param name="pageTitle" value="Home"/>
    </jsp:include>
  </div>
<%@include file="footer.html"%>
</body>

</html>