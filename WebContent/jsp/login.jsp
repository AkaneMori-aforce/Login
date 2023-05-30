<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.html" %>

<h2>ログイン</h2>

<form action="/Login/jp.co.aforce.login/login" method="post">
ID<input type="text" name="id"><br>
パスワード<input type="password" name="password"><br>
<input type="submit" value="ログイン">
</form>

<%--idかpasswordが間違っているとき、Login.javaから返ってくる--%>
<%String message = (String)request.getAttribute("failure");%>
<% if (message != null) {%>
    <font color="red"><%= message %></font>
<%} %>

<%@ include file="../footer.html" %>
