package org.kartavich.controllers;

import org.kartavich.domain.IphonesEntities;
import org.kartavich.domain.UserEntities;
import org.kartavich.repository.PriceRepository;
import org.kartavich.repository.RoleRepository;
import org.kartavich.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
// Реализация запроса Help
    @GetMapping("/help")
    public String help() {
        System.out.println("For help contact (https://t.me/Kartavichhh)");
        return "For help contact (https://t.me/Kartavichhh)";
    }
    // Реализация запроса на регистрацию
    @PostMapping("/registration")
    public SourcePack<String> addUser(@RequestParam("username") String username,
                                      @RequestParam("password") String password) {
        if (!userService.saveUser(new UserEntities(username, password))) {
            return new SourcePack<>(null, "Username not available, try another username");
        }
        return new SourcePack<>("Registration complete");
    }
    @PostMapping("/lot/add")
    @Secured("ROLE_SELLER")
    public SourcePack<String> addLot(@RequestParam("price") Float price, @RequestParam("nameOfModel") String nameOfModel) {
        priceRepository.save(new IphonesEntities(price, nameOfModel));
        return new SourcePack<>("Lot was added");
    }
    @PostMapping("/lot/delete")
    @Secured("ROLE_SELLER")
    public SourcePack<String> deleteLot(@RequestParam("id") Integer id) {
        if(userRepository.findById(id).isPresent()) {
            priceRepository.deleteById(id);
            return new SourcePack<>("Lot was deleted");
        }
        return new SourcePack<>(null, "Id isn't found");
    }

    @GetMapping("/lot/get")
    @Secured("ROLE_USER")
    public SourcePack<List<IphonesEntities>> getLot(@RequestParam("id") Integer id) {
        if(id == 0) {
            return new SourcePack<List<IphonesEntities>>(priceRepository.findAll());
        }
        return new SourcePack<>(priceRepository.findAllById(Collections.singleton(id)));
    }
//    Получение всех моделей смартфонов
    @GetMapping("/lot/getAll")
    @Secured("ROLE_USER")
    public SourcePack<List<IphonesEntities>> getAllLots() {
            return new SourcePack<List<IphonesEntities>>(priceRepository.findAll());
    }

    @GetMapping("/getUsersList")
    @Secured("ROLE_ADMIN")
    public SourcePack<List<UserEntities>> userList() {
        return new SourcePack<>(userService.allUsers());
    }

    @PostMapping("/deleteUser")
    @Secured("ROLE_ADMIN")
//    Возвращает элемент в оболочке, если он пуст, удаляем
    public SourcePack<String> deleteUser(@RequestParam("id") Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return new SourcePack<>("All right! User was delete");
        }
        return new SourcePack<>(null, "User wasn't found");
    }

    @GetMapping("/getUserById")
    @Secured("ROLE_ADMIN")
    public SourcePack<UserEntities> getUser(@RequestParam("id") Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            return new SourcePack<>(userRepository.findById(userId).orElse(new UserEntities()));
        }
        return new SourcePack<>(null, "User wasn't found");
    }
}
