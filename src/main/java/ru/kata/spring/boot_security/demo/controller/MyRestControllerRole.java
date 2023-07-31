package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestControllerRole {

    public RoleService roleService;

    public MyRestControllerRole(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> allRoles() {
        return  new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }
}
