<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html >
<html lang="${language}">
<head>
<title><fmt:message key="editBook.title" /></title>
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
				<input type="hidden" name="id" value="${param.id}">
			</form>
		</div>
		<form action="controller" method="post" align="center">
			<input type="hidden" name="id" value="${param.id}">
			<div class="row">
				<div class="col-md-1 col-md-offset-4">
					<fmt:message key="addBook.label.name" />
				</div>
				<div class="col-md-2">
					: <input type="text" name="name">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1 col-md-offset-4">
					<fmt:message key="addBook.label.description" />
				</div>
				<div class="col-md-2">
					: <input type="text" name="description">
				</div>
			</div>
			<br>

			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<button name="request" value="updateBook" type="submit">
						<fmt:message key="editBook.button.update" />
					</button>
				</div>
			</div>
			<br>
		</form>
	</div>
</body>
</html>