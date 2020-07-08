package com.jbartek.front.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantProtection {
    @JsonProperty
    private String id;
    @JsonProperty
    private LocalDate dateOfWork;
    @JsonProperty
    private String productName;
    @JsonProperty
    private String protectionType;
    @JsonProperty
    private double dose;
    @JsonProperty
    private String cultivatedPlant;
    @JsonProperty
    private String parcelId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlantProtection)) return false;
        PlantProtection that = (PlantProtection) o;
        return id == that.id &&
                Double.compare(that.dose, dose) == 0 &&
                parcelId == that.parcelId &&
                Objects.equals(dateOfWork, that.dateOfWork) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(protectionType, that.protectionType) &&
                Objects.equals(cultivatedPlant, that.cultivatedPlant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfWork, productName, protectionType, dose, cultivatedPlant, parcelId);
    }
}
