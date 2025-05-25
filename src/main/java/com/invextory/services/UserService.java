package com.invextory.services;

import com.invextory.dtos.UserDTO;
import com.invextory.dtos.request.LoginRequest;
import com.invextory.dtos.request.RegisterRequest;
import com.invextory.dtos.response.Response;
import com.invextory.models.User;

public interface UserService {

    Response registerUser(RegisterRequest registerRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getCurrentLoggedInUser();

    Response getUserById(Long id);

    Response updateUser(Long id, UserDTO userDTO);

    Response deleteUser(Long id);

    Response getUserTransactions(Long id);
    
}
