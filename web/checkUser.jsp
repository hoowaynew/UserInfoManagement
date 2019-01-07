<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>checkUser</title>
</head>
<body>
<c:if test="${not empty administrator}">
        <span style="margin: 20px; font-size: 24px; font-family: 'Microsoft JhengHei UI Light',serif; color: deeppink;">${administrator.username},欢迎您! &nbsp;
        <%--<c:forEach items="cookies" var="cookie">--%>
            <%--<c:if test="${cookie.name == 'lastVisit'}">--%>
                <%--${cookie.value}--%>
            <%--</c:if>--%>
        <%--</c:forEach>--%>
        <a href="${pageContext.request.contextPath}/logoutServlet">注销</a>
            </span>
</c:if>

<%--<%--%>

    <%--List<String> list = new ArrayList<String>();--%>

    <%--list.add("孙悟空");--%>

    <%--list.add("吉莲");--%>

    <%--session.setAttribute("list",list);--%>

<%--%>--%>

<%--&lt;%&ndash;${list[0]}&ndash;%&gt;--%>
<%--&lt;%&ndash;${list[1]}&ndash;%&gt;--%>

<%--<c:forEach items="${list}" var="s">--%>
    <%--${s}--%>
<%--</c:forEach>--%>

</body>
</html>
