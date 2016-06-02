<%-- 
    Document   : main
    Created on : 2016/01/06, 17:38:33
    Author     : g14925mm
--%>

<%@page import="model.Monster"%>
<%@page import="java.util.List"%>
<%@page import="model.Battle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
List<Battle> battleList = (List<Battle>) request.getAttribute("battleList");
Monster monster = (Monster) session.getAttribute("monster");
String win = (String)request.getAttribute("win");
String lose = (String)request.getAttribute("lose");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>戦闘中！</title>
    </head>
    <body>
        <%if(win == null && lose == null){ %>
        	 <p>うめこの前に<%=monster.getName()%>があらわれた！</p>	 
	<form action="pairpro7" method="post">
            <input type="submit" value="攻撃">
           </form>
	<% } %>
        <%if(battleList != null){ %>
            <%for(Battle battle : battleList) {%>
            <p><%= battle.getText() %></p>
            <%} %>
         <% } %>
         <%if(win != null){ %>
             <a href="pairpro7">次へ</a>
          <% } %>
         <%if(lose != null) { %>
            <a href="/pairpro7/Lose">次へ</a>
         <% } %>
	</body>
</html>
