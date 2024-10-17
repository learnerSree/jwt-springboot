package com.experiments.filter;

import com.experiments.service.impl.JWTService;
import com.experiments.service.impl.MyUserDetailesService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader( "Authorization");
        String token = null;
        String userName = null;

        if( authHeader != null && authHeader.startsWith("Bearer ")){

            token = authHeader.substring( 7 );
            userName = jwtService.extractUserName( token );
        }

        if( userName != null && SecurityContextHolder.getContext().getAuthentication() == null ){

            UserDetails userDetails = applicationContext.getBean(MyUserDetailesService.class).loadUserByUsername( userName );

            if ( jwtService.validateToken( token, userDetails ) ){

                UsernamePasswordAuthenticationToken upToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                upToken.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ) );

                SecurityContextHolder.getContext().setAuthentication( upToken );
            }
            filterChain.doFilter( request, response );
        }

    }
}
