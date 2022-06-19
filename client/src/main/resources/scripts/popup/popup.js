function popup(id_popup) {
    const popupLinks = document.querySelectorAll('.popup-link');
    const body = document.querySelector('body'); //для блокировки боди
    const lockPadding = document.querySelectorAll(".lock-padding");
    let unlock = true;
    const timeout = 600;
    const currentPopup = document.getElementById(id_popup);
    popupOpen(currentPopup);
    const popupCloseIcon = document.querySelectorAll('.popup-close');
    if (popupCloseIcon.length > 0) {
        for (let index = 0; index < popupCloseIcon.length; index++) {
            const e1 = popupCloseIcon[index];
            e1.addEventListener('click', function (e) {
                popupClose(e1.closest(".popup"));
                e.preventDefault();

            })
        }
    }

    function popupOpen(currentPopup) {
        if (currentPopup && unlock) {
            const popupActive = document.querySelector('popup.open');
            if (popupActive) {
                popupClose(popupActive, false);
            } else {
                bodyLock();
            }
            currentPopup.classList.add('open');
            currentPopup.addEventListener("click", function (e) {
                if (!e.target.closest('.popup-content')) {
                    popupClose(e.target.closest('.popup'));
                }

            })
        }
    }

    function popupClose(popupActive, doUnlock = true) {
        if (unlock) {
            popupActive.classList.remove('open');
            if (doUnlock) {
                bodyUnlock();
            }
        }
    }

    function bodyLock() {
        //const lockPaddingValue = window.innerWidth - document.querySelector('.wrapper').offsetWidth + 'px';
        body.classList.add('lock');
        unlock = false;
        setTimeout(function () {
            unlock = true;
        }, timeout);
    }

    function bodyUnlock() {
        setTimeout(function () {
            for (let index = 0; index < lockPadding.length; index++) {
                const e1 = lockPadding[index];
                e1.style.paddingRight = '0px';
            }
            body.style.paddingRight = '0px';
            body.classList.remove('lock');
        }, timeout);
    }
}