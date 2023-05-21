package utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simple Utility class that reads from properties file and provides a resource bundle.
 *
 * @author Roshan
 */
@Slf4j
public final class PropsUtil {

    private static final String CONFIG_FOLDER = "config";
    private static final String DB_PROP_NAME = "database";


    /* Private Constructor. */
    private PropsUtil() {
    }

    /**
     * Prepares and returns a ResourceBundle for the file passed in method body.
     *
     * @return ResourceBundle
     * @throws MalformedURLException, in case of failure to form URLs.
     */
    public static ResourceBundle getDbResourceBundle() throws MalformedURLException {
        File file = FileUtils.getFile(CONFIG_FOLDER);
        URL[] urls = {file.toURI().toURL()};
        var string = Arrays.stream(urls).map(URL::toString).collect(Collectors.joining(","));
        log.info("This is it {}", string);
        ClassLoader loader = new URLClassLoader(urls);
        return ResourceBundle.getBundle(DB_PROP_NAME, Locale.getDefault(), loader);
    }
}
