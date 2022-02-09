<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="style.css">
    <title>${app_title}</title>
</head>
<body>
<div class="wrapper">
    <header id="header" class="header lock_padding">
        <div class="header_inner">
            <div class="logo">
                <div class="logo_img">
                    <img src="" alt="">
                </div>
                <span class="logo_text">Фотосток</span>
            </div>

            <nav class="nav">
                <a href="<c:url value="/main"/>">Главная</a>
                <a href="<c:url value="/recommendation"/>">Рекомендуемое</a>
                <a href="<c:url value="/search"/>">Найти</a>
            </nav>

            <div class="user">
                <a href="<c:url value="/account"/>" class="name">Иван К.</a>
                <a href="#" class="btn">Выйти</a>
            </div>
        </div>
    </header>