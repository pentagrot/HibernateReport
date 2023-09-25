package com.reports.hibernate.entitycreation.id.generator.auto;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.enitycreation.id.generator.auto.AutoIdGeneratorUser;
import com.reports.hibernate.model.enitycreation.minimalsettings.MinimalSettingsUser;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.enitycreation.id.generator.auto") // scan only required entities
@DisplayName("Entity with auto id generator")
class AutoIdGeneratorTests extends BaseTest {

    @Test
    @DisplayName("Entity saving with all fields set")
    void entityWithAllFields() {
        AutoIdGeneratorUser user = new AutoIdGeneratorUser();
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        long id = (long) session.save(user);
        flushAndClear();
        AutoIdGeneratorUser fetchedUser = session.get(AutoIdGeneratorUser.class, id);
        assertAll(
                () -> AssertQueryCount.assertNextValCount(1),
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

}