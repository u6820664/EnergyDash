(function ($) {
    'use strict';
    $(function () {
        //Historical
        if ($("#historical-chart-year").length) {
            var AudienceChartCanvas = $("#historical-chart-year").get(0).getContext("2d");
            var AudienceChart = new Chart(AudienceChartCanvas, {
                type: 'bar',
                data: {
                    labels: ["2014", "2015", "2016", "2017", "2018", "2019", "2020"],
                    datasets: [
                        {
                            type: 'line',
                            fill: false,
                            data: [100, 230, 130, 140, 270, 140, 90],
                            borderColor: '#ff4c5b'
                        },
                        {
                            label: 'Consumption',
                            data: [100, 230, 340, 340, 260, 340, 300],
                            backgroundColor: '#6640b2'
                        },
                        {
                            label: 'Price',
                            data: [130, 190, 250, 250, 190, 260, 300],
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
                                max: 400,
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
                        display: false
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
                type: 'bar',
                data: {
                    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                    datasets: [
                        {
                            type: 'line',
                            fill: false,
                            data: [100, 230, 130, 140, 270, 140, 100, 230, 130, 140, 270, 140],
                            borderColor: '#ff4c5b'
                        },
                        {
                            label: 'Consumption',
                            data: [100, 230, 340, 340, 260, 340, 100, 230, 130, 140, 270, 140],
                            backgroundColor: '#6640b2'
                        },
                        {
                            label: 'Price',
                            data: [130, 190, 250, 250, 190, 260, 100, 230, 130, 140, 270, 140],
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
                                max: 400,
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
                        display: false
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
                type: 'bar',
                data: {
                    labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                        "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"],
                    datasets: [
                        {
                            type: 'line',
                            fill: false,
                            data: [100, 230, 130, 140, 270, 140, 90, 100, 230, 130, 140, 270, 140, 90, 100, 230, 130, 140, 270, 140, 90,
                                100, 230, 130, 140, 270, 140, 90, 350, 100, 120],
                            borderColor: '#ff4c5b'
                        },
                        {
                            label: 'Consumption',
                            data: [100, 230, 340, 340, 260, 340, 300, 100, 230, 130, 140, 270, 140, 290, 400, 230, 130, 140, 270, 140, 90,
                                100, 230, 130, 140, 270, 140, 90, 200, 210, 90],
                            backgroundColor: '#6640b2'
                        },
                        {
                            label: 'Price',
                            data: [130, 190, 250, 250, 190, 260, 300, 100, 230, 130, 140, 270, 140, 90, 100, 230, 130, 140, 270, 140, 90,
                                100, 230, 130, 140, 270, 140, 390, 160, 130, 100],
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
                                max: 400,
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
                        display: false
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


        //Hidden charts for SWAP
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

    });

})(jQuery);


//Auto loading
function PerRefresh() {

}

setInterval("PerRefresh()", 2000);

