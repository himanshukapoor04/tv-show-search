package com.entertainment.search.tvshows.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvShow {

    private ShowDetails show;
    private double score;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ShowDetails {
        private String name;
        private String premiered;
        private Rating rating;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Rating {
        private Double average;

    }
}

