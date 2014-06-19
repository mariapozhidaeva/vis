var map;

function initMap(properties, name, style) {

    var bounds = new OpenLayers.Bounds(
        0.1356780529022217, 0.854128897190094, 150.44995880126953, 174.91212272644043
    );
    var epsg_ = new OpenLayers.Projection("EPSG:" + properties.epsg);
    map = new OpenLayers.Map({
        div: "map",
        projection: epsg_,
        displayProjection: epsg_,
        bounds: bounds,
        maxResolution: 200000,
        center: new OpenLayers.LonLat(properties.center_long, properties.center_lat).transform('EPSG:4326', 'EPSG:900913')
    });

    var osm = new OpenLayers.Layer.OSM();
    var gmap = new OpenLayers.Layer.Google("Google Streets");
    var gwc = new OpenLayers.Layer.WMS(
        "Global Imagery",
        "http://maps.opengeo.org/geowebcache/service/wms",
        {layers: "bluemarble"},
        {tileOrigin: new OpenLayers.LonLat(-180, -90)}
    );
    var g_ph =
        new OpenLayers.Layer.Google(
            "Google Physical",
            {type: google.maps.MapTypeId.TERRAIN, numZoomLevels: 22});

    var g_hibr = new OpenLayers.Layer.Google(
        "Google Hybrid",
        {type: google.maps.MapTypeId.HYBRID, numZoomLevels: 22}
    );
    var g_satellite = new OpenLayers.Layer.Google(
        "Google Satellite",
        {type: google.maps.MapTypeId.SATELLITE, numZoomLevels: 22}
    );

    map.addLayers([osm, gmap, g_ph, g_hibr, g_satellite]);
    var layerSwitcher = new OpenLayers.Control.LayerSwitcher();
    layerSwitcher.useLegendGraphics = true;
    map.addControl(layerSwitcher);

    var layer, county;

    function setHTML(response) {
        document.getElementById('nodelist').innerHTML = response.responseText;
    };

    var signals = formLayer(properties.ws, properties.epsg, properties.url, name, style);
    map.addLayers([signals]);
    map.addControl(new OpenLayers.Control.Navigation());
    map.addControl(new OpenLayers.Control.Scale($('scale')));
    map.addControl(new OpenLayers.Control.PanZoomBar());
    map.addControl(new OpenLayers.Control.OverviewMap());
    map.addControl(new OpenLayers.Control.KeyboardDefaults());
    map.addControl(new OpenLayers.Control.ScaleLine());
    map.addControl(new OpenLayers.Control.Attribution());

    var mousePositionCtrl = new OpenLayers.Control.MousePosition({
            prefix: '<a target="_blank" ' +
                'href="http://spatialreference.org/ref/epsg/4326/">' +
                'EPSG:' + properties.epsg + '</a> coordinates: ',
            formatOutput: formatLonlats
        }
    );
    map.addControl(mousePositionCtrl);
}

function callInitMap(epsg, url, ws, layer) {

    var properties = {
        epsg: epsg,
        url: url,
        ws: ws,
        center_long: 31,
        center_lat: 59.67

    }
    initMap(properties, layer, "signals_style_pgups");
}

function formatLonlats(lonLat) {
    var lat = lonLat.lat;
    var long = lonLat.lon;
    var ns = OpenLayers.Util.getFormattedLonLat(lat);
    var ew = OpenLayers.Util.getFormattedLonLat(long, 'lon');
    return ns + ', ' + ew + ' (' + (Math.round(lat * 10000) / 10000) + ', ' + (Math.round(long * 10000) / 10000) + ')';
}

function formLayer(ws, epsg, url, name, style) {
    var signals = new OpenLayers.Layer.WMS(
        ws + ":" + name + " - " + name, url + "/" + ws + "/wms",
        {
            width: '700',
            height: '700',
            srs: 'EPSG:' + epsg,
            layers: ws + ":" + name,
            styles: style,
            transparent: true,
            resotution: 20000,
            format: 'image/png'
        },
        {isBaseLayer: false, singleTile: true, ratio: 1}
    );
    return signals;
}
