package com.user.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserRequest;
import com.user.entity.User;
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
	public Response getAllUser( @RequestParam(required = false) String firstName,
	        @RequestParam(required = false) String lastName,  @RequestParam(required = false) String city) {
		
		
		Response users = userService.findAll(firstName, lastName, city);
		
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
	 
	 @GetMapping("/users")
	 public ResponseEntity<Response> findAll(
	         @RequestParam(required = false) String startDate,
	         @RequestParam(required = false) String lastDate) {

	     Response res = new Response();
	     List<User> users;

	     try {
	         LocalDate start = (startDate != null) ? LocalDate.parse(startDate) : null;
	         LocalDate end = (lastDate != null) ? LocalDate.parse(lastDate) : null;

	         users = userService.findAll(start, end);

	         if (users.isEmpty()) {
	             res.setData(null);
	             res.setMessage("No users found");
	             res.setResult(false);
	             res.setStatus(204);
	         } else {
	             res.setData(users);
	             res.setMessage("Users fetched successfully");
	             res.setResult(true);
	             res.setStatus(200);
	         }
	     } catch (DateTimeParseException e) {
	         res.setData(null);
	         res.setMessage("Invalid date format. Please use yyyy-MM-dd");
	         res.setResult(false);
	         res.setStatus(400);
	     }

	     return ResponseEntity.status(res.getStatus()).body(res);
	 }

	
}