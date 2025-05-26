package com.invextory.services.impl;

import com.invextory.dtos.UserDTO;
import com.invextory.dtos.request.LoginRequest;
import com.invextory.dtos.request.RegisterRequest;
import com.invextory.dtos.response.Response;
import com.invextory.enums.UserRole;
import com.invextory.models.User;
import com.invextory.repositories.UserRepository;
import com.invextory.security.JwtUtils;
import com.invextory.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.invextory.constants.AppText.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;

    @Override
    public Response registerUser(RegisterRequest registerRequest) {
        log.info(LOG_REGISTER_INIT, registerRequest.getEmail());

        UserRole role = UserRole.MANAGER;
        if (registerRequest.getRole() != null) {
            role = registerRequest.getRole();
            log.info(LOG_ROLE_PROVIDED, role);
        } else {
            log.info(LOG_ROLE_DEFAULT);
        }

        User userToSave = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .build();

        log.info(LOG_USER_SAVE, userToSave.getEmail());
        userRepository.save(userToSave);

        log.info(LOG_REGISTER_SUCCESS, userToSave.getEmail());

        return Response.builder()
                .status(201)
                .message(USER_REGISTRATION_SUCCESS)
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Response getAllUsers() {
        return null;
    }

    @Override
    public User getCurrentLoggedInUser() {
        return null;
    }

    @Override
    public Response getUserById(Long id) {
        return null;
    }

    @Override
    public Response updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public Response deleteUser(Long id) {
        return null;
    }

    @Override
    public Response getUserTransactions(Long id) {
        return null;
    }
}
