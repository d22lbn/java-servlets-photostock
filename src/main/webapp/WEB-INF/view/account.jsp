<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="app_title" value="Личный кабинет"/>
<%@include file="/WEB-INF/view/header.jsp"%>

    <main class="main">
        <div class="lk">
            <div class="user_inf">
                <div class="inf_img">
                    <img src="resources/ava1.jpg" alt="">
                </div>
                <form class="inf_text" method="post" action="/photo/account">
                        <div class="inf_text_item">
                            <div>Фамилия:</div>
                            <input type="text" name="surname" placeholder="${surname}">
                        </div>
                        <div class="inf_text_item">
                            <div>Имя:</div>
                            <input type="text" name="name" placeholder="${name}">
                        </div>
                        <div class="inf_text_item">
                            <div>Почта:</div>
                            <input type="email" name="email" placeholder="${email}">
                        </div>
                        <div class="inf_text_item">
                            <div>Пароль:</div>
                            <input type="password" name="password" placeholder="пароль">
                        </div>
                        <div class="inf_text_item">
                            <div>Баланс:</div>
                            <div class="balance">${balance} руб.</div>
                        </div>
                        <div class="change">
                            <input type="submit" name="enter" value="Изменить">
                        </div>
                </form>
            </div>
            <div class="uploaded">
                <div class="uploaded_text">
                    <form action="/photo/account" method="post" enctype="multipart/form-data">
                        <input type="file" required name="filename"/>
                        <input type="text" required name="photoname" placeholder="название">
                        <input type="text" required name="photodescription" placeholder="описание">
                        <input type="text" required name="photoprice" placeholder="цена">
                        <input type="text" required name="photocanbuy" placeholder="количество">
                        <input type="submit" value="Добавить" name="upl">
                    </form>
                    <h3>Загруженные:</h3>
                </div>
                <div class="main_slide">
                    <c:forEach var="path" items="${uploaded}">
                        <a href="#popup" class="main_item popup_link">
                            <img src="${path}" alt="">
                        </a>
                    </c:forEach>
                </div>
            </div>
            <div class="purchased">
                <div class="purchased_text">
                    <h3>Купленные:</h3>
                </div>
                <div class="main_slide">
                    <c:forEach var="path" items="${purchased}">
                        <a href="#popup" class="main_item popup_link">
                            <img src="${path}" alt="">
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>

<%@include file="/WEB-INF/view/footer.jsp"%>