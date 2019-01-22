$(document).ready(function () {


	//hide all ul li page load
	$("ul.footermobrightcolumn li ul").hide();

	//show first child
	$("ul.footermobrightcolumn li ul.first-list").show();


	//To add class selected for mobile    
	var checkIOSDev = (navigator.userAgent.match(/(iPad|iPhone|iPod)/g) ? true : false);
	if (checkIOSDev) {
		$("ul.footermobrightcolumn li span").on('touchstart', function() {  

	//to show/hide links

		$(this).parent().children("ul").toggle();
		$(this).parent().siblings().children("ul").hide();


		$("ul.footermobrightcolumn li").not($(this).parent()).find(".fa").removeClass("fa fa-angle-up").addClass("fa fa-angle-down");

        $(this).toggleClass("fa fa-angle-down fa fa-angle-up");

        $('ul.footermobrightcolumn li span').animate({
			scrollTop: $('ul.footermobrightcolumn li span')
		}, 'slow');
		return false;

		});
} else {
	//click event
	$("ul.footermobrightcolumn li span").on('click', function (e) {


		//to show/hide links

		$(this).parent().children("ul").toggle();
		$(this).parent().siblings().children("ul").hide();


		$("ul.footermobrightcolumn li").not($(this).parent()).find(".fa").removeClass("fa fa-angle-up").addClass("fa fa-angle-down");

        $(this).toggleClass("fa fa-angle-down fa fa-angle-up");

        $('ul.footermobrightcolumn li span').animate({
			scrollTop: $('ul.footermobrightcolumn li span')
		}, 'slow');
		return false;


	});
}


});