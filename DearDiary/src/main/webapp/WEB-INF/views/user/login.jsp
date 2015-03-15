<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<h1><spring:message code="lbl.girisYap"  /></h1>
<sf:form  name="input" method="post" action="login" modelAttribute="user" cssClass="form_settings">
<table width="%100" cellspacing="10">
	<tr>
	<td align="right"><label for="username"><spring:message code="lbl.username"  />:</label></td>
	<td>
		<sf:input path="username" size="15" id="username"/>
		<sf:errors path="username"></sf:errors>
	</td>
	</tr>
	<tr>
	<td align="right"><label for="password"><spring:message code="lbl.password"  />:</label></td>
	<td>
		<sf:input path="password" size="15" maxlength="15" id="password"/>
		<sf:errors path="password"></sf:errors>
	</td>
	</tr>
	<tr>
	<td>&nbsp;</td>
	<td><input type="submit" value='<spring:message code="lbl.girisYap"  />' >
	</td>
	</tr>
</table>
</sf:form>