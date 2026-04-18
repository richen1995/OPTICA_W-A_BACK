package com.rikdev.crud.services;

import com.rikdev.crud.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person savePerson(Person person);
    Person updatePerson(Person person);
    List<Person> getPersons();                  //obtener todas las personas
    Optional<Person> getPersonById(Long id);
    void deletePerson(Long id);
    List<Person> searchByDescription(String keyword);
    Optional<Person> getPersonByIdentification(String identification);
}
