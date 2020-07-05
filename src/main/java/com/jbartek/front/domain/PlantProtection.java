package com.jbartek.front.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantProtection {
    private LocalDate dateOfWork;
    private String productName;
    private String protectionType;
    private double dose;
    private String cultivatedPlant;
    private long parcelId;
}
