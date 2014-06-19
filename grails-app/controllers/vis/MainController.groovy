package vis

import entity.CalculationOutput
import entity.Coordinates
import entity.Signal

class MainController {

    def start() {

        CalculationOutput co = new CalculationOutput();
        List list = new ArrayList<Signal>(50);
        Random r = new Random(System.currentTimeMillis());
        // stub collection of signals
        for (int i = 0; i < 50; i++) {
            Signal s = new Signal(new Coordinates((float) (r.nextFloat() * 10f + 51), (float) (r.nextFloat() * 50f + 31)), (float) (r.nextFloat() % 1));
            list.add(s);
        }
        co.setSignals(list);
        VisualizationController vc = new VisualizationController();
        String name = vc.visualize(co);
        chain(controller: "visualization", action: "map", params: [name: name])
    }
}
