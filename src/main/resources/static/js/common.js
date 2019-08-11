$(function () {
	 //-------- 左右高度 --------
    var leftcont = $(".pagesleft").height();
    var righthe = $(".pagesright").outerHeight();
    setTimeout(function () {
        if (leftcont < righthe) {
            $(".pagesleft").css("height", righthe);
        }
    }, 100);
	
	
});