$(document).ready(function () {
	$(".lazy").slick({
		dots: true,
		autoplay: true,
		autoplaySpeed: 5000,
		lazyLoad: 'ondemand', // ondemand progressive anticipated
		infinite: true,
		prevArrow: '<button class="slick-prev slick-arrow" aria-label="Previous" type="button" style="">Previous<i><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 34 51" height="30"><defs><style>.svg-chevron {fill: #d0021b;fill-rule: evenodd; }</style></defs><path id="iconmonstr-angel-left-thin" class="svg-chevron" d="M38,1.6,7.455,25.5,38,49.34,36.685,51,4,25.5,36.706,0Z" transform="translate(-4)"/></i></button>',
		nextArrow: '<button class="slick-next slick-arrow" aria-label="Next" type="button" style="">Next<i><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 34 51" height="30"><defs><style>.cls-1 {fill: #d0021b;fill-rule: evenodd;}</style></defs><path id="iconmonstr-angel-right-thin" class="cls-1" d="M4,1.6,34.545,25.5,4,49.34,5.315,51,38,25.5,5.294,0Z" transform="translate(-4)"/></svg></i></button>'
	});
	
	var msieFeatureContent = false;
    var uaFeatureContent = window.navigator.userAgent;
    var oldieFeatureContent = uaFeatureContent.indexOf('MSIE ');
    var newieFeatureContent = uaFeatureContent.indexOf('Trident/');

    if((oldieFeatureContent > -1) || (newieFeatureContent > -1)) {
    	msieFeatureContent = true;
    }
    
    if(msieFeatureContent) {
        //IE specific code
        $('.feature-content__wrapper .slick-prev').css('left','-140px');
        $('.feature-content__wrapper .slick-next').css('right','-140px');
    }

});