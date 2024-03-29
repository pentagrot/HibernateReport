package com.reports.hibernate.model.entity.creation.id.generator.table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
public class TableIdGeneratorOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;
}
