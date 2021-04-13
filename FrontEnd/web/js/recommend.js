(function ($) {
    'use strict';
    $(function () {
        // Recommend
        var imageData = "";
        var companyNameData = "";
        var planNameData = "";
        var pidData = "";
        var saveMoneyData = "";
        var totalCostData = "";

        var flagData = "";
        var tariffData = "";
        var supplyPerData = "";
        var energyPerData = "";

        $.ajax({
            type: "get",
            url: "http://54.79.60.225:8080/data/user_get_recommend",
            data: {
                email: "xxx@anu.edu.au",
            },
            async: false, // 要求同步
            //success函数表示交互成功后的操作
            success: function (response) {//response代表后台传过来的数据
                // alert(response.data[0].companyName);
                // alert(response.data.length);
                for (var i = 0; i < response.data.length; i++) {
                    if (i == response.data.length - 1) {
                        imageData += response.data[i].image;
                        companyNameData += response.data[i].companyName;
                        planNameData += response.data[i].planName;
                        pidData += response.data[i].pid;
                        saveMoneyData += response.data[i].saveMoney;
                        totalCostData += response.data[i].totalCost;

                        flagData += response.data[i].flag;
                        tariffData += response.data[i].tariff;
                        supplyPerData += response.data[i].supplyPer;
                        energyPerData += response.data[i].energyPer;
                    } else {
                        imageData += response.data[i].image + ",";
                        companyNameData += response.data[i].companyName + ",";
                        planNameData += response.data[i].planName + ",";
                        pidData += response.data[i].pid + ",";
                        saveMoneyData += response.data[i].saveMoney + ",";
                        totalCostData += response.data[i].totalCost + ",";

                        flagData += response.data[i].flag + ",";
                        tariffData += response.data[i].tariff + ",";
                        supplyPerData += response.data[i].supplyPer + ",";
                        energyPerData += response.data[i].energyPer + ",";
                    }
                }
            }
        });
        // alert(companyNameData);

        //Str2Arr
        var imageDataArr = imageData.split(',');
        var companyNameDataArr = companyNameData.split(',');
        var planNameDataArr = planNameData.split(',');
        var pidDataArr = pidData.split(',');
        var saveMoneyDataArr = saveMoneyData.split(',');
        var totalCostDataArr = totalCostData.split(',');

        var flagDataArr = flagData.split(',');
        var tariffDataArr = tariffData.split(',');
        var supplyPerDataArr = supplyPerData.split(',');
        var energyPerDataArr = energyPerData.split(',');

        // alert(companyNameDataArr.length);
        for (var i = 0; i < imageDataArr.length; i++) {
            if (flagDataArr[i] == 0) {
                var tr = document.createElement("TR");
            } else {
                var tr = document.createElement("TR");
                tr.style.background = "#f83e37";
                tr.style.color = "#f3f3f3";
            }

            var rankTD = document.createElement("TD");
            var rankT = document.createTextNode(i + 1);
            rankTD.appendChild(rankT);

            var companyNameTD = document.createElement("TD");
            var imageIMG = document.createElement('img');
            imageIMG.src = 'data:image/png;base64,' + imageDataArr[i];
            imageIMG.className = 'mw-100';
            companyNameTD.appendChild(imageIMG);
            var companyNameT = document.createTextNode(" " + companyNameDataArr[i]);
            companyNameTD.appendChild(companyNameT);

            var planNameTD = document.createElement("TD");
            var planNameT = document.createTextNode(planNameDataArr[i]);
            planNameTD.appendChild(planNameT);

            var tariffTD = document.createElement("TD");
            var tariffT = document.createTextNode(tariffDataArr[i]);
            tariffTD.appendChild(tariffT);

            var totalCostTD = document.createElement("TD");
            var totalCostT = document.createTextNode("$ " + parseInt(totalCostDataArr[i]));
            totalCostTD.appendChild(totalCostT);

            var saveMoneyTD = document.createElement("TD");
            var saveMoneyT = document.createTextNode("$ " + parseInt(saveMoneyDataArr[i]));
            saveMoneyTD.appendChild(saveMoneyT);

            tr.appendChild(rankTD);
            tr.appendChild(companyNameTD);
            tr.appendChild(planNameTD);
            tr.appendChild(tariffTD);
            tr.appendChild(totalCostTD);
            tr.appendChild(saveMoneyTD);

            // alert(tr);
            document.getElementById("recommended_list").appendChild(tr);
        }

        // alert(parseInt(saveMoneyDataArr[0]));
        document.getElementById('most_saved').innerHTML = parseInt(saveMoneyDataArr[0]);

        //Best Matching Energy Plan
        document.getElementById('BM_image').src = 'data:image/png;base64,' + imageDataArr[0];
        document.getElementById('BM_companyName').innerHTML = companyNameDataArr[0];
        document.getElementById('BM_planName').innerHTML = planNameDataArr[0];
        document.getElementById('BM_totalCost').innerHTML = parseInt(totalCostDataArr[0]);
        document.getElementById('BM_saveMoney').innerHTML = parseInt(saveMoneyDataArr[0]);
        document.getElementById('BM_energyPer').style.width = parseInt(energyPerDataArr[0] * 100) + "%";
        document.getElementById('BM_supplyPer').style.width = parseInt(supplyPerDataArr[0] * 100) + "%";

        //Your Current Energy Plan
        var current_rank = imageDataArr.length - 1;
        // alert(current_rank);
        document.getElementById('YC_image').src = 'data:image/png;base64,' + imageDataArr[current_rank];
        document.getElementById('YC_companyName').innerHTML = companyNameDataArr[current_rank];
        document.getElementById('YC_planName').innerHTML = planNameDataArr[current_rank];
        document.getElementById('YC_totalCost').innerHTML = parseInt(totalCostDataArr[current_rank]);
        document.getElementById('YC_saveMoney').innerHTML = parseInt(saveMoneyDataArr[current_rank]);
        document.getElementById('YC_energyPer').style.width = parseInt(energyPerDataArr[current_rank] * 100) + "%";
        document.getElementById('YC_supplyPer').style.width = parseInt(supplyPerDataArr[current_rank] * 100) + "%";

    });

})(jQuery);


function displayRecommended() {
    var x = document.getElementById("recommended_energyPlan");
    var y = document.getElementById("planDisplayBtn");

    if (x.style.display === "none") {
        x.style.display = "block";
        y.innerHTML = "Hidden list";
    } else {
        x.style.display = "none";
        y.innerHTML = "Show more";
    }
}
