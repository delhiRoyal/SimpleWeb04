<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html >
<html lang="${language}">
<head>
<style>
.hoverClass:hover {
	cursor: pointer;
	background-color: #f4e1d2;
	color: #674d3c;
}

td {
	color: #4040a1;
}
</style>
<title><fmt:message key="userList.title" /></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<form action="controller" align="right" method="post">
				<select id="language" name="language" onchange="submit()">
					<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
					<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
					<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
				</select> <input type="hidden" name="request" value="${param.request}">
			</form>
		</div>
		<br>
		<div class="row">
			<table class="table table-striped" border="2" width="50%">
				<tr>

					<th><fmt:message key="userList.table.th.name" /></th>

				</tr>

				<c:forEach items="${requestScope.users}" var="user">
					<form id="userForm${user }" action="controller" method="post">
					<tr onclick="document.getElementById('userForm${user}').submit()"
						class="hoverClass">
						<input type="hidden" name="request" value="getUser" />
						<input type="hidden" name="name" value="${user }" />
						<td><c:out value="${user}" /></td>
					</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>