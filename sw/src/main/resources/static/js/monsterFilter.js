function filterMonster(){
    let jsonToUse =$("#jsonChosen").val();
    if(jsonToUse!=null){
        $.post("/monsters/filter",{
                jsonChosen:jsonToUse,
            },
            function (data,status){
                let nats5 = data.nat5;
                let nats4 = data.nat4;
                let awk2 = data.a2;
                let div = $(".monster-grid");
                div.empty();
                $(".missingParameter").empty();
                nats5.forEach(nat5=>{
                    let str="";
                    str+="<span class=\"monster-grid-image\">" +
                        "            <div>" +
                        "                <p class=\"which-monster\">"+nat5['name']+"</p>" +
                        "                <a href='/bestiary/"+nat5['id']+"'>"+
                        "                    <img src='/images/monsters/"+nat5['image']+"'>" +
                        "                </a>" +
                        "            </div>" +
                        "        </span>";
                    div.append(str);
                });
                nats4.forEach(nat4=>{
                    let str="";
                    str+="<span class=\"monster-grid-image\">" +
                        "            <div>" +
                        "                <p class=\"which-monster\">"+nat4['name']+"</p>" +
                        "                <a href='/bestiary/"+nat4['id']+"'>"+
                        "                    <img src='/images/monsters/"+nat4['image']+"'>" +
                        "                </a>" +
                        "            </div>" +
                        "        </span>";
                    div.append(str);
                });
                awk2.forEach(a2=>{
                    let str="";
                    str+="<span class=\"monster-grid-image\">" +
                        "            <div>" +
                        "                <p class=\"which-monster\">"+a2['name']+"</p>" +
                        "                <a href='/bestiary/"+a2['id']+"'>"+
                        "                    <img src='/images/monsters/"+a2['image']+"'>" +
                        "                </a>" +
                        "            </div>" +
                        "        </span>";
                    div.append(str);
                });
            })
    }else{
        $(".missingParameter").append("Select a json");
    }

}