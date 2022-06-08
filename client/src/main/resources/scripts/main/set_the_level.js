function set_the_level(number) {
    var listOfNumOfModules = document.getElementsByClassName(number);
    for (var i = 0; i < listOfNumOfModules.length; i++) {
        var num = Number(listOfNumOfModules[i].innerHTML);
        listOfNumOfModules[i].innerHTML = "Уровень " + num;
    }
}