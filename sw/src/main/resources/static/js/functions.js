function showRightSide() {
    document.getElementsByClassName('right-side')[0].style.display = "flex";
    document.getElementsByClassName('menu-button')[0].children[0].onclick = hideRightSide;
}

function hideRightSide() {
    document.getElementsByClassName('right-side')[0].style.display = "none";
    document.getElementsByClassName('menu-button')[0].children[0].onclick = showRightSide;
}

function showKEK() {
    document.getElementsByClassName('kek')[0].style.display = "flex";
}

function showSkillDescription(skill_id) {
    let skill_selected = document.getElementsByClassName("skill-selected")[0].id;
    document.getElementById(skill_selected).className = "skill-not-selected";
    document.getElementById(skill_id).className = "skill-selected";

    document.getElementById(skill_selected + "-description").style.display = "none";
    document.getElementById(skill_id + "-description").style.display = "flex";
}

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