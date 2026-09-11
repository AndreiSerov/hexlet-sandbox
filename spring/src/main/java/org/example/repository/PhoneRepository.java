package org.example.repository;

import org.example.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author andreiserov
 */
public interface PhoneRepository extends JpaRepository<Phone,  Long> {
}
