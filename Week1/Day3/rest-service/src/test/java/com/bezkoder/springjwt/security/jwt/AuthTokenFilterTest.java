package com.bezkoder.springjwt.security.jwt;

import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import com.bezkoder.springjwt.security.services.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class AuthTokenFilterTest {

    @InjectMocks
    AuthTokenFilter authTokenFilter;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    Authentication authentication;

    @Mock
    UserDetailsServiceImpl userDetailsService;

    @Mock
    FilterChain filterChain;

    @Test
    public void doFilterInternal() {
        Mockito.when(jwtUtils.generateJwtToken(authentication)).thenReturn("jd67");
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        authorities.add(grantedAuthority);
        UserDetailsImpl userDetails = new UserDetailsImpl(1l, "pnaik", "pnaik@g.com", "7584djj", authorities);
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);
        Mockito.when(jwtUtils.validateJwtToken("dhsgd")).thenReturn(true);
        Mockito.when(jwtUtils.getUserNameFromJwtToken("dhsgd")).thenReturn("rdtrr65");
        Mockito.when(userDetailsService.loadUserByUsername("dhsgd")).thenReturn(userDetails);
        try {
            authTokenFilter.doFilterInternal(httpServletRequest,httpServletResponse,filterChain);
        }
        catch (Exception e){

        }
    }
}