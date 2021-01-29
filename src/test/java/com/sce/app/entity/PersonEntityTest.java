package com.sce.app.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static com.sce.app.common.ValuesCommon.EXPECTED_AGE_22;
import static com.sce.app.common.ValuesCommon.EXPECTED_ID_1;
import static com.sce.app.common.ValuesCommon.EXPECTED_IS_LEGAL_TRUE;
import static com.sce.app.common.ValuesCommon.EXPECTED_NAME_IVAN;
import static com.sce.app.common.ValuesCommon.getPerson_Ivan;
import static com.sce.app.common.ValuesCommon.person;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Person Entity test class definition.
 *
 * @author julija.anna.lisaja@accenture.com
 */
public class PersonEntityTest {

    private static final Logger logger = LogManager.getLogger(PersonEntityTest.class);

    @Test
    public void testUserDetails_WithAllArgsConstructorAndGetters() {
        assertThat(person.getId()).isEqualTo(EXPECTED_ID_1);
        assertThat(person.getName()).isEqualTo(EXPECTED_NAME_IVAN);
        assertThat(person.getAge()).isEqualTo(EXPECTED_AGE_22);
        assertThat(person.isLegal()).isEqualTo(EXPECTED_IS_LEGAL_TRUE);
    }

    @Test
    public void testUserDetails_WithEmptyConstructorAndSetters() {

        PersonEntity person = getPerson_Ivan();
        person.setId(EXPECTED_ID_1);

        logger.debug(person);

        assertThat(person.getId()).isEqualTo(EXPECTED_ID_1);
        assertThat(person.getName()).isEqualTo(EXPECTED_NAME_IVAN);
        assertThat(person.getAge()).isEqualTo(EXPECTED_AGE_22);
        assertThat(person.isLegal()).isEqualTo(EXPECTED_IS_LEGAL_TRUE);
    }

}
