<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="app_title" value="Поиск"/>
<%@include file="/WEB-INF/view/blocks/header.jsp"%>

    <main class="main">
        <div class="search">
            <form method="post" action="/photo/search">
                <input type="text" name="search" placeholder="Искать фото...">
                <button type="submit">🔎</button>
            </form>
        </div>

        <div class="main_slider">
            <div class="main_slide">
                <c:forEach var="path" items="${paths}">
                    <a href="#popup" class="main_item popup_link">
                        <img src="${path}" alt="">
                    </a>
                </c:forEach>
            </div>
        </div>
    </main>

<%@include file="/WEB-INF/view/blocks/footer.jsp"%>