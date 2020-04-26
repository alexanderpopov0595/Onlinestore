function getIndex(value) {
    var options = document.getElementsByTagName("option");
    for (var i = 0; i < options.length; i++) {
        if (options[i].value == value) {
            // return option id
            return options[i].id;
        }
    }
    return null;
}

function addForm(e) {
    deleteForm();
    var i = getIndex(e.value);
    var form = document.getElementById("category-" + i);
    form.style.display = "block";
}
function deleteForm() {
    var forms = document.getElementsByClassName("dynamic");
    for (var i = 0; i < forms.length; i++) {
        forms[i].style.display = "none";
    }
}

function clearEmptyForms() {
    var forms = document.getElementsByClassName("dynamic");
    for (var i = 0; i <forms.length; i++) {
        if (forms[i].style.display == "none") {
            document.getElementById(forms[i].id).remove();
            i=-1;
        }
    }

}
function checkSelected(){
   return document.getElementById("categoryList").value!="Choose category";
}
function check() {
    var value=document.getElementById("categoryList").value;
    var options = document.getElementsByTagName("option");
    for (var i = 0; i < options.length; i++) {
        if (options[i].value == value) {
            // return option id
            return true;
        }
    }
    var error=document.getElementById("error");
    return false;
}