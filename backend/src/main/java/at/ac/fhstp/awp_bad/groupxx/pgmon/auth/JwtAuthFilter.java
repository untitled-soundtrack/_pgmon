package at.ac.fhstp.awp_bad.groupxx.pgmon.auth;

import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = httpServletRequest.getHeader("Authorization");
        boolean sendError = false;

        if (token != null && token.split(" ").length >= 2) {
            token = (token.split(" ")[1]).trim();
        }
        if (token != null && token.length() > 0 && !token.equals("undefined")) {
            JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(token);
            Authentication authentication = null;
            try {
                authentication = authenticationManager.authenticate(jwtAuthenticationToken);
            } catch (JwtException e) {
                sendError = true;
            }
            if (authentication == null || !authentication.isAuthenticated()) {
                sendError = true;
            }
        }
        if (sendError) {
            httpServletResponse.sendError(403, "Invalid Token");
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
