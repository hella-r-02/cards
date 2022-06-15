function declension_of_words(text_words, number) {
    var listOfNumOfModules = document.getElementsByClassName(number);
    for (i = 0; i < listOfNumOfModules.length; i++) {
        var count = listOfNumOfModules[i].innerHTML;
        if (count == 0 || count % 10 >= 5 || count == 11) {
            listOfNumOfModules[i].innerHTML = count + text_words[0];
        } else if (count % 10 == 1) {
            listOfNumOfModules[i].innerHTML = count +  text_words[1];
        } else {
            listOfNumOfModules[i].innerHTML = count +  text_words[2];
        }
    }
}