(function ($) {
    'use strict';
    $(function () {

        // Notification
        $.ajax({
            type: "get",
            url: "http://54.79.60.225:8080/data/get_notification",
            data: {
                email: "xxx@anu.edu.au",
            },
            async: false, // 要求同步

            //success函数表示交互成功后的操作
            success: function (response) {//response代表后台传过来的数据
                // alert(response.data.value);
                document.getElementById('recommnd-value').innerHTML = response.data.value;
                var recommendVal = document.getElementById('recommnd-value').innerHTML;
                recommendVal = recommendVal.substr(0, recommendVal.length - 1);
                // alert(recommendVal);

                var recommendLev = response.data.level;
                // alert(recommendLev);

                if (recommendLev == 1) {
                    if (recommendVal >= 0 && recommendVal <= 33) {
                        document.getElementById('recommnd-level').innerHTML = "General Recommend";
                    } else if (recommendVal >= 33 && recommendVal <= 66) {
                        document.getElementById('recommnd-level').innerHTML = "Slightly Recommend";
                    } else {
                        document.getElementById('recommnd-level').innerHTML = "Highly Recommend";
                    }

                    document.getElementById("recommend_yes").style.display = 'inline';
                    document.getElementById("recommend_med").style.display = 'none';
                    document.getElementById("recommend_not").style.display = 'none';

                } else if (recommendLev == 2) {
                    if (recommendVal >= 0 && recommendVal <= 33) {
                        document.getElementById('recommnd-level').innerHTML = "General Warning";
                    } else if (recommendVal >= 33 && recommendVal <= 66) {
                        document.getElementById('recommnd-level').innerHTML = "Slightly Warning";
                    } else {
                        document.getElementById('recommnd-level').innerHTML = "Highly Warning";
                    }

                    document.getElementById("recommend_yes").style.display = 'none';
                    document.getElementById("recommend_med").style.display = 'inline';
                    document.getElementById("recommend_not").style.display = 'none';

                } else if (recommendLev == 3) {
                    if (recommendVal >= 0 && recommendVal <= 33) {
                        document.getElementById('recommnd-level').innerHTML = "General Not Recommend";
                    } else if (recommendVal >= 33 && recommendVal <= 66) {
                        document.getElementById('recommnd-level').innerHTML = "Slightly Not Recommend";
                    } else {
                        document.getElementById('recommnd-level').innerHTML = "Highly Not Recommend";
                    }

                    document.getElementById("recommend_yes").style.display = 'none';
                    document.getElementById("recommend_med").style.display = 'none';
                    document.getElementById("recommend_not").style.display = 'inline';
                }
            }
        });

        // Hidden charts for SWAP
        // #task-chart1 represents Carbon Intensity
        if ($("#task-chart1").length) {
            var taskChartCanvas = $("#task-chart1").get(0).getContext("2d");
            var taskChart = new Chart(taskChartCanvas, {
                type: 'line',
                data: {
                    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"],
                    datasets: [{
                        label: 'Profit',
                        data: [Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10],
                        backgroundColor: 'orange'
                    }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 0,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                drawBorder: false,
                                color: '#f1f3f9',
                                zeroLineColor: '#f1f3f9'
                            },
                            ticks: {
                                display: true,
                                fontColor: "#9fa0a2",
                                fontSize: 10,
                                padding: 0,
                                stepSize: 10,
                                min: -10,
                                max: 10
                            }
                        }],
                        xAxes: [{
                            display: false,
                            stacked: false,
                            categoryPercentage: 1,
                            ticks: {
                                display: false,
                                beginAtZero: false,
                                display: true,
                                padding: 10,
                                fontSize: 11
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            position: 'bottom',
                            barPercentage: 0.7
                        }]
                    },
                    legend: {
                        display: false
                    },
                    elements: {
                        point: {
                            radius: 0
                        }
                    }
                }
            });
        }

        // #task-chart2 represents Coal Generator Profits
        if ($("#task-chart2").length) {
            var taskChartCanvas = $("#task-chart2").get(0).getContext("2d");
            var taskChart = new Chart(taskChartCanvas, {
                type: 'line',
                data: {
                    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"],
                    datasets: [{
                        label: 'Profit',
                        data: [Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10],
                        backgroundColor: 'lightblue'
                    }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 0,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                drawBorder: false,
                                color: '#f1f3f9',
                                zeroLineColor: '#f1f3f9'
                            },
                            ticks: {
                                display: true,
                                fontColor: "#9fa0a2",
                                fontSize: 10,
                                padding: 0,
                                stepSize: 10,
                                min: -10,
                                max: 10
                            }
                        }],
                        xAxes: [{
                            display: false,
                            stacked: false,
                            categoryPercentage: 1,
                            ticks: {
                                display: false,
                                beginAtZero: false,
                                display: true,
                                padding: 10,
                                fontSize: 11
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            position: 'bottom',
                            barPercentage: 0.7
                        }]
                    },
                    legend: {
                        display: false
                    },
                    elements: {
                        point: {
                            radius: 0
                        }
                    }
                }
            });
        }

        // #task-chart3 represents Renewable Energy Profits
        if ($("#task-chart3").length) {
            var taskChartCanvas = $("#task-chart3").get(0).getContext("2d");
            var taskChart = new Chart(taskChartCanvas, {
                type: 'line',
                data: {
                    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"],
                    datasets: [{
                        label: 'Profit',
                        data: [Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10],
                        backgroundColor: 'red'
                    }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 0,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                drawBorder: false,
                                color: '#f1f3f9',
                                zeroLineColor: '#f1f3f9'
                            },
                            ticks: {
                                display: true,
                                fontColor: "#9fa0a2",
                                fontSize: 10,
                                padding: 0,
                                stepSize: 10,
                                min: -10,
                                max: 10
                            }
                        }],
                        xAxes: [{
                            display: false,
                            stacked: false,
                            categoryPercentage: 1,
                            ticks: {
                                display: false,
                                beginAtZero: false,
                                display: true,
                                padding: 10,
                                fontSize: 11
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            position: 'bottom',
                            barPercentage: 0.7
                        }]
                    },
                    legend: {
                        display: false
                    },
                    elements: {
                        point: {
                            radius: 0
                        }
                    }
                }
            });
        }

        // #task-chart4 represents Energy Consumption
        if ($("#task-chart4").length) {
            var taskChartCanvas = $("#task-chart4").get(0).getContext("2d");
            var taskChart = new Chart(taskChartCanvas, {
                type: 'line',
                data: {
                    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"],
                    datasets: [{
                        label: 'Profit',
                        data: [Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10, Math.random() * 20 - 10],
                        backgroundColor: 'blue'
                    }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 0,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                drawBorder: false,
                                color: '#f1f3f9',
                                zeroLineColor: '#f1f3f9'
                            },
                            ticks: {
                                display: true,
                                fontColor: "#9fa0a2",
                                fontSize: 10,
                                padding: 0,
                                stepSize: 10,
                                min: -10,
                                max: 10
                            }
                        }],
                        xAxes: [{
                            display: false,
                            stacked: false,
                            categoryPercentage: 1,
                            ticks: {
                                display: false,
                                beginAtZero: false,
                                display: true,
                                padding: 10,
                                fontSize: 11
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            position: 'bottom',
                            barPercentage: 0.7
                        }]
                    },
                    legend: {
                        display: false
                    },
                    elements: {
                        point: {
                            radius: 0
                        }
                    }
                }
            });
        }

        // Canvas_swap Metrics 1-5
        document.getElementById("tab_Metrics_1").onclick = function () {
            changeToMetrics_1()
        };

        function changeToMetrics_1() {
            document.getElementById("card_Metrics_1").style.display = 'block';
            document.getElementById("card_Metrics_2").style.display = 'none';
            document.getElementById("card_Metrics_3").style.display = 'none';
            document.getElementById("card_Metrics_4").style.display = 'none';
            document.getElementById("card_Metrics_5").style.display = 'none';
        }

        document.getElementById("tab_Metrics_2").onclick = function () {
            changeToMetrics_2()
        };

        function changeToMetrics_2() {
            document.getElementById("card_Metrics_1").style.display = 'none';
            document.getElementById("card_Metrics_2").style.display = 'block';
            document.getElementById("card_Metrics_3").style.display = 'none';
            document.getElementById("card_Metrics_4").style.display = 'none';
            document.getElementById("card_Metrics_5").style.display = 'none';
        }

        document.getElementById("tab_Metrics_3").onclick = function () {
            changeToMetrics_3()
        };

        function changeToMetrics_3() {
            document.getElementById("card_Metrics_1").style.display = 'none';
            document.getElementById("card_Metrics_2").style.display = 'none';
            document.getElementById("card_Metrics_3").style.display = 'block';
            document.getElementById("card_Metrics_4").style.display = 'none';
            document.getElementById("card_Metrics_5").style.display = 'none';
        }

        document.getElementById("tab_Metrics_4").onclick = function () {
            changeToMetrics_4()
        };

        function changeToMetrics_4() {
            document.getElementById("card_Metrics_1").style.display = 'none';
            document.getElementById("card_Metrics_2").style.display = 'none';
            document.getElementById("card_Metrics_3").style.display = 'none';
            document.getElementById("card_Metrics_4").style.display = 'block';
            document.getElementById("card_Metrics_5").style.display = 'none';
        }

        document.getElementById("tab_Metrics_5").onclick = function () {
            changeToMetrics_5()
        };

        function changeToMetrics_5() {
            document.getElementById("card_Metrics_1").style.display = 'none';
            document.getElementById("card_Metrics_2").style.display = 'none';
            document.getElementById("card_Metrics_3").style.display = 'none';
            document.getElementById("card_Metrics_4").style.display = 'none';
            document.getElementById("card_Metrics_5").style.display = 'block';
        }

    });

    // Small chart Notify
    $(function () {
        function notify() {
            for (var i = 0; i < document.getElementsByClassName("recommend_yes").length; i++) {
                var randv = Math.random() * 3;
                if (0 < randv && randv < 1) {
                    document.getElementsByClassName("recommend_yes")[i].style.display = 'inline';
                    document.getElementsByClassName("recommend_med")[i].style.display = 'none';
                    document.getElementsByClassName("recommend_not")[i].style.display = 'none';
                }
                if (1 < randv && randv < 2) {
                    document.getElementsByClassName("recommend_yes")[i].style.display = 'none';
                    document.getElementsByClassName("recommend_med")[i].style.display = 'inline';
                    document.getElementsByClassName("recommend_not")[i].style.display = 'none';
                }
                if (2 < randv && randv < 3) {
                    document.getElementsByClassName("recommend_yes")[i].style.display = 'none';
                    document.getElementsByClassName("recommend_med")[i].style.display = 'none';
                    document.getElementsByClassName("recommend_not")[i].style.display = 'inline';
                }
            }
        }

        setInterval(notify, 1000)
    })

    // Loading Data
    $(function () {

        var carbonData = "";
        var renewableData = "";
        var consumptionData = "";
        var coalData = "";
        var priceData = "";

        var timeData = "";

        $.ajax({
            type: "get",
            url: "http://54.79.60.225:8080/data/get_realtime_data",
            data: {
                email: "xxx@anu.edu.au",
            },
            async: false, // 要求同步

            //success函数表示交互成功后的操作
            success: function (response) {//response代表后台传过来的数据
                // alert(response.data[0].renewableProfits);
                for (var i = 0; i < response.data.length; i++) {
                    if (i == response.data.length - 1) {
                        carbonData += response.data[i].carbonIntensity;
                        renewableData += response.data[i].renewableProfits;
                        consumptionData += response.data[i].userConsumption;
                        coalData += response.data[i].coalProfits;
                        priceData += response.data[i].wholesalePrice;

                        timeData += response.data[i].datetime;
                    } else {
                        carbonData += response.data[i].carbonIntensity + ",";
                        renewableData += response.data[i].renewableProfits + ",";
                        consumptionData += response.data[i].userConsumption + ",";
                        coalData += response.data[i].coalProfits + ",";
                        priceData += response.data[i].wholesalePrice + ",";

                        timeData += response.data[i].datetime + ",";
                    }
                }

            }
        });

        var carbonDataArr = carbonData.split(',');
        var renewableDataArr = renewableData.split(',');
        var consumptionDataArr = consumptionData.split(',');
        var coalDataArr = coalData.split(',');
        var priceDataArr = priceData.split(',');

        var timeDataArr = timeData.split(',');

        var carbonDataArrFloat = carbonDataArr.map(function (value, index) {
            return parseFloat(value);
        })
        var renewableDataArrFloat = renewableDataArr.map(function (value, index) {
            return parseFloat(value);
        })
        var consumptionDataArrFloat = consumptionDataArr.map(function (value, index) {
            return parseFloat(value);
        })
        var coalDataArrFloat = coalDataArr.map(function (value, index) {
            return parseFloat(value);
        })
        var priceDataArrFloat = priceDataArr.map(function (value, index) {
            return parseFloat(value);
        })

        //count avg
        const average = (...arr) => {
            const nums = [].concat(...arr);
            return nums.reduce((acc, val) => acc + val, 0) / nums.length;
        };

        // console.log(average(carbonDataArrFloat).toFixed(2));

        var avg_carbonData = average(carbonDataArrFloat).toFixed(2);
        var avg_coalData = average(coalDataArrFloat).toFixed(2);
        var avg_renewableData = average(renewableDataArrFloat).toFixed(2);
        var avg_consumptionData = average(consumptionDataArrFloat).toFixed(2);
        var avg_priceData = average(priceDataArrFloat).toFixed(2);

        document.getElementById('avg_carbon-intensity').innerHTML = avg_carbonData;
        document.getElementById('avg_coal-profits').innerHTML = avg_coalData;
        document.getElementById('avg_renewable-profits').innerHTML = avg_renewableData;
        document.getElementById('avg_energy-consumption').innerHTML = avg_consumptionData;
        document.getElementById('avg_energy-price').innerHTML = avg_priceData;


        if ($("#energy-price").length) {
            var areaData = {
                labels: timeDataArr,
                datasets: [
                    {
                        data: priceDataArr,
                        borderColor: [
                            '#1faf47'
                        ],
                        borderWidth: 3,
                        fill: false,
                        label: "services"
                    },
                ]
            };
            var areaOptions = {
                responsive: true,
                maintainAspectRatio: true,
                plugins: {
                    filler: {
                        propagate: false
                    }
                },
                scales: {
                    xAxes: [{
                        display: true,
                        ticks: {
                            display: false,
                        },
                        gridLines: {
                            display: false,
                            drawBorder: false,
                            color: 'transparent',
                            zeroLineColor: '#eeeeee'
                        }
                    }],
                    yAxes: [{
                        display: true,
                        ticks: {
                            display: true,
                            autoSkip: false,
                            maxRotation: 0,
                            stepSize: 100,
                            fontColor: "#000",
                            fontSize: 14,
                            padding: 18,
                            stepSize: 50,
                            fontSize: 10,
                            fontColor: "#b1b0b0",
                            callback: function (value) {
                                var ranges = [
                                    {divider: 1e6, suffix: 'M'},
                                    {divider: 1e3, suffix: 'k'}
                                ];

                                function formatNumber(n) {
                                    for (var i = 0; i < ranges.length; i++) {
                                        if (n >= ranges[i].divider) {
                                            return (n / ranges[i].divider).toString() + ranges[i].suffix;
                                        }
                                    }
                                    return n;
                                }

                                return formatNumber(value);
                            }
                        },
                        gridLines: {
                            drawBorder: false,
                            color: "#f8f8f8",
                            zeroLineColor: "#f8f8f8"
                        }
                    }]
                },
                legend: {
                    display: false
                },
                tooltips: {
                    enabled: true
                },
                elements: {
                    line: {
                        tension: 0
                    },
                    point: {
                        radius: 0
                    }
                }
            }
            var balanceChartCanvas = $("#energy-price").get(0).getContext("2d");
            var balanceChart = new Chart(balanceChartCanvas, {
                type: 'line',
                data: areaData,
                options: areaOptions
            });
        }


        if ($("#carbon-intensity").length) {
            var taskChartCanvas = $("#carbon-intensity").get(0).getContext("2d");
            var taskChart = new Chart(taskChartCanvas, {
                type: 'line',
                data: {
                    // labels: [],
                    labels: timeDataArr,
                    datasets: [{
                        label: 'Carbon Intensity',
                        // data:[],
                        data: carbonDataArr,
                        backgroundColor: 'orange'
                    }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 0,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                drawBorder: false,
                                color: '#f1f3f9',
                                zeroLineColor: '#f1f3f9'
                            },
                            ticks: {
                                display: true,
                                fontColor: "#9fa0a2",
                                fontSize: 10,
                                padding: 0,
                                stepSize: 200
                            }
                        }],
                        xAxes: [{
                            display: false,
                            stacked: false,
                            categoryPercentage: 1,
                            ticks: {
                                display: false,
                                beginAtZero: false,
                                display: true,
                                padding: 10,
                                fontSize: 11
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            position: 'bottom',
                            barPercentage: 0.7
                        }]
                    },
                    legend: {
                        display: false
                    },
                    elements: {
                        point: {
                            radius: 0
                        }
                    }
                }
            });
        }

        if ($("#coal-profits").length) {
            var taskChartCanvas = $("#coal-profits").get(0).getContext("2d");
            var taskChart = new Chart(taskChartCanvas, {
                type: 'line',
                data: {
                    // labels: [],
                    labels: timeDataArr,
                    datasets: [{
                        label: 'Coal Generator Profits',
                        // data:[],
                        data: coalDataArr,
                        backgroundColor: 'brown'
                    }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 0,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                drawBorder: false,
                                color: '#f1f3f9',
                                zeroLineColor: '#f1f3f9'
                            },
                            ticks: {
                                display: true,
                                fontColor: "#9fa0a2",
                                fontSize: 10,
                                padding: 0,
                                stepSize: 20
                            }
                        }],
                        xAxes: [{
                            display: false,
                            stacked: false,
                            categoryPercentage: 1,
                            ticks: {
                                display: false,
                                beginAtZero: false,
                                display: true,
                                padding: 10,
                                fontSize: 11
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            position: 'bottom',
                            barPercentage: 0.7
                        }]
                    },
                    legend: {
                        display: false
                    },
                    elements: {
                        point: {
                            radius: 0
                        }
                    }
                }
            });
        }

        if ($("#renewable-profits").length) {
            var taskChartCanvas = $("#renewable-profits").get(0).getContext("2d");
            var taskChart = new Chart(taskChartCanvas, {
                type: 'line',
                data: {
                    // labels: [],
                    labels: timeDataArr,
                    datasets: [{
                        label: 'Renewable Energy Profits',
                        // data:[],
                        data: renewableDataArr,
                        backgroundColor: 'lightgreen'
                    }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 0,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                drawBorder: false,
                                color: '#f1f3f9',
                                zeroLineColor: '#f1f3f9'
                            },
                            ticks: {
                                display: true,
                                fontColor: "#9fa0a2",
                                fontSize: 10,
                                padding: 0,
                                stepSize: 10
                            }
                        }],
                        xAxes: [{
                            display: false,
                            stacked: false,
                            categoryPercentage: 1,
                            ticks: {
                                display: false,
                                beginAtZero: false,
                                display: true,
                                padding: 10,
                                fontSize: 11
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            position: 'bottom',
                            barPercentage: 0.7
                        }]
                    },
                    legend: {
                        display: false
                    },
                    elements: {
                        point: {
                            radius: 0
                        }
                    }
                }
            });
        }

        if ($("#energy-consumption").length) {
            var taskChartCanvas = $("#energy-consumption").get(0).getContext("2d");
            var taskChart = new Chart(taskChartCanvas, {
                type: 'line',
                data: {
                    // labels: [],
                    labels: timeDataArr,
                    datasets: [{
                        label: 'Energy Consumption',
                        // data:[],
                        data: consumptionDataArr,
                        backgroundColor: 'pink'
                    }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 0,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                drawBorder: false,
                                color: '#f1f3f9',
                                zeroLineColor: '#f1f3f9'
                            },
                            ticks: {
                                display: true,
                                fontColor: "#9fa0a2",
                                fontSize: 10,
                                padding: 0,
                                stepSize: 0.2
                            }
                        }],
                        xAxes: [{
                            display: false,
                            stacked: false,
                            categoryPercentage: 1,
                            ticks: {
                                display: false,
                                beginAtZero: false,
                                display: true,
                                padding: 10,
                                fontSize: 11
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            position: 'bottom',
                            barPercentage: 0.7
                        }]
                    },
                    legend: {
                        display: false
                    },
                    elements: {
                        point: {
                            radius: 0
                        }
                    }
                }
            });
        }

        // Cumulative
        $.ajax({
            type: "get",
            url: "http://54.79.60.225:8080/data/get_cumulative_data",
            data: {
                email: "xxx@anu.edu.au",
            },
            async: false, // 要求同步

            //success函数表示交互成功后的操作
            success: function (response) {//response代表后台传过来的数据
                // alert(response.data.value);
                document.getElementById('user-consumption').innerHTML = response.data.userConsumption;
                document.getElementById('user-price').innerHTML = response.data.price;

            }
        });

        // historical
        var yearlyUserConsumptionData = "";
        var yearlyPriceData = "";
        var yearlyDatatimeStr = "";

        var monthlyUserConsumptionData = "";
        var monthlyPriceData = "";
        var monthlyDatatimeStr = "";

        var dailyUserConsumptionData = "";
        var dailyPriceData = "";
        var dailyDatatimeStr = "";


        $.ajax({
            type: "get",
            url: "http://54.79.60.225:8080/data/get_historical_data",
            data: {
                email: "xxx@anu.edu.au",
            },
            async: false, // 要求同步

            //success函数表示交互成功后的操作
            success: function (response) {//response代表后台传过来的数据
                for (var i = 0; i < response.data.monthly.length; i++) {
                    if (i == response.data.length) {
                        yearlyUserConsumptionData += response.data.yearly[i].userConsumption * 50;
                        yearlyPriceData += response.data.yearly[i].price;
                        yearlyDatatimeStr += response.data.yearly[i].datetimeStr;
                        console.log(yearlyUserConsumptionData);
                    } else {
                        yearlyUserConsumptionData += response.data.yearly[i].userConsumption * 50 + ",";
                        yearlyPriceData += response.data.yearly[i].price + ",";
                        yearlyDatatimeStr += response.data.yearly[i].datetimeStr + ",";
                    }
                }


            }

        });

        $.ajax({
            type: "get",
            url: "http://54.79.60.225:8080/data/get_historical_data",
            data: {
                email: "xxx@anu.edu.au",
            },
            async: false, // 要求同步

            //success函数表示交互成功后的操作
            success: function (response) {//response代表后台传过来的数据
                for (var i = 0; i < response.data.monthly.length; i++) {
                    if (i == response.data.length - 1) {
                        monthlyUserConsumptionData += response.data.monthly[i].userConsumption * 50;
                        monthlyPriceData += response.data.monthly[i].price;
                        monthlyDatatimeStr += response.data.monthly[i].datetimeStr;
                    } else {
                        monthlyUserConsumptionData += response.data.monthly[i].userConsumption * 50 + ",";
                        monthlyPriceData += response.data.monthly[i].price + ",";
                        monthlyDatatimeStr += response.data.monthly[i].datetimeStr + ",";
                    }
                }

            }

        });

        $.ajax({
            type: "get",
            url: "http://54.79.60.225:8080/data/get_historical_data",
            data: {
                email: "xxx@anu.edu.au",
            },
            async: false, // 要求同步

            //success函数表示交互成功后的操作
            success: function (response) {//response代表后台传过来的数据
                for (var i = 0; i < response.data.monthly.length; i++) {
                    if (i == response.data.length - 1) {
                        dailyUserConsumptionData += response.data.daily[i].userConsumption * 50;
                        dailyPriceData += response.data.daily[i].price;
                        dailyDatatimeStr += response.data.daily[i].datetimeStr;
                    } else {
                        dailyUserConsumptionData += response.data.daily[i].userConsumption * 50 + ",";
                        dailyPriceData += response.data.daily[i].price + ",";
                        dailyDatatimeStr += response.data.daily[i].datetimeStr + ",";
                    }
                }

            }

        });

        var yearlyUserConsumptionDataArr = yearlyUserConsumptionData.split(',');
        var yearlyPriceDataArr = yearlyPriceData.split(',');
        var yearlyDatatimeStrArr = yearlyDatatimeStr.split(',');

        var monthlyUserConsumptionDataArr = monthlyUserConsumptionData.split(',');
        var monthlyPriceDataArr = monthlyPriceData.split(',');
        var monthlyDatatimeStrArr = monthlyDatatimeStr.split(',');

        var dailyUserConsumptionDataArr = dailyUserConsumptionData.split(',');
        var dailyPriceDataArr = dailyPriceData.split(',');
        var dailyDatatimeStrArr = dailyDatatimeStr.split(',');

        if ($("#historical-chart-year").length) {
            var AudienceChartCanvas = $("#historical-chart-year").get(0).getContext("2d");
            var AudienceChart = new Chart(AudienceChartCanvas, {
                type: 'line',
                data: {
                    labels: yearlyDatatimeStrArr,
                    datasets: [
                        {
                            type: 'line',
                            fill: false,
                            label: 'User Consumption',
                            data: yearlyUserConsumptionDataArr,
                            borderColor: '#ff4c5b'
                        },
                        {
                            type: 'line',
                            fill: false,
                            label: 'Price',
                            data: yearlyPriceDataArr,
                            backgroundColor: '#1cbccd'
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 20,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                display: true,
                                drawBorder: false,
                                color: "#f8f8f8",
                                zeroLineColor: "#f8f8f8"
                            },
                            ticks: {
                                display: true,
                                min: 0,
                                max: 120000,
                                stepSize: 10000,
                                fontColor: "#b1b0b0",
                                fontSize: 10,
                                padding: 10
                            }
                        }],
                        xAxes: [{
                            stacked: false,
                            ticks: {
                                beginAtZero: true,
                                fontColor: "#b1b0b0",
                                fontSize: 10
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            barPercentage: .9,
                            categoryPercentage: .7,
                        }]
                    },
                    legend: {
                        display: true
                    },
                    elements: {
                        point: {
                            radius: 3,
                            backgroundColor: '#ff4c5b'
                        }
                    }
                },
            });
        }

        if ($("#historical-chart-month").length) {
            var AudienceChartCanvas = $("#historical-chart-month").get(0).getContext("2d");
            var AudienceChart = new Chart(AudienceChartCanvas, {
                type: 'line',
                data: {
                    labels: monthlyDatatimeStrArr,
                    datasets: [
                        {
                            type: 'line',
                            fill: false,
                            label: 'User Consumption',
                            data: monthlyUserConsumptionDataArr,
                            borderColor: '#ff4c5b'
                        },
                        {
                            type: 'line',
                            fill: false,
                            label: 'Price',
                            data: monthlyPriceDataArr,
                            backgroundColor: '#1cbccd'
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 20,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                display: true,
                                drawBorder: false,
                                color: "#f8f8f8",
                                zeroLineColor: "#f8f8f8"
                            },
                            ticks: {
                                display: true,
                                min: 0,
                                max: 15000,
                                stepSize: 1000,
                                fontColor: "#b1b0b0",
                                fontSize: 10,
                                padding: 10
                            }
                        }],
                        xAxes: [{
                            stacked: false,
                            ticks: {
                                beginAtZero: true,
                                fontColor: "#b1b0b0",
                                fontSize: 10
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            barPercentage: .9,
                            categoryPercentage: .7,
                        }]
                    },
                    legend: {
                        display: true
                    },
                    elements: {
                        point: {
                            radius: 3,
                            backgroundColor: '#ff4c5b'
                        }
                    }
                },
            });
        }

        if ($("#historical-chart-day").length) {
            var AudienceChartCanvas = $("#historical-chart-day").get(0).getContext("2d");
            var AudienceChart = new Chart(AudienceChartCanvas, {
                type: 'line',
                data: {
                    labels: dailyDatatimeStrArr,
                    datasets: [
                        {
                            type: 'line',
                            fill: false,
                            label: 'User Consumption',
                            data: dailyUserConsumptionDataArr,
                            borderColor: '#ff4c5b'
                        },
                        {
                            type: 'line',
                            fill: false,
                            label: 'Price',
                            data: dailyPriceDataArr,
                            backgroundColor: '#1cbccd'
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            top: 20,
                            bottom: 0
                        }
                    },
                    scales: {
                        yAxes: [{
                            display: true,
                            gridLines: {
                                display: true,
                                drawBorder: false,
                                color: "#f8f8f8",
                                zeroLineColor: "#f8f8f8"
                            },
                            ticks: {
                                display: true,
                                min: 0,
                                max: 500,
                                stepSize: 100,
                                fontColor: "#b1b0b0",
                                fontSize: 10,
                                padding: 10
                            }
                        }],
                        xAxes: [{
                            stacked: false,
                            ticks: {
                                beginAtZero: true,
                                fontColor: "#b1b0b0",
                                fontSize: 10
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, 0)",
                                display: false
                            },
                            barPercentage: .9,
                            categoryPercentage: .7,
                        }]
                    },
                    legend: {
                        display: true
                    },
                    elements: {
                        point: {
                            radius: 3,
                            backgroundColor: '#ff4c5b'
                        }
                    }
                },
            });
        }

        // Tab_swap Day/ Month /Year
        document.getElementById("tab_day").onclick = function () {
            changeToDay()
        };

        function changeToDay() {
            document.getElementById("card_day").style.display = 'block';
            document.getElementById("card_month").style.display = 'none';
            document.getElementById("card_year").style.display = 'none';
        }

        document.getElementById("tab_month").onclick = function () {
            changeToMonth()
        };

        function changeToMonth() {
            document.getElementById("card_day").style.display = 'none';
            document.getElementById("card_month").style.display = 'block';
            document.getElementById("card_year").style.display = 'none';
        }

        document.getElementById("tab_year").onclick = function () {
            changeToYear()
        };

        function changeToYear() {
            document.getElementById("card_day").style.display = 'none';
            document.getElementById("card_month").style.display = 'none';
            document.getElementById("card_year").style.display = 'block';
        }

        // 1
        if (400 < avg_carbonData && avg_carbonData < 500) {
            document.getElementById("CI_recommend_yes").style.display = 'inline';
            document.getElementById("CI_recommend_med").style.display = 'none';
            document.getElementById("CI_recommend_no").style.display = 'none';
        }
        if (500 < avg_carbonData && avg_carbonData < 700) {
            document.getElementById("CI_recommend_yes").style.display = 'none';
            document.getElementById("CI_recommend_med").style.display = 'inline';
            document.getElementById("CI_recommend_no").style.display = 'none';
        }
        if (700 < avg_carbonData && avg_carbonData < 1000) {
            document.getElementById("CI_recommend_yes").style.display = 'none';
            document.getElementById("CI_recommend_med").style.display = 'none';
            document.getElementById("CI_recommend_no").style.display = 'inline';
        }

        // 2
        if (40 < avg_coalData && avg_coalData < 50) {
            document.getElementById("CGP_recommend_yes").style.display = 'inline';
            document.getElementById("CGP_recommend_med").style.display = 'none';
            document.getElementById("CGP_recommend_no").style.display = 'none';
        }
        if (50 < avg_coalData && avg_coalData < 70) {
            document.getElementById("CGP_recommend_yes").style.display = 'none';
            document.getElementById("CGP_recommend_med").style.display = 'inline';
            document.getElementById("CGP_recommend_no").style.display = 'none';
        }
        if (70 < avg_coalData && avg_coalData < 100) {
            document.getElementById("CGP_recommend_yes").style.display = 'none';
            document.getElementById("CGP_recommend_med").style.display = 'none';
            document.getElementById("CGP_recommend_no").style.display = 'inline';
        }

        // 3
        if (30 < avg_renewableData && avg_renewableData < 50) {
            document.getElementById("REP_recommend_yes").style.display = 'inline';
            document.getElementById("REP_recommend_med").style.display = 'none';
            document.getElementById("REP_recommend_no").style.display = 'none';
        }
        if (20 < avg_renewableData && avg_renewableData < 30) {
            document.getElementById("REP_recommend_yes").style.display = 'none';
            document.getElementById("REP_recommend_med").style.display = 'inline';
            document.getElementById("REP_recommend_no").style.display = 'none';
        }
        if (10 < avg_renewableData && avg_renewableData < 20) {
            document.getElementById("REP_recommend_yes").style.display = 'none';
            document.getElementById("REP_recommend_med").style.display = 'none';
            document.getElementById("REP_recommend_no").style.display = 'inline';
        }

        // 4
        if (0 < avg_consumptionData && avg_consumptionData < 0.2) {
            document.getElementById("EC_recommend_yes").style.display = 'inline';
            document.getElementById("EC_recommend_med").style.display = 'none';
            document.getElementById("EC_recommend_no").style.display = 'none';
        }
        if (0.2 < avg_consumptionData && avg_consumptionData < 0.5) {
            document.getElementById("EC_recommend_yes").style.display = 'none';
            document.getElementById("EC_recommend_med").style.display = 'inline';
            document.getElementById("EC_recommend_no").style.display = 'none';
        }
        if (0.5 < avg_consumptionData && avg_consumptionData < 0.8) {
            document.getElementById("EC_recommend_yes").style.display = 'none';
            document.getElementById("EC_recommend_med").style.display = 'none';
            document.getElementById("EC_recommend_no").style.display = 'inline';
        }


    });

})(jQuery);


// Auto loading
function PerRefresh() {

}

setInterval("PerRefresh()", 2000);

