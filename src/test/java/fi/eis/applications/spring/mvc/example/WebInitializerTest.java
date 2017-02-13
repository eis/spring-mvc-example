package fi.eis.applications.spring.mvc.example;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * There is no test support for web app initializers in Spring, so this is done separately.
 *
 * @see <a href="https://jira.spring.io/browse/SPR-10199">https://jira.spring.io/browse/SPR-10199</a>
 */
public class WebInitializerTest {
    static class JettyStartingListener extends AbstractLifeCycle.AbstractLifeCycleListener {

        private final ServletContext sc;

        public JettyStartingListener(ServletContext sc){
            this.sc = sc;
        }

        @Override
        public void lifeCycleStarting(LifeCycle event) {
            try {
                new WebInitializer().onStartup(sc);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Server server;

    @BeforeClass
    public static void setUp() throws Exception {
        server = new Server();
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        server.setHandler(contextHandler);
        contextHandler.addLifeCycleListener(new JettyStartingListener(contextHandler.getServletContext()));
        server.start();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.stop(); // async call
        server.join(); // we wait for server to stop
    }

    @Test
    public void testWebInitializerUsingEmbeddedJetty() throws Exception {
        Handler handler = server.getHandlers()[0];
        assertTrue(handler instanceof ServletContextHandler);
        FilterHolder[] filterHolders = ((ServletContextHandler)handler).getServletHandler().getFilters();
        assertNotNull(filterHolders);
        assertTrue(filterHolders.length > 0);
        assertTrue(filterHolders[0].getFilter() instanceof CharacterEncodingFilter);
    }
}
