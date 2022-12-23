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
