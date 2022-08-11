function work_with_date(date, num_of_date, index_year = 5) {
    let listOfDate = document.getElementsByClassName(date);
    let listOfNumberDate = document.getElementsByClassName(num_of_date);
    const repeat = 'повторить: '
    const today = new Date();
    let tomorrow = new Date(today);
    tomorrow.setDate(today.getDate() + 1);
    for (let i = 0; i < listOfDate.length; i++) {
        const tempStr = listOfDate[i].innerHTML.toString();
        const listSplit = tempStr.split([' ']);
        if ((parseInt(listSplit[2]) < today.getDate() && month_to_number(listSplit[1]) == today.getMonth() && listSplit[index_year] == today.getFullYear())
            || ((month_to_number(listSplit[1]) < today.getMonth()) && (listSplit[index_year] == today.getFullYear()))
            || (listSplit[5] < today.getFullYear())) {
            set_overdue(listSplit, listOfDate, today, i, index_year);

        } else if (month_to_number(listSplit[1]) == today.getMonth() && parseInt(listSplit[2]) == today.getDate() && listSplit[index_year] == today.getFullYear()) {
            listOfDate[i].innerHTML = repeat;
            listOfNumberDate[i].innerHTML = '\u00A0 сегодня';
            listOfNumberDate[i].style.fontWeight = 700;
        } else if (month_to_number(listSplit[1]) == tomorrow.getMonth() && parseInt(listSplit[2]) == tomorrow.getDate() && listSplit[index_year] == tomorrow.getFullYear()) {
            listOfDate[i].innerHTML = repeat + ' завтра';
        } else {
            var dateHtml = repeat + parseInt(listSplit[2]) + " " + en_month_to_ru_month(listSplit[1]);
            if (listSplit[index_year] != today.getFullYear()) {
                dateHtml += " " + listSplit[index_year].toString();
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
    if (number % 10 == 0 || number % 10 >= 5 || ((number % 100 >= 10) && (number % 100 <= 19))) {
        return number + text_words[0];
    } else if (number % 10 == 1) {
        return number + text_words[1];
    } else {
        return number + text_words[2];
    }
}

function set_overdue(listSplit, listOfDate, today, i, index_year = 5) {
    let tempDate = new Date(listSplit[index_year], month_to_number(listSplit[1]), parseInt(listSplit[2]));
    const timeDiff = Math.abs(tempDate.getTime() - today.getTime());
    const diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
    listOfDate[i].innerHTML = 'просрочено на ' + decl(diffDays, [' дней', ' день', ' дня']);
    listOfDate[i].style.color = 'red';
    listOfDate[i].style.fontWeight = 700;
}