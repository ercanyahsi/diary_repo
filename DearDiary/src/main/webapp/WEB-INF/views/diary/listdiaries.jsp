<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<div style="divContainer">
	
	<c:forEach  var="diaryPage" items="${somePages}">

			<c:choose>
			 <c:when test="${empty diaryPage.content}">
					<div class="divItem">
			</c:when>
			<c:otherwise>
					<div class="divItemFull">
			</c:otherwise>
			</c:choose>

			<fmt:formatDate value="${diaryPage.pageDate}"/> 
			<br>
			<a href='<spring:url value="/diary?write=${diaryPage.pageDate}"/>'><spring:message code="lbl.yaz" /></a>
		</div>
	</c:forEach>
	
</div>