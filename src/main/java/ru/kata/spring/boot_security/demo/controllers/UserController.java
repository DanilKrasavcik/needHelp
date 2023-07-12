package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UsersService;

import java.security.Principal;

@Controller
public class UserController {

    private UsersService usersService;
    private final RoleService roleService;

    public UserController(UsersService usersService, RoleService roleService) {
        this.usersService = usersService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String getUsers(@AuthenticationPrincipal User user, Model model,
                           Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("usingUser", usersService.getUserByUsername(principal.getName()));
        return "user";
    }
}
