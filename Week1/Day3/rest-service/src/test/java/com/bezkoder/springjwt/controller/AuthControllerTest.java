package com.bezkoder.springjwt.controller;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    RoleRepository roleRepository;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    Authentication authentication;

    @Test
    public void authenticateUserTest() {
        LoginRequest request = new LoginRequest();
        request.setUsername("pnaik");
        request.setPassword("fd74");
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        authorities.add(grantedAuthority);
        UserDetailsImpl userDetails = new UserDetailsImpl(1l, "pnaik", "pnaik@g.com", "7584djj", authorities);
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);
        Mockito.when(jwtUtils.generateJwtToken(Mockito.any())).thenReturn("54gdfgdg837");
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);
        ResponseEntity<?> responseEntity = authController.authenticateUser(request);
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void registerUserTest() {
        SignupRequest signupRequest = new SignupRequest();
        Set<String> strRoles = new HashSet<>();
        strRoles.add("user");
        signupRequest.setEmail("pnaik@1.com");
        signupRequest.setUsername("pnaik");
        signupRequest.setPassword("abcd");
        signupRequest.setRole(strRoles);
        Role role = new Role();
        role.setName(ERole.ROLE_USER);
        role.setId(1);
        Optional<Role> adminRole = Optional.of(role);
        Mockito.when(userRepository.existsByEmail("pnaik@1.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUsername("pnaik")).thenReturn(false);
        Mockito.when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("64767647fhdgffh");
        Mockito.when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(adminRole);
        ResponseEntity<?> responseEntity = authController.registerUser(signupRequest);
        Assert.assertNotNull(responseEntity);
    }

    @Test(expected = RuntimeException.class)
    public void registerUserExceptionTest() {
        SignupRequest signupRequest = new SignupRequest();
        Set<String> strRoles = new HashSet<>();
        strRoles.add("user");
        signupRequest.setEmail("pnaik@1.com");
        signupRequest.setUsername("pnaik");
        signupRequest.setPassword("abcd");
        signupRequest.setRole(strRoles);
        Mockito.when(userRepository.existsByEmail("pnaik@1.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUsername("pnaik")).thenReturn(false);
        Mockito.when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("64767647fhdgffh");
        Mockito.when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(null);
        ResponseEntity<?> responseEntity = authController.registerUser(signupRequest);
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void registerU5ser() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("pnaik@1.com");
        signupRequest.setUsername("pnaik");
        signupRequest.setPassword("abcd");
        Role role = new Role();
        role.setName(ERole.ROLE_USER);
        role.setId(1);
        Optional<Role> adminRole = Optional.of(role);
        Mockito.when(userRepository.existsByEmail("pnaik@1.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUsername("pnaik")).thenReturn(false);
        Mockito.when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("64767647fhdgffh");
        Mockito.when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(adminRole);
        ResponseEntity<?> responseEntity = authController.registerUser(signupRequest);
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void regiUser() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("pnaik@1.com");
        signupRequest.setUsername("pnaik");
        signupRequest.setPassword("abcd");
        Mockito.when(userRepository.existsByUsername("pnaik")).thenReturn(true);
        ResponseEntity<?> responseEntity = authController.registerUser(signupRequest);
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void regifUser() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("pnaik@1.com");
        signupRequest.setUsername("pnaik");
        signupRequest.setPassword("abcd");
        Mockito.when(userRepository.existsByEmail("pnaik@1.com")).thenReturn(true);
        ResponseEntity<?> responseEntity = authController.registerUser(signupRequest);
        Assert.assertNotNull(responseEntity);
    }


}