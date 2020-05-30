

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
    var orderid=qsParm["oid"];
    var customer=qsParm["cid"];

    
    var Customerdet;
    var proddata=[];
    var discount=0;
    var subtotal=0;
    var storedata;

    $.get( "/customer/"+customer+"/order/"+orderid)
  .done(function(data,status,xhr)
  {
  
      if(xhr.status&&xhr.status==200)
      {
   
        Customerdet=data.customer;
        storedata=data.store;
         $("#orders").append("<tr><td>"+data.dateOfOrder+"</td><td>"+data.customer.firstname+" "+data.customer.lastname+
        "</td><td>"+data.store.name+"</td><td><input type='button'  class='mylink' name='product' id='"+data.oid+"' value='Get product List'></input></td><td><input type='button'  class='mylink' name='delete' id='"+data.oid+"' value='Delete Order'></input></td></tr>"
        );

        $("#customerdet").append("<h5>Shipping Details</h5><br>"+Customerdet.firstname+" "+Customerdet.lastname+
        "<br>"+Customerdet.contactno+
        "<br>"+Customerdet.houseno+
        "<br>"+Customerdet.address.city+
        "<br>"+Customerdet.address.pincode
        )

      }    
  })
  .fail(function(xhr, errorType, exception)
  {
      storeCount=-1;
      $("#storecount").text("Some Error Occured");
  
  });

  $.get( "/order/products/"+orderid)
  .done(function(data,status,xhr)
  {
  
      if(xhr.status&&xhr.status==200)
      {
        
          
          proddata=data;
          
          
          $("#prodlist").html("<table><thead><th style='visibility:collapse;'>Id</th><th>Product Name</th><th>Actual Price</th><th>Discount Price</th><th>Quantity</th></thead>");

          data.map((x,index)=>{$("#prodlist").append("<tbody><tr><td style='visibility:collapse;'>"+x.pid+"</td><td>"+x.pname+"</td><td>"+x.pprice+"</td><td>"+x.disprice+"</td><td>"+x.quantity+"</td></tbody></table>")});
          
       
          data.forEach((x,index)=>{
            subtotal=subtotal+(x.disprice*x.quantity);
            discount=discount+(x.pprice-x.disprice)*x.quantity;
            });
          
      }    
  })
  .fail(function(xhr, errorType, exception)
  {
      
      if(xhr.status&&xhr.status==404)
      {
         alert("No Products Found!!!");
      }
      else if(xhr.status&&xhr.status==500)
      {
          alert("Some internal error occured!!");
      }
  
  });

  $(document).ajaxStop(function () {
    // 0 === $.active
   
    $("#subtotal").text("Rs "+subtotal);
    $("#savings").text("Rs "+ discount);
    $("#total").text("Rs "+ subtotal);

    $("#homedelivery").click(function(event){
        $("#slots").slideUp();
    })

    $("#pickupslots").click(function(event){
        $("#slots").slideDown();
        $("#slotdetails").empty();
    
        $.get( "/store/slots/"+storedata.sid)
            .done(function(data,status,xhr)
            {
            
                if(xhr.status&&xhr.status==200)
                {
                  
                   var stimes=storedata.startTime.split(":");
                   var etimes=storedata.endTime.split(":");
                   
                    var startTime = moment().utc().set({hour:stimes[0],minute:stimes[1]});
                    var endTime = moment().utc().set({hour:etimes[0],minute:etimes[1]});
                    
                     var timeStops = [];
                    
                    while(startTime <= endTime){
                        timeStops.push(new moment(startTime).format('HH:mm'));
                        startTime.add(storedata.duration, 'minutes');
                    }
                    

                    
                    
                    
                    var finalTime=[];
                    for(var i=0;i<timeStops.length;i++)
                    {
                        let flag=1;
                        for(var j=0;j<data.length;j++)
                        {
                            var time=data[j].split(":");
                            var d=moment().utc().set({hour:time[0],minute:time[1]});
                            var tempd=new moment(d).format('HH:mm');
                        
                            
                            if(tempd.valueOf()==timeStops[i].valueOf())
                            {
                                flag=0;
                                break;
                            }
                            
                        }
                        if(flag==1)
                        {
                            finalTime.push(timeStops[i]);
                        }
                    
                  }
                   
                  finalTime.map((x,index)=>{$("#slotdetails").append("<option value='"+x+"'>"+x+"</option>")});
                 
                }    
            })
            .fail(function(xhr, errorType, exception)
            {
                
                if(xhr.status&&xhr.status==404)
                {
                alert("no slot found!!!");
                }
                else if(xhr.status&&xhr.status==500)
                {
                    alert("Some internal error occured!!");
                }
            
            });
    })

  });  
           
            

  $(document).on("click", 'input[type="button"]', function(event) { 
            
  
    if(event.target.name=="product")
    {
        $("#prodlist_new").slideToggle();
    }
    else if(event.target.name=="delete")
    {
        $.ajax({url: "/order/"+orderid,
         type: 'DELETE',
         })
        .done(function(data,status,xhr)
        {
        
            if(xhr.status&&xhr.status==200)
            {
                alert("Successfully Deleated!!");
                $(location).attr('href',"index.html?cid="+qsParm["cid"]);
            }    
        })
        .fail(function(xhr, errorType, exception){
        
                if(xhr.status&&xhr.status==404)
                {
                alert("Wrong order id!!!");
                }
                else if(xhr.status&&xhr.status==500)
                {
                    alert("Some internal error occured!!");
                }
        
        });
    }
    else if(event.target.id=="placeorder")
    {
        var deliveryType;
        if($("#pickupslots").is(":checked") && $("#homedelivery").is(":checked"))
        {
            alert("please select approriate delivery type!!");

        }
        else
        {
            if($("#pickupslots").is(":checked"))
            {
                deliveryType="pickup";
                var sTime=$("#slotdetails").val();
                st=sTime.split(":");
                let t=moment().utc().set({hour:st[0],minute:st[1]});
                let td=new moment(t).format('HH:mm');

                t.add(storedata.duration, 'minutes');
                var eTime=new moment(t).format('HH:mm');
               
                var request={

                    type:deliveryType,
                    endTime:eTime,
                    startTime:sTime
                    
                };

            }
            else if($("#homedelivery").is(":checked"))
            {
                deliveryType="delivery";
                request={

                    type:deliveryType,
                    endTime:"",
                    startTime:""
                    
                };
            }

            
            $.ajax({
                url:"/order/"+orderid,
                data:request,
                type:"PUT"
            })
            .done(function(data,status,xhr){
            
            if(xhr.status&&xhr.status==200)
            {
                    alert("Thanks for buying!!")
                    $(location).attr('href',"index.html?cid="+qsParm["cid"]);

            
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
        
    }       

});
  


  
});

