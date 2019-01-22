$(document).ready(function(){ 
$('.dropdown-menu-primary').mouseleave(function() {
$(this).removeClass("active");  
$('.dropdown-inside').hide();
$(".promo-box").hide();
});
$('.dropdown-menu-primary').mouseover(function() {
$(this).children().show();
$(this).addClass("active");
$(".promo-box").show();

var navHeight= $(".col-1:visible").height()+60; 
$(".col-1:visible").closest(".dropdown-inside").height(navHeight);
  if($(".promoWrapper:visible").height()<navHeight){
       $(".promoWrapper:visible").height(navHeight);
  }

  var cnt=  $(".dropdown-links li:visible").length;
    for(var i=1; i<=cnt;i++){
        if(i%3==0){
         $(".dropdown-links li:visible").eq(i-1).addClass("rows"); 

        }

    }

$(".rows").each(function(e){
  var prev2=$(this).prev().outerHeight()||0;  
  var prev1=$(this).prev().prev().outerHeight()||0;  
  var current = $(this).outerHeight()||0;  
  var rows = [prev1,prev2,current];
  var max =Math.max.apply(Math,rows);
   $(this).height(max);
   $(this).prev().prev().height(max);
   $(this).prev().height(max);


});


}); 
$('.dropdown-menu-primary').on("keydown", function() {
 $('.dropdown-menu-primary').removeClass("active"); 
$(this).children().show();
$(this).addClass("active");
$(".promo-box").show();
}); 
});