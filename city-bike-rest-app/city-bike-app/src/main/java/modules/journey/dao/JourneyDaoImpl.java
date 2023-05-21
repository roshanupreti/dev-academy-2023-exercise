package modules.journey.dao;

import dbutil.DSLContextFactory;
import modules.journey.pojo.Journey;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import java.util.Collection;

import static jooq_generated.tables.Journey.JOURNEY;

@Service
@PerLookup
public class JourneyDaoImpl implements JourneyDao{

    @Override
    public Collection<Journey> getJourneyCollection() {
        var ctx = new DSLContextFactory("client").getDSLContext();
        return ctx.selectFrom(JOURNEY)
                .limit(20)
                .fetchInto(Journey.class);
    }
}
