package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UsersService;

import java.security.Principal;
import java.util.HashSet;

import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UsersService usersService;
    private final RoleService roleService;

    public AdminController(UsersService usersService, RoleService roleService) {
        this.usersService = usersService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUsers(@ModelAttribute("user") User user, Model model,
                           Principal principal) {

        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("users", usersService.findAll());
        model.addAttribute("usingUser", usersService.getUserByUsername(principal.getName()));
        model.addAttribute("allRoles", roleService.getRoles());
        return "admin";
    }



    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getRoles());
        return "/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {

        usersService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", usersService.findOne(id));
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("allRoles", roleService.getRoles());
        return "redirect:/admin";
    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user,
                         @ModelAttribute("nameRole") String name,
                         @PathVariable("id") long id, Model model) {


        model.addAttribute("allRoles", roleService.getRoles());
        Set<Role> roleSet = new HashSet<>();

        roleSet.add(roleService.getRoleByName(name));
        user.setRoles(roleSet);

        usersService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        usersService.delete(id);
        return "redirect:/admin";
    }
}