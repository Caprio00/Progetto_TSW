<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="${title}"/>
</jsp:include>
  <div class="card ricerca_mobile">
      <%@include file="search.html"%>
  </div>
  <div class="row">
      <div class="leftcolumn">
        <c:forEach items="${prodotti}" var="libro">
      <div class="card bookbox">
          <img onclick="location.href='libro?id=${libro.isbn}'" src="${pageContext.request.contextPath}/img/${libro.path}" alt="libro" height="215px" class="image" />
          <div class="book">
            <h3>
              ${libro.titolo}
            </h3>
            <h4>
                ${libro.prezzoEuro}
            </h4>
            <p class="description">${libro.sdescrizione}</p>
            <a href="libro?id=${libro.isbn}">Vai alla scheda tecnica</a>
          </div>
      </div>
        </c:forEach>
        <%@include file="selectorpage.html"%>
      <div class="card" id="indexbox">
        <a href="?page=${page-1}&n=${n}" id="back"
        <c:set var = "limit" scope = "session" value = "limit"/>
        <c:if test = "${page <=1}">
          style="
          pointer-events: none;
          cursor: default;
          background-color: gray;
          "
        </c:if>
        >«Indietro</a>
        <h3 id="pageindex">Pagina ${page} di ${totlibri}</h3>
        <a href="?page=${page+1}&n=${n}" id="next"
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

      <script>
          $(document).ready( function() {
              $('#select option[value=${n}]').prop('selected', true);
              $('#select').change( function() {
                  location.href = "?page=${page}&n=" + $(this).val();

              });
          });
      </script>
      <jsp:include page="footererightcollum.jsp"/>
