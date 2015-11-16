<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<div class="pagingContainer">
	<c:forEach var="i" begin="1" end="${lastPageNumberSharedList}">
		<c:url var="pageChangeUrl" value="/diary/list/${i}"></c:url>
		<c:choose>
			<c:when test="${activePageSharedList==i}">
				<a href="${pageChangeUrl}" class="page active">${i}</a>
			</c:when>
			<c:otherwise>
				<a href="${pageChangeUrl}" class="page">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</div>

<div class="divContainer">
	<c:forEach var="diaryPage" items="${sharedList}">

		<div class="divItem"
			onclick="window.location='<c:url value="/shared/view/${diaryPage.recordId}" />'">

			<c:choose>
				<c:when test="${diaryPage.likeCount>0}">
					<div class="divItemBegeni">
						<c:out value="${diaryPage.likeCount}" />
						<spring:message code="lbl.begeni" />
					</div>
				</c:when>
			</c:choose>

			<fmt:formatDate value="${diaryPage.pageDate}" pattern="dd/MM/yyyy" />
			<br> <br>
			<c:out value="${fn:substring(diaryPage.content,0, 100)}" />

			<div class="divItemLink">
				<a href="<c:url value="/shared/view/${diaryPage.recordId}" />"><spring:message
						code="lbl.goruntule" /></a>
			</div>


		</div>
	</c:forEach>
	<c:choose>
		<c:when test="${sharedList.size()==0}">
			<h3>
				<spring:message code="lbl.suandagoruntulemediginizpaylasimyok" />
			</h3>
		</c:when>
	</c:choose>

</div>

<div class="divContainer">
	<h3>
		<spring:message code="lbl.dahaoncegoruntuledikleriniz" />
	</h3>
	<c:forEach var="diaryPage" items="${userViewedList}">

		<div class="divItem"
			onclick="javascript:window.location='<c:url value="/shared/view/${diaryPage.recordId}" />'">

			<c:choose>
				<c:when test="${diaryPage.likeCount>0}">
					<div class="divItemBegeni">
						<c:out value="${diaryPage.likeCount}" />
						<spring:message code="lbl.begeni" />
					</div>
				</c:when>
			</c:choose>

			<fmt:formatDate value="${diaryPage.pageDate}" pattern="dd/MM/yyyy" />
			<br> <br>
			<c:out value="${fn:substring(diaryPage.content,0, 100)}" />

			<div class="divItemLink">
				<a href="<c:url value="/shared/view/${diaryPage.recordId}" />"><spring:message
						code="lbl.goruntule" /></a>
			</div>

		</div>
	</c:forEach>
</div>