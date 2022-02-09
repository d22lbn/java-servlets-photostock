<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="app_title" value="Главная"/>
<%@include file="/WEB-INF/view/blocks/header.jsp"%>

    <main class="main">
        <div class="poster">
            <div class="poster_text">
                <h1>Фотосток - </h1>
                <h2>размещайте и приобретайте фотографии</h2>
            </div>
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

<div id="popup" class="popup">
    <div class="popup_body">
        <a href="#header" class="popup_close">X</a>
        <img src="img/item2.jpg" alt="">
        <div class="popup_content">
            <div class="popup_name">Ладонь</div>
            <div class="popup_desc">Тень ладони на стене от заката. И еще мловиала лилуи луаимло уилмоиулоп иул илуа ила илуаи луи луки луаимоуаилимлимлуки амтшщкпзыукртп оиклыимлку ифмикл илкцТень ладони на стене от заката. И еще мловиала лилуи луаимло уилмоиулоп иул илуа ила илуаи луи луки луаимоуаилимлимлуки амтшщкпзыукртп оиклыимлку ифмикл илкции</div>
            <a href="" class="popup_price">5000 руб.</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/view/blocks/footer.jsp"%>
