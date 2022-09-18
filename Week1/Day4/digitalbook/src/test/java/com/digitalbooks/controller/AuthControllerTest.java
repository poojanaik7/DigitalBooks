package com.digitalbooks.controller;

import com.digitalbooks.entity.BRole;
import com.digitalbooks.entity.BookRole;
import com.digitalbooks.payload.request.LoginRequest;
import com.digitalbooks.payload.request.SignupRequest;
import com.digitalbooks.repository.BookRoleRepository;
import com.digitalbooks.repository.UserRepository;
import com.digitalbooks.security.jwt.JwtUtils;
import com.digitalbooks.security.services.UserDetailsImpl;
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
    BookRoleRepository bookRoleRepository;

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
                return "ROLE_AUTHOR";
            }
        };
        authorities.add(grantedAuthority);
        UserDetailsImpl userDetails = new UserDetailsImpl(1, "pnaik", "pnaik@g.com", "7584djj", authorities);
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);
        Mockito.when(jwtUtils.generateJwtToken(Mockito.any())).thenReturn("54gdfgdg837");
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);
        ResponseEntity<?> responseEntity = authController.authenticateUser(request);
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void registerReaderTest() {
        SignupRequest signupRequest = new SignupRequest();
        Set<String> strRoles = new HashSet<>();
        strRoles.add("user");
        signupRequest.setEmail("pnaik@1.com");
        signupRequest.setUsername("pnaik");
        signupRequest.setPassword("abcd");
        signupRequest.setRole(strRoles);
        BookRole role = new BookRole();
        role.setName(BRole.ROLE_READER);
        role.setId(1);
        Optional<BookRole> readerRole = Optional.of(role);
        Mockito.when(userRepository.existsByEmail("pnaik@1.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUsername("pnaik")).thenReturn(false);
        Mockito.when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("64767647fhdgffh");
        Mockito.when(bookRoleRepository.findByName(BRole.ROLE_READER)).thenReturn(readerRole);
        ResponseEntity<?> responseEntity = authController.registerUser(signupRequest);
        Assert.assertNotNull(responseEntity);
    }

    @Test(expected = RuntimeException.class)
    public void registerUserExceptionTest() {
        SignupRequest signupRequest = new SignupRequest();
        Set<String> strRoles = new HashSet<>();
        strRoles.add("ROLE_AUTHOR");
        signupRequest.setEmail("pnaik@1.com");
        signupRequest.setUsername("pnaik");
        signupRequest.setPassword("abcd");
        signupRequest.setRole(strRoles);
        Mockito.when(userRepository.existsByEmail("pnaik@1.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUsername("pnaik")).thenReturn(false);
        Mockito.when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("64767647fhdgffh");
        ResponseEntity<?> responseEntity = authController.registerUser(signupRequest);
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void registerAuthorTest() {
        SignupRequest signupRequest = new SignupRequest();
        Set<String> strRoles = new HashSet<>();
        strRoles.add("ROLE_AUTHOR");
        signupRequest.setEmail("pnaik@1.com");
        signupRequest.setUsername("pnaik");
        signupRequest.setPassword("abcd");
        signupRequest.setRole(strRoles);
        BookRole role = new BookRole();
        role.setName(BRole.ROLE_READER);
        role.setId(1);
        Optional<BookRole> authorRole = Optional.of(role);
        Mockito.when(userRepository.existsByEmail("pnaik@1.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUsername("pnaik")).thenReturn(false);
        Mockito.when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("64767647fhdgffh");
        Mockito.when(bookRoleRepository.findByName(BRole.ROLE_AUTHOR)).thenReturn(authorRole);
        ResponseEntity<?> responseEntity = authController.registerUser(signupRequest);
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void registerU5ser() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("pnaik@1.com");
        signupRequest.setUsername("pnaik");
        signupRequest.setPassword("abcd");
        BookRole role = new BookRole();
        role.setName(BRole.ROLE_READER);
        role.setId(1);
        Optional<BookRole> readerRole = Optional.of(role);
        Mockito.when(userRepository.existsByEmail("pnaik@1.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUsername("pnaik")).thenReturn(false);
        Mockito.when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("64767647fhdgffh");
        Mockito.when(bookRoleRepository.findByName(BRole.ROLE_READER)).thenReturn(readerRole);
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