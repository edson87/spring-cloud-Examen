package principalproducto.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import principalproducto.Filters.JwtRequestFilter;

@Configuration
public class AppConfiguration {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    public SecurityFilterChain filteerChain(HttpSecurity http) throws  Exception{
        // Me falta la dependencia de seguridad
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/producto/**").authenticated()
                .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
