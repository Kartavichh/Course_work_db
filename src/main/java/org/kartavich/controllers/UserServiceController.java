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
public class UserServiceController implements UserDetailsService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntities user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found, make sure what user exist");
        return user;
    }
    public UserEntities findUserById(Integer userId) {
        Optional<UserEntities> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new UserEntities());
    }

    public List<UserEntities> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(UserEntities user) {
        UserEntities userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null)
            return false;
        user.roles = Collections.singleton(roleRepository.findByNameRole("USER"));
        user.password = (passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public boolean deleteUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
    public List<UserEntities> usergtList(Integer idMin) {
        return entityManager.createQuery("SELECT u FROM my_user u WHERE u.id > :paramId", UserEntities.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
