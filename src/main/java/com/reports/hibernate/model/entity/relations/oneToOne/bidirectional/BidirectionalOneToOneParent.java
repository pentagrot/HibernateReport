package com.reports.hibernate.model.entity.relations.oneToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BidirectionalOneToOneParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @OneToOne(cascade = CascadeType.PERSIST)
    private BidirectionalOneToOneChild child;
}
