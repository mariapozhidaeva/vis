<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <title>Визуализация расчета ${name}</title>

    <link rel="stylesheet" href="/vis/static/css/style.css" type="text/css">
    <link rel="stylesheet" href="/vis/static/css/style2.css" type="text/css">
    <link rel="stylesheet" href="/vis/static/css/style3.css" type="text/css">
    <script src="http://www.openlayers.org/api/OpenLayers.js"></script>
    <script src="${resource(dir: '/static/js/', file: 'osm.js')}" type="text/javascript"></script>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?v=3&amp;sensor=false"></script>
    <g:javascript library="prototype"/>
    <r:layoutResources/>
</head>

<body>

<g:javascript>
    Event.observe(window, 'load', initByParams(), false);

    function initByParams() {

       callInitMap('${epsg}', '${url}', '${ws}', '${name}' );
    }

</g:javascript>

<div id="nodelist"></div>

<h1 id="title">Результат расчета ${name}</h1>

<p id="shortdesc"></p>

<div id="tags"></div>

<div id="map" class="smallmap"></div>

<div id="legend">
    <img id="im_legend"
         src="http://localhost:9999/geoserver/wms?REQUEST=GetLegendGraphic&VERSION=1.0.0&FORMAT=image/png&WIDTH=50&HEIGHT=70&LAYER=pgups_ws_3:${name}"
         alt="легенда">
</div>
<r:layoutResources/>
</body>

</html>
