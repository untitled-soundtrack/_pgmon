package at.ac.fhstp.awp_bad.groupxx.pgmon.auth;

import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final TokenService tokenService;

    public JwtAuthenticationProvider(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

        User user = null;
        String jwt = jwtAuthenticationToken.getJwt();

        user = tokenService.parseToken(jwt);

        jwtAuthenticationToken = new JwtAuthenticationToken(grantedAuths, user, jwt);
        SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);

        return jwtAuthenticationToken;
    }
    @Override
    public boolean supports(Class<?> c) {
        return c.equals(JwtAuthenticationToken.class);
    }
}
