package com.dev.lembrete.rest;

import com.dev.lembrete.domain.Role;
import com.dev.lembrete.domain.User;
import com.dev.lembrete.repository.RoleRepository;
import com.dev.lembrete.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuerRest
{
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/saveuser")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user)
    {
        return ResponseEntity.ok(userService.saveUser(user));
    }
    @PutMapping("/updateuser")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user)
    {
        return ResponseEntity.ok(userService.updateUser(user));
    }
    @DeleteMapping("/deleteuser")
    public ResponseEntity deleteUser(@PathVariable Long id)
    {
        try
        {
            userService.deleteUserById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex)
        {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/listuser")
    public ResponseEntity<List<User>> listUser()
    {
        return ResponseEntity.ok(userService.listUser());
    }

    @GetMapping("/getuser")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
    {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/test")
    public String testUser(){
        return "olaaaare";
    }

    @RequestMapping("/secure")
    public ModelAndView secure() {
        return new ModelAndView("secure");
    }

    @PostMapping("/saveRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role)
    {
        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwww"+role.getRoleId()+""+" "+role.getRoleName());
        return ResponseEntity.ok(roleRepository.save(role));
    }

    /*@RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }*/


}
