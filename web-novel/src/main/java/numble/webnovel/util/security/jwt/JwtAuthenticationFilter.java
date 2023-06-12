package numble.webnovel.util.security.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static numble.webnovel.util.security.jwt.JwtAuthenticationProvider.AUTHORIZATION_TOKEN;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        if(uri.contains("api/auth/sign-up") || uri.contains("api/auth/login")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtAuthProvider.resolveToken(request, AUTHORIZATION_TOKEN);

        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }

        jwtAuthProvider.validateAccessToken(request, response);
        Claims info = jwtAuthProvider.getUserInfoFromToken(token, false);
        setAuthentication(info.getSubject());
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String username){
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtAuthProvider.createAuthentication(username);
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}
