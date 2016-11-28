<nav id="nav"><c:if test="${not empty User}"><div class="userdetails">${User.name}<span>|Portfolio</span><div id="user-icon">${User.getFirstLetter()}</div></div></c:if> <ul>
            <c:forEach items="${Links}" var ="link"><li><a class="${link.value}" href="${pageContext.request.contextPath}/gallery/<c:url value="${link.key}"/>">${link.key}</a></li></c:forEach>
             </ul></nav>

