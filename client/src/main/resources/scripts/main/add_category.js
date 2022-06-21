function add_category() {
    popup('popup_add_category');
    let form = document.getElementById("form_add_category");
    if (form) {
        form.action = "/category/add";
    }
}