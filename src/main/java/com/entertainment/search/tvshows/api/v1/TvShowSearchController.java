package com.entertainment.search.tvshows.api.v1;

import com.entertainment.search.tvshows.service.TvShowSearchService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/tv-search")
@Slf4j
public class TvShowSearchController {

    @Inject
    TvShowSearchService tvShowSearchService;

    @GET
    @Path("/shows")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TvShowDto> getTopThreeShows(@QueryParam("q") @NotNull String showName,
                                            @QueryParam("results") @NotNull int noOfResults) {
        return tvShowSearchService.getTopNShows(showName, noOfResults);
    }
}
