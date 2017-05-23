<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html >
<html lang="${language}">
<head>
<title><fmt:message key="user.title" /></title>
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
				<input type="hidden" name="name" value="${param.name}">
			</form>
		</div>
		<br>


		<div class="row">

			<form id="userForm${user.name }" action="controller" method="post">
				<div class="row">
					<div class="col-md-2 col-md-offset-4">
						<fmt:message key="user.label.name" />
						:
					</div>
					<div class="col-md-2">
						<c:out value="${user.name}" />
					</div>
				</div>
				<br> <input type="hidden" name="name" value="${user.name}" />

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<button name="request" value="goToEditUserPage" type="submit">
							<fmt:message key="user.button.edit" />
						</button>
					</div>
				</div>
			</form>

		</div>

	</div>
</body>
</html>