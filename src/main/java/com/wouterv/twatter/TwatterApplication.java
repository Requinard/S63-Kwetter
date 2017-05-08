package com.wouterv.twatter; /**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
        import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
        import org.glassfish.jersey.server.ResourceConfig;

        import javax.ws.rs.ApplicationPath;
        import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class TwatterApplication extends Application{

        public TwatterApplication() {
                // Create JAX-RS application.

                final Application application = new ResourceConfig()
                        .packages("org.glassfish.jersey.examples.linking")
                        .register(DeclarativeLinkingFeature.class);
        }
}
