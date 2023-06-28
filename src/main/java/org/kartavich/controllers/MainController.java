package org.kartavich.controllers;

import org.kartavich.domain.UserEntities;
import org.kartavich.repository.PriceRepository;
import org.kartavich.repository.RoleRepository;
import org.kartavich.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceController userServiceController;

    @GetMapping("/help")
    public String help() {
        System.out.println("For help contact (https://t.me/Kartavichhh)");
        return "For help contact (https://t.me/Kartavichhh)";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntities());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") UserEntities userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userServiceController.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }
    @GetMapping("/admin")
    @Secured("ADMIN")
    public String userList(Model model) {
        model.addAttribute("allUsers", userServiceController.allUsers());
        return "admin";
    }
    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Integer userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userServiceController.deleteUser(userId);
        }
        return "redirect:/admin";
    }
    @GetMapping("/admin/gt/{userId}")
    public String  gtUser(@PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("allUsers", userServiceController.usergtList(userId));
        return "admin";
    }
}
