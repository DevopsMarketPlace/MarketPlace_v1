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

  $("button").click(function(e){

    if(e.target.name=="home")
    {
      $(location).attr('href',"index.html?cid="+qsParm["cid"]);
    }
    else if(e.target.name=="logout")
    {
      $(location).attr('href',"login.html");
    }
    
    

   

    



  });
    })
