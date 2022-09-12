package com.bezkoder.springjwt;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    RoleRepository roleRepository;


    @Test
    public void registerUser() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("pnaik@1.com");
        signupRequest.setUsername("pnaik");
        signupRequest.setPassword("abcd");
        Optional<Role> adminRole = new Optional<>();
        adminRole.ifPresent(a->a.setId(1));
        adminRole.ifPresent(a->a.setName(ERole.ROLE_USER));
        Mockito.when(authController.registerUser(signupRequest)).thenReturn(new ResponseEntity(signupRequest, HttpStatus.OK));
        Mockito.when(userRepository.existsByEmail("pnaik@1.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUsername("pnaik")).thenReturn(false);
        Mockito.when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("64767647fhdgffh");
        Mockito.when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(adminRole);
        ResponseEntity<?> r = authController.registerUser(signupRequest);
        Assert.assertEquals(r,new ResponseEntity(signupRequest, HttpStatus.OK));
    }
}