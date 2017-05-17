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
<title><fmt:message key="index.title" /></title>
<!-- Latest compiled and minified CSS -->
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
			<form action="index.jsp" align="right">
				<select id="language" name="language" onchange="submit()">
					<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
					<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
					<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
				</select>
			</form>
		</div>
		<br>
		<div class="row">
			<form action="controller" method="post" align="center">
				<div class="row">
					<div class="col-md-1 col-md-offset-4">
						<fmt:message key="index.label.name" />
					</div>
					<div class="col-md-2">
						: <input type="text" name="name">
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-1 col-md-offset-4">
						<fmt:message key="index.label.password" />
					</div>
					<div class="col-md-2">
						: <input type="password" name="password">
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<button name="request" value="login" type="submit">
							<fmt:message key="index.button.login" />
						</button>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<c:if test="${not empty requestScope.errorMessage}">
							<fmt:message key="index.error.message" />
						</c:if>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
