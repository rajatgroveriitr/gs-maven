package route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by rgrover on 25/11/18.
 */
public class SimpleRouteBuilder extends RouteBuilder {

    public static String PREFIX_DIR = "/tmp/";//"/Users/rgrover/SampleRepos/gs-maven/src/resource/";
    @Override
    public void configure() throws Exception {
        System.out.println("I am in SimpleRouteBuilder:configure");
//        from("file://" + PREFIX_DIR + "src?noop=true").to("file://" + PREFIX_DIR + "dest");

        from("file:" + PREFIX_DIR + "src?noop=true").split().tokenize("\n").dynamicRouter(method(DynamicRouterBean.class, "route"));

        from("direct:route1").process(new Processor() {
            public void process(Exchange  exchange) {
                String body = exchange.getIn().getBody().toString();
                body = body + " in route 1";
                System.out.println(body);
                exchange.getIn().setBody(body);
            }
        });

        from("direct:route2").process(new Processor() {
            public void process(Exchange exchange) {
                String body = exchange.getIn().getBody().toString();
                body = body + " in route 2";
                System.out.println(body);
                exchange.getIn().setBody(body);
            }
        });

        from("direct:route3").process(new Processor() {
            public void process(Exchange exchange) {
                String body = exchange.getIn().getBody().toString();
                body = body + " in route 3";
                exchange.getIn().setBody(body);
                System.out.println(body);
            }
        });
    }
}
