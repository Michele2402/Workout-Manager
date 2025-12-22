    package duck.workoutmanager.configuration.security;

    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.io.Decoders;
    import io.jsonwebtoken.security.Keys;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Service;

    import javax.crypto.SecretKey;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.function.Function;

    @Service
    public class JwtService {

        private String secretKey = "46615927b14f974780b4a9b2f34a6871f2974d95af0f44b8d52c34a0169bafb6be0e877ace5c58cd9ab3bdcaa23be5047d4624a4cdfd5a67ca1d944b3e6b1d82";

        public String generateToken(String email, String role) {

            Map<String, Object> claims = new HashMap<>();
            claims.put("role", role);

            return Jwts
                    .builder()
                    .claims()
                    .add(claims)
                    .subject(email)
                    .issuer("WorkoutManagerAPI")
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                    .and()
                    .signWith(generateKey())
                    .compact();
        }

        private SecretKey generateKey() {
            byte[] decode = Decoders.BASE64.decode(secretKey);

            return Keys.hmacShaKeyFor(decode);
        }

        private Claims extractClaims(String token) {
            return Jwts
                    .parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }

        private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
            Claims claims = extractClaims(token);
            return claimsResolver.apply(claims);
        }

        public String extractUsername(String token) {
            return extractClaims(token, Claims::getSubject);
        }

        private boolean isTokenExpired(String token) {
            Date expiration = extractClaims(token, Claims::getExpiration);
            return expiration.before(new Date());
        }

        public boolean isTokenValid(String token, UserDetails userDetails) {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        }

        public String extractRole(String token) {
            String role = extractClaims(token, claims -> claims.get("role", String.class));

            return role;
        }

    }
