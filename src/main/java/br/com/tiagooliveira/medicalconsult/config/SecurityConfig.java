package br.com.tiagooliveira.medicalconsult.config;

import io.swagger.io.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] PUBLIC_MATCHERS ={
            "/h2-console/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
    private static final String[] PUBLIC_MATCHERS_POST = {
            "/usuarios",
            "/consultas"
    };
    private static final String[] PUBLIC_MATCHERS_PUT = {
            "/usuarios/**",
            "/consultas/**"
    };
    private static final String[] PUBLIC_MATCHERS_GET = {
            "/usuarios/**",
            "/consultas/**"
    };
    private static final String[] PUBLIC_MATCHERS_DELETE = {
            "/usuarios/**",
            "/consultas/**"
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .headers(h -> h.frameOptions().disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST)
                        .hasAnyRole("ADMIN", "SECRETARIO")
                        .requestMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_PUT)
                        .hasAnyRole("ADMIN", "SECRETARIO")
                        .requestMatchers(HttpMethod.DELETE, PUBLIC_MATCHERS_DELETE)
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET)
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        
        auth.inMemoryAuthentication()
                .withUser("Fulano").password(passwordEncoder.encode("123456")).roles("ADMIN")
                .and()
                .withUser("Beltrano").password(passwordEncoder.encode("123456")).roles("SECRETARIO")
                .and()
                .withUser("Sicrano").password(passwordEncoder.encode("123456")).roles("PACIENTE");
    }
}
