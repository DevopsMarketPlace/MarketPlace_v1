$(document).ready(function(){

   
    var productList=[];
    $.get( "http://localhost:8085/carts/")
        .done(function(data,status,xhr)
        {
        
            if(xhr.status&&xhr.status==200)
            {
                productList=data;
            	console.log(data);
            	data.map((x,index)=>{$("#cartproducts").append('<tr class="text-center"><td class="product-remove"><a href="#"><span class="ion-ios-close"></span></a></td><td class="image-prod"><div class="img" style="background-image:url(images/product-4.jpg);"></div></td><td class="product-name"><h3>'+x.productname+'</h3></td><td class="price">'+x.pprice+'Rs</td><td class="total">'+x.disprice+'</td></tr>')})
                     
            }
        })
        .fail(function(xhr, errorType, exception)
        {
            
            if(xhr.status&&xhr.status==404)
            {
            alert("Backend Not working");
            }
            else if(xhr.status&&xhr.status==500)
            {
                alert("Some internal error occured!!");
            }
        
        });
    })
