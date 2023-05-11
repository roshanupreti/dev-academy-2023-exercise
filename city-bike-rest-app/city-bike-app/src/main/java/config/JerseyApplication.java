package config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class JerseyApplication extends ResourceConfig {

    public JerseyApplication() {
        register(AutoScanFeature.class);
        packages(
                "modules",
                "exception",
                "filter",
                "provider"
        );
    }
}
