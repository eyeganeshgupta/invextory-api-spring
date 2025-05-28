package com.invextory.services.impl;

import com.invextory.dtos.UserDTO;
import com.invextory.dtos.request.LoginRequest;
import com.invextory.dtos.request.RegisterRequest;
import com.invextory.dtos.response.Response;
import com.invextory.enums.UserRole;
import com.invextory.exceptions.InvalidCredentialsException;
import com.invextory.exceptions.NotFoundException;
import com.invextory.models.User;
import com.invextory.repositories.UserRepository;
import com.invextory.security.JwtUtils;
import com.invextory.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        log.info(LOG_LOGIN_INIT, loginRequest.getEmail());

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
                    log.info(LOG_USER_NOT_FOUND, loginRequest.getEmail());
                    return new NotFoundException(ERROR_EMAIL_NOT_FOUND);
                });

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.info(LOG_INVALID_PASSWORD, loginRequest.getEmail());
            throw new InvalidCredentialsException(ERROR_PASSWORD_MISMATCH);
        }

        String token = jwtUtils.generateToken(user.getEmail());

        log.info(JWT_LOGIN_SUCCESS, loginRequest.getEmail());

        return Response.builder()
                .status(200)
                .message(USER_LOGIN_SUCCESS_MESSAGE)
                .role(user.getRole())
                .token(token)
                .expirationTime(JWT_TOKEN_EXPIRATION)
                .build();
    }

    @Override
    public Response getAllUsers() {
        log.info(LOG_GET_ALL_USERS_INIT);

        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        users.forEach(user -> user.setTransactions(null));

        List<UserDTO> userDTOs = modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());

        log.info(LOG_USERS_RETRIEVED, users.size());

        return Response.builder()
                .status(200)
                .message(USERS_FETCH_SUCCESS_MESSAGE)
                .users(userDTOs)
                .build();
    }

    @Override
    public User getCurrentLoggedInUser() {
        log.info(LOG_GET_CURRENT_USER_INIT);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.info(LOG_AUTHENTICATION_NULL);
            throw new NotFoundException(ERROR_EMAIL_NOT_FOUND);
        }

        String email = authentication.getName();
        log.info(LOG_AUTHENTICATION_EMAIL, email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.info(LOG_USER_NOT_FOUND, email);
                    return new NotFoundException(ERROR_EMAIL_NOT_FOUND);
                });

        user.setTransactions(null);
        log.info(LOG_GET_CURRENT_USER_SUCCESS, user.getEmail());

        return user;
    }

    @Override
    public Response getUserById(Long id) {
        log.info(LOG_GET_USER_BY_ID_INIT, id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ERROR_USER_ID_NOT_FOUND));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setTransactions(null);

        log.info(LOG_GET_USER_BY_ID_SUCCESS, id);

        return Response.builder()
                .status(200)
                .message(USER_FETCH_SUCCESS_MESSAGE)
                .user(userDTO)
                .build();
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
