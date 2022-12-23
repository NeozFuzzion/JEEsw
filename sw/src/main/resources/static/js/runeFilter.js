$(function() {
    $("#rangeVal").val("400");
    $("#nbRunes").on("input", function() {
        $("#rangeVal").val($(this).val())
    });
});

function sortRune(){
    let jsonToUse =$("#jsonChosen").val();
    let runeSet =$("#runeSet").val();
    if(jsonToUse!=null && runeSet!=null){
        $.post("/runes/filter", {
                jsonChosen:jsonToUse,
                set:runeSet,
                ancient:$("#ancient").val(),
                nbRunes:$("#nbRunes").val()
            },
            function(data,status){
                createChart(data.efficiency,data.effMaxHero,data.effMaxLegend,data.totalRunes,data.jsonToUse);
                displayRunes(data.runes);
                $(".missingParameter").empty();
            }
        )
    }else if(jsonToUse==null && runeSet!=null){
        $(".missingParameter").empty();
        $(".missingParameter").append("Select a json");
    }else if(runeSet==null && jsonToUse!=null){
        $(".missingParameter").empty();
        $(".missingParameter").append("Select a rune set");
    }else if(runeSet==null && jsonToUse==null){
        $(".missingParameter").empty();
        $(".missingParameter").append("Select a json & a rune set");
    }


}
function displayRunes(runes) {
    let div = $(".runes-display");
    div.empty();
    runes.forEach(rune => {
        let str = "";
        str += "<div >" +
            "<a href='/runesBook/"+ rune['id']+"'>"+
            "<div class='rune'>" +
            "<div class=\"rune-slot-image\">" +
            "<p class=\"rune-slot\">" + rune['slot_no'] + "</p>" +
            "<p class=\"rune-rang\">" + rune['rang'] + "</p>" +
            "<div class=\"rune-image-div\">" +
            "<img class=\"rune-image\" src=\"\">" +
            "</div>" +
            "<div class=\"rune-type-div\">" +
            "<img class=\"rune-type\" src='/images/runes/" + rune['set_id']['image'] + "'>" +
            "</div>" +
            "<p class=\"rune-upgrade\">" + rune['upgrade_curr'] + "</p>" +
            "</div>" +
            "<div class=\"rune-infos\">" +
            "                    <p >" + rune['pri'] + ' ' + rune['statPri']['name'] + "</p>"
        if (rune.hasOwnProperty("innate")) {
            str += "<p>" + rune['innate'] + ' ' + rune['statInnate']['name'] + "</p>"

        }
        str += "</div>" +
            "</div>"+
            "</a>"+
            "</div>"
        div.append(str);

    })
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
}

function createChart(eff,effMaxHero,effMaxLegend,nbRunes,jsonToUse){
    let chartStatus = Chart.getChart("myChart");
    if (chartStatus != undefined) {
        chartStatus.destroy();
    }
    let xValues = [];
    for (let i = 0; i < nbRunes; i++) {
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
            }, {
                label: "Efficiency - Max hero",
                fill: false,
                lineTension: 0,
                backgroundColor: "red",
                borderColor: "red",
                pointRadius: 0,
                data: effMaxHero
            }, {
                label: "Efficiency - Max legend",
                fill: false,
                lineTension: 0,
                backgroundColor: "green",
                borderColor: "green",
                pointRadius: 0,
                data: effMaxLegend
            }],
        },
        options: {
            plugins: {
                title: {
                    display: true,
                    text: 'Efficiency - '+ nbRunes +' first Runes from ' + jsonToUse
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
