package com.entertainment.search.tvshows.service;


import com.entertainment.search.tvshows.api.v1.TvShowDto;

import java.util.List;

public interface TvShowSearchService {

    /**
     * Method to fetch top N shows from the TVMaze API
     * @param showQueryString show to search for
     * @param number of shows to show
     * @return {@link List< TvShowDto >} list of shows
     */
    List<TvShowDto> getTopNShows(String showQueryString,
                                 int number);
}
