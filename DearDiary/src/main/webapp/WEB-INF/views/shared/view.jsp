<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<div style="divContainer">

	<div class="divItem" style="width:50%; height:50%;overflow: auto;">
		<fmt:formatDate value="${diaryPage.pageDate}"  pattern="dd/MM/yyyy" /> <br><br>
		<c:out value="${ diaryPage.content}"></c:out>
	</div>

</div>