/**
 * Created by Nathan on 3/18/2016.
 */

var width = 900,
    height = 450;

var svg = null,
    yAxisLabels = null,
    xAxisLabels = null,
    informationCircles = null,
    linesOnGraph = null;

//TODO wire up text box to button
//d3.select('#button').on('click', draw);

function draw() {
    var data = getData(),
        margin = 40,
        max = 0,
        min = 0,
        circleRadius = 4,
        x = 0,
        y = 0,
        xAxisScale,
        yAxisScale;

    svg = d3.select('#chart').select('svg').select('g');
    if (svg.empty()) {
        svg = d3.select('#chart')
            .append('svg')
                .attr('width', width)
                .attr('height', height)
                .style('background', "#93A1A1")
            .attr('class', 'viz')
            .append('svg:g')
            .attr('transform', 'translate(' + margin + ',' + margin + ')');
    }

}

function getData() {

}

function isValidDataRequest() {

}

draw();