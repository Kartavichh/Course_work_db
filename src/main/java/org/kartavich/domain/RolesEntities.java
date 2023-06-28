package org.kartavich.domain;

import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity @Table
public class RolesEntities implements GrantedAuthority {
    @Id @GeneratedValue
    public Integer ID;
    public String name;

    public RolesEntities(String name) {
        this.name = name;
    }
    public RolesEntities() {
   }

    @Override
    public String getAuthority() {
        return name;
    }
}
