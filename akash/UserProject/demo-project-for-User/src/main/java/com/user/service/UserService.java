package com.user.service;


import com.user.dto.UserRequest;
import com.wfm.response.Response;

import jakarta.validation.Valid;

public interface UserService {
	Response createUser(UserRequest userRequest);

	Response findAll();

	
	Response deleteUser(Long id);

	Response getUserById(Long id);

	Response updateUser(Long id, @Valid UserRequest userRequest);

	

	

	
    
}