<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
<spring:url value="/resources/css/style.css" var="mainCss" />	
<link href="${mainCss}" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"></script>

<link rel="stylesheet" type="text/css" href="https://code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">

</head>
<body>
 	 <tiles:insertAttribute name="top"></tiles:insertAttribute> 
 
		<div id="site_content">
			<div id="content">
			
			
<c:if test="${SUCCESS_MESSAGE != null}">
  <div id="successMessage">${SUCCESS_MESSAGE}</div>
</c:if> 

<c:choose>
	<c:when test = "${empty handledError}">
	</c:when>
	<c:otherwise>
		<font color="red">${handledError}</font>
	</c:otherwise>
</c:choose>
					<tiles:insertAttribute name="content"></tiles:insertAttribute>	
		  </div><!--close content-->   	
     </div>
 	 <tiles:insertAttribute name="footer"></tiles:insertAttribute> 



</body>
</html>
