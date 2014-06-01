
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
    <script src="${resource(dir: '/static/js/', file: 'osm-google3.js')}" type="text/javascript"></script>

    <script type="text/javascript" src="http://maps.google.com/maps/api/js?v=3&amp;sensor=false"></script>

</head>
<body>
<h1 id="title">Visualization</h1>
<p id="shortdesc">
    Demonstrate use of an OSM layer and a Google layer as base layers.
</p>
<div id="tags">
    openstreetmap google light
</div>
<div id="map" class="smallmap"></div>

<div id="wrapper" onloadstart="getParams(${name})">
    <div id="location"></div>
    <div id="scale"></div>
</div>

<div id="docs">
    <p>

    </p>
</div>


</body>
</html>
