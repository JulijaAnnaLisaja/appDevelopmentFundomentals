package com.sce.app.repository;

import com.sce.app.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.sce.app.common.ConstantCommon.PERSON;

/**
 * Person JpaRepository definition.
 *
 * @author julija.anna.lisaja@accenture.com
 */
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    /**
     * Find all Persons in H2 database.
     *
     * @return all Person records.
     */
    @Query("SELECT p from " + PERSON + " p")
    List<PersonEntity> findAllPersons();

    /**
     * Find one Person in H2 database by dedicated id, that path into SQL query as a parameter.
     *
     * @param id unique identifier of the Person.
     * @return person by id.
     */
    @Query("from " + PERSON + " p where p.id = :id")
    Optional<PersonEntity> findPersonById(@Param("id") Integer id);

    /**
     * Delete one Person in H2 database by dedicated id, that path into SQL query as a parameter.
     *
     * @param id unique identifier of the Person.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM " + PERSON + " p WHERE p.id = :id")
    void deletePersonById(@Param("id") Integer id);
}

