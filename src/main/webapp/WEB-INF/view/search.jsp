<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="app_title" value="Поиск"/>
<%@include file="/WEB-INF/view/blocks/header.jsp"%>

    <main class="main">
        <div class="search">
            <form>
                <input type="text" placeholder="Искать фото...">
                <button type="submit">🔎</button>
            </form>
        </div>

        <div class="main_slider">
            <div class="main_slide">
                <div class="main_item">
                    <img src="img/item2.jpg" alt="">
                    <div class="main_item_text">Ладонь</div>
                </div>
                <div class="main_item">
                    <img src="img/item3.jpg" alt="">
                    <div class="main_item_text">Цветы</div>
                </div>
                <div class="main_item">
                    <img src="img/item4.jpg" alt="">
                    <div class="main_item_text">Природы</div>
                </div>
                <div class="main_item">
                    <img src="img/item2.jpg" alt="">
                    <div class="main_item_text">Ладонь</div>
                </div>
                <div class="main_item">
                    <img src="img/item3.jpg" alt="">
                    <div class="main_item_text">Цветы</div>
                </div>
                <div class="main_item">
                    <img src="img/item4.jpg" alt="">
                    <div class="main_item_text">Природы</div>
                </div>
            </div>
        </div>

    </main>

<%@include file="/WEB-INF/view/blocks/footer.jsp"%>