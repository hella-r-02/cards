function declension_of_words(text_words, number) {
    let listOfNumOfModules = document.getElementsByClassName(number);
    for (let i = 0; i < listOfNumOfModules.length; i++) {
        const count = listOfNumOfModules[i].innerHTML;
        if (count % 10 == 0 || (count > 10 && count < 20) || count % 10 >= 5) {
            listOfNumOfModules[i].innerHTML = count + text_words[0];
        } else if (count % 10 == 1) {
            listOfNumOfModules[i].innerHTML = count + text_words[1];
        } else {
            listOfNumOfModules[i].innerHTML = count + text_words[2];
        }
    }
}