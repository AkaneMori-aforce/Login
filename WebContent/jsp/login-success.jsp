<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.html" %>

<%--ログインに成功したとき、Login.javaから受け取る--%>
<%String name = (String) request.getAttribute("name");%>

<p>ようこそ、<%= name %>さん！</p>

<%@ include file="../footer.html" %>
