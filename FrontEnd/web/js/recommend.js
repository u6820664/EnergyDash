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
                    } else {
                        imageData += response.data[i].image + ",";
                        companyNameData += response.data[i].companyName + ",";
                        planNameData += response.data[i].planName + ",";
                        pidData += response.data[i].pid + ",";
                        saveMoneyData += response.data[i].saveMoney + ",";
                        totalCostData += response.data[i].totalCost + ",";
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

        // alert(companyNameDataArr.length);
        for (var i = 0; i < imageDataArr.length; i++) {
            var tr = document.createElement("TR");

            var imageTD = document.createElement("TD");
            var imageIMG = document.createElement('img');
            imageIMG.src = 'data:image/png;base64,'+imageDataArr[i];
            imageTD.appendChild(imageIMG);

            var companyNameTD = document.createElement("TD");
            var companyNameT = document.createTextNode(companyNameDataArr[i]);
            companyNameTD.appendChild(companyNameT);

            var planNameTD = document.createElement("TD");
            var planNameT = document.createTextNode(planNameDataArr[i]);
            planNameTD.appendChild(planNameT);

            var totalCostTD = document.createElement("TD");
            var totalCostT = document.createTextNode("$ " + parseInt(totalCostDataArr[i]));
            totalCostTD.appendChild(totalCostT);

            var saveMoneyTD = document.createElement("TD");
            var saveMoneyT = document.createTextNode("$ " + parseInt(saveMoneyDataArr[i]));
            saveMoneyTD.appendChild(saveMoneyT);

            tr.appendChild(imageTD);
            tr.appendChild(companyNameTD);
            tr.appendChild(planNameTD);
            tr.appendChild(totalCostTD);
            tr.appendChild(saveMoneyTD);

            // alert(tr);
            document.getElementById("recommended_list").appendChild(tr);
        }

        // alert(parseInt(saveMoneyDataArr[0]));
        document.getElementById('most_saved').innerHTML = parseInt(saveMoneyDataArr[0]);




        // most_saved
    });

})(jQuery);
