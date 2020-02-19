package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByActivationCode(String code);

}
