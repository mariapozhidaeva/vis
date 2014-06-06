<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <title>OpenLayers OSM and Google Example</title>

    <link rel="stylesheet" href="/vis/static/css/style.css" type="text/css">
    <link rel="stylesheet" href="/vis/static/css/style2.css" type="text/css">
    <link rel="stylesheet" href="/vis/static/css/style3.css" type="text/css">

    <script src="${resource(dir: '/static/js/', file: 'OpenLayers.js')}" type="text/javascript"></script>
    <script src="${resource(dir: '/static/js/', file: 'osm.js')}" type="text/javascript"></script>

    <script type="text/javascript" src="http://maps.google.com/maps/api/js?v=3&amp;sensor=false"></script>
    <g:javascript library="prototype"/>
    <%@ page import="vis.TestController; vis.Calc" %>
    <g:header name="hesd"></g:header>
</head>

<body>

<g:javascript>
    // Call the function 'init()' on the 'load' event of the window.+
    Event.observe(window, 'load', initByParams(), false);

    function initByParams() {
        alert("FUUU");

  /*var epsg_ = ${epsg};
    var url_ = ${url};
    var ws_ = ${ws};*/
  //  document.getElementById('ddiv2').innerHTML =  epsg_ ;
  //  callInitMap("AAA", epsg_, url_, ws_);
    callInitMap("AAA");
  //  callInitMap("AAA","4326","http://localhost:9999/geoserver", "pgups_ws");
    }

</g:javascript>

<div id="nodelist"></div>

${epsg}

<h1 id="title">Visualization of calculation result</h1>

<p id="shortdesc">

</p>

<div id="tags">
    openstreetmap google light
</div>

<div id="map" class="smallmap"></div>
${message}
<div id="docs">
    <p>

    </p>
</div>

<div id="ddiv"></div>

<div id="ddiv2"></div>



<r:layoutResources disposition="defer"/>
<div id="legend">
    <img src="http://localhost:9999/geoserver/wms?REQUEST=GetLegendGraphic&VERSION=1.0.0&FORMAT=image/png&WIDTH=50&HEIGHT=100&LAYER=pgups_ws:output"
         alt="легенда">
</div>
</body>

</html>
