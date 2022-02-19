<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="app_title" value="Поиск"/>
<%@include file="/WEB-INF/view/header.jsp"%>

    <main class="main">
        <div class="search">
            <form method="post" action="/photo/search">
                <input type="text" name="search" placeholder="Искать фото...">
                <button type="submit">🔎</button>
            </form>
        </div>

        <div class="main_slider">
            <div class="main_slide">
                <c:forEach var="path" items="${paths}" varStatus="status">
                    <a id="${path}" href="#${status.getIndex()}" class="main_item popup_link">
                        <img src="${path}" alt="">
                    </a>
                </c:forEach>
            </div>
        </div>
    </main>

    <c:forEach var="path" items="${paths}" varStatus="status">
        <div id="${status.getIndex()}" class="popup">
            <div class="popup_body">
                <a href="#header" class="popup_close">X</a>
                <img src="${path}" alt="">
                <div class="popup_content">
                    <div class="popup_name">${names.get(status.getIndex())}</div>
                    <div class="popup_desc">${descriptions.get(status.getIndex())}</div>
                    <div>
                        <form method="post" action="/photo/main">
                            <input style="display: none; width: 0; height: 0;" type="text" value="${path}" name="imgPath">
                            <input type="submit" value="${prices.get(status.getIndex())} руб." name="price">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

<%@include file="/WEB-INF/view/footer.jsp"%>