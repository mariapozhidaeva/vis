package vis

import entity.CalculationOutput
import grails.validation.Validateable
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

            IVisualizationService visualizationService = new VisualizationService();
            visualizationService.visualize(co);

        } catch (Exception e) {
            e.printStackTrace();
        };
        //redirect(action: "map")
        //, model: [name: "TEST MESSAGE"]
        // render(controllerName: "visualization", view: "map")
    }


    def map() {
        [message: "OK"];
    }
}

