package com.sce.app.service;

import com.sce.app.entity.PersonEntity;

import java.util.List;

/**
 * Person Service interface definition.
 *
 * @author julija.anna.lisaja@accenture.com
 */
public interface PersonService {

    /**
     * Get all persons from H2 database.
     *
     * @return all Person records.
     */
    List<PersonEntity> getAllPersons();

    /**
     * Find one Person in H2 database by its unique id.
     *
     * @return person by id.
     */
    PersonEntity getPersonById(Integer id);

    /**
     * Creates new Person record in H2 database.
     *
     * @return person by id.
     */
    PersonEntity createPerson(PersonEntity personEntity);

    /**
     * Update one dedicates person in the database by its unique id.
     *
     * @return person by id.
     */
    PersonEntity updatePersonById(PersonEntity personEntity, Integer id);

    /**
     * Delete one dedicates person in the database by its unique id.
     */
    void deletePersonById(Integer id);

}
