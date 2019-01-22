$(document).ready(function(){ 
    
    /*** Desktop Starts **/
    
    //To hide text decoration without link
    $('.topnav-links ul li a').addClass("decoration");
    $('.topnav-links ul li [href="#"]').removeClass("decoration").addClass("nodecoration");
    $('.topnav-links ul li [href="/"]').removeClass("decoration").addClass("nodecoration");
    
    //To add class active
    $('.topnav-links ul li').mouseover(function () {
        $('.topnav-links ul li').find('.topnav-sublinks').removeClass('active');
        $(this).find('.topnav-sublinks').addClass('active');
    }); 
    
    //To remove class active
    $('.topnav-links').mouseout(function () {
        $('.topnav-links ul li').find('.topnav-sublinks').removeClass('active');        
    });
    
    //To show search section
    $('.topnav-search-main').on('click',function () {
        $('.top-nav__search').show();
    });
    
    //To hide search section
    $('.topnav-search-close').on('click',function () {
        $('.top-nav__search').hide();
        $('#topnav-search-value').val('');
    });
    
    /*** Desktop Ends **/
    
    /*** Mobile Starts **/
    
    //To show mobile search section
    $('.topnav-mobi-searchicon').on('click',function () {
        $('.topnav-mobi-subsearch').show();
        $('.topnav-mobi-accordion').hide();
        
        $('.topnav-mobi-search').hide();
        $('.topnav-mobi-close').show();
        $('.topnav-mobi-bars').show();  
        
        //to display icon
        $('.topnav-mobi-close').css('padding','0 0 0 22px');
        
        //to empty search text
        $('#topnav-mobi-searchvalue').val('');
    });
    
    //To hide mobile search section
    $('.topnav-mobi-closeicon').on('click',function () {
        $('.topnav-mobi-subsearch').hide();
        $('.topnav-mobi-accordion').hide();
        
        $('.topnav-mobi-search').show();
        $('.topnav-mobi-close').hide();
        $('.topnav-mobi-bars').show();
    });
    
    //To show mobile accordion section
    $('.topnav-mobi-barsicon').on('click',function () { 
        $('.topnav-mobi-subsearch').hide();
        $('.topnav-mobi-accordion').show();
        $('.topnav-mobi-sublinkssection').hide();
        $(".topnav-mobi-acclinks").parent().removeClass("active");
        
        $('.topnav-mobi-search').show();
        $('.topnav-mobi-close').show();
        $('.topnav-mobi-bars').hide();  
        
        //to display icon
        $('.topnav-mobi-close').css('padding','0 0 0 23px');
        
        if($('.topnav-mobi-acclinks').find("i").hasClass("fa-angle-up")) {
              $('.topnav-mobi-acclinks').find("i").removeClass("fa-angle-up").addClass("fa-angle-down");
          }
    });
    
    //To show/hide mobile accordion
    $('.topnav-mobi-acclinks').on('click',function () {
        
        //To remove active and fa-angle-up class
        $(".topnav-mobi-acclinks").not(this).parent().removeClass("active");
        $(".topnav-mobi-acclinks").not(this).find("i").removeClass("fa-angle-up").addClass("fa-angle-down");
        
        //To hide sublinks and toggle
        $('.topnav-mobi-sublinkssection').hide();        
        $(this).parent().next().toggle();
        
        if($(this).find("i").hasClass("fa-angle-down")) {
              $(this).find("i").removeClass("fa-angle-down").addClass("fa-angle-up");
              $(this).parent().addClass('active');
          } else if($(this).find("i").hasClass("fa-angle-up")) {
              $(this).find("i").removeClass("fa-angle-up").addClass("fa-angle-down");
              $(this).parent().removeClass('active');
              $(this).parent().next().hide(); 
          }
    });
    
    /*** Mobile Ends **/
    
});