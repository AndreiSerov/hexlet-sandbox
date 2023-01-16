package org.example.service;

import org.example.config.SecurityConfig;
import org.example.exception.ServiceException;
import org.example.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author andreiserov
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private PersonRepository personRepository;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return personRepository.findById(1L)
            .map(it ->
                new org.springframework.security.core.userdetails.User(
                    "mockk",
                    "mockk",
                    SecurityConfig.DEFAULT_AUTHORITIES
                )
            )
            .orElseThrow(() -> ServiceException.unauthorized("Invalid email"));
    }
}
