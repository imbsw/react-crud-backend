package com.example.vms.config.filter;

import com.example.vms.config.security.JwtAuthenticationProvider;
import com.example.vms.service.impl.AccUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final JwtAuthenticationProvider tokenService;
    private final AccUserDetailsService accUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = null;
        String jwt = null;

        Optional<String> hashToken = Optional.ofNullable(request.getHeader("Authorization"));
        if(hashToken.isPresent() && hashToken.get().startsWith("Bearer ")){
            jwt = hashToken.get().substring(7);
            try {
                username = tokenService.getUsernameFromToken(jwt);
            }catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token has expired");
            }
        }

        if(Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())){
            UserDetails userDetails = this.accUserDetailsService.loadUserByUsername(username);
            if (Boolean.TRUE.equals(tokenService.validateToken(jwt, userDetails))){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token replaced or expired.");
                return;
            }
        }

        filterChain.doFilter(request,response);
    }
}
