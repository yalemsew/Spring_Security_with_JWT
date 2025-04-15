package org.example.spring_security.auth;

import lombok.RequiredArgsConstructor;
import org.example.spring_security.config.JwtService;
import org.example.spring_security.user.User;
import org.example.spring_security.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
       Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );
       var user = (User) authentication.getPrincipal();
        //create a user object
        //save it in DB

        //Generate token for this User
        return new AuthenticationResponse(jwtService.generateToken(user));

    }
    public AuthenticationResponse register(RegisterRequest registerRequest) {

        //create a user object
        User user  = new User(
                registerRequest.firstName(),
                registerRequest.lastName(),
                registerRequest.username(),
                passwordEncoder.encode(registerRequest.password()),
                registerRequest.role()
        );

        //save it in DB
        User registeredUser = userRepository.save(user);
        //Generate token for this User
        String token = jwtService.generateToken(registeredUser);
        return new AuthenticationResponse(token);

    }
}
