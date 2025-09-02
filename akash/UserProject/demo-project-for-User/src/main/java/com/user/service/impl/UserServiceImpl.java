package com.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dto.UserRequest;
import com.user.entity.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import com.wfm.response.Response;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Response createUser(UserRequest request) {

		// Create a response object
		Response res = new Response();

		// Check if email already exists
		if (userRepository.existsByEmail(request.getEmail())) {
			res.setData(null);
			res.setMessage("User already exists with email: " + request.getEmail());
			res.setResult(false);
			res.setStatus(409);
			return res;
		}

		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setAddress(request.getAddress());
		user.setCity(request.getCity());
		user.setState(request.getState());
		user.setZipCode(request.getZipCode());
		user.setRole(request.getRole());
		User savedUser = userRepository.save(user);

		res.setData(savedUser);
		res.setMessage("User created successfully");
		res.setResult(true);
		res.setStatus(201);
		return res;

	}

	@Override
	public Response findAll() {
	    Response res = new Response();
	    List<User> users = userRepository.findAll();

	    if (users.isEmpty()) {
	        res.setData(null);
	        res.setMessage("No users found");
	        res.setResult(false);
	        res.setStatus(204); // No Content
	    } else {
	        res.setData(users);
	        res.setMessage("Users fetched successfully");
	        res.setResult(true);
	        res.setStatus(200); // OK
	    }

	    return res;
	}

	@Override
	public Response deleteUser(Long id) {
		Response res = new Response();
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            res.setData(null);
            res.setMessage("User deleted successfully");
            res.setResult(true);
            res.setStatus(200);
        } else {
            res.setData(null);
            res.setMessage("User not found with ID: " + id);
            res.setResult(false);
            res.setStatus(404);
        }

        return res;
	}

	@Override
	public Response getUserById(Long id) {
		  Response res = new Response();
	        Optional<User> optionalUser = userRepository.findById(id);

	        if (optionalUser.isPresent()) {
	        	UserRequest dto = mapToResponse(optionalUser.get());
	            res.setData(dto);
	            res.setMessage("User found");
	            res.setResult(true);
	            res.setStatus(200);
	        } else {
	            res.setData(null);
	            res.setMessage("User not found with ID: " + id);
	            res.setResult(false);
	            res.setStatus(404);
	        }

	        return res;
	    }

	 private UserRequest mapToResponse(User user) {
		 UserRequest dto = new UserRequest();
	        
	        dto.setFirstName(user.getFirstName());
	        dto.setLastName(user.getLastName());
	        dto.setEmail(user.getEmail());
	        dto.setPhoneNumber(user.getPhoneNumber());
	        dto.setAddress(user.getAddress());
	        dto.setCity(user.getCity());
	        dto.setState(user.getState());
	        dto.setZipCode(user.getZipCode());
	        dto.setRole(user.getRole());
	        return dto;
	    }

	@Override
	public Response updateUser(Long id, @Valid UserRequest request) {
		Response res = new Response();
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setAddress(request.getAddress());
            user.setCity(request.getCity());
            user.setState(request.getState());
            user.setZipCode(request.getZipCode());
            user.setRole(request.getRole());

            User updatedUser = userRepository.save(user);
            UserRequest dto = mapToResponse(updatedUser);

            res.setData(dto);
            res.setMessage("User updated successfully");
            res.setResult(true);
            res.setStatus(200);
        } else {
            res.setData(null);
            res.setMessage("User not found with ID: " + id);
            res.setResult(false);
            res.setStatus(404);
        }

        return res;
    }
	}

