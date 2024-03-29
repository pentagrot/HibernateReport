package com.reports.hibernate.entity.creation.id;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.creation.id.generator.sequence.customised.CustomisedSequenceIdGeneratorOwner;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.creation.id.generator.sequence.customised") // scan only required entities
@DisplayName("Entity with pooled sequence id generator")
class CustomisedSequenceIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get multiple entities")
    void createAndGetMultipleEntities() {
        int entitiesCount = 10;
        List<CustomisedSequenceIdGeneratorOwner> users = new ArrayList<>();
        List<Long> userIds = new ArrayList<>();
        for (int i = 0; i < entitiesCount; i++) {
            CustomisedSequenceIdGeneratorOwner user = new CustomisedSequenceIdGeneratorOwner();
            user.setFirstName("First " + i);
            user.setMiddleName("Middle " + i);
            user.setLastName("Last " + i);
            users.add(user);
            userIds.add((Long) session.save(user));
        }
        flushAndClear();
        List<CustomisedSequenceIdGeneratorOwner> fetchedUsers = new ArrayList<>();
        for (long id : userIds) {
            fetchedUsers.add(session.get(CustomisedSequenceIdGeneratorOwner.class, id));
        }
        // increment size = 5 and default initial value = 2
        // next val number1 = selected [2]
        // next val number2 = selected [3, 4, 5, 6, 7]
        // next val number3 = selected [8, 9, 10, 11, 12]
        assertAll(
                () -> AssertQueryCount.assertNextValCount(3),
                () -> AssertQueryCount.assertInsertCount(entitiesCount),
                () -> AssertQueryCount.assertSelectCount(entitiesCount),
                () -> assertEquals(users, fetchedUsers)
        );
    }

}
