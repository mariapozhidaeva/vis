package vis

import entity.CalculationOutput
import entity.Coordinates
import entity.Signal

class MainController {

    def start() {

        CalculationOutput co = new CalculationOutput();
        List list = new ArrayList<Signal>(500); // stub collection of signals
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 500; i++) {
            Signal s = new Signal(new Coordinates((float) (r.nextFloat() * 100), (float) (r.nextFloat() * 15f)), (float) (r.nextFloat() % 1));
            list.add(s);
        }
        co.setSignals(list);
        System.out.println("FORMED1" + co.signals.size());

        VisualizationController vc = new VisualizationController();
        vc.visualize(co);
        chain(controller: "visualization", action: "map")  // todo::
    }
}
