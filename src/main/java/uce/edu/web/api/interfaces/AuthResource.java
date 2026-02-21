package uce.edu.web.api.interfaces;

import java.time.Instant;
import java.util.Set;

import uce.edu.web.api.application.UserService;
import uce.edu.web.api.domain.User;
import jakarta.inject.Inject;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Produces;

@Path("/auth")
@ApplicationScoped
public class AuthResource {

    @Inject
    UserService userService;

    @GET
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public TokenResponse token(
            @QueryParam("user") String username,
            @QueryParam("password") String password) {

        User user = userService.validateUser(username, password);

        if (user != null) {
            System.out.println("User found");
            String role = user.getRole();
            String issuer = "consultorio-auth";
            long ttl = 3600;

            Instant now = Instant.now();
            Instant exp = now.plusSeconds(ttl);

            String jwt = Jwt.issuer(issuer)
                    .subject(username)
                    .groups(Set.of(role))
                    .issuedAt(now)
                    .expiresAt(exp)
                    .sign();
            System.out.println("User found: " + jwt);
            return new TokenResponse(jwt, exp.getEpochSecond(), role);
        } else {
            System.out.println("User not found");
            return null; // O lanzar una excepción de no autorizado/forbidden
        }
    }

    public static class TokenResponse {
        public String accessToken;
        public long expiresAt;
        public String role;

        public TokenResponse() {
        }

        public TokenResponse(String accessToken, long expiresAt, String role) {
            this.accessToken = accessToken;
            this.expiresAt = expiresAt;
            this.role = role;
        }
    }
}
