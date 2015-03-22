<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

<header>
 <div id="header_section">	  
<div id="welcome">
     <h2><img alt="pencil" src="/DearDiary/resources/images/pencil.png" width="60px" height="60px" align="absmiddle"><spring:message code="lbl.title"  /></h2>
   </div><!--close welcome-->			  	
    <nav>
         <ul id="nav">
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
	   		<a href='<spring:url value="/diary?list"/>'><spring:message code="lbl.gunlugum"  /></a>
	   	</li>
	   	<li>
		<a href="void">${userProfile.username}&nbsp;</a>
		<a href='<spring:url value="/login?logout"/>'>Logout</a>
		</li>
	</c:otherwise>
</c:choose>
           	
           	
         </ul>
       </nav>
  </div>		
</header>