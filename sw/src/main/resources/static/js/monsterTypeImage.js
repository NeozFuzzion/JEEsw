document.addEventListener('DOMContentLoaded', function() {
    switch (document.getElementById("monster-type").innerHTML) {
        case "Dark" :
            document.getElementById("type-image").src = "/images/elements/dark.png";
            break;
        case "Fire" :
            document.getElementById("type-image").src = "/images/elements/fire.png";
            break;
        case "Light" :
            document.getElementById("type-image").src = "/images/elements/light.png";
            break;
        case "Water" :
            document.getElementById("type-image").src = "/images/elements/water.png";
            break;
        case "Wind" :
            document.getElementById("type-image").src = "/images/elements/wind.png";
            break;
        default:
            break;
    }
});