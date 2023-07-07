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
@RequestMapping("/")
public class AdminController {

    private UsersService usersService;
    private final RoleService roleService;

    public AdminController(UsersService usersService, RoleService roleService) {
        this.usersService = usersService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getUsers(@ModelAttribute("user") User user, Model model,
                           Principal principal) {

        model.addAttribute("users", usersService.findAll());
        model.addAttribute("admin", usersService.getUserByUsername(principal.getName()));
        return "admin";
    }

    /*@GetMapping("/admin/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", usersService.findOne(id));
        return "/admin";
    }*/

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getRoles());
        return "/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {


        //Set<Role> roleSet = new HashSet<>();
        //List<Role> listRoles = new ArrayList<>();
        /*for (Role role : roleService.getRoles()) {
            roleSet.add(roleService.getRoleByName(name));
        }*/
        //user.setRoles(listRoles);
        //user.setRoles(listRoles);



//        Set<Role> roleSet = new HashSet<>();

//        roleSet.add(roleService.getRoleByName(name));
       // user.setRoles(roleSet);

        usersService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", usersService.findOne(id));
        model.addAttribute("roles", roleService.getRoles());
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @ModelAttribute("nameRole") String name,
                         @PathVariable("id") long id) {

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