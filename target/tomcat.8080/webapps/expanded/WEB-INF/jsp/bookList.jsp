<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html >
<html lang="${language}">
<head>
<title>BookList</title>
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
				<input type="hidden" name="category" value="${param.category}">
			</form>
		</div>
		<br>
		<div class="row">
			<table class="table table-striped" border="2" width="100%">
				<tr>

					<th><fmt:message key="bookList.table.th.name" /></th>
					<th><fmt:message key="bookList.table.th.description" /></th>
					<th><fmt:message key="bookList.table.th.noOfpages" /></th>
					<th><fmt:message key="bookList.table.th.category" /></th>
				</tr>

				<c:forEach items="${requestScope.bookList}" var="book">
					<tr>
						<td><c:out value="${book.name}" /></td>
						<td><c:out value="${book.description}" /></td>
						<td><c:out value="${book.numberOfPages}" /></td>
						<td><c:out value="${book.category}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>