<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Allerta+Stencil"
	rel="stylesheet">
<link rel="stylesheet" href="stu_punch_search_result.css">
<title>打刻検索画面</title>
</head>
<body>
	<!-- ヘッダー -->
	<header>
		<div class="id">
			<div id="header">
				<h1 class="LB">
					<a href="stu_top.html">Love&Berry</a>
				</h1>
				<!-- リンク -->
				<div class="link">
					<a href="stu_time.html">時間割</a> <a href="stu_punch_search.html">打刻時間検索</a>
					<a href="">ログアウト</a>
				</div>
				<p></p>
			</div>
		</div>
	</header>

	<div id="main">
<% List<String[]> resultStuPunch = (List<String[]>)request.getAttribute("resultStuPunch");
%>
<p><%=resultStuPunch.get(0)[0] %><p>
		<div class="punch_table">
			<table class="punch_result_table">
				<thead>
					<tr>
						<th>打刻日時</th>
						<th>打刻場所</th>
					</tr>
				</thead>
				<tbody>
					<% try {%>
					<% for(int i = 0; i < 1; i++){ %>
					<tr>
						<td><%=resultStuPunch.get(i)[1] %></td>
						<td><%=resultStuPunch.get(i)[2] %></td>
					</tr>
					<% } %>
					<%}catch(IndexOutOfBoundsException e){
						e.printStackTrace();
						getServletConfig().getServletContext().getRequestDispatcher("/stu_punch_search.html").forward(request, response);
					}
						%>

				</tbody>
			</table>
		</div>
	</div>
</body>
</html>