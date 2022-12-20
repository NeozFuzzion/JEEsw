$(function() {
    $("#rangeVal").text("");
    $("#nbRunes").on("input", function() {
        $("#rangeVal").text($(this).val())
    });
});

function sortRune(){
    $.post("/runes/filter", {
            set:$("#set").val(),
            ancient:$("#ancient").val(),
            nbRunes:$("#nbRunes").val()
        },
        function(data,status){
            createChart(data.efficiency,data.totalRunes)
        }
    )
}
function createChart(yValues,nbRunes){
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
                data: yValues
            }, {
                label: "Efficiency - Max hero",
                fill: false,
                lineTension: 0,
                backgroundColor: "red",
                borderColor: "red",
                pointRadius: 0,
            }, {
                label: "Efficiency - Max legend",
                fill: false,
                lineTension: 0,
                backgroundColor: "green",
                borderColor: "green",
                pointRadius: 0,
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
