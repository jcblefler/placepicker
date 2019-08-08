package com.jlefler.placepicker;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Long countByUsername(String username);
}