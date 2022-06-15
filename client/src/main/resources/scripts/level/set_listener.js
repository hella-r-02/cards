function set_listener() {
    const flipper = document.getElementById("flipper");
    flipper.addEventListener("click", flipCard);

    function flipCard() {
        flipper.classList.toggle("flipCard");
    }
}