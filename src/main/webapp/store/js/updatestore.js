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
    var storeList=[];

    $.get( "http://localhost:8085/storemanager/stores/"+storeManager)
        .done(function(data,status,xhr)
        {
        
            if(xhr.status&&xhr.status==200)
            {
                storeList=data;
            
                data.map((x,index)=>{$("#storelist").append("<option value="+x.sid +"_"+index+">"+x.name+"</option>")});
            }    
        })
        .fail(function(xhr, errorType, exception)
        {
            
            if(xhr.status&&xhr.status==404)
            {
            alert("Wrong Manager id!!!");
            }
            else if(xhr.status&&xhr.status==500)
            {
                alert("Some internal error occured!!");
            }
        
        });

  $("#storelist").change(function()
  {


    var selection=$("#storelist").val();
    if(selection==-1)
    {
        alert("select valid option!!");
        $("#form").css('display','none');
    }
    else
    {
        var sel=selection.split("_");
        $("#form").css('display','block');
        $("#name").val(storeList[sel[1]].name);
        $("#sid").val(sel[0]);
        $("#duration").val(storeList[sel[1]].duration);
        $("#startTime").val(storeList[sel[1]].startTime);
        $("#endTime").val(storeList[sel[1]].endTime);
        $("#person").val(storeList[sel[1]].person);
        $("#city").val(storeList[sel[1]].address.city);
        $("#pincode").val(storeList[sel[1]].address.pincode);

    }

  });

  $("#delete").click(function()
  {

    var sid=$("#sid").val();
    alert(sid);
    $.ajax({url: "http://localhost:8085/store/"+sid,
         type: 'DELETE',
    })
    .done(function(data,status,xhr)
    {
    
        if(xhr.status&&xhr.status==200)
        {
            alert("Deletion successful");
            location.reload();
        }    
     })
  .fail(function(xhr, errorType, exception){
  
        if(xhr.status&&xhr.status==404)
        {
        alert("Wrong Manager id!!!");
        }
        else if(xhr.status&&xhr.status==500)
        {
            alert("Some internal error occured!!");
        }
  
  });

  });

 $("#update").click(function(){


var name = $("#name").val();
    var startTime= $("#startTime").val()+":00";
    var endTime=$("#endTime").val()+":00";
    var duration=$("#duration").val();
    var person=$("#person").val();
    var city=$("#city").val();
    var pincode=$("#pincode").val();
    var sid=$("#sid").val();
   
    if(Date.parse('01/01/2020 '+startTime)>=Date.parse('01/01/2020 '+endTime)){
        alert("Opening time should be smaller than closing time")
    }
    else
    {
        
                var data={
                    sid:sid,
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
        
                $.ajax({
                        url:"http://localhost:8085/store/"+storeManager,
                        data:JSON.stringify(data),
                        type:"PUT"
                    })
                    .done(function(data,status,xhr){
                    
                    if(xhr.status&&xhr.status==200)
                    {
                            alert("Successfully updated!!")
                            location.reload(true);
                    
                    }    
                })
                .fail(function(xhr, errorType, exception){
                   
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
        




 });
});