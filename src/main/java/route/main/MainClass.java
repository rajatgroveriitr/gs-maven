package route.main;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import route.SimpleRouteBuilder;

/**
 * Created by rgrover on 25/11/18.
 */
public class MainClass {

    public static void main(String[] args) {
        SimpleRouteBuilder routeBuilder = new SimpleRouteBuilder();
        CamelContext ctx = new DefaultCamelContext();
        try{
            ctx.addRoutes(routeBuilder);
            ctx.start();
            Thread.sleep(5 * 60 * 1000);
            ctx.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
