package com.MoneyTrackr.MoneyTrackr.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.MoneyTrackr.MoneyTrackr.security.jwt.AuthFilterToken;
import com.MoneyTrackr.MoneyTrackr.security.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter unauthorized;
    
    private static final String[] PUBLIC_MATCHERS_POST = {
			"/api/v1/users**",
			"/api/v1/auth/**"
	};
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    	
    	return configuration.getAuthenticationManager();
    }
    
    @Bean
    public AuthFilterToken authFilterToken() {
    	return new AuthFilterToken();
    }
    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	
    	httpSecurity.cors(Customizer.withDefaults());
    	httpSecurity.csrf(csrf -> csrf.disable())
    	.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorized))
    	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    	.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST,PUBLIC_MATCHERS_POST).permitAll()
    			.anyRequest().authenticated());
    	
    	httpSecurity.addFilterBefore(authFilterToken(), UsernamePasswordAuthenticationFilter.class);
    	
    	return httpSecurity.build();
    }
    
}
