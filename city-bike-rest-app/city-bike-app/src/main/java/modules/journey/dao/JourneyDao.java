package modules.journey.dao;

import modules.journey.pojo.Journey;
import org.jvnet.hk2.annotations.Contract;

import java.util.Collection;

@Contract
public interface JourneyDao {

    Collection<Journey> getJourneyCollection();
}
