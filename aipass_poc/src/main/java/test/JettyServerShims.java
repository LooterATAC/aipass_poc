package test;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHolder;

/**
 * Created by q.a.guo on 2/9/2017.
 */
public class JettyServerShims implements ServerShims{

    private Server server = null;

    private int port = 9250;

    public JettyServerShims() {
    }

    public void start() {
        server = new Server(port);
        final org.mortbay.jetty.servlet.Context ctxRest = new org.mortbay.jetty.servlet.Context(server, "/rest");
        final ServletHolder jerseyServletHolder = ctxRest.addServlet(ServletContainer.class, "/*");
        jerseyServletHolder.setInitParameter("com.sun.jersey.config.property.resourceConfigClass","com.sun.jersey.api.core.PackagesResourceConfig");
        jerseyServletHolder.setInitParameter("com.sun.jersey.config.property.packages","test.action");
        jerseyServletHolder.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        ctxRest.addServlet(jerseyServletHolder, "/*");
        try {
            server.start();
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {

    }

    public static void main(String[] args) {
//        new JettyServerShims().start();
//        System.out.println("started");
        String CONNECT_ADDR = "52.193.151.211:2181,52.199.131.58:2181,52.199.151.21:2181";
        int SESSION_TIMEOUT = 5000;

        ZkClient zkClient = new ZkClient(new ZkConnection(CONNECT_ADDR), SESSION_TIMEOUT);
        String cData = zkClient.readData("/hiveserver2");
        System.out.println(cData);
    }
}
