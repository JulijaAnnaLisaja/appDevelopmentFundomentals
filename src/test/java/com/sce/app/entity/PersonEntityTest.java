package com.sce.app.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static com.sce.app.common.ValuesCommon.EXPECTED_AGE;
import static com.sce.app.common.ValuesCommon.EXPECTED_ID;
import static com.sce.app.common.ValuesCommon.EXPECTED_IS_LEGAL;
import static com.sce.app.common.ValuesCommon.EXPECTED_NAME;
import static com.sce.app.common.ValuesCommon.person;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonEntityTest {

    private static final Logger logger = LogManager.getLogger(PersonEntityTest.class);

    @Test
    public void testUserDetails() {

        assertThat(person.getId()).isEqualTo(EXPECTED_ID);
        assertThat(person.getName()).isEqualTo(EXPECTED_NAME);
        assertThat(person.getAge()).isEqualTo(EXPECTED_AGE);
        assertThat(person.isLegal()).isEqualTo(EXPECTED_IS_LEGAL);
    }

    @Test
    public void testUserDetailsWithEmptyConstructor() {

        PersonEntity person = new PersonEntity();

        person.setId(EXPECTED_ID);
        person.setName(EXPECTED_NAME);
        person.setLegal(EXPECTED_IS_LEGAL);
        person.setAge(EXPECTED_AGE);

        logger.debug(person);

        assertThat(person.getId()).isEqualTo(EXPECTED_ID);
        assertThat(person.getName()).isEqualTo(EXPECTED_NAME);
        assertThat(person.getAge()).isEqualTo(EXPECTED_AGE);
        assertThat(person.isLegal()).isEqualTo(EXPECTED_IS_LEGAL);
    }

}
