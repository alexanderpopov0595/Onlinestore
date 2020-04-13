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
        '<input type="hidden" name="parameterList['+i+'].id" value="0"/>'+
        '<div class="form-element">'+
        '<input class="form-button delete" type="button" value="&#8854 Delete parameter"  onclick="removeForm(this)">'+
        '</div>'+
        '<div class="form-element">'+
        '<input type="text" class="form-input" name="parameterList['+i+'].name"  placeholder="Parameter name" required/>'+
        '</div>';
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
    rewrite(document.getElementsByClassName("dynamic"));
}
//function rewrites ids and indexes
function rewrite(forms){
    for(var i=0; i<forms.length; i++){
        //set id of form
        forms[i].id=i;
        //set indexes to every field
        forms[i].getElementsByTagName("input")[0].setAttribute("name", "parameterList["+i+"].id");
        forms[i].getElementsByTagName("input")[2].setAttribute("name", "parameterList["+i+"].name");


    }
}