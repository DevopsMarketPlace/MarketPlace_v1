$(document).ready(function(){
     var qsParm = new Array();
  
      var query = window.location.search.substring(1);
  
      var parms = query.split('&');
 
      for (var i=0; i < parms.length; i++) {
        var pos = parms[i].indexOf('=');
   
      if (pos > 0) {
          var key = parms[i].substring(0, pos);
          var val = parms[i].substring(pos + 1);
          qsParm[key] = val;
      }

     

      }
       var pincode=qsParm["pincode"];

   
    var storeList=[];
    $.get( "http://localhost:8085/store/bypincode/"+pincode)
        .done(function(data,status,xhr)
        {
        
            if(xhr.status&&xhr.status==200)
            {
                storeList=data;
               
                // data.map((x,index)=>{$("#looping").append('<div class="col-md-6 col-lg-3 "><div class="product"><a href="#" class="img-prod"><img class="img-fluid" src="images/product-12.jpg" alt="Colorlib Template"><div class="overlay"></div></a><div class="text py-3 pb-4 px-3 text-center"><h3><a href="#">100</a></h3><div class="d-flex"><div class="pricing"><p class="price"><span>'+x.pprice+'</span></p></div></div><button  name="click" value="'+x.sid+'"></div></div></div>')})
                // data.map((x,index)=>{$("#looping").append('<div class="row"><div class="row"><div class="col-lg-12"><img class="img-fluid" src="images/store.png" alt="Colorlib Template"></div></div><div class="row"><div class="col-lg-12"><input type="button" class="mylink" name="storelink" id="'+x.sid+'" value="'+x.name+'"> </input></div> </div></div>')});
             data.map((x,index)=>{$("#looping").append(
                '<div class="col-lg-2 ">'+
                 '<div class="card border-left-warning shadow h-100 py-2">'+
                  '<div class="card-body">'+
                     '<div class="row no-gutters align-items-center">'+
                      '<div class="col mr-2">'+
                      '<div class="h5 mb-0 font-weight-bold text-gray-800" id="pickups"><img class="img-fluid" src="/assets/img/store.png" alt="Colorlib Template"></div>'+
                      '<br><div class="text-xs font-weight-bold text-warning text-uppercase mb-1">'+'<input type="button" class="mylink" name="storelink" id="'+x.sid+'" value="'+x.name+'"> </input>'+'</div>'+
                        
                       '</div>'+
                      
                     '</div>'+
                   '</div>'+
                 '</div>'+
               '</div>' )});       
            }
        })
        .fail(function(xhr, errorType, exception)
        {
            
            if(xhr.status&&xhr.status==404)
            {
            alert("No stores Found");
            $(location).attr('href',"index.html?cid="+qsParm["cid"]);

            }
            else if(xhr.status&&xhr.status==500)
            {
                alert("Some internal error occured!!");
            }
        
        });
    
        $(document).on("click", 'input[type="button"]', function(event) { 
        
           
           $(location).attr('href',"products.html?cid="+qsParm['cid']+"&sid="+event.target.id);

        });
    
    })

