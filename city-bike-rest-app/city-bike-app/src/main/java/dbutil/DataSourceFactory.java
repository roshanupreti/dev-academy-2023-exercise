package dbutil;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import exception.ApplicationException;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toMap;
import static utils.PropsUtil.getDbResourceBundle;

/**
 * A simple utility class to read database properties file and create a datasource based on
 * the given database name.
 *
 * @author Roshan
 */
@Slf4j
public final class DataSourceFactory {

    private DataSourceFactory() {}

    private static final Map<String, DataSource> DB_DATASOURCE_MAP = new ConcurrentHashMap<>();

    public static DataSource getDataSourceForDB(String db) {
        if (StringUtils.isEmpty(db)) {
            throw new ApplicationException(500, "Null or empty db-name specified");
        }
        if (!DB_DATASOURCE_MAP.containsKey(db)) {
            addToDataSourceMap(db);
        }
        return DB_DATASOURCE_MAP.get(db);
    }

    /**
     * Retrieve a datasource for the specified database, and add to the datasource map.
     *
     * @param db {@link String}
     */
    private static void addToDataSourceMap(String db) {
        try {
            ResourceBundle rb = getDbResourceBundle();
            Map<String, String> configDataMap = Arrays.stream(rb.getString(db)
                    .split(","))
                    .map(e -> e.split("="))
                    .collect(toMap(keyValue -> keyValue[0].trim(),
                            keyValue -> keyValue.length > 1 ? keyValue[1].trim() : "",
                            (a, b) -> b));
            if (!MapUtils.isEmpty(configDataMap)) {
                // Prepare a datasource object for the db-name, and add to the map.
                DataSource dataSource = createDataSource(configDataMap);
                DB_DATASOURCE_MAP.put(db, dataSource);
            }
        } catch (MalformedURLException e) {
            throw new ApplicationException(500, e.getMessage());
        }
    }


    /**
     * @param parametersMap {@link Map}
     * @return {@link HikariDataSource} based on the database String passed as parameter.
     */
    private static HikariDataSource createDataSource(Map<String, String> parametersMap) {
        log.info("parameters map {}", parametersMap);
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl(parametersMap.get("uri"));
        config.setUsername(parametersMap.get("uname"));
        config.setPassword(parametersMap.get("pwd"));
        log.info("The url is {}", config.getJdbcUrl());
        return new HikariDataSource(config);
    }
}
