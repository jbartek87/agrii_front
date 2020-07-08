package com.jbartek.front.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accountancy)) return false;
        Accountancy that = (Accountancy) o;
        return id == that.id &&
                productQuantity == that.productQuantity &&
                vatRate == that.vatRate &&
                userId == that.userId &&
                Objects.equals(dateOfEvent, that.dateOfEvent) &&
                Objects.equals(typeOfEvent, that.typeOfEvent) &&
                Objects.equals(invoiceNumber, that.invoiceNumber) &&
                Objects.equals(product, that.product) &&
                Objects.equals(netUnitPrice, that.netUnitPrice) &&
                Objects.equals(netTotalSum, that.netTotalSum) &&
                Objects.equals(totalSum, that.totalSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfEvent, typeOfEvent, invoiceNumber, product, productQuantity, netUnitPrice, vatRate, netTotalSum, totalSum, userId);
    }
}
