<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>


<sf:form  name="input" method="post" action="/dear/diary/save" modelAttribute="diaryPage" cssClass="form_settings">
<sf:input type="hidden" id="recordId" path="recordId" />
<sf:input type="hidden" id="diaryId" path="diaryId" />
<sf:input type="hidden" id="pageDate" path="pageDate" />
<fmt:formatDate value="${diaryPage.pageDate}"/><br>
<sf:textarea id="content" path="content" style="width:600px" cssClass="notebookLookTextarea" /><br>
<input type="submit" value="<spring:message code='lbl.kaydet'  />">
</sf:form>