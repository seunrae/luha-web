package com.example.luha.service;

import com.example.luha.Dtos.UserLoginDto;
import com.example.luha.Dtos.UserRegisterDto;
import com.example.luha.Dtos.UserResponseDto;
import com.example.luha.ROLE;
import com.example.luha.Repositories.UserRepository;
import com.example.luha.models.User;
import com.example.luha.security.JwtUtil;
import com.example.luha.models.UserDetailServiceImpl;
import com.example.luha.token.ConfirmationToken;
import com.example.luha.token.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserDetailServiceImpl userDetailService;

    private final PasswordEncoder passwordEncoder;

    private final ConfirmationTokenService confirmationTokenService;



    @Override
    public UserResponseDto userRegister(UserRegisterDto userRegisterDto) {
        Boolean exist = userRepository.findByEmail(userRegisterDto.getEmail()).isPresent();
        if(!exist) {
            User user = User.builder()
                    .firstName(userRegisterDto.getFirstName())
                    .lastName(userRegisterDto.getLastName())
                    .email(userRegisterDto.getEmail())
                    .phoneNumber(userRegisterDto.getPhoneNumber())
                    .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                    .address(userRegisterDto.getAddress())
                    .userRole(ROLE.USER).build();

            userRepository.save(user);

            //add mail confirmation here
            String token = UUID.randomUUID().toString();

            //populating token table for user
            ConfirmationToken confirmationToken = ConfirmationToken.builder().token(token)
                    .createdAt(LocalDateTime.now()).expiresAt(LocalDateTime.now().plusMinutes(15)).user(user)
                    .build();
            confirmationTokenService.saveConfirmationToken(confirmationToken);

            return userResponse(user);
        }

        //TODO: catch exception

        throw new IllegalStateException("email already registered");
    }
    @Override
    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(()->
            new IllegalStateException("Token not found")
        );

        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expired = confirmationToken.getExpiresAt();

        if(expired.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        enableUser(confirmationToken.getUser().getEmail());
   return "Confirmed";

    }

    @Override
    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }


    @Override
    public UserResponseDto userResponse(User user) {
        UserResponseDto userResponseDto;
        userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .userRole(user.getUserRole()).build();

        return userResponseDto;
    }

    @Override
    public ResponseEntity<UserResponseDto> userLogin(UserLoginDto userLoginDto) {

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));


        final UserDetails userDetails = userDetailService.loadUserByUsername(userLoginDto.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        if (userDetails != null) {
            User user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException(userLoginDto.getEmail() + " not found")
            );
            UserResponseDto userResponseDto;
            userResponseDto = UserResponseDto.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .address(user.getAddress())
                    .userRole(user.getUserRole())
                    .token(token)
                    .build();
            return ResponseEntity.ok(userResponseDto);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UserResponseDto.builder().build());
    }

    @Override
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for(User user: users){
            UserResponseDto userResponseDto = UserResponseDto.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .address(user.getAddress())
                    .userRole(user.getUserRole()).build();
            userResponseDtos.add(userResponseDto);
        }

        return ResponseEntity.ok(userResponseDtos);
    }
}
