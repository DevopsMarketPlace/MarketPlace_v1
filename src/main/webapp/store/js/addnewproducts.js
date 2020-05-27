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
            
                data.map((x,index)=>{$("#storelist").append("<option value="+x.sid +">"+x.name+"</option>")});
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

        $("#tablebody").append("<tr>"+
            
            "<td><input type='text' placeholder='Enter product name' /></td>"+
            "<td><input type='number' min='0' value='0' step='0.01' placeholder='Enter product price' /></td>"+"<td><input type='number' step='0.01' required value='0.00' plceholder='Enter discounted price' /></td>"+
            "<td><input type='number' step='1' min='0' value='0' placeholder='Enter qauntity'/></td>"+ 
            "</tr>");

        $("#addrow").click(function(){

            $("#tablebody").append("<tr>"+
            
            "<td><input type='text' placeholder='Enter product name' /></td>"+
            "<td><input type='number' min='0' value='0' step='0.01' placeholder='Enter product price' /></td>"+"<td><input type='number' step='0.01' required value='0.00' plceholder='Enter discounted price' /></td>"+
            "<td><input type='number' step='1' min='0' value='0' placeholder='Enter qauntity'/></td>"+ 
            "</tr>");
        });

        $("#delrow").click(function(){
            $("#tablebody").find("tr:last").remove();

        })
               
                   
        $("#addproduct").click(function(){

            var data=[];
            var store_selection=$("#storelist").val();;
           
            if(store_selection==-1)
            {
                alert("Select appropriate store!!");
            }
            else{

            
              $("#tablebody").children("tr").each(function(){
              
              var tds = $(this).find('td');
              var temp={
  
                       pid:224324, 
                       pname:tds.eq(0).find("input").val(),
                       pprice:tds.eq(1).find("input").val(),
                       disprice:tds.eq(2).find("input").val(),
                       quantity:tds.eq(3).find("input").val()
  
              }
                  
                 data.push(temp);
  
              
          });
          
          
  
              $.ajaxSetup({
                  headers: {
                   'Content-Type': 'application/json',
                   'Accept': 'application/json'
               } });
          
          
          $.post( "http://localhost:8085/product/"+store_selection,JSON.stringify(data) )
          .done(function(data,status,xhr){
          
           if(xhr.status&&xhr.status==200)
           {
             alert("success");
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