<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Welcome | Gallery</title>
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"/>
<link href="<c:url value="/resources/css/LoginForm.css"/>" rel="stylesheet"/>


</head>
<body>
<%@include file='../../templates/Header.jsp'%>
<section class="content">
<c:if test="${not empty error}">
<div class="error">${error}</div>
</c:if>
<div class="galleryTitle">
			<div>My<span>Gallery</span></div>
		</div>

<div class="login">
<form action="login" method="POST">
<input type="text" placeholder="username" name="name"><br>
<input type="password" placeholder="password" name="password"><br>
<input type="submit" value="Login">
</form>

</div>
</section>
<%@include file='../../templates/Footer.jsp'%>

</body>
</html>