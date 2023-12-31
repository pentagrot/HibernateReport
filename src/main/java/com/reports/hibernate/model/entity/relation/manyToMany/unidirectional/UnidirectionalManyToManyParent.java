package com.reports.hibernate.model.entity.relation.manyToMany.unidirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UnidirectionalManyToManyParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "parent_child_bridge",
            joinColumns = @JoinColumn(
                    name = "parentId_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "childId_id", referencedColumnName = "id"
            )
    )
    private Set<UnidirectionalManyToManyChild> children;
}
