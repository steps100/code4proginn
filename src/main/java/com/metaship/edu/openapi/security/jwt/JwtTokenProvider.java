package com.metaship.edu.openapi.security.jwt;

import com.alibaba.fastjson.JSON;
import com.metaship.edu.openapi.security.MyUserDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    /**
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
     * microservices environment, this key would be kept on a config-server.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    @Autowired
    private MyUserDetails myUserDetails;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * 生成JWT
     * @param payload 签名信息
     * @param secretKey 秘钥
     * @return
     */
    public String createToken(JwtPayload payload, String secretKey) {
        String payloadString = JSON.toJSONString(payload);
        Claims claims = Jwts.claims().setSubject(payloadString);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(validity)
                .compact();
    }

    public boolean validateToken(String token, String channel) {
        try {
            Jwts.parser().setSigningKey(JwtUtils.getSecretKey(channel)).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.info("Invalid JWT signature.");
            logger.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            logger.info("Invalid JWT token.");
            logger.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            logger.info("Expired JWT token.");
            logger.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.info("Unsupported JWT token.");
            logger.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            logger.info("JWT token compact of handler are invalid.");
            logger.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    public Authentication getAuthentication(String token, String channel) {
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token, channel));
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    public String getUsername(String token, String channel) {
        JwtPayload payload = getJWSPayload(token, JwtUtils.getSecretKey(channel));
        return payload.getChannel();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public JwtPayload getJWSPayload(String token, String secretKey) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return JSON.parseObject(claims.getSubject(), JwtPayload.class);
    }

    public JwtPayload getJWTPayload(String token) {
        Claims claims = Jwts.parser().parseClaimsJwt(token).getBody();
        return JSON.parseObject(claims.getSubject(), JwtPayload.class);
    }
}
