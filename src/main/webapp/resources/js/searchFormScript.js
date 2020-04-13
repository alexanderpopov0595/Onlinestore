function deleteEmpty(){
    console.log("INSIDE");
    var form=document.getElementById("search");
    var forms=document.getElementsByTagName("input");
    console.log("SIZE: "+forms.length);
    for(var i=0; i<forms.length; i++){
        if(forms[i].value==""){
            console.log("i="+i+"is empty");
            forms[i].parentElement.remove();
            i=-1;
        }
    }
}
function rewriteIndexes(){
    var forms=document.getElementsByClassName("dynamic");
    for( var i=0; i<forms.length; i++){
        console.log("i="+i);
        forms[i].getElementsByTagName("input")[1].setAttribute("name", "productDetailsList["+i+"].parameter.id");
        forms[i].getElementsByTagName("input")[2].setAttribute("name", "productDetailsList["+i+"].value");
    }

}