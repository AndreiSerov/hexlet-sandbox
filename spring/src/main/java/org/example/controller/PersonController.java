package org.example.controller;

import org.apache.catalina.core.ApplicationContext;
import org.example.controller.api.request.PersonRequest;
import org.example.controller.api.response.PersonResponse;
import org.example.controller.mapper.PersonMapper;
import org.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author andreiserov
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    AbstractApplicationContext context;


    private PersonService personService;
    private PersonMapper personMapper;

    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @PostMapping
    public PersonResponse createPerson(
        @RequestBody PersonRequest request
    ) {
        return personMapper.toResponse(
            personService.create(personMapper.toModel(request))
        );
    }

    @GetMapping("/{id}")
    public PersonResponse createPerson(
        @PathVariable Long id
    ) {
        return personMapper.toResponse(
            personService.getById(id)
        );
    }

    @GetMapping
    public Collection<PersonResponse> getAll() {
        return personService.getAll()
            .stream()
            .map(it -> personMapper.toResponse(it))
            .toList();
    }
}
