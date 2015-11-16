<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<spring:url value="/resources/images/" var="imagesUrl" />
<header> 
     <h2><spring:message code="lbl.title"  /></h2>
 	
 		<div id="menu">
         <ul>
           <li>
           		<a href='<spring:url value="/navigate/home"/>'><spring:message code="lbl.home"  /></a>
           	</li>
           	
<c:choose>
	<c:when test = "${empty userProfile.username}">
	   	<li>
	   		<a href='<spring:url value="/user?new"/>'><spring:message code="lbl.uyeol"  /></a>
	   	</li>
	   	<li>
	   		<a href='<spring:url value="/login?new"/>'><spring:message code="lbl.girisYap"  /></a>
	   	</li>
	</c:when>
	<c:otherwise>
	   	<li>
	   		<a href='<spring:url value="/diary/select"/>'><spring:message code="lbl.gunlugeyaz"  /></a>
	   	</li>
	   	<li>
	   		<a href='<spring:url value="/diary/list/1"/>'><spring:message code="lbl.yazdiklarim"  /></a>
	   	</li>
	   	<li>
	   		<a href='<spring:url value="/shared/listshared/1"/>'><spring:message code="lbl.paylasilanlar"  /></a>
	   	</li>
	   	<li>
		<a href='<spring:url value="/user/${userProfile.username}"/>'>${userProfile.username}</a>
		<a href='<spring:url value="/login?logout"/>'>Logout</a>
		</li>
	</c:otherwise>
</c:choose>
         </ul>
         </div> <!-- menu div -->
</header>