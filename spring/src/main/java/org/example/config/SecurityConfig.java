package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.ServiceException;
import org.example.repository.PersonRepository;
import org.example.security.JWTAuthenticationFilter;
import org.example.security.JWTAuthorizationFilter;
import org.example.security.JwtHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

/**
 * @author andreiserov
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    public static final List<GrantedAuthority> DEFAULT_AUTHORITIES = List.of(new SimpleGrantedAuthority("USER"));

    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;
    private final AuthenticationConfiguration authConfiguration;
    private final ObjectMapper mapper;


    public SecurityConfig(UserDetailsService userDetailsService,
                          JwtHelper jwtHelper,
                          AuthenticationConfiguration authConfiguration,
                          ObjectMapper mapper) {
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
        this.authConfiguration = authConfiguration;
        this.mapper = mapper;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final RequestMatcher loginRequest = new AntPathRequestMatcher("/login");

        final RequestMatcher publicUrls = new OrRequestMatcher(
            loginRequest,
            new AntPathRequestMatcher("/persons"),
//            new NegatedRequestMatcher(
                new AntPathRequestMatcher("/**")
//            )
        );

        final var authenticationFilter = new JWTAuthenticationFilter(
            authenticationManager(),
            loginRequest,
            jwtHelper,
            mapper
        );

        final var authorizationFilter = new JWTAuthorizationFilter(
            publicUrls,
            jwtHelper
        );

        return http.csrf().disable()
            .authorizeRequests()
            .requestMatchers(publicUrls).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(authenticationFilter)
            .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .logout().disable()
            .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
            .ignoring()
            .antMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
