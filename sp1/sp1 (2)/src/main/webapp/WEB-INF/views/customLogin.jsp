<%--
  Created by IntelliJ IDEA.
  User: sehee
  Date: 2021-03-21
  Time: 오후 3:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html lang="en">
<head>
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="/resources/dist/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <style>
        .img-login {text-align: center; }
    </style>
</head>
<body>
    <form role="form" method="post" action="/login">
        <fieldset>
            <br>
            <br>
            <div class="img-login">
                <img src="/resources/img/loginhaha.png" ></div><br>
            <div class="form-group">
                <input class="form-control" placeholder="userid" name="usrname" type="text" autofocus>
            </div>
            <div class="form-group">
                <input class="form-control" placeholder="Password" name="password" type="password" value="">
            </div>
            <div class="checkbox">
                <label><input name="remeber-me" type="checkbox">로그인 상태 유지</label></div>
            </div>
            <a href="index.html" class="btn btn-lg btn-success btn-block">로그인</a>
        </fieldset>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>
<script src="/resources/dist/js/sb-admin-2.js"></script>
<script>
    $(".btn-success").on("click", function (e) {
        e.preventDefault();
        $("form").submit();
    });
</script>
</body>
</html>
