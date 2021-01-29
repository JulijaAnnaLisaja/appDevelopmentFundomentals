package com.sce.app.controller;

import com.sce.app.entity.PersonEntity;
import com.sce.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Person Controller definition.
 *
 * @author julija.anna.lisaja@accenture.com
 */
@Controller
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Output all Persons.
     *
     * @return OK HTTP status.
     */
    @GetMapping(value = "/get", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<PersonEntity>> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    /**
     * Output one Person by id.
     *
     * @param id unique identifier of the Person.
     * @return FOUND HTTP status.
     */
    @GetMapping(value = "/get/{id}", consumes = "application/json", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<PersonEntity> getEmployee(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.FOUND);
    }

    /**
     * Save user in the database.
     *
     * @param person one Person record.
     * @return CREATED HTTP status.
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PersonEntity> savePerson(@RequestBody PersonEntity person) {
        personService.createPerson(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    /**
     * Update existing user in the database
     *
     * @param person one Person record.
     * @param id unique identifier of the Person.
     * @return OK HTTP status.
     */
    @PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PersonEntity> updateEmployee(@RequestBody PersonEntity person, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(personService.updatePersonById(person, id), HttpStatus.OK);
    }

    /**
     *
     * @param id unique identifier of the Person.
     * @return NO_CONTENT HTTP status.
     */
    @DeleteMapping(value = "/delete/{id}", consumes = "text/plain")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
        personService.deletePersonById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
