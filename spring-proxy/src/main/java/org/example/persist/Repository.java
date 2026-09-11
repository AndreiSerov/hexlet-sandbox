package org.example.persist;

import org.example.annotation.Transactional;

/**
 * @author andreiserov
 */
public interface Repository {

    @Transactional
    void methodTransactional();

    void method();
}
