package com.reports.hibernate.entity.creation.id;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.creation.id.generator.uuid.UUIDGeneratorOwner;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;

@EntityScan("com.reports.hibernate.model.entity.creation.id.generator.uuid") // scan only required entities
@DisplayName("Entity with uuid id generator")
class UUIDIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void createAndGetEntity() {
        UUIDGeneratorOwner user = new UUIDGeneratorOwner();
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        UUID id = (UUID) session.save(user);
        flushAndClear();
        UUIDGeneratorOwner fetchedUser = session.get(UUIDGeneratorOwner.class, id);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

}
