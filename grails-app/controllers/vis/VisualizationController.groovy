package vis

import entity.CalculationOutput
import grails.validation.Validateable
import org.codehaus.groovy.grails.commons.ApplicationHolder
import service.IVisualizationService
import service.impl.VisualizationService

@Validateable
class VisualizationController {

    def visualize(CalculationOutput co) {
        String layer = "";
        try {
            Map<String, String> map = new HashMap();
            map.put("password", ApplicationHolder.application.metadata['geoserver.password']);
            map.put("user", ApplicationHolder.application.metadata['geoserver.user']);
            map.put("url", ApplicationHolder.application.metadata['geoserver.url']);
            map.put("ws", ApplicationHolder.application.metadata['geoserver.workspace']);
            map.put("geoserver.temp.storage", ApplicationHolder.application.metadata['geoserver.temp.storage']);
            IVisualizationService visualizationService = new VisualizationService();
            layer = visualizationService.visualize(map, co);
        } catch (Exception e) {
            e.printStackTrace();
        };
        return layer;
    }

    def map(String name) {

        String epsg = ApplicationHolder.application.metadata['geoserver.map.srid'];
        String ws = ApplicationHolder.application.metadata['geoserver.workspace'];
        String url = ApplicationHolder.application.metadata['geoserver.url'];
        [name: params.name]
        render(controllerName: "visualization", view: "map", model: [epsg: epsg, url: url, ws: ws, name: name]);
    }
}

