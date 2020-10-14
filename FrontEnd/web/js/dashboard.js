(function ($) {
    'use strict';
    $(function () {

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

