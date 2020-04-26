//index and id of form
var i;

//function adds new form
function addForm(e){

    //get all elements with class "form"
    var forms=document.getElementsByClassName("dynamic");

    //if there are no such elements
    if(forms.length==0){
        //set i to 0
        i=0;
    }
    //else set i as last element id +1
    else{
        i=setId(forms);
    }
    //set new form template with indexes
    var form=
        '<input type="hidden" name="addressList['+i+'].id" value="0"/>'+
        '<input type="hidden" name="addressList['+i+'].status" value="ACTIVE"/>'+
        '<div class="form-element">'+
        '<input class="form-button delete" type="button" value="&#8854 Delete address"  onclick="removeForm(this)">'+
        '</div>'+
        '<div class="form-element">'+
        '<input type="text" class="form-input" name="addressList['+i+'].country"  placeholder="Country" required/>'+
        '</div>'+
        '<div class="form-element">'+
        '<input type="text" name="addressList['+i+'].city" class="form-input" placeholder="City" required/>'+
        '</div>'+
        '<div class="form-element">'+
        '<input type="number" name="addressList['+i+'].zipcode" class="form-input" placeholder="Zipcode" required/>'+
        '</div>'+
        '<div class="form-element">'+
        '<input type="text" name="addressList['+i+'].street" class="form-input"  placeholder="Street" required/>'+
        '</div>'+
        '<div class="form-element">'+
        '<input type="number" name="addressList['+i+'].building" class="form-input" placeholder="Building" required/>'+
        '</div>'+
        '<div class="form-element">'+
        '<input type="number" name="addressList['+i+'].apartment" class="form-input" placeholder="Apartment" required/>'+
        '</div>'
    ;
    //create new div element
    var div = document.createElement("div");
    // set id
    div.setAttribute("id", i);
    //set class name
    div.setAttribute("class", "dynamic");
    //insert content into created div
    div.innerHTML = form;
    //get parent div of e(button with onclick) and insert created element before e(button)
    e.parentElement.insertBefore(div, e);
}

//function returns last id of elements with class "form" and increment it
function setId(forms){
    var max=forms[0].id;
    for(var i=0; i<forms.length; i++){
        if(forms[i].id>max){
            max=forms[i].id;
        }
    }
    max++;
    return max;
}

//function removes form
function removeForm(e){
    e.parentElement.parentElement.remove();
    rewriteForms(document.getElementsByClassName("dynamic"));
}
//function rewrites ids and indexes
function rewriteForms(forms){
    for(var i=0; i<forms.length; i++){

        //set id of form
        forms[i].setAttribute("id", i);

        //set indexes to every field
        forms[i].getElementsByTagName("input")[0].setAttribute("name", "addressList["+i+"].id");
        forms[i].getElementsByTagName("input")[1].setAttribute("name", "addressList["+i+"].status");
        forms[i].getElementsByTagName("input")[3].setAttribute("name", "addressList["+i+"].country");
        forms[i].getElementsByTagName("input")[4].setAttribute("name", "addressList["+i+"].city");
        forms[i].getElementsByTagName("input")[5].setAttribute("name", "addressList["+i+"].zipcode");
        forms[i].getElementsByTagName("input")[6].setAttribute("name", "addressList["+i+"].street");
        forms[i].getElementsByTagName("input")[7].setAttribute("name", "addressList["+i+"].building");
        forms[i].getElementsByTagName("input")[8].setAttribute("name", "addressList["+i+"].apartment");

    }

}
