
//function returns index
function getIndex(value) {
    // get all options
    var options = document.getElementsByTagName("option");
    // run through the array
    for (var i = 0; i < options.length; i++) {
        // if options value == choosen value
        if (options[i].value == value) {
            // return option id
            return options[i].id;
        }
    }
    return null;
}

function addForm(e) {
    // remove previous form
    deleteForm();
    // get category id
    var i = getIndex(e.value);
    console.log("index: " + i);

    // get form with id=i
    var form = document.getElementById("category-" + i);

    // change style
    form.style.display = "block";

}
function deleteForm() {
    // get elements with class form
    var forms = document.getElementsByClassName("dynamic");
    // run through array
    for (var i = 0; i < forms.length; i++) {
        // set display to none
        forms[i].style.display = "none";
    }
}
function clearEmptyForms() {
    // get elements with class form

    var forms = document.getElementsByClassName("dynamic");


    // run through array

    for (var i = 0; i <forms.length; i++) {
        // set display to none

        if (forms[i].style.display == "none") {

            document.getElementById(forms[i].id).remove();

            i=-1;

        }
    }

}
