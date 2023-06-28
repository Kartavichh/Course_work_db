package org.kartavich.repository;

import org.kartavich.domain.UserEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository @Transactional
public interface UserRepository extends JpaRepository<UserEntities, Integer> {
    UserEntities findByUsername(String username);
}
