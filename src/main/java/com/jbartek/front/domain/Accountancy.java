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
public class Accountancy {
    @JsonProperty
    private String id;
    @JsonProperty
    private LocalDate dateOfEvent;
    @JsonProperty
    private String typeOfEvent;
    @JsonProperty
    private String invoiceNumber;
    @JsonProperty
    private String product;
    @JsonProperty
    private String productQuantity;
    @JsonProperty
    private String netUnitPrice;
    @JsonProperty
    private String vatRate;
    @JsonProperty
    private String netTotalSum;
    @JsonProperty
    private String totalSum;
    @JsonProperty
    private String userId;


}
