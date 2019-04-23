package com.entertainment.search.tvshows.external;

import com.entertainment.search.tvshows.model.TvShow;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient
@Path("/search")
public interface TvMazeClient {

    @GET
    @Path("/shows")
    @Produces(MediaType.APPLICATION_JSON)
    List<TvShow> getTvShows(@QueryParam("q") String q);


}
