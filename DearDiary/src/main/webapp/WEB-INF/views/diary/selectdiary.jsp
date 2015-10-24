<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<script type="text/javascript" src="/dear/resources/js/diary/selectdiary.js"></script>
<div style="divContainer">
	<script>
	$(document).ready(function(){
		$.datepicker.setDefaults({ dateFormat: 'dd/mm/yy' });
		$("#date").datepicker();
		
	});
	</script>

<input type="text" id="date" size="10" /><input type="button" value="<spring:message code='lbl.gunlugeyaz'  />"  onclick="selectDiary()"/>
	
</div>