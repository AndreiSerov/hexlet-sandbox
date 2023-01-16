package org.example.security;

import org.example.config.SecurityConfig;
import org.example.exception.ServiceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

/**
 * @author andreiserov
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer";

    private final RequestMatcher publicUrls;
    private final JwtHelper jwtHelper;

    public JWTAuthorizationFilter(final RequestMatcher publicUrls,
                                  final JwtHelper jwtHelper) {
        this.publicUrls = publicUrls;
        this.jwtHelper = jwtHelper;
    }

    @Override
    protected boolean shouldNotFilter(final @NotNull HttpServletRequest request) {
        return publicUrls.matches(request);
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final @NotNull HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        final var authToken = Optional.ofNullable(request.getHeader(AUTHORIZATION))
            .map(header -> header.replaceFirst("^" + BEARER, ""))
            .map(String::trim)
            .map(jwtHelper::getClaims)
            .map(claims -> claims.get(SPRING_SECURITY_FORM_USERNAME_KEY))
            .map(Object::toString)
            .map(this::buildAuthToken)
            .orElseThrow(() -> ServiceException.unauthorized("Invalid Token"));


        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken buildAuthToken(final String username) {
        return new UsernamePasswordAuthenticationToken(
            username,
            null,
            SecurityConfig.DEFAULT_AUTHORITIES
        );
    }
}
