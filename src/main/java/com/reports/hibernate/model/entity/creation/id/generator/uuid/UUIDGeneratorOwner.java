package com.reports.hibernate.model.entity.creation.id.generator.uuid;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
public class UUIDGeneratorOwner {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String middleName;

    private String lastName;
}
