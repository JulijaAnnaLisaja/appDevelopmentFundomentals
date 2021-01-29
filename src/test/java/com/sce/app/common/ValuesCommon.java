package com.sce.app.common;

import com.sce.app.entity.PersonEntity;

public class ValuesCommon {

    public static final int EXPECTED_ID = 1;
    public static final String EXPECTED_NAME = "Ivan";
    public static final int EXPECTED_AGE = 22;
    public static final boolean EXPECTED_IS_LEGAL = true;

    public static final PersonEntity person = new PersonEntity(EXPECTED_ID, EXPECTED_NAME, EXPECTED_AGE, EXPECTED_IS_LEGAL);
}
