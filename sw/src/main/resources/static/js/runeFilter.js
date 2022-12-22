$(function() {
    $("#rangeVal").val("400");
    $("#nbRunes").on("input", function() {
        $("#rangeVal").val($(this).val())
    });
});

function sortRune(){
    $.post("/runes/filter", {
            set:$("#runeSet").val(),
            ancient:$("#ancient").val(),
            nbRunes:$("#nbRunes").val()
        },
        function(data,status){
            createChart(data.efficiency,data.effMaxHero,data.effMaxLegend,data.totalRunes);
        }
    )
}
function createChart(eff,effMaxHero,effMaxLegend,nbRunes){
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
                    text: 'Efficiency - '+ nbRunes +' first Runes'
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
