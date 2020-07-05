package com.jbartek.front.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parcel {
    @JsonProperty
    private long id;
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

    public long getId() {
        return id;
    }

    public String getParcelNumber() {
        return parcelNumber;
    }

    public String getPrecinct() {
        return precinct;
    }

    public String getSoilType() {
        return soilType;
    }

    public double getArea() {
        return area;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parcel)) return false;
        Parcel parcel = (Parcel) o;
        return id == parcel.id &&
                Double.compare(parcel.area, area) == 0 &&
                userId == parcel.userId &&
                Objects.equals(parcelNumber, parcel.parcelNumber) &&
                Objects.equals(precinct, parcel.precinct) &&
                soilType == parcel.soilType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parcelNumber, precinct, soilType, area, userId);
    }



}
