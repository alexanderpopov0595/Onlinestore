
//function removes product form from document
function removeForm(e){
   e.parentElement.parentElement.remove();
  rewriteForms();
}
//function rewrites product form array indexes
function rewriteForms(){
    var forms=document.getElementsByClassName("dynamic");
    for(var i=0; i<forms.length; i++){
        console.log("id="+forms[i].id);
        forms[i].getElementsByTagName("input")[0].setAttribute("name", "orderDetailsList["+i+"].product.id");
        forms[i].getElementsByTagName("input")[1].setAttribute("name", "orderDetailsList["+i+"].quantity");

    }

    if(forms.length==0){
        document.getElementById("submit").setAttribute("disabled", "disabled");
    }

}

function checkSelected(){
  return document.getElementById("addressList").value!="Choose address" && document.getElementById("deliveryTypes").value!="Choose delivery type" && document.getElementById("paymentTypes").value!="Choose payment type" ;
}
