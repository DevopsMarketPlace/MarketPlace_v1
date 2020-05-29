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
   
    // alert(qsParm.length);
    // if(qsParm.length==0)
    // {
    //     $(location).attr('href',"login.html");  
    // }
    
    
    var storeManager=qsParm['uid'];
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

    $("#form").submit(function(){
    var name = $("#name").val();
    var startTime= $("#startTime").val()+":00";
    var endTime=$("#endTime").val()+":00";
    var duration=$("#duration").val();
    var person=$("#person").val();
    var city=$("#city").val();
    var pincode=$("#pincode").val();
    // alert(startTime+" "+endTime);
   if(Date.parse('01/01/2020 '+startTime)>=Date.parse('01/01/2020 '+endTime)){
       alert("Opening time should be smaller than closing time")
   }
   else
   {
    
    var data={
    name:name,
	person:person,
	duration:duration,
	startTime:startTime,
	endTime:endTime,
	address:{
		pincode:pincode,
		city:city,
		lat:"26",
		lon:"27"
	}
	
}
          
    $.ajaxSetup({
       headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    } });

    $.post( "/store/"+storeManager,JSON.stringify(data) )
        .done(function(data,status,xhr){
        
         if(xhr.status&&xhr.status==200)
         {
           if(confirm("Store successfully Added. Do you want to add more")){
               location.reload(true);
           }
           else{
                var url = "dashboard.html?uid="+qsParm["uid"]+"&uname="+qsParm["uname"];
               $(location).attr('href',url);
            }
         }    
      })
      .fail(function(xhr, errorType, exception){
        //console.log(xhr.status);
        if(xhr.status&&xhr.status==404)
        {
          alert("Wrong Manager id!!!");
        }
        else if(xhr.status&&xhr.status==500)
        {
            alert("Some internal error occured!!");
        }
      
      });

    }
    event.preventDefault();

    });
});
