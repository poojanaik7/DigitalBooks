package com.digitalbooks.controller;

import com.digitalbooks.entity.BRole;
import com.digitalbooks.entity.BookRole;
import com.digitalbooks.entity.User;
import com.digitalbooks.payload.request.LoginRequest;
import com.digitalbooks.payload.request.SignupRequest;
import com.digitalbooks.payload.response.JwtResponse;
import com.digitalbooks.payload.response.MessageResponse;
import com.digitalbooks.repository.BookRoleRepository;
import com.digitalbooks.repository.UserRepository;
import com.digitalbooks.security.jwt.JwtUtils;
import com.digitalbooks.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/digitalbooks/auth")
public class AuthController extends BaseController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    BookRoleRepository roleRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strRoles = signUpRequest.getRole();
        List<BookRole> roles = new ArrayList<>();

        if (strRoles == null) {
            BookRole userRole = roleRepository.findByName(BRole.ROLE_READER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            switch (strRoles) {
                case "Author":
                    BookRole authorRole = roleRepository.findByName(BRole.ROLE_AUTHOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(authorRole);
                    break;
                default:
                    BookRole readerRole = roleRepository.findByName(BRole.ROLE_READER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(readerRole);
            }
        }

        user.setRoles(roles);
        userRepository.save(user);


        return ResponseEntity.ok(new MessageResponse(strRoles + " registered successfully!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
