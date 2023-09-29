package com.reports.hibernate.model.entity.relations.manyToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class BidirectionalManyToOneChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String childName;

    @OneToMany(mappedBy="child")
    private List<BidirectionalManyToOneParent> parents;
}