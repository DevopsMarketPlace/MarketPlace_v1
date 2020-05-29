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

    $.get( "/storemanager/stores/"+storeManager)
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

        var productList=[];

        $.get( "/products")
        .done(function(data,status,xhr)
        {
        
            if(xhr.status&&xhr.status==200)
            {
                productList=data;

            data.map((x,index)=>{$("#tablebody").append("<tr>"+
            
            "<td><label class='switch'><input name='check' type='checkbox' checked><span class='slider round'></span></label></td>"+"<td>"+x.pid+"</td>"+
            "<td>"+x.productname+"</td>"+"<td>"+x.pprice+"</td>"+"<td><input class='form-control' type='number' step='0.01' required value="+x.pprice+" /></td>"+
            "<td><input class='form-control' type='number' step='1' min='0' value='0' /></td>"+
            "</tr>")});

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



        $("#addproduct").click(function(){

          var data=[];
          
            var store_selection=$("#storelist").val();
            if(store_selection==-1)
            {
                alert("Select appropriate Store!!");
            }
            else
            {
          
            $("#tablebody").children("tr").each(function(){
            
            var tds = $(this).find('td');
            // alert(tds.eq(0));
            // alert(tds.eq(1).text());
             var flag=tds.eq(0).find("input").is(':checked');
             var quant=tds.eq(5).find("input").val();

            if(flag && quant !=0)
            {
                var temp={

                     pid:tds.eq(1).text(),
                     pname:tds.eq(2).text(),
                     pprice:tds.eq(3).text(),
                     disprice:tds.eq(4).find("input").val(),
                     quantity:tds.eq(5).find("input").val()

                }
                
               data.push(temp);

            }
            });
        
        

            $.ajaxSetup({
                headers: {
                 'Content-Type': 'application/json',
                 'Accept': 'application/json'
             } });
        
        
        $.post( "/product/store/"+store_selection,JSON.stringify(data) )
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