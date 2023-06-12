package numble.webnovel.util.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import numble.webnovel.util.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider {

    public static final String AUTHORIZATION_TOKEN = "AUTH_TOKEN";
    private static final String BEARER_TYPE = "BEARER";

    @Value("${jwt.access-token-expire-time}")
    private long accessTokenExpireTime;

    @Value("${jwt.secret}")
    private String accessTokenSecretKey;
    private Key accessTokenKey;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    protected void init(){
        byte[] accessTokenBytes = Base64.getDecoder().decode(accessTokenSecretKey);
        accessTokenKey = Keys.hmacShaKeyFor(accessTokenBytes);
    }

    // token 확인
    public String resolveToken(HttpServletRequest request, String authorization){
        String token = request.getHeader(authorization);
        if(StringUtils.hasText(token) && token.startsWith(BEARER_TYPE)){
            return token;
        }
        return null;
    }

    // token 생성
    public String createAccessToken(String email){
        Date date = new Date();
        return BEARER_TYPE + Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpireTime))
                .setIssuedAt(date)
                .signWith(accessTokenKey, signatureAlgorithm)
                .compact();
    }

    // 유효성 검사
    public void validateAccessToken(HttpServletRequest request, HttpServletResponse response){
        try{
            Jwts.parserBuilder().setSigningKey(accessTokenKey).build().parseClaimsJwt(request.getHeader(AUTHORIZATION_TOKEN));
        }catch (Exception e){

        }
    }

    public Claims getUserInfoFromToken(String token, boolean isRefresh){
        if(isRefresh){
            try{
                return Jwts.parserBuilder().setSigningKey(accessTokenKey).build().parseClaimsJwt(token).getBody();
            }catch (ExpiredJwtException e){
                return e.getClaims();
            }
        }else{
            return Jwts.parserBuilder().setSigningKey(accessTokenKey).build().parseClaimsJwt(token).getBody();
        }
    }

    public Authentication createAuthentication(String username){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, null);
    }
}
