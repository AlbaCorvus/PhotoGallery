<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Gifs | Gallery</title>
<link rel="stylesheet" href="<c:url value="/resources/css/gifs.css"/>" />
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>

<body>
<%@include file='../../templates/Header.jsp'%>
<section class="content">
<c:if test="${not empty error}">
<div class="error">${error}</div>
</c:if>
<div class="gallery-holder">
<div class="gallery-header"><h2 style="color:${Category.colorCode};">${Category.name}</h2></div>
<div class="big-image-display"></div>
<div class="small-image-holder">
<c:forEach items="${Category.getGif()}" var="categoryImages">
<div class="image"><img src="/Spring-Photo-Gallery/gallery/gifs/${categoryImages.id}" alt="categoryImages.name"/></div>
</c:forEach>
</div>

<form action="${action}" method="POST" enctype="multipart/form-data">
<input type="hidden" name="categoryId" value="${Category.getId()}"/>
<input type="text" placeholder="Gif name" name="gifName" />
<label for="file">Upload</label>
<input type="file" name="file" />
<input type="submit" value="Add Gif" />
</form>

</div>

</section>
<%@include file='../../templates/Footer.jsp'%>
<script src="<c:url value="/resources/js/LoadBigImage.js"/>"></script>
</body>

</html>