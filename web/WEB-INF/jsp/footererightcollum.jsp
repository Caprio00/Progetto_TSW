<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@taglib prefix="c"
                                         uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="rightcolumn">
    <div class="card right ricerca">
        <%@include file="search.html"%>
    </div>
    <div class="card right" id="classifica">
        <h3>Classifica libri più venduti</h3>
        <ol>
            <c:forEach items="${classifica}" var="libro">
            <li>
                <a href="libro?id=${libro.isbn}"><b>${libro.titolo}</b>
                    <div>
                        <img src="img/${libro.path}" height="150px" class="image"/>
                    </div>
                </a>
            </li>
            </c:forEach>
        </ol>
    </div>
    <c:if test="${faq ==  null}">
    <div class="card right" id="FAQcard">
        <h3>FAQ</h3>
        <p>Consulta le nostre domande frequenti o contattaci per avere maggiori informazioni.</p>
        <a href="FAQ"> FAQ</a>
    </div>
    </c:if>
</div>
</div>
</div>
</div>
<%@include file="footer.html"%>
</body>
</html>
