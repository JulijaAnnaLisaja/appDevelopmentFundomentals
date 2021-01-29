package com.sce.app.service;

import com.sce.app.entity.PersonEntity;
import com.sce.app.exception.status.NotExistException;
import com.sce.app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.sce.app.common.ConstantCommon.PERSON;
import static com.sce.app.exception.CustomizedExceptionHandler.customExceptionMessage;

/**
 * Person Service implementation definition.
 *
 * @author julija.anna.lisaja@accenture.com
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Fet all Person records from the database as a list.
     *
     * @return all personEntities.
     */
    @Override
    public List<PersonEntity> getAllPersons() {

        List<PersonEntity> personEntities = personRepository.findAllPersons();

        if (personEntities.isEmpty()) throw new NotExistException("No " + PERSON + " Found");

        return personEntities;
    }

    /**
     * Get one person by its unique identifier.
     *
     * @param id unique identifier of the Person.
     *
     * @return one dedicated entity.
     */
    @Override
    public PersonEntity getPersonById(Integer id) {

        Optional<PersonEntity> person = personRepository.findPersonById(id);

        if (person.isEmpty()) throw new NotExistException(idNotFoundExceptionMessage(id));

        return person.get();
    }

    /**
     * Saves One person entity into database.
     *
     * @param personEntity Person entity.
     *
     * @return saved person entity.
     */
    @Override
    public PersonEntity createPerson(PersonEntity personEntity) {
        personEntity.setLegal(setIsLegal(personEntity.getAge()));
        return personRepository.save(personEntity);
    }

    /**
     * Update existing person entity on the database.
     *
     * @param personEntity Person entity.
     * @param id           unique identifier of the Person.
     *
     * @return saved Person entity.
     */
    @Override
    public PersonEntity updatePersonById(PersonEntity personEntity, Integer id) {

        if (getPersonById(id) != null) throw new NotExistException(idNotFoundExceptionMessage(id));

        PersonEntity newPersonEntity = new PersonEntity();
        newPersonEntity.setId(id);
        newPersonEntity.setName(personEntity.getName());
        newPersonEntity.setAge(personEntity.getAge());
        newPersonEntity.setLegal(setIsLegal(personEntity.getAge()));

        return personRepository.save(newPersonEntity);
    }

    /**
     * Delete one Person record from the database.
     *
     * @param id unique identifier of the Person.
     */
    @Override
    public void deletePersonById(Integer id) {

        if (getPersonById(id) != null) throw new NotExistException(idNotFoundExceptionMessage(id));

        personRepository.deletePersonById(id);
    }

    private String idNotFoundExceptionMessage(Integer id) {
        return customExceptionMessage(PERSON, "id", id.toString());
    }

    private boolean setIsLegal(int age) {
        return age < 18;
    }
}

