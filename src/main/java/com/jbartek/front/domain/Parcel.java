package com.jbartek.front.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parcel {
    @JsonProperty
    private String id;
    @JsonProperty
    private String parcelNumber;
    @JsonProperty
    private String precinct;
    @JsonProperty
    private String soilType;
    @JsonProperty
    private double area;
    @JsonProperty
    private String userId;


}
