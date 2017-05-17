<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html >
<html lang="${language}">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title><fmt:message key="user.title" /></title>
</head>
<body>
	<div class="container">
		<div class="row">
			<form align="right">
				<select id="language" name="language" onchange="submit()">
					<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
					<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
					<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
				</select>
			</form>
		</div>
		<br>
		<div class="row">
			<h1>
				<fmt:message key="user.greeting.hello" />
				,
				<c:out value="${sessionScope.user.name}" />
			</h1>
		</div>
		<br>
		<div class="row">
			<form action="controller" method="post">
				<div class="row">
					<div class="col-md-1 ">
						<input type="radio" name="category" value="eBook">
						<fmt:message key="user.label.ebook" />
					</div>
					<div class="col-md-2">
						<input type="radio" name="category" value="paperBack">
						<fmt:message key="user.label.paperback" />
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-3">
						<button name="request" value="getBooks" type="submit">
							<fmt:message key="user.button.getbooks" />
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>