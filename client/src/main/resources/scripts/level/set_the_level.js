function set_the_level(number) {
    let listOfNumOfModules = document.getElementsByClassName(number);
    for (let i = 0; i < listOfNumOfModules.length; i++) {
        const num = Number(listOfNumOfModules[i].innerHTML);
        listOfNumOfModules[i].innerHTML = "Уровень " + num;
    }
}