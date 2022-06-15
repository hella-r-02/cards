function check_is_flip() {
    if (document.getElementById("flipper").classList.contains("flipCard")) {
        document.getElementById("card-back").style.display = 'none';
        document.getElementById("card-front").style.display = '';
    } else {
        document.getElementById("card-front").style.display = 'none';
        document.getElementById("card-back").style.display = '';
    }
}