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

        var customer_details=[];

    $("#orders").click(function(){


        var store=$("#storelist").val();
        var deltype=$("#deltype").val();
        

        $.get( "http://localhost:8085/storemanager/"+storeManager+"/store/"+store+"/orders/"+deltype)
        .done(function(data,status,xhr)
        {
        
            if(xhr.status&&xhr.status==200)
            {
              
                if(deltype=="delivery")
                {
                    $("#showorders").html("<thead><th>Order Date</th><th>Customer Details</th><th>Product Details</th><th>Store Name</th>");
               
                    customer_details=data;
                    data.map((x,index)=>{$("#showorders").append("<tr><td>"+x.dateOfOrder+"</td>"+
                    
                           "<td> <input type='button' class='mylink' name='customer' id='"+index + "' value='Get Customer Details'></input></td>"+
                           "<td><input type='button'  class='mylink' name='product' id='"+x.oid+"' value='Get product List'></input></td>"+
                           "<td>"+x.store.name+"</td>")});
                }
                else{

                    $("#showorders").html("<thead><th>Order Date</th><th>Customer Details</th><th>Product Details</th><th>Store Name</th><th>Start Time</th><th>End Time</th>");
               
                    customer_details=data;
                    data.map((x,index)=>{$("#showorders").append("<tr><td>"+x.dateOfOrder+"</td>"+
                    
                           "<td> <input type='button' class='mylink' name='customer' id='"+index + "' value='Get Customer Details'></input></td>"+
                           "<td><input type='button'  class='mylink' name='product' id='"+x.oid+"' value='Get product List'></input></td>"+
                           "<td>"+x.store.name+"</td>"+
                           "<td>"+x.slots.startTime+"</td>"+
                           "<td>"+x.slots.endTime+"</td>"
                           )});
                }
            
  
            }    
        })
        .fail(function(xhr, errorType, exception)
        {
            
            if(xhr.status&&xhr.status==404)
            {
            alert("No Orders Found!!!");
            }
            else if(xhr.status&&xhr.status==500)
            {
                alert("Some internal error occured!!");
            }
        
        });

    })
    $(document).on("click", 'input[type="button"]', function(event) { 
            
        if(event.target.name=="customer")
        {
            var index=customer_details[event.target.id];
            $("#result").append("Cusomer Details <br> Name:<b> "+index.customer.firstname+" "+index.customer.lastname
            +"</b><br>" 
            +"Contact no:-<b> "+index.customer.contactno+"</b><br>"+
            "Address :- <br>"+
            "House no:- <b>"+index.customer.houseno+"</b><br>"+
            "City :- <b>"+index.customer.address.city+"</b><br>"+
            "Pincode:- <b>"+index.customer.address.pincode+"</b>"        
            );
        }
        else
        {
            alert(event.target.id);

            $.get( "http://localhost:8085/order/products/"+event.target.id)
        .done(function(data,status,xhr)
        {
        
            if(xhr.status&&xhr.status==200)
            {
              
                $("#result").append("<table><thead><th>Product Name</th><th>Quantity</th></thead>");

                data.map((x,index)=>{$("#result").append("<tbody><tr><td>"+x.pname+"</td><td>"+x.quantity+"</td></tbody></table>")});
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
        }
    });
    

    
   

    })