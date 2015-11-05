<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<div class="pagingContainer">
<c:forEach var="i" begin="1" end="${lastPageNumber}">
<c:url var="pageChangeUrl" value="/diary/list/${i}"></c:url>
	<c:choose>
		<c:when test="${activePage==i}">
			<a href="${pageChangeUrl}" class="page active">${i}</a>
		</c:when>
		<c:otherwise>
			<a href="${pageChangeUrl}" class="page">${i}</a>		
		</c:otherwise>
	</c:choose>
</c:forEach>
</div>
<div style="divContainer">

	<c:forEach  var="diaryPage" items="${somePages}">

			<div class="divItem">
			
			<c:choose>
				<c:when test="${diaryPage.likeCount>0}">
					<div class="divItemBegeni"><c:out value="${diaryPage.likeCount}" /> <spring:message code="lbl.begeni" /></div>			
				</c:when>
			</c:choose>

			<fmt:formatDate value="${diaryPage.pageDate}"  pattern="dd/MM/yyyy" /> <br>
			<c:out value="${fn:substring(diaryPage.content,0, 100)}" /> 
			<a href="<spring:url value="/diary/write/${diaryPage.pageDate}" />"><spring:message code="lbl.devam" />...</a>
			
			<div class="divItemLink">
				<c:choose >
					<c:when test="${diaryPage.shared!=0}">
						[<spring:message code="lbl.paylasildi"  />]
					</c:when>
					<c:otherwise>
					
						<a href="<spring:url value="/diary/share/${diaryPage.pageDate}"/>"><spring:message code="lbl.isimsizolarakpaylas" /></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</c:forEach>
	
</div>