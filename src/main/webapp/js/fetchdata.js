 $(document).ready(function(){
      $("#pincode").click(function(){
          var pincode=$("#pincode_value").val();
          $(location).attr('href',"store.html?pincode="+pincode);
          alert(pincode);
        });

})