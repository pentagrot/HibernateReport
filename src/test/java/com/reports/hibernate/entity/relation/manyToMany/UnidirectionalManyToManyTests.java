package com.reports.hibernate.entity.relation.manyToMany;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.relation.manyToMany.unidirectional.UnidirectionalManyToManyPet;
import com.reports.hibernate.model.entity.relation.manyToMany.unidirectional.UnidirectionalManyToManyOwner;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.HashSet;
import java.util.Set;

@EntityScan("com.reports.hibernate.model.entity.relation.manyToMany.unidirectional") // scan only required entities
@DisplayName("Unidirectional ManyToMany relationhip")
class UnidirectionalManyToManyTests extends BaseTest {

    @Test
    @DisplayName("One sided saving of parents")
    void oneSidedSaveOfParent() {
        int parentsAmount = 3;
        int childrenAmount = 3;
        Set<UnidirectionalManyToManyPet> pets = generatePets(parentsAmount);
        Set<UnidirectionalManyToManyOwner> owners = generateOwners(childrenAmount);

        for (UnidirectionalManyToManyOwner parent : owners) {
            parent.setPets(pets);
            session.persist(parent);
            session.flush();
        }

        assertAll(
                () -> AssertQueryCount.assertInsertCount(
                        childrenAmount + parentsAmount + childrenAmount * childrenAmount)
        );
    }

    private Set<UnidirectionalManyToManyPet> generatePets(int amount) {
        Set<UnidirectionalManyToManyPet> result = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            UnidirectionalManyToManyPet pet = new UnidirectionalManyToManyPet();
            // id autogenerated
            pet.setName("Pet number " + i);
            result.add(pet);
        }
        return result;
    }

    private Set<UnidirectionalManyToManyOwner> generateOwners(int amount) {
        Set<UnidirectionalManyToManyOwner> result = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            UnidirectionalManyToManyOwner owner = new UnidirectionalManyToManyOwner();
            // id autogenerated
            owner.setName("Owner number " + i);
            result.add(owner);
        }
        return result;
    }

}
