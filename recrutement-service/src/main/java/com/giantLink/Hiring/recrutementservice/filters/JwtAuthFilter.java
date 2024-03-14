package com.giantLink.Hiring.recrutementservice.filters;

import com.giantLink.Hiring.recrutementservice.services.Impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.giantLink.Hiring.recrutementservice.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtility jwtUtility;
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//          Check request has header "Authorization"
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            doFilter(request, response, filterChain);
            return;
        }

//          Check header has "Bearer"
        if (!authHeader.contains("Bearer")) {
            doFilter(request, response, filterChain);
            return;
        }

//          Extract token from header
        String token = authHeader.substring(7);

//          Check token is valid
        if (!jwtUtility.isTokenValid(token)){
            doFilter(request, response, filterChain);
            return;
        }

//          Extract email from token
        String cin = jwtUtility.extractUsername(token);
        
//          Generate UserDetails
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(cin);

//          Authenticate user for the next filter (UsernamePasswordAuthenticationFilter)
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//          Put user in app context
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        doFilter(request, response, filterChain);
    }
}
