package com.jbartek.front.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldWork {
    @JsonProperty
    private long id;
    @JsonProperty
    private LocalDate dateOfWork;
    @JsonProperty
    private String cultivatedPlant;
    @JsonProperty
    private String typeOfWork;
    @JsonProperty
    private String comments;
    @JsonProperty
    private String parcelId;


}
