<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="app_title" value="Авторизация"/>
<%@include file="/WEB-INF/view/blocks/header.jsp"%>

<main class="main">
    <div class="reg_window">
        <div class="reg_inner">
            <form method="post" action="">
                <div class="reg_item">
                    <input type="email" required name="email" placeholder="почта">
                </div>
                <div class="reg_item">
                    <input type="password" required name="password" placeholder="пароль">
                </div>
                <div class="reg_item reg_btn">
                    <input type="submit" name="enter" value="готово">
                </div>
            </form>
        </div>
    </div>
</main>

<%@include file="/WEB-INF/view/blocks/footer.jsp"%>