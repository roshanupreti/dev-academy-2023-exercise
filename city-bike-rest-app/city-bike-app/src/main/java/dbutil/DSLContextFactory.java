package dbutil;

import lombok.extern.slf4j.Slf4j;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.MappedSchema;
import org.jooq.conf.RenderMapping;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;

import javax.sql.DataSource;

import static dbutil.DataSourceFactory.getDataSourceForDB;
import static org.jooq.impl.DSL.using;

/**
 * Provider class for {@link DSLContext}, to interact with database(s).
 *
 * @author roshan
 */
@Slf4j
public class DSLContextFactory {

    private final Configuration jooqConfig;
    private final DataSource dataSource;

    /**
     * Constructor initiates Settings and Configuration
     */
    public DSLContextFactory(String db) {
        log.info("Initializing DSLContext for {}", db);
        this.dataSource = getDataSourceForDB(db);
        log.info("Datasource info {}", dataSource);
        jooqConfig = new DefaultConfiguration();
        Settings jooqSettings = new Settings()
                .withRenderMapping(new RenderMapping().withSchemata(
                        new MappedSchema().withInput(db).withOutput(db)))
                .withRenderFormatted(true)
                .withExecuteLogging(false);
        jooqConfig.set(jooqSettings);
        jooqConfig.set(SQLDialect.MARIADB);
    }

    /**
     * @return DSLContext
     */
    public DSLContext getDSLContext() {
        return using(jooqConfig.derive(dataSource));
    }
}
