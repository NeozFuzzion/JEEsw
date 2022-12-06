function showRightSide() {
    document.getElementsByClassName('right-side')[0].style.display = "flex";
    document.getElementsByClassName('menu-button')[0].children[0].onclick = hideRightSide;
}

function hideRightSide() {
    document.getElementsByClassName('right-side')[0].style.display = "none";
    document.getElementsByClassName('menu-button')[0].children[0].onclick = showRightSide;
}