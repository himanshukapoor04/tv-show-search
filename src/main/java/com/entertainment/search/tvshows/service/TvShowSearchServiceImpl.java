package com.entertainment.search.tvshows.service;

import com.entertainment.search.tvshows.api.v1.TvShowDto;
import com.entertainment.search.tvshows.external.TvMazeClient;
import com.entertainment.search.tvshows.model.TvShow;

import lombok.val;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TvShowSearchServiceImpl implements TvShowSearchService {

    @Inject
    @RestClient
    private TvMazeClient tvMazeClient;

    @Override
    public List<TvShowDto> getTopNShows(String showQueryString, int number) {
        val shows = tvMazeClient.getTvShows(showQueryString);
        return handleTvMazeResponse(number, shows);

    }

    private List<TvShowDto> handleTvMazeResponse(int number,
                                                 List<TvShow> shows) {
        return shows.parallelStream()
                .filter(this::areShowDetailsPresent)
                .map(this::buildTvShowDto)
                .sorted(this::compareShows)
                .limit(number)
                .collect(Collectors.toList());
    }

    private boolean areShowDetailsPresent(TvShow show) {
        return show.getShow() != null;
    }

    private TvShowDto buildTvShowDto(TvShow show) {
        return TvShowDto.builder()
                .score(show.getScore())
                .premiered(show.getShow().getPremiered())
                .name(show.getShow().getName())
                .rating(show.getShow().getRating() != null ? show.getShow()
                        .getRating().getAverage() :
                        null)
                .build();
    }

    private int compareShows(TvShowDto show1, TvShowDto show2) {
        Double lhs = show1.getRating() != null ? show1.getRating() : 0;
        Double rhs = show2.getRating() != null ? show2.getRating() : 0;
        return rhs.compareTo(lhs);
    }
}
