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
    <g:javascript library="prototype"/>
    <%@ page import="vis.Calc" %>
    <g:header name="hesd"></g:header>
</head>

<body>

<g:javascript>
    // Call the function 'init()' on the 'load' event of the window.+
    Event.observe(window, 'load', meth, false);

    function meth() {
        // Do initialisation here...   ++

        alert("FUUU");
        alert(${message});

        setParams(${message});
    }

</g:javascript>

<div id="nodelist"></div>

<h1 id="title">Visualization</h1>

<p id="shortdesc">
    Результат расчета
</p>

<div id="tags">
    openstreetmap google light
</div>

<div id="map" class="smallmap"></div>


${message}


<div id="wrapper" onloadstart="getParams(${name})">
    <div id="location"></div>

    <div id="scale"></div>
</div>

<div id="docs">
    <p>

    </p>
</div>


<div id="ddiv">

</div>
<g:javascript>
   windows.onload = setParams(${message});
   alert('hello33');


function getParam(){$.getJSON('', {}, function(obj){
 alert('hello66');
});}
</g:javascript>

<g:link title="erere" onclick="setParams(${message})">rere</g:link>
<g:link title="erere" onload="setParams(${message})">rere</g:link>


<g:formRemote name="to_form"
              url="[controller: 'test', action: 'iata']"
              onSuccess="callMethod(e,1)">
    To: <br/>


    <input type="text" name="id" size="3"/>
    <input type="submit" value="Search"/>
</g:formRemote>


<r:layoutResources disposition="defer"/>

<g:remoteLink action="showBook" id="${1}"
              update="book${1}">Update Book</g:remoteLink>

<div id="book${1}">
    <!--existing book mark-up -->
</div>


<script type="text/javascript">

    function callMethod(response, position) {
        var airport = eval('(' + response.responseText + ')')
        var label = airport.description + " -- " + airport.name
        //  var marker = new GMarker(new GLatLng(airport.lat, airport.lng), {title:label})
        //   marker.bindInfoWindowHtml(label)
        alert("CALL METHOD");

        document.getElementById("ddiv").innerHTML = label

        document.getElementById("ddiv").innerHTML = "abel";
    }

</script>
</body>

</html>
