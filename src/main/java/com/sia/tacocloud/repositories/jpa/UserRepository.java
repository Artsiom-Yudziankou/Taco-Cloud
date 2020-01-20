package com.sia.tacocloud.repositories.jpa;

import com.sia.tacocloud.essences.jpa.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
