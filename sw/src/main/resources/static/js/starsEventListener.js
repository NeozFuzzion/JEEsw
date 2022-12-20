document.addEventListener('DOMContentLoaded', function() {
    let nb_stars = parseInt(document.getElementById("monster-stars").innerHTML);
    let awakening_lvl = parseInt(document.getElementById("awakening_level").innerHTML);
    let error = false;

    if (awakening_lvl == 1) {
        nb_stars += 1;
    }
    if (awakening_lvl == 2) {
        nb_stars = 6;
    }

    for (let i = 0; i < nb_stars; i++) {
        if (awakening_lvl == 0) {
            document.getElementsByClassName("monster-stars-images")[0].innerHTML += "<img src='/images/stars/star-unawakened.png'>";
        } else if (awakening_lvl == 1) {
            document.getElementsByClassName("monster-stars-images")[0].innerHTML += "<img src='/images/stars/star-awakened.png'>";
        } else if (awakening_lvl == 2) {
            document.getElementsByClassName("monster-stars-images")[0].innerHTML += "<img src='/images/stars/star-awakened2.png'>";
        } else {
            error = true;
        }
    }
});