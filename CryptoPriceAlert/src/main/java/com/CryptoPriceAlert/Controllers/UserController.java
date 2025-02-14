package com.CryptoPriceAlert.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CryptoPriceAlert.Payloads.UserDTO;
import com.CryptoPriceAlert.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
    @Autowired
    private UserService userService;

    @GetMapping("/get-user/{userId}")
    private ResponseEntity<UserDTO> getUser(@PathVariable Long userId)
    {
        UserDTO fetchedUserDto=this.userService.getUser(userId);
        return new ResponseEntity<>(fetchedUserDto,HttpStatus.OK);
    }

    @PostMapping("/create-user")
    private ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto)
    {
        UserDTO createdUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
    }

    
    
}
