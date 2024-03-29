package com.reports.hibernate.entity.creation.id;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.creation.id.embedded.EmbeddedIdOwner;
import com.reports.hibernate.model.entity.creation.id.embedded.OwnerId;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.creation.id.embedded") // scan only required entities
@DisplayName("Entity with embedded id")
class EmbeddedIdTests extends BaseTest {

    @Test
    @DisplayName("Create and get entity")
    void createAndGetEntity() {
        OwnerId id = new OwnerId();
        id.setFirstName("First One");
        id.setMiddleName("Middle One");
        id.setLastName("Last One");
        EmbeddedIdOwner user = new EmbeddedIdOwner();
        user.setId(id);
        user.setAddress("Address One");
        session.save(user);
        flushAndClear();
        EmbeddedIdOwner fetchedUser = session.get(EmbeddedIdOwner.class, id);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

}
