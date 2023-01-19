package org.example.service;

import org.example.domain.Person;
import org.example.repository.PersonRepository;
import org.example.repository.PhoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author andreiserov
 */
@Service
public class PersonService {

    private PersonRepository personRepository;
    private PhoneRepository phoneRepository;

    public PersonService(PersonRepository personRepository, PhoneRepository phoneRepository) {
        this.personRepository = personRepository;
        this.phoneRepository = phoneRepository;
    }

    @Transactional
    public Person create(Person person) {
        final Person savedPerson = personRepository.save(person);
        person
            .getPhones()
            .forEach(
                it -> {
                    it.setPerson(savedPerson);
                    phoneRepository.save(it);
                }
            );
        return personRepository.findById(savedPerson.getId()).orElseThrow(RuntimeException::new);
    }

    public Collection<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(Long id) {
        return personRepository.findById(id)
            .orElseThrow(RuntimeException::new);
    }
}
