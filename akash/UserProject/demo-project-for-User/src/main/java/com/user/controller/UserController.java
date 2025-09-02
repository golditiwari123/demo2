package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserRequest;
import com.user.service.UserService;
import com.wfm.response.Response;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/addUsers")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public Response createUser(@Valid @RequestBody UserRequest userRequest) {
	    return userService.createUser(userRequest);
	}

	@GetMapping
	public Response getAllUser(){
		Response users = userService.findAll();
		return users;
	}
	@DeleteMapping("/{id}")
	public Response deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
	}
	 @GetMapping("/{id}")
	    public Response getUserById(@PathVariable Long id) {
	        return userService.getUserById(id);
	    }
	 
	 @PutMapping("/{id}")
	    public Response updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
	        return userService.updateUser(id, userRequest);
	    }
	
}