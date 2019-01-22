$(document).ready(function() {

	var url="";

  function loadMore(id) {

      var limitFrom= parseInt($("#"+id).attr('data-limit'));
      if($("#"+id+" .grid-list-wrapper").attr("data-path")!=""){
          url= $("#"+id+" .grid-list-wrapper").attr("data-path")+".model.json?limit="+limitFrom;

      }
      
     $.ajax({

         type: "GET",

         dataType: 'json',

         async: true,

         url: url,

         success: function(data) {

             if (data !== undefined) {

                 console.log(data);

                renderTiles(data,id );
                 var curlimit =  $('#'+id).attr('data-limit');
                 curlimit= parseInt(curlimit)+1;
                 $('#'+id).attr('data-limit', curlimit);

             }

 

         },

         error: function(textStatus, errorThrown) {

             console.error('ERROR FROM FILE');

         }

     });

 

}

    $(".load-more").on("click", function(){
     var  id= $(this).closest('.component').attr("id");
         loadMore(id);

    });
    
    $(".load-more").keydown(function(e) {
	  if(e.keyCode == 13) {
		  var  parseid= $(this).closest('.component').attr("id");
	      loadMore(parseid);
	  }
	});

function renderTiles(data,id){

    var resultsData = data.gridList;

    var tilesCount = resultsData.length;
    var column;
     if($("#"+id+" .cmp-list__item").hasClass("col-md-3")){

        column = 4;

     }else{

       column = 2;

     }

 

    for (var i = 0; i < tilesCount; i++) {
    var desc = "";

       desc = '<div class="cmp-list__item col-xs-12 col-sm-6 '+ ((column == 2) ? 'col-md-6 two-coloumn' : 'col-md-3')+'">';

          desc+='<div class="cmp-list__item-img">';

            desc+='<a href="'+resultsData[i].path+'" data-analytics-type="gridlist" data-analytics-value="'+resultsData[i].title+'" class="h-bar">'

            desc+='<figure class="rollover">';

            desc+='<div class="box-photo">';

            desc+='<img alt="'+resultsData[i].imageAltText+'" src="'+resultsData[i].image+'">';

            desc+='</div>';

            desc+='</figure>';

            desc+='</a>';

            desc+='</div>';

            desc+='<div class="cmp-list__item-content">';

            desc+='<a data-analytics-type="gridlist" data-analytics-value="'+resultsData[i].title+'"  href="'+resultsData[i].path+'" class="h-bar cmp-list__item-link"> <h3>'+resultsData[i].title+'</h3> </a>';

            desc+='<div class="tag-links">'+ ((resultsData[i].categoryTagUrl !==null && resultsData[i].categoryTagUrl !="" ) ? '<a  data-analytics-type="gridlist" data-analytics-value="'+resultsData[i].categoryTag+':'+resultsData[i].title+'"  href="'+resultsData[i].categoryTagUrl+'">'+resultsData[i].categoryTag+'</a>' : '') ;

            desc+='<span data-sly-test="${item.articleDate}" class="date">'+resultsData[i].articleDate+'</span>';

            desc+='</div>';

            desc+='<p>'+resultsData[i].shortDescription+'</p>'

            desc+='</div>';

            desc+='</div>';
            
          if(data.hideLoadMore===true){
              $("#"+id+" .load-more").hide();
         }
         $("#"+id+" .cmp-list").append(desc);

    }

    } 

});