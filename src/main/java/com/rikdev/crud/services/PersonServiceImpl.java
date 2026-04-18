package com.rikdev.crud.services;

import com.rikdev.crud.entities.Person;
import com.rikdev.crud.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> searchByDescription(String keyword) {
        System.out.println("Buscando dispositivos con el término:" + keyword);
        /*keyword = "VALIEN";*/
        return personRepository.searchByDescription(keyword);
    }

    @Override
    public Optional<Person> getPersonByIdentification(String identification) {
        return personRepository.findByIdentification(identification);
    }
}
