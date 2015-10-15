<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
<spring:url value="/resources/css/style.css" var="mainCss" />	
<link href="${mainCss}" rel="stylesheet" />
</head>
<body>
 	 <tiles:insertAttribute name="top"></tiles:insertAttribute> 
 <div id="main">
 
		<div id="site_content">
			<div id="content">
				<div class="content_item">
<c:choose>
	<c:when test = "${empty handledError}">
	</c:when>
	<c:otherwise>
		<font color="red">${handledError}</font>
	</c:otherwise>
</c:choose>
					<tiles:insertAttribute name="content"></tiles:insertAttribute>	
				</div>
 	 		<tiles:insertAttribute name="rightside"></tiles:insertAttribute>
		  </div><!--close content-->   
		</div><!--close site_content-->  	
     </div>
  
  </div>
 	 <tiles:insertAttribute name="footer"></tiles:insertAttribute> 



</body>
</html>
