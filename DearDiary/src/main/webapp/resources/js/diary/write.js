$(document).ready(function(){
	
	$("#dateToChoose").datepicker({ dateFormat: 'dd.mm.yy' });
	
});

function gotoDate() {

	var formatDate=$("#dateToChoose").val().substring(6,10)+"-"+
	$("#dateToChoose").val().substring(3,5)+"-"+
	$("#dateToChoose").val().substring(0,2);
	window.location = "/dear/diary/write/"+formatDate;
	
}