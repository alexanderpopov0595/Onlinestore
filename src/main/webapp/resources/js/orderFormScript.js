//function returns option id, which is equals to real value
function getOptionId(code){
    //get all options form
    var options=document.getElementsByTagName("option");
    //get id of form  with chosen value
    for(var i=0; i<options.length;i++){

        if(options[i].value==code){

            return options[i].id;
        }
    }
}

//fucntion insert address id input form in document
function setValues(){

    setValue("address.id");

    setValue("paymentStatus");

    setValue("orderStatus");
    setValue("paymentType");
    setValue("deliveryType");


}
function setValue(name){
    //get input element
    var inputForm=document.getElementById(name);
    //get inputForm value (code of real value)
    var code=inputForm.value;
    console.log("Getted code: "+code);
    //get value
    var value=getOptionId(code);
    console.log("Getted value"+value);
    //Create input form with value
    var insertedForm='<input type="hidden" name='+name+' value='+value+' />';
    //create new div element
    var div = document.createElement("div");
    //insert content into created div
    div.innerHTML = insertedForm;
    //insert created element
    inputForm.parentElement.insertBefore(div, inputForm);
}
//function removes product form from document
function removeForm(e){
    e.parentElement.parentElement.remove();
    rewriteForms();
}
//function rewrites product form array indexes
function rewriteForms(){
    var forms=document.getElementsByClassName("dynamic");
    for(var i=0; i<forms.length; i++){
        forms[i].getElementsByTagName("input")[0].setAttribute("name", "orderDetailsList["+i+"].product.id");
        forms[i].getElementsByTagName("input")[1].setAttribute("name", "orderDetailsList["+i+"].quantity");

    }
    //check forms legnth
    forms=document.getElementsByClassName("form");
    //if there are no forms
    if(forms.length==0){
        //disable save
        document.getElementById("submit").setAttribute(disabled);
    }
}

