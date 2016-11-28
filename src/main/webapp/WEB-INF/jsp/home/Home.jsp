<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home | MyGallery</title>
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"/>
<link href="<c:url value="/resources/css/Welcome.css"/>" rel="stylesheet"/>
</head>
<body>
<%@include file='../../templates/Header.jsp'%>
<section class="content">
<c:if test="${not empty error}">
<div class="error">${error}</div>
</c:if>
<div class="categories-holder">
<ul>
<c:forEach items="${User.category}" var="categories">
<li><a style="color:${categories.colorCode};" href="${pageContext.request.contextPath}/gallery/categories/${categories.id}">${categories.name}</a></li>
</c:forEach>
</ul>
</div>
<div class="profile-content">
<section class="profile-info"><span>N</span>ame:&nbsp;<span>${User.getFirstLetter()}</span>${User.getTrailingLetters()}</section>
<img src="${pageContext.request.contextPath}/gallery/profilePic" alt="me"/>
<section class="profile-info">
<span>C</span>ategories: &nbsp;${User.category.size()}
<hr class="divide">
</section>
</div>
</section>

<%@include file='../../templates/Footer.jsp'%>
</body>

</html>