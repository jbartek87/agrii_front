package com.jbartek.front.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parcel)) return false;
        Parcel parcel = (Parcel) o;
        return Double.compare(parcel.area, area) == 0 &&
                Objects.equals(id, parcel.id) &&
                Objects.equals(parcelNumber, parcel.parcelNumber) &&
                Objects.equals(precinct, parcel.precinct) &&
                Objects.equals(soilType, parcel.soilType) &&
                Objects.equals(userId, parcel.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parcelNumber, precinct, soilType, area, userId);
    }
}
