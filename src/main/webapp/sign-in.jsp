<%--
  Created by IntelliJ IDEA.
  User: DzyBX
  Date: 2018/11/6
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav id="nav" class="index navbar navbar-inverse"></nav>
<header class="index jumbotron"></header>
<main class="container">
    <section class="col-md-4 col-md-offset-4">
        <form action="${ctx}/user/signIn" method="post">

            <div class="form-group">
                <label for="email">邮箱</label>
                <input id="email" name="email" class="form-control" value="dzy@dzy.com">
            </div>

            <div class="from-group">
                <label for="password">密码</label>
                <input id="password" type="password" name="password" class="from-control" value="123">
            </div>
            <button type="submit" class="btn btn-primary btn-block">登录</button>
        </form>
        ${requestScope.message}
    </section>
</main>
<footer class="index jumbotron"></footer>
<script src="assets/scripts/jquery-3.3.1.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.js"></script>
<script src="assets/scripts/global.js"></script>
</body>
</html>
