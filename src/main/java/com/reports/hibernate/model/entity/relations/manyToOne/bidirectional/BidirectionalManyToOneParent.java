package com.reports.hibernate.model.entity.relations.manyToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BidirectionalManyToOneParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="child_id")
    private BidirectionalManyToOneChild child;
}
