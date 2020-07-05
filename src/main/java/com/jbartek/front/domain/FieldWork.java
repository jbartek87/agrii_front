package com.jbartek.front.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private long id;
    private LocalDate dateOfWork;
    private String cultivatedPlant;
    private String typeOfWork;
    private String comments;
    private long parcelId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldWork)) return false;
        FieldWork fieldWork = (FieldWork) o;
        return id == fieldWork.id &&
                parcelId == fieldWork.parcelId &&
                Objects.equals(dateOfWork, fieldWork.dateOfWork) &&
                Objects.equals(cultivatedPlant, fieldWork.cultivatedPlant) &&
                Objects.equals(typeOfWork, fieldWork.typeOfWork) &&
                Objects.equals(comments, fieldWork.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfWork, cultivatedPlant, typeOfWork, comments, parcelId);
    }
}
