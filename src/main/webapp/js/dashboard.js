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

  $("#manager_name").text(qsParm["uname"]);


  $("button").click(function(e){

    if(e.target.name=="dashboard")
    {
      $(location).attr('href',"dashboard.html?uid="+qsParm["uid"]+"&uname="+qsParm["uname"]);
    }
    else if(e.target.name=="addstore")
    {
      $(location).attr('href',"addstore.html?uid="+qsParm["uid"]+"&uname="+qsParm["uname"]);
    }
    else if(e.target.name=="updatestore")
    {
     
      $(location).attr('href',"updatestore.html?uid="+qsParm["uid"]+"&uname="+qsParm["uname"]);
    }


  });
    })
 