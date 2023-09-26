package com.reports.hibernate.model.enitycreation.id.generator.sequence.customised;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class CustomisedSequenceIdGeneratorUser {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    @SequenceGenerator(
            name = "post_sequence",
            allocationSize = 5,
            initialValue = 2
    )
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;
}
