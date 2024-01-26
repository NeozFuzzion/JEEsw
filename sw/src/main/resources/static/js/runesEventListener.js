document.addEventListener('DOMContentLoaded', function() {
    let nb_runes = document.getElementsByClassName("rune-slot").length;

    let rSlot;
    let rRank;
    for (let i = 0; i < nb_runes; i++) {
        rSlot = document.getElementsByClassName("rune-slot")[i].innerHTML;
        rRank = document.getElementsByClassName("rune-rang")[i].innerHTML;

        switch (rSlot) {
            case "1":
                document.getElementsByClassName("rune-image")[i].src = "/images/runes/rune1.png";
                break;
            case "2":
                document.getElementsByClassName("rune-image")[i].src = "/images/runes/rune2.png";
                break;
            case "3":
                document.getElementsByClassName("rune-image")[i].src = "/images/runes/rune3.png";
                break;
            case "4":
                document.getElementsByClassName("rune-image")[i].src = "/images/runes/rune4.png";
                break;
            case "5":
                document.getElementsByClassName("rune-image")[i].src = "/images/runes/rune5.png";
                break;
            default:
                document.getElementsByClassName("rune-image")[i].src = "/images/runes/rune6.png";
                break;
        }

        switch (rRank) {
            case "1":
                document.getElementsByClassName("rune-slot-image")[i].style.backgroundImage = "url(/images/runes/bg_normal.png)";
                break;
            case "2":
                document.getElementsByClassName("rune-slot-image")[i].style.backgroundImage = "url(/images/runes/bg_rare.png)";
                break;
            case "3":
                document.getElementsByClassName("rune-slot-image")[i].style.backgroundImage = "url(/images/runes/bg_magic.png)";
                break;
            case "4":
                document.getElementsByClassName("rune-slot-image")[i].style.backgroundImage = "url(/images/runes/bg_hero.png)";
                break;
            default:
                document.getElementsByClassName("rune-slot-image")[i].style.backgroundImage = "url(/images/runes/bg_legend.png)";
                break;
        }

        document.getElementsByClassName("rune-slot-image")[i].style.backgroundSize = "contain";
        document.getElementsByClassName("rune-slot-image")[i].style.backgroundRepeat = "no-repeat";

    }
});