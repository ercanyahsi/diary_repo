<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<div style="divContainer">
	
	<c:forEach  var="diaryPage" items="${somePages}">

			<div class="divItem">

			<fmt:formatDate value="${diaryPage.pageDate}"  pattern="dd/MM/yyyy" /> <br>
			<c:out value="${fn:substring(diaryPage.content,0, 100)}" /> 
			<a href="${contextPath}/dear/diary/write/${diaryPage.pageDate}"><spring:message code="lbl.tumu" />...</a>
			<br><br>
			<c:choose >
				<c:when test="${diaryPage.shared!=0}">
					<spring:message code="lbl.paylasildi"  />
				</c:when>
				<c:otherwise>
					<a href="${contextPath}/dear/diary/share/${diaryPage.pageDate}"><spring:message code="lbl.isimsizolarakpaylas" /></a>
				</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
	
</div>