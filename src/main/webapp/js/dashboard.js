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
  var storeCount=0;

  $.get( "http://localhost:8085/storemanager/stores/"+qsParm["uid"]+"/count")
  .done(function(data,status,xhr)
  {
  
      if(xhr.status&&xhr.status==200)
      {
          storeCount=data;

          $("#storecount").text(data);
      }    
  })
  .fail(function(xhr, errorType, exception)
  {
      storeCount=-1;
      $("#storecount").text("Some Error Occured");
  
  });
  

  $.get( "http://localhost:8085/storemanager/stores/"+qsParm["uid"]+"/totalordercount")
  .done(function(data,status,xhr)
  {
  
      if(xhr.status&&xhr.status==200)
      {
          
          $("#totalordercount").text(data);
      }    
  })
  .fail(function(xhr, errorType, exception)
  {
      
      $("#totalordercount").text("Some Error Occured");
  
  });

  $.get( "http://localhost:8085/storemanager/stores/"+qsParm["uid"]+"/ordercount/type")
  .done(function(data,status,xhr)
  {
  
      if(xhr.status&&xhr.status==200)
      {
          
          $("#homedel").text(data[0]);
          $("#pickups").text(data[1]);
      }    
  })
  .fail(function(xhr, errorType, exception)
  {
      
    $("#homedel").text("Some Error Occured");
    $("#pickups").text("Some Error Occured");
  
  });

  $.get( "http://localhost:8085/storemanager/stores/inventory/"+qsParm["uid"])
  .done(function(data,status,xhr)
  {
  
      if(xhr.status&&xhr.status==200)
      {
        if(data.length==0 && storeCount!=0)
        {
          $("#inventoryAlert").text("No Alerts are there ");
        }
        else if(data.length==0 && storeCount==0)
        {
          $("#inventoryAlert").text("No Stores are present");
        }
        else
        {
          data.map(x => {$("#inventoryAlert").append("<tr><td>"+x.sid+"</td><td>"+x.sname+"</td><td>"+x.pname+"</td><td>"+x.quantity+"</td></tr>")});
        }  
         
      }    
  })
  .fail(function(xhr, errorType, exception)
  {
      $("#storecount").text("Some Error Occured");
  
  });
  
  
  
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
    else if(e.target.name=="addproducttostore")
    {
     
      $(location).attr('href',"addproducts.html?uid="+qsParm["uid"]+"&uname="+qsParm["uname"]);
    }
    else if(e.target.name=="addnewproducts")
    {
     
      $(location).attr('href',"addnewproducts.html?uid="+qsParm["uid"]+"&uname="+qsParm["uname"]);
    }
    else if(e.target.name=="showorders")
    {
      $(location).attr('href',"showorders.html?uid="+qsParm["uid"]+"&uname="+qsParm["uname"]);

    }
    

   

    



  });
    })
 