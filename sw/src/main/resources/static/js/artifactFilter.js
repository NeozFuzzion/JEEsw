$(function() {
    $("#rangeVal").val("200");
    $("#nbArtifacts").on("input", function() {
        $("#rangeVal").val($(this).val())
    });
});
function sortArtifact(){
    $.post("/artifacts/filter", {
            artifactType:$("#artifactType").val(),
            nbArtifacts:$("#nbArtifacts").val()
        },
        function(data,status){
            createChart(data.efficiency,data.totalArtifacts);
        }
    )
}
function createChart(eff,nbArtifacts){
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
                    text: 'Efficiency - '+ nbArtifacts +' first Artifacts'
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
