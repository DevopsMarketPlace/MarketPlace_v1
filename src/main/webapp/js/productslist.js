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

    var i=0;
    function purchaseClicked() {

        var cartItems = document.getElementsByClassName('cart-items')[0];
        var order_product=[];
        var quantities = [];
        var finalPrice=[];
        var total = document.getElementsByClassName('cart-items')[0];
        if(i==0)
            cartItems.removeChild(cartItems.firstChild)
        while (cartItems.hasChildNodes()) {

            var Product = document.getElementsByClassName('ppid')[0].value;
            var ItemQuantity = document.getElementsByClassName('cart-quantity-input')[0];
            var final_price=document.getElementsByClassName("cartp")[0].value;
            order_product.push(Product);
            quantities.push(ItemQuantity.value);
            finalPrice.push(final_price);
            // order_product.push(Product);
            // quantities.push(quantities);
            cartItems.removeChild(cartItems.firstChild)

        }
        // var url = "cart.html?product=" + order_product+"&quantity="+quantities;
        // $(location).attr('href',url);
        i++;

        //conversion logic
        var prodid=order_product[0];
        var quant=quantities[0];
        var priceList=finalPrice[0];

        for(var k=1;k<order_product.length;k++)
        {
            prodid+="_"+order_product[k];
            quant+="_"+quantities[k];
            priceList+="_"+finalPrice[k];
        }

        var data={
            cid:qsParm["cid"],
            sid:qsParm["sid"],
            prodlist:prodid,
            quantity:quant,
            dislist:priceList
        }
        var oid;
        $.post( "/order",data )
            .done(function(data,status,xhr){

                if(xhr.status&&xhr.status==200)
                {
                    oid=data;
                    $(location).attr('href',"cart.html?cid="+qsParm["cid"]+"&oid="+data);

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



        updateCartTotal();
        $(document).ajaxStop(function () {

            //   $(location).attr('href',"cart.html?cid="+qsParm["cid"]+"&oid="+oid);

        }) ;

    }


    function removeCartItem(event) {
        var buttonClicked = event.target
        buttonClicked.parentElement.parentElement.remove()
        updateCartTotal()
    }

    function quantityChanged(event) {
        var input = event.target
        if (isNaN(input.value) || input.value <= 0) {
            input.value = 1
        }
        updateCartTotal()
    }

    function addToCartClicked(event) {
        var button = event.target
        var shopItem = button.parentElement.parentElement
        var title = shopItem.getElementsByClassName('shop-item-title')[0].innerText
        var price = shopItem.getElementsByClassName('shop-item-price')[0].innerText
        var disprice = shopItem.getElementsByClassName('shop-item-discount-price')[0].innerText
        var imageSrc = shopItem.getElementsByClassName('shop-item-image')[0].src
        var imageSrc = $("span.shop-item-image").text();
        var actualprice = Math.min(parseInt(disprice),parseInt(price));
        var pid =shopItem.getElementsByClassName('pid')[0].value;
        addItemToCart(title, actualprice, imageSrc,pid);
        updateCartTotal()
    }

    function addItemToCart(title, price, imageSrc,pid) {
        var cartRow = document.createElement('div')
        cartRow.classList.add('cart-row')
        var cartItems = document.getElementsByClassName('cart-items')[0]
        var cartItemNames = cartItems.getElementsByClassName('cart-item-title')
        for (var i = 0; i < cartItemNames.length; i++) {
            if (cartItemNames[i].innerText == title) {
                alert('This item is already added to the cart')
                return
            }
        }
        var cartRowContents = `
        <div class="cart-item cart-column">
        <input type="hidden" class="ppid" value="${pid}" />
            <img class="cart-item-image" src="${imageSrc}" width="100" height="100">
            <span class="cart-item-title">${title}</span>
        </div>
        <span class="cart-price cart-column">${price}</span>
        <input type="hidden" class="cartp" value="${price}" />
        <div class="cart-quantity cart-column">
            <input class="cart-quantity-input" type="number" value="1">
            <button class="btn btn-danger" type="button">REMOVE</button>
        </div>`
        cartRow.innerHTML = cartRowContents
        cartItems.append(cartRow)
        cartRow.getElementsByClassName('btn-danger')[0].addEventListener('click', removeCartItem)
        cartRow.getElementsByClassName('cart-quantity-input')[0].addEventListener('change', quantityChanged)
    }

    function updateCartTotal() {
        var cartItemContainer = document.getElementsByClassName('cart-items')[0]
        var cartRows = cartItemContainer.getElementsByClassName('cart-row')
        var total = 0
        for (var i = 0; i < cartRows.length; i++) {
            var cartRow = cartRows[i]
            var priceElement = cartRow.getElementsByClassName('cart-price')[0]
            var quantityElement = cartRow.getElementsByClassName('cart-quantity-input')[0]
            var price = parseFloat(priceElement.innerText.replace('$', ''))
            var quantity = quantityElement.value
            total = total + (price * quantity)
        }

        total = Math.round(total * 100) / 100
        document.getElementsByClassName('cart-total-price')[0].innerText = 'Rs' + total
    }

    var productList=[];

    // $.get( "http://localhost:8085/storemanager/stores/"+storeManager)
    $.get( "/store/product/"+qsParm["sid"])
        .done(function(data,status,xhr)
        {
            if(xhr.status&&xhr.status==200)
            {
                productList=data;
                // data.map((x,index)=>{$("#forloop").append('<div class="shop-item"><span class="shop-item-title">'+x.productname+'</span><img class="shop-item-image" src="assets/img/product2.png"><div class="row"><div class="col-lg-8"><div class="row"><div class="shop-item-details"><span class="shop-item-actual-price">'+x.pprice+'</strike></span></div></div><div class="row"><br><span class="shop-item-price">'+x.pprice+'</span></div></div><div class="col-lg-4"><input class="btn btn-primary shop-item-button" type="button" value="Add To Cart"></button></div></div></div>')})

                // data.map((x,index)=>{$("#forloop").append('<div class="shop-item"><div class="shop" ><span class="shop-item-title">'+x.pname+'</span><img class="shop-item-image" src="images/product2.png"><div class="row"><div class="col-lg-8"><div class="row"><div class="shop-item-details"><span class="shop-item-price">Actual Price:- Rs <strike>'+x.pprice+'</strike></span></div></div><div class="row"><br><span class="shop-item-price">Sale Price:- Rs '+x.disprice+'</span></div></div><div class="col-lg-4"><input class="btn btn-primary shop-item-button" type="button" value="Add To Cart"></button></div></div></div>')})
                // data.map((x,index)=>{$("#forloop").append('<div class="shop-item"><span class="shop-item-title">'+x.pname+'</span><img class="shop-item-image" src="assets/img/product3.png"><div class="shop-item-details"><div class="row"><h4>Product Price:<span class="shop-item-price">'+x.pprice+'Rs</span><h4></div></div><div class="shop-item-details"><div class="row"><h4>Discounted Price:<span class="shop-item-discount-price">'+x.disprice+'Rs</span><h4></div></div><div class="shop-item-details"><input class="btn btn-primary " type="button" value="ADD TO CART" ></input></div></div></div>')});
                data.map((x,index)=>{$("#forloop").append('<div class="shop-item"><input class="pid" type="hidden"  value="'+x.pid+'"/><span class="shop-item-title">'+x.pname+'</span><img class="shop-item-image" src="assets/img/product3.png"><div class="shop-item-details"><h4>Product Price:<span class="shop-item-price">'+x.pprice+'Rs</span><h4></div><div class="shop-item-details"><h5>Discounted Price:<span class="shop-item-discount-price">'+x.disprice+'Rs</span></h5></div><div class="shop-item-details"><input class="btn btn-primary" type="button" value="ADD TO CART" ></input></div></div>')});

            }
        })
        .fail(function(xhr, errorType, exception)
        {

            if(xhr.status&&xhr.status==404)
            {
                $("#forloop").html("<h3>No products Found</h3>");
            }
            else if(xhr.status&&xhr.status==500)
            {
                alert("Some internal error occured!!");
            }

        });

    $(document).on("click", 'input[type="button"]', function(event) {
        addToCartClicked(event);
    });
    document.getElementsByClassName('btn-purchase')[0].addEventListener('click', purchaseClicked)
})
