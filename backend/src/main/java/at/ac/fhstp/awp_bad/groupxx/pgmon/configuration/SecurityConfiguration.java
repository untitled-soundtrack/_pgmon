package at.ac.fhstp.awp_bad.groupxx.pgmon.configuration;

import at.ac.fhstp.awp_bad.groupxx.pgmon.auth.JwtAuthFilter;
import at.ac.fhstp.awp_bad.groupxx.pgmon.auth.JwtAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public SecurityConfiguration(JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http.csrf().disable();
        http.cors();
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(new JwtAuthFilter(authenticationManager), BasicAuthenticationFilter.class)
                .exceptionHandling().and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("**/api/sightings/**").permitAll()
                .antMatchers("**/api/users/login").permitAll()
                .antMatchers("**/api/users/register").permitAll()
                .antMatchers("**/api/pokemon").permitAll()
                .antMatchers("/pokemon-icons/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/pokemon/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/sightings/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}
