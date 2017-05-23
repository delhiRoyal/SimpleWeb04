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
<title><fmt:message key="admin.title" /></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="row">
				<form align="right">
					<select id="language" name="language" onchange="submit()">
						<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
						<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
						<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
					</select>
				</form>
				<h1>
					<fmt:message key="admin.greeting.hello" />
					,
					<c:out value="${sessionScope.user.name}" />
				</h1>
			</div>
			<div class="row">
				<form action="controller" method="post">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<c:if test="${not empty addMessage}">
								<fmt:message key="admin.addMessage" />
								<c:remove var="addMessage" scope="session" />
							</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<c:if test="${not empty bookUpdateMessage}">
								<fmt:message key="admin.bookUpdateMessage" />
								<c:remove var="addMessage" scope="session" />
							</c:if>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<c:if test="${not empty userUpdateMessage}">
								<fmt:message key="admin.userUpdateMessage" />
								<c:remove var="addMessage" scope="session" />
							</c:if>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-1 ">
							<input type="radio" name="category" value="eBook" checked>
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
								<fmt:message key="admin.button.getbooks" />
							</button>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-3">
							<button name="request" value="getUsers" type="submit">
								<fmt:message key="admin.button.getusers" />
							</button>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-3">
							<button name="request" value="goToAddBookPage" type="submit">
								<fmt:message key="admin.button.addbook" />
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>