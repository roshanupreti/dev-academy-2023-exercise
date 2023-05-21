package modules.journey.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import modules.journey.dao.JourneyDao;
import modules.journey.pojo.Journey;

import java.util.Collection;

@Path("journey")
public class JourneyResource {

    @Inject
    private JourneyDao journeyDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Journey> getJourney() {
        return journeyDao.getJourneyCollection();
    }
}
