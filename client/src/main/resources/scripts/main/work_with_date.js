function work_with_date(date, num_of_date) {
    var listOfDate = document.getElementsByClassName(date);
    var listOfNumberDate = document.getElementsByClassName(num_of_date);
    const repeat = 'повторить: '
    var today = new Date();
    var tomorrow = new Date(today);
    tomorrow.setDate(today.getDate() + 1);
    for (i = 0; i < listOfDate.length; i++) {
        const tempStr = listOfDate[i].innerHTML.toString();
        const listSplit = tempStr.split([' ']);
        if ((parseInt(listSplit[2]) < today.getDate() && month_to_number(listSplit[1]) == today.getMonth() && listSplit[5] == today.getFullYear())
            || (month_to_number(listSplit[1]) < today.getMonth() && listSplit[5] == today.getFullYear())
            || (listSplit[5] < today.getFullYear())) {
            var tempDate = new Date(listSplit[5], month_to_number(listSplit[1]), parseInt(listSplit[2]));
            var timeDiff = Math.abs(tempDate.getTime() - today.getTime());
            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
            listOfDate[i].innerHTML = 'просрочено на ' + decl(diffDays, [' дней', ' день', ' дня']);
            listOfDate[i].style.color = 'red';
            listOfDate[i].style.fontWeight = 700;
        } else if (month_to_number(listSplit[1]) == today.getMonth() && parseInt(listSplit[2]) == today.getDate() && listSplit[5] == today.getFullYear()) {
            listOfDate[i].innerHTML = repeat;
            listOfNumberDate[i].innerHTML = '\u00A0 сегодня';

            listOfNumberDate[i].style.fontWeight = 700;
        } else if (month_to_number(listSplit[1]) == tomorrow.getMonth() && parseInt(listSplit[2]) == tomorrow.getDate() && listSplit[5] == tomorrow.getFullYear()) {
            listOfDate[i].innerHTML = repeat + ' завтра';
        } else {
            var dateHtml = repeat + parseInt(listSplit[2]) + " " + en_month_to_ru_month(listSplit[1]);
            if (listSplit[5] != today.getFullYear()) {
                dateHtml += " " + listSplit[5].toString();
            }
            listOfDate[i].innerHTML = dateHtml;
        }
    }
}

function month_to_number(month) {
    var listOfMonths = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', "Jul", 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    return listOfMonths.indexOf(month);
}

function en_month_to_ru_month(month) {
    var listOfMonthsEn = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', "Jul", 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    var listOfMonthsRu = ['января', 'февраля', 'марта', 'апреля', 'мая', 'июня', "июля", 'августа', 'сентября', 'октября', 'Ноября', 'Декабря'];
    return listOfMonthsRu[listOfMonthsEn.indexOf(month)];
}

function decl(number, text_words) {
    if (number == 0 || number % 10 > 5 || number == 11) {
        return number + text_words[0];
    } else if (number % 10 == 1) {
        return number + text_words[1];
    } else {
        return count + text_words[2];
    }
}
