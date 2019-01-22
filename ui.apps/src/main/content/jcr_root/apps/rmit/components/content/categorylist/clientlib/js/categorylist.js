$(document).ready(function(){ 
            
    //To hide text decoration without link
    $('.viewCategoryLinks [href="/"]').addClass("noTextDecoration");
  
    //To add class selected for mobile    
    var checkIOSCategoryLinks = (navigator.userAgent.match(/(iPad|iPhone|iPod)/g) ? true : false);
    if (checkIOSCategoryLinks) {
        $(".categoryLinksCollapse ul > li").on('touchstart', function() {            
            $(this).toggleClass('selected').siblings().removeClass('selected');
        });
        
      //To toggle icon and show/hide blue border for mobile
        $(".categoryLinksHeader").on('touchstart',function (e) { 
        	
        	//to stop scrolling to top
        	e.preventDefault();
        	
        	//to show/hide links
            $(this).parent().next().toggle();
            
            //to remove selected class
            $(".categoryLinksCollapse ul > li").removeClass('selected');
            
            if($(this).parent().find("i").hasClass("fa-angle-down")) {
          	  $(this).parent().find("i").removeClass("fa-angle-down categoryIcon").addClass("fa-angle-up categoryIcon");
                
                if($(this).parent().hasClass('checked')) {
              	  $(this).parent().css("border-bottom","none");
               }
                       
            } else if($(this).parent().find("i").hasClass("fa-angle-up")) {
          	  $(this).parent().find("i").removeClass("fa-angle-up categoryIcon").addClass("fa-angle-down categoryIcon");
                
                if($(this).parent().hasClass('checked')) {
              	  $(this).parent().css("border-bottom","1px solid white");
               }                
            }  
                           	  
          });
    } else {
        $(".categoryLinksCollapse ul > li").on('click', function() {            
            $(this).toggleClass('selected').siblings().removeClass('selected');
        });
        
      //To toggle icon and show/hide blue border for mobile
        $(".categoryLinksHeader").on('click',function (e) {
        	
        	//to stop scrolling to top
        	e.preventDefault();
        	
        	//to show/hide links
            $(this).parent().next().toggle();
            
            //to remove selected class
            $(".categoryLinksCollapse ul > li").removeClass('selected');
            
            if($(this).parent().find("i").hasClass("fa-angle-down")) {
          	  $(this).parent().find("i").removeClass("fa-angle-down categoryIcon").addClass("fa-angle-up categoryIcon");
                
                if($(this).parent().hasClass('checked')) {
              	  $(this).parent().css("border-bottom","none");
               }
                       
            } else if($(this).parent().find("i").hasClass("fa-angle-up")) {
          	  $(this).parent().find("i").removeClass("fa-angle-up categoryIcon").addClass("fa-angle-down categoryIcon");
                
                if($(this).parent().hasClass('checked')) {
              	  $(this).parent().css("border-bottom","1px solid white");
               }                
            }                       
     	  
          });
    }                
        
  //categoryNoBorder function
    function categoryNoBorder() {
    var findRow = "single"; 
        
        //to add css min-height (or) height
        if(findRow=='multiple') {
            $('.category-links__wrapper').css('min-height','150px'); // to apply min-height for more than one row
        } else {
            $('.category-links__wrapper').css('height','150px'); // to resolve center alignment issue in IE
        }
    }

    //To call function categoryNoBorder
    categoryNoBorder();
    
});