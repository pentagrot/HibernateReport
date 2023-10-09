package com.reports.hibernate.model.entity.collection.map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MapCollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "collectionEntity", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @MapKeyColumn(name = "name")
    private Map<String, MapReferencedEntity> referencedEntities;

}
