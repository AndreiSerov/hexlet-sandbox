package org.example.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.api.request.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author andreiserov
 */

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper mapper;
    private final JwtHelper jwtHelper;

    public JWTAuthenticationFilter(final AuthenticationManager authenticationManager,
                                   final RequestMatcher loginRequest,
                                   final JwtHelper jwtHelper,
                                   ObjectMapper mapper
    ) {
        super(authenticationManager);
        super.setRequiresAuthenticationRequestMatcher(loginRequest);
        this.jwtHelper = jwtHelper;
        this.mapper = mapper;
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response) throws AuthenticationException {
        final LoginRequest loginData = getLoginData(request);
        final var authRequest = new UsernamePasswordAuthenticationToken(
            loginData.email(),
            loginData.password()
        );
        setDetails(request, authRequest);
        return getAuthenticationManager().authenticate(authRequest);
    }

    private LoginRequest getLoginData(final HttpServletRequest request) throws AuthenticationException {
        try {
            final String json = request.getReader()
                .lines()
                .collect(Collectors.joining());
            return mapper.readValue(json, LoginRequest.class);
        } catch (IOException e) {
            throw new BadCredentialsException("Can't extract login data from request");
        }
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request,
                                            final HttpServletResponse response,
                                            final FilterChain chain,
                                            final Authentication authResult) throws IOException {
        final UserDetails user = (UserDetails) authResult.getPrincipal();
        final String token = jwtHelper.generate(Map.of(SPRING_SECURITY_FORM_USERNAME_KEY, user.getUsername()));

        response.getWriter().println(token);
    }
}
