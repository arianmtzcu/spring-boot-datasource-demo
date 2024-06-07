package com.example.arian.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arian.entity.User;
import com.example.arian.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

   private final UserService userService;

   public UserController(final UserService userService) {
      this.userService = userService;
   }

   @GetMapping("/all")
   public List<User> getAllUsers() {
      return userService.getAllUsers();
   }

   @PostMapping("/create")
   public User createUser(@RequestBody User user) {
      return userService.createUser(user);
   }

   @PostMapping("/random")
   public User createRandomUser() {
      return userService.createRandomUser();
   }

   @PostMapping("/stress-test")
   public String stressTest() {
      return userService.stressTest(10, 100, 1000);
   }

}
