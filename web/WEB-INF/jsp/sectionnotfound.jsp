<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Pagina non trovata"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>La sezione da te richiesta non è esistente</h2>
        </div>
    </div>
    <jsp:include page="footererightcollum.jsp"/>
</div>
</div>
<%@include file="footer.html"%>
</body>

</html>

</body>
</html>
