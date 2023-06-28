package org.kartavich.repository;

import org.kartavich.domain.RolesEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public interface RoleRepository extends JpaRepository<RolesEntities, Integer> {
    RolesEntities findByName(String name);

}
