function declension_of_words(text_words, number, nameOfBlock) {
    var listOfModules = document.getElementsByClassName(nameOfBlock);
    var listOfNumOfModules = document.getElementsByClassName(number);
    for (i = 0; i < listOfModules.length; i++) {
        var count = listOfNumOfModules[i].innerHTML;
        if (count == 0 || count % 10 > 5 || count == 11) {
            listOfModules[i].innerHTML=text_words[0];
        } else if (count % 10 == 1) {
            listOfModules[i].innerHTML=text_words[1];
        } else {
            listOfModules[i].innerHTML=text_words[2];
        }
    }
}