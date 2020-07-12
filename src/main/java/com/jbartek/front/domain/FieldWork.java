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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldWork)) return false;
        FieldWork fieldWork = (FieldWork) o;
        return id == fieldWork.id &&
                Objects.equals(dateOfWork, fieldWork.dateOfWork) &&
                Objects.equals(cultivatedPlant, fieldWork.cultivatedPlant) &&
                Objects.equals(typeOfWork, fieldWork.typeOfWork) &&
                Objects.equals(comments, fieldWork.comments) &&
                Objects.equals(parcelId, fieldWork.parcelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfWork, cultivatedPlant, typeOfWork, comments, parcelId);
    }
}
