package config;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.Populator;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ClasspathDescriptorFileFinder;
import org.glassfish.hk2.utilities.DuplicatePostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * This class is used for the dynamic discovery of classes, for injection.
 *
 * @author roshan
 */
@Slf4j
public class AutoScanFeature implements Feature {

    @Inject
    ServiceLocator serviceLocator;

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoScanFeature.class.getName());

    /**
     * A call-back method called when the feature is to be enabled in a given runtime configuration scope.
     * <p>
     * The responsibility of the feature is to properly update the supplied runtime configuration context and return
     * {@code true} if the feature was successfully enabled or {@code false} otherwise.
     * <p>
     * Note that under some circumstances the feature may decide not to enable itself, which is indicated by returning
     * {@code false}. In such case the configuration context does not add the feature to the collection of enabled features
     * and a subsequent call to {@link Configuration#isEnabled(Feature)} or {@link Configuration#isEnabled(Class)} method
     * would return {@code false}.
     * </p>
     *
     * @param context configurable context in which the feature should be enabled.
     * @return {@code true} if the feature was successfully enabled, {@code false} otherwise.
     */
    @Override
    public boolean configure(FeatureContext context) {
        DynamicConfigurationService dcs =
                serviceLocator.getService(DynamicConfigurationService.class);
        Populator populator = dcs.getPopulator();
        try {
            // Populator - populate HK2 service locators from inhabitants files
            // ClasspathDescriptorFileFinder - find files from META-INF/hk2-locator/default
            populator.populate(
                    new ClasspathDescriptorFileFinder(this.getClass().getClassLoader()),
                    new DuplicatePostProcessor());

        } catch (IOException | MultiException e) {
            LOGGER.error("One or more exceptions were caught while discovering the classes dynamically: {}", e.getMessage());
        }
        return true;
    }
}
