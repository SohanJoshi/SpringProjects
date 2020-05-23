package com.sohan.security.jwtsecurity.security.validator;

import com.sohan.security.jwtsecurity.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secretKey = "SECRET_KEY";

    public JwtUser validate(String token) {
        JwtUser jwtUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();


            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId((Long.parseLong((String) body.get("userId"))));
            jwtUser.setRole((String) body.get("role"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jwtUser;
    }
}
