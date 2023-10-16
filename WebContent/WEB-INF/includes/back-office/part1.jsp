<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
<!doctype html>
<html lang="en">
<%@ include file="head.jsp"%>
<body>
	<div class="dash">
		<%@ include file="sidebar.jsp"%>
		<div class="dash-app">
			<%@ include file="navbar.jsp"%>
			<main class="dash-content">
				<div class="container-fluid">