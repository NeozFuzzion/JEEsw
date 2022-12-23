$(function() {
    $("#rangeVal").val("200");
    $("#nbArtifacts").on("input", function() {
        $("#rangeVal").val($(this).val())
    });
});
function sortArtifact(){
    let jsonToUse =$("#jsonChosen").val();
    let type =$("#artifactType").val()
    if(jsonToUse!=null && type!=null){
        $.post("/artifacts/filter", {
            jsonChosen:jsonToUse,
            artifactType:type,
            nbArtifacts:$("#nbArtifacts").val()
        },
            function(data,status){
                createChart(data.efficiency,data.totalArtifacts,data.jsonToUse);
                displayArtifacts(data.artifacts);
                $(".missingParameter").empty();
            }
        )
    }else if(jsonToUse==null && type!=null){
        $(".missingParameter").empty();
        $(".missingParameter").append("Select a json");
    }else if(type==null && jsonToUse!=null){
        $(".missingParameter").empty();
        $(".missingParameter").append("Select an artifact type");
    }else if(type==null && jsonToUse==null){
        $(".missingParameter").empty();
        $(".missingParameter").append("Select a json & an artifact type");
    }
}
function createChart(eff,nbArtifacts,jsonToUse){
    let chartStatus = Chart.getChart("myChart");
    if (chartStatus != undefined) {
        chartStatus.destroy();
    }
    let xValues = [];
    for (let i = 0; i < nbArtifacts; i++) {
        xValues[i]=i+1;
    }
    new Chart("myChart", {
        type: "line",
        data: {
            labels: xValues,
            datasets: [{
                label: "Current Efficiency",
                fill: false,
                lineTension: 0,
                backgroundColor: "#3437eb",
                borderColor: "#3437eb",
                pointRadius: 0,
                data: eff
            }],
        },
        options: {
            plugins: {
                title: {
                    display: true,
                    text: 'Efficiency - '+ nbArtifacts +' first Artifacts from ' + jsonToUse
                },
                legend: {
                    position: 'bottom'
                },
            },
            scales: {
                x: {
                    min: 1,
                    type: 'linear',
                    ticks: {
                        stepSize: 25,
                    }
                },
                y: {
                    type: 'linear',
                    ticks: {}
                }
            }
        },
    });
}
function displayArtifacts(artifacts){
    let div = $(".artifacts-display");
    div.empty();
    artifacts.forEach(artifact => {
        let str="";
        str+= "<div>" +
            "         <div class=\"artifact\" >" +
            "           <div class=\"artifact-type-image\">" +
            "               <p class=\"artifact-type\" >"+artifact['type']+"</p>" +
            "               <p class=\"artifact-rank\" >"+artifact['rang']+"</p>" +
            "               <p class=\"artifact-restriction\" >"+artifact['restriction']+"</p>" +
            "               <div class=\"artifact-image-div\">" +
            "                 <img class=\"artifact-image\" src=\"\">" +
            "                </div>" +
            "                <div class=\"artifact-restriction-div\">" +
            "                  <img class=\"artifact-restriction\" src='/images/artifacts/"+artifact['restriction'].toLowerCase()+".png'>" +
            "                 </div>" +
            "                 <p class=\"artifact-upgrade\" >"+artifact['level']+"</p>" +
            "                </div>" +
            "                <div class=\"artifact-infos\">" +
            "                  <p>"+artifact['pri'] + " " + artifact['statPri']+"</p>" +
            "                </div>" +
            "              </div>" +
            "           </div>";
        div.append(str);
    })
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
}
