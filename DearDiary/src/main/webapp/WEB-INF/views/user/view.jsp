<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<sf:form method="POST" modelAttribute="user" action="user">
<table cellspacing="3" cellpadding="2">
	<tr>
	<th><label for="username"><spring:message code="lbl.username"  />:</label></th>
	<td>${user.username}</td>
	</tr>
	<tr>
	<th><label for="email"><spring:message code="lbl.email"  />:</label></th>
	<td>${user.email}
	</td>
	</tr>
</table>
</sf:form>