document.addEventListener('DOMContentLoaded', function() {
    let nbArtifacts = document.getElementsByClassName("artifact-rank").length;
    let aRank;
    let aType;
    for (let i = 0; i < nbArtifacts; i++) {
        aRank = document.getElementsByClassName("artifact-rank")[i].innerHTML;
        aType =document.getElementsByClassName("artifact-type")[i].innerHTML;
        switch (aRank) {
            case "1":
                if(aType=="Attribute"){
                    document.getElementsByClassName("artifact-image")[i].src = "/images/artifacts/element_normal.png";
                }else{
                    document.getElementsByClassName("artifact-image")[i].src = "/images/artifacts/archetype_normal.png";
                }
                document.getElementsByClassName("artifact-type-image")[i].style.backgroundImage = "url(/images/artifacts/bg_normal.png)";
                break;
            case "2":
                if(aType=="Attribute"){
                    document.getElementsByClassName("artifact-image")[i].src = "/images/artifacts/element_rare.png";
                }else{
                    document.getElementsByClassName("artifact-image")[i].src = "/images/artifacts/archetype_rare.png";
                }
                document.getElementsByClassName("artifact-type-image")[i].style.backgroundImage = "url(/images/artifacts/bg_rare.png)";
                break;
            case "3":
                if(aType=="Attribute"){
                    document.getElementsByClassName("artifact-image")[i].src = "/images/artifacts/element_magic.png";
                }else{
                    document.getElementsByClassName("artifact-image")[i].src = "/images/artifacts/archetype_magic.png";
                }
                document.getElementsByClassName("artifact-type-image")[i].style.backgroundImage = "url(/images/artifacts/bg_magic.png)";
                break;
            case "4":
                if(aType=="Attribute"){
                    document.getElementsByClassName("artifact-image")[i].src = "/images/artifacts/element_hero.png";
                }else{
                    document.getElementsByClassName("artifact-image")[i].src = "/images/artifacts/archetype_hero.png";
                }
                document.getElementsByClassName("artifact-type-image")[i].style.backgroundImage = "url(/images/artifacts/bg_hero.png)";
                break;
            case "5":
                if(aType=="Attribute"){
                    document.getElementsByClassName("artifact-image")[i].src = "/images/artifacts/element_legend.png";
                }else{
                    document.getElementsByClassName("artifact-image")[i].src = "/images/artifacts/archetype_legend.png";
                }
                document.getElementsByClassName("artifact-type-image")[i].style.backgroundImage = "url(/images/artifacts/bg_legend.png)";
                break;
            default:
                break;
        }

        document.getElementsByClassName("artifact-type-image")[i].style.backgroundSize = "contain";
        document.getElementsByClassName("artifact-type-image")[i].style.backgroundRepeat = "no-repeat";

    }
});