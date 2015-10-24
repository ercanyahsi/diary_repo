function selectDiary(){
	window.location = "/dear/diary/write/"+$("#date").val().replace(/\//g , ".")+"/";
}