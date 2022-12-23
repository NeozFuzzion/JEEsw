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

function searchMonster() {
    let input = document.getElementById("search-monsters-bar").value;
    input = input.toLowerCase();
    let monsters = document.getElementsByClassName("which-monster");

    for (let i = 0; i < monsters.length; i++) {
        if (!monsters[i].innerHTML.toLowerCase().includes(input)) {
            document.getElementsByClassName("monster-grid-image")[i].style.display = "none";
        } else {
            document.getElementsByClassName("monster-grid-image")[i].style.display = "block";
        }
    }

}

function teamBuilder(){
    $.post("/gvoteambuilder", {
            monster1:$("#monster1").val(),
            monster2:$("#monster2").val(),
            monster3:$("#monster3").val()
        })
}