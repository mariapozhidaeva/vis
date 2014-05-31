var map;

window.onload = function () {
    var bounds = new OpenLayers.Bounds(
        0.1356780529022217, 0.854128897190094, 99.44995880126953, 14.91212272644043
    );
    var options = {
        controls: [],
        maxExtent: bounds,
        maxResolution: 0.0002,
        projection: "EPSG:4269",
        units: 'degrees'
    };


    map = new OpenLayers.Map({
        div: "map",
        projection: new OpenLayers.Projection("EPSG:900913"),
        bounds: bounds
    });

    var osm = new OpenLayers.Layer.OSM();
    var gmap = new OpenLayers.Layer.Google("Google Streets");

    map.addLayers([osm, gmap]);

    map.addControl(new OpenLayers.Control.LayerSwitcher());

    map.setCenter(
        new OpenLayers.LonLat(10.2, 48.9).transform(
            new OpenLayers.Projection("EPSG:4326"),
            map.getProjectionObject()
        ),
        5
    );

    var layer, county;

    function setHTML(response) {
        document.getElementById('nodelist').innerHTML = response.responseText;
    };


    layer = new OpenLayers.Layer.WMS(
        "topp:CaptiveHazardousWasteOperations2008_01 - Untiled",
        "http://localhost:9999/geoserver/topp/wms",
        {
            width: '1500',
            srs: 'EPSG:4326',
            layers: 'topp:states',
            height: '312',
            styles: 'population',
            transparent: true,
            format: 'image/png'
        },
        {isBaseLayer: false, singleTile: true, ratio: 1}
    );
    county = new OpenLayers.Layer.WMS(
        "topp:PA_COUNTY - Untiled", "http://localhost:9999/geoserver/topp/wms",
        {   width: '1500',
            srs: 'EPSG:4269',
            layers: 'topp:PA_COUNTY',
            height: '328',
            styles: '',
            format: 'image/png'
        },
        {singleTile: true, ratio: 1}
    );
    //http://localhost:9999/geoserver/mary_ws2/wms?service=WMS&version=1.1.0&
    //request=GetMap&layers=mary_ws2:output&styles=&bbox=0.1356780529022217,0.854128897190094,99.44995880126953,
    //14.91212272644043&width=2331&height=330&srs=EPSG:2000&format=application/openlayers
    var sign = new OpenLayers.Layer.WMS(
        "mary_ws2:output - output", "http://localhost:9999/geoserver/mary_ws2/wms",
        {   width: '2330',
            height: '330',
            srs: 'EPSG:2000',
            layers: 'mary_ws2:output_signal',

            styles: 'point_style',
            transparent: true,
            format: 'image/png'
        },
        {isBaseLayer: false, singleTile: true, ratio: 1}
    );
    //	 var osm = new OpenLayers.Layer.OSM();
    //   var gmap = new OpenLayers.Layer.Google("Google Streets", {visibility: false});
    // map.addLayers([ osm]);


    map.addLayers([layer, county, sign]);
    // setup controls and initial zooms
    map.addControl(new OpenLayers.Control.Navigation());
    map.addControl(new OpenLayers.Control.Scale($('scale')));
    map.addControl(new OpenLayers.Control.MousePosition({element: $('location')}));
    map.addControl(new OpenLayers.Control.PanZoomBar());
    map.zoomToExtent(bounds);
    // support GetFeatureInfo
    map.events.register('click', map, function (e) {
        document.getElementById('nodelist').innerHTML = "Loading... please wait...";
        var url = map.layers[1].getFullRequestString(
            {
                REQUEST: "GetFeatureInfo",
                EXCEPTIONS: "application/vnd.ogc.se_xml",
                BBOX: map.getExtent().toBBOX(),
                X: e.xy.x,
                Y: e.xy.y,
                INFO_FORMAT: 'text/html',
                QUERY_LAYERS: [map.layers[0].params.LAYERS],
                FEATURE_COUNT: 50,
                WIDTH: map.size.w,
                HEIGHT: map.size.h
            },
            "http://localhost:9999/geoserver/wms"
        );
        OpenLayers.loadURL(url, '', this, setHTML, setHTML);
        OpenLayers.Event.stop(e);
    });

}


function getParams(l2) {

       alert(l2);
}
