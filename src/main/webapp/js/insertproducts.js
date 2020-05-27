$(document).ready(function(){


   

        $("#tablebody").append("<tr>"+
            
            "<td><input type='text' placeholder='Enter product name' /></td>"+
            "<td><input type='number' min='0' value='0' step='0.01' placeholder='Enter product price' /></td>"+
            
            "</tr>");

        $("#addrow").click(function(){

            $("#tablebody").append("<tr>"+
            
            "<td><input type='text' placeholder='Enter product name' /></td>"+
            "<td><input type='number' min='0' value='0' step='0.01' placeholder='Enter product price' /></td>"+
            "</tr>");
        });

        $("#delrow").click(function(){
            $("#tablebody").find("tr:last").remove();

        })
               
                   
        $("#addproduct").click(function(){

            
           
           
            var data=[];
            
              $("#tablebody").children("tr").each(function(){
              
              var tds = $(this).find('td');
              var temp={
  
                      
                       productname:tds.eq(0).find("input").val(),
                       pprice:tds.eq(1).find("input").val(),  
              }
                  
                 data.push(temp);
  
              
          });
          
          
  
              $.ajaxSetup({
                  headers: {
                   'Content-Type': 'application/json',
                   'Accept': 'application/json'
               } });
          
          
          $.post( "http://localhost:8085/products",JSON.stringify(data) )
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
  
    
  
  
          
          
          
          
          });




});