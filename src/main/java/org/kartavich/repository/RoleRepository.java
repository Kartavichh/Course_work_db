package org.kartavich.repository;

import org.kartavich.domain.RolesEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public interface RoleRepository extends JpaRepository<RolesEntities, Integer> {
    RolesEntities findByName(String name);
    @Modifying
    @Query(value = "INSERT INTO user_entities_roles(user_entities_id, roles_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void addRoleSQL(Integer userId, Integer roleId);

    @Modifying
    @Query(value = "DELETE FROM user_entities_roles AS VALUES (:userId, :roleId)", nativeQuery = true)
    void dellRoleSQL(Integer userId, Integer roleId);
}
