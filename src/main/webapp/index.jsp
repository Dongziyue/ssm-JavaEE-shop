<%--
  Created by IntelliJ IDEA.
  User: DzyBX
  Date: 2018/10/27
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>主页面</title>
    <style>
      @import "assets/bootstrap/css/bootstrap.css";
      @import "assets/styles/global.css";

      article{
        background: #dddddd;
        height: 800px;
      }
      .avatar{
        height: 20px;
      }
    </style>
  </head>
  <body>
  <nav id="nav" class="index navbar navbar-inverse"></nav>
  <header class="index jumbotron"></header>
  <main class="container">
    <div class="row">
      <section class="col-md-4">
          <article></article>
      </section>
      <section class="col-md-4">
        <article></article>
      </section>
    </div>
  </main>
  <footer class="index jumbotron"></footer>
  <script src="assets/scripts/jquery-3.3.1.min.js"></script>
  <script src="assets/bootstrap/js/bootstrap.js"></script>
  <script src="assets/scripts/global.js"></script>
  </body>
</html>
