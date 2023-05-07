package com.program.controller;

import com.program.exception.CategoryException;
import com.program.exception.RoleException;
import com.program.exception.UserException;
import com.program.helper.TokenHelper;
import com.program.model.Category;
import com.program.model.Role;
import com.program.model.User;
import com.program.repository.UserRepository;
import com.program.service.RoleService;
import com.program.service.UserService;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TokenHelper tokenHelper;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User newUser) throws UserException {

        User user = userService.getUserByEmail(newUser.getEmail());
        if (user != null) {
            return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
        }
        userService.createUser(newUser);
        return new ResponseEntity<>("Created user successfully, please login.", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() throws UserException {
        List<User> users =	userService.findAll();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) throws UserException {
        if (email == null || password == null) {
            return new ResponseEntity<>("No credentials specified", HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            int token = tokenHelper.generateToken(user);
            return new ResponseEntity<>("Authorization successful. Here's your token: " + token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Bad credentials", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/profile")
    public HttpEntity<? extends Object> getUser(@RequestHeader int token) {
        User user = tokenHelper.getUserByToken(token);
        if (user == null) {
            return new ResponseEntity<>("Token expired", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader int token) {
        String msg = "Bye, " + tokenHelper.getUserByToken(token).getEmail() + "!";
        tokenHelper.deleteToken(token);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


    @GetMapping("/user/getById/{Id}")
    public ResponseEntity<User> getCategoryById(@PathVariable("Id") Integer id ) throws UserException {
        User user1 = userService.getUserById(id);
        return new ResponseEntity<User>(user1,HttpStatus.OK);
    }

//    @PostMapping("/user/update/{id}")
//    public ResponseEntity<User> updateUserById( @PathVariable Integer id, @RequestBody User user) throws UserException{
//        User user1=	userService.updateUser(id,user);
//        return new ResponseEntity<User>(user1,HttpStatus.OK);
//    }



}