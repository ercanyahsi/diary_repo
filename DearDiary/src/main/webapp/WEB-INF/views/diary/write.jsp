<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false"%>

<script type="text/javascript" src="/dear/resources/js/diary/write.js"></script>

<sf:form  name="input" method="post" action="/dear/diary/save" modelAttribute="diaryPage" cssClass="form_settings">
<sf:input type="hidden" id="recordId" path="recordId" />
<sf:input type="hidden" id="diaryId" path="diaryId" />
<sf:input type="hidden" id="pageDate" path="pageDate" />
<input type="text" name="dateToChoose" id="dateToChoose" size="10" value="<fmt:formatDate value="${diaryPage.pageDate}" pattern="dd.MM.yyyy"/>">
<input type="button" onclick="gotoDate()" value='<spring:message code="lbl.tarihinegit" />'/>
<br>
<sf:textarea id="content" path="content" style="width:800px" cssClass="notebookLookTextarea" />
<input type="submit" value="<spring:message code='lbl.kaydet'  />">
</sf:form>