package org.example.repository;

import org.example.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author andreiserov
 */
public interface PersonRepository extends JpaRepository<Person,  Long> {
}
