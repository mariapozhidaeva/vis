package vis

class TestController {

    def index() {
        render(controllerName: "visualization", view: "index", model: [message: "hello111"]);  //layout: "ajax")
    }

    def iata = {
        // def iata = params.id?.toUpperCase() ?: "NO IATA"
        //http://www.ibm.com/developerworks/web/library/j-grails11188/index.html
        def airport = new Calc(name: "NAME", description: "DESCR")
        System.out.println("here");
        model: [name: "TEST MESSAGE"]
        // render airport as JSON
        render(controllerName: "visualization", view: "index", params: [message: "OKi poki"]);  //layout: "ajax")

    }
/** return from property*/

    def getEPSG = {
        return 4326;
    }

    def getURL = {
        return "http://localhost:9999/geoserver";
    }

    def getWorkspace = {
        return "pgups_ws";
    }
}
