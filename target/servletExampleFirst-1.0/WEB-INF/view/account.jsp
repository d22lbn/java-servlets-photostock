<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="app_title" value="Личный кабинет"/>
<%@include file="/WEB-INF/view/blocks/header.jsp"%>

    <main class="main">
        <div class="lk">
            <div class="user_inf">
                <div class="inf_img">
                    <img src="resources/ava.jpg" alt="">
                </div>
                <form class="inf_text" method="post" action="">
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
                    <h3>Загруженные:</h3>
                    <button type="submit">Загрузить</button>
                </div>
                <div class="main_slide">
                    <div class="main_item">
                        <img src="img/item2.jpg" alt="">
                        <div class="main_item_text">Ладонь</div>
                    </div>
                    <div class="main_item">
                        <img src="img/item3.jpg" alt="">
                        <div class="main_item_text">Цветы</div>
                    </div>
                </div>
            </div>
            <div class="purchased">
                <div class="purchased_text">
                    <h3>Купленные:</h3>
                </div>
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
                        <img src="img/item5.jpg" alt="">
                        <div class="main_item_text">Камера</div>
                    </div>
                    <div class="main_item">
                        <img src="img/item6.jpg" alt="">
                        <div class="main_item_text">Женщина</div>
                    </div>
                    <div class="main_item">
                        <img src="img/item7.jpg" alt="">
                        <div class="main_item_text">Шишка</div>
                    </div>
                </div>
            </div>
        </div>
    </main>

<%@include file="/WEB-INF/view/blocks/footer.jsp"%>