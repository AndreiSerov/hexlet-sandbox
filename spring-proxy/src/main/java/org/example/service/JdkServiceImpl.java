package org.example.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author andreiserov
 */
@Service
public class JdkServiceImpl implements JdkService {

    @Cacheable
    public void method1() {

    }
}
