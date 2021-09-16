function getChartConfig(data, labels, legendLabel, title, min, max, axisLabel, unit) {
    return {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: legendLabel,
                data: data,
                pointRadius: 1,
                borderColor: 'rgb(13, 110, 253)',
                backgroundColor: 'rgb(13, 110, 253)',
                borderWidth: 2
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'bottom',
                    display: true,
                    labels: {
                        usePointStyle: true
                    }
                },
                title: {
                    display: true,
                    text: title
                }
            },
            scales: {
                x: {
                    type: 'time',
                    display: true,
                    title: {
                        display: false
                    },
                    time: {
                        stepSize: 2,
                        unit: unit,
                        // Luxon format string
                        tooltipFormat: 'DD T',
                        displayFormats: {
                            hour: 'T'
                        }
                    }
                },
                y: {
                    min: min,
                    max: max,
                    title: {
                        display: true,
                        text: axisLabel
                    }
                }
            },
            zone: timezone
        },
    };
}

function getDonationChartConfig(data, labels, legendLabel, title, axisLabel, unit) {
    return {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: legendLabel,
                data: data,
                borderColor: 'rgb(13, 110, 253)',
                backgroundColor: 'rgba(13, 110, 253, 0.5)',
                borderWidth: 2,
                fill: {
                    target: {
                        value: 60
                    },
                    above: 'rgba(25, 135, 84, 0.5)',
                    below: 'rgba(220, 53, 69, 0.5)'
                }
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'bottom',
                    display: true,
                    labels: {
                        usePointStyle: true
                    }
                },
                title: {
                    display: true,
                    text: title
                }
            },
            scales: {
                x: {
                    type: 'time',
                    display: true,
                    title: {
                        display: false
                    },
                    time: {
                        unit: unit,
                        // Luxon format string
                        tooltipFormat: 'DD T'
                    }
                },
                y: {
                    min: 0,
                    title: {
                        display: true,
                        text: axisLabel
                    }
                }
            },
            zone: timezone
        },
    };
}