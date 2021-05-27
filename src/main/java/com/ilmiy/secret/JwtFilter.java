package com.ilmiy.secret;

import com.ilmiy.repository.UserRepository;
import com.ilmiy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;


public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        //O'ZIMIZ YOZGAN METHOD. MAQSAD USER(USERDETAILS) NI OLISH
        UserDetails userDetails = getUserDetails(httpServletRequest);
        //BEARER TOKEN YOKI BASIC TOKEN ORQALI TEKSHIRILIB OLINGAN USER
        if (userDetails != null) {
            if (userDetails.isEnabled()
                    && userDetails.isAccountNonExpired()
                    && userDetails.isAccountNonLocked()
                    && userDetails.isCredentialsNonExpired()) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    /**
     * HttpServletRequest dan tokenni olib, u orqali userni olish
     *
     * @param httpServletRequest
     * @return
     */
    public UserDetails getUserDetails(HttpServletRequest httpServletRequest) {
        try {
            //REQUESTNI HEADER DAN "AUTHORIZATION" KALIT SO'Z ORQALI TOKENNI OLISH
            String tokenClient = httpServletRequest.getHeader("Authorization");
            //TOKENNI NULL GA TEKSHIRISH
            if (tokenClient != null) {
                //TOKENNI BEARER EKANLIGINI TEKSHIRISH
                if (tokenClient.startsWith("Bearer ")) {
                    //TOKENNI 7 INDEX DAN BOSHLAB KESIB OLISH
                    tokenClient = tokenClient.substring(7);
                    //TOKENNI VALID LIGINI TEKSHIRISH
                    if (jwtTokenProvider.validateToken(tokenClient)) {
                        //USERID ni oldik tokendan
                        String userIdFromToken = jwtTokenProvider.getUserIdFromToken(tokenClient);
                        return authService.loadByUserId(UUID.fromString(userIdFromToken));
                    }
                } else if (tokenClient.startsWith("Basic ")) {
                    String[] userNameAndPassword = getUserNameAndPassword(tokenClient);
                    UserDetails userDetails = authService.loadUserByUsername(userNameAndPassword[0]);
                    if (userDetails != null && passwordEncoder.matches(userNameAndPassword[1], userDetails.getPassword())) {
                        return userDetails;
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public String[] getUserNameAndPassword(String token) {
        // Authorization: Basic base64credentials
        String base64Credentials = token.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        // credentials = username:password
        return credentials.split(":", 2);
    }


}

