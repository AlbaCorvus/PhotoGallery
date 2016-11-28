<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>Categories | Gallery</title>
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"/>
<link href="<c:url value="/resources/css/Category.css"/>" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
<%@include file='../../templates/Header.jsp'%>
<section class="content">
<form id="categories-form" action="${action}" method="POST">
<c:if test="${not empty error}">
<div class="error">${error}</div>
</c:if>
  <h2>New Category</h2>
  <input name='CategoryName' placeholder='Category Name' type='text'>

<select name="color-code" id="color-choice">
<option value="" disabled="disabled">Category-Color</option>
<c:forEach items="${colors}" var="color">
<option style="color:${color.hexCode};" value="${color.hexCode}">${color.name}</option>
</c:forEach>
</select>
  <input class='animated' type='submit' value='Create Category'>




</form>
<section>
<%@include file='../../templates/Footer.jsp'%>
<script src="<c:url value="/resources/js/ColorChange.js"/>"></script>
</body>
</html>