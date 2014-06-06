package vis

import entity.CalculationOutput
import grails.validation.Validateable
import org.codehaus.groovy.grails.commons.ApplicationHolder
import service.IVisualizationService
import service.impl.VisualizationService

@Validateable  // todo ?
class VisualizationController {

// todo:: как инъектить?
// todo:: как получить параметры?
// todo:: нормально исключения

    def visualize(CalculationOutput co) {

        try {
            System.out.println(co.signals.size());
            System.out.println("co");

            Map<String, String> map = new HashMap();
            map.put("password", ApplicationHolder.application.metadata['geoserver.password']);
            map.put("user", ApplicationHolder.application.metadata['geoserver.user']);
            map.put("url", ApplicationHolder.application.metadata['geoserver.url']);
            map.put("ws", ApplicationHolder.application.metadata['geoserver.workspace']);
            map.put("geoserver.temp.storage", ApplicationHolder.application.metadata['geoserver.temp.storage']);

            IVisualizationService visualizationService = new VisualizationService();
            visualizationService.visualize(map, co);

        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    def map() {
        TestController tc = new TestController();

        String epsg = ApplicationHolder.application.metadata['geoserver.map.srid'];
        String ws = ApplicationHolder.application.metadata['geoserver.workspace'];
        String url = ApplicationHolder.application.metadata['geoserver.url'];
        System.out.println(epsg + ws + url);

        render(controllerName: "visualization", view: "map", model: [message: "hello111", epsg: epsg, url: url, ws: ws]);

    }
}

