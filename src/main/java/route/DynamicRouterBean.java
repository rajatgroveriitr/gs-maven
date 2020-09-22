package route;

import org.apache.camel.Exchange;
import org.apache.camel.Header;

/**
 * Created by rgrover on 25/11/18.
 */
public class DynamicRouterBean {
    public String route(String body, @Header(Exchange.SLIP_ENDPOINT) String previousRoute) {
        if (previousRoute == null) {
            return "direct://route3";
            // check the body content and decide route
        } else if (body.toString().equals("javainuse in route 3")) {
            return "direct://route2";
            // check the body content and decide route
        } else if (body.toString().equals("javainuse in route 3 in route 2")) {
            return "direct://route1";
        } else {
            return null;
        }
    }
}
