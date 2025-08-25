package com.user.service;


import java.time.LocalDate;
import java.util.List;

import com.user.dto.UserRequest;
import com.user.entity.User;
import com.wfm.response.Response;

import jakarta.validation.Valid;

public interface UserService {
	Response createUser(UserRequest userRequest);

	Response findAll(String firstName, String lastName, String city);

	
	Response deleteUser(Long id);

	Response getUserById(Long id);

	Response updateUser(Long id, @Valid UserRequest userRequest);

	List<User> findAll(LocalDate start, LocalDate last);

	

	

	
    
}