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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="administrator.title" /></title>
</head>
<body>
	<div class="container-fluid">
		<form align="right">
			<select id="language" name="language" onchange="submit()">
				<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
				<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
				<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
			</select>
		</form>
		<h1>
			<fmt:message key="administrator.greeting.hello" />
			,
			<c:out value="${sessionScope.user.name}" />
		</h1>
	</div>
</body>
</html>