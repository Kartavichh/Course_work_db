package org.kartavich.controllers;

import org.kartavich.domain.*;
import org.kartavich.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
//    Переопределение метода из UserDetailsService (обязательно), проверяем есть ли такой ник для авторизации
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntities user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found, make sure what user exist");
        return user;
    }
//    Реализация GetAll
    public List<UserEntities> allUsers() {
        return userRepository.findAll();
    }
//    Сохранение юзера в репозиторий, проверка существует ли такой юзер, если есть, выдаст ошибку
    public boolean saveUser(UserEntities user) {
        UserEntities userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null)
            return false;
        user.roles = Collections.singleton(roleRepository.findByName("ROLE_USER"));
        user.password = (passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public boolean addRole(String username, String role){
        UserEntities userFromDB = userRepository.findByUsername(username);
        RolesEntities rolesEntities = roleRepository.findByName(role);
        if (userFromDB == null || rolesEntities == null){
            return false;
        }
       roleRepository.addRoleSQL(userFromDB.ID, rolesEntities.ID);
        return true;
    }

    public boolean deleteRole(String username, String role){
        UserEntities userFromDB = userRepository.findByUsername(username);
        RolesEntities rolesEntities = roleRepository.findByName(role);
        if (userFromDB == null || rolesEntities == null){
            System.out.printf("False");
            return false;
        }
        roleRepository.dellRoleSQL(userFromDB.ID, rolesEntities.ID);
        System.out.printf("True");
        return true;
    }
}
