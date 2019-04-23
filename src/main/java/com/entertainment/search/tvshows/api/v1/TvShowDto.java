package com.entertainment.search.tvshows.api.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;

/**
 * DTO class for the response of TV show
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TvShowDto {

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("score")
    private double score;

    @JsonbProperty("premiered")
    private String premiered;

    @JsonbTransient
    private Double rating;


}
