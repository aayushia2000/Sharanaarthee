package com.hms.authService.config;

import com.hms.authService.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
//    authentication manager authenticate user from database, everytime hs/she tries to login so if u r not user of any particular webApp, you will not get token
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
    /*CSRF (Cross-Site Request Forgery) ek security vulnerability hoti hai jo web applications ko affect karti hai. Isme attackers, user ke behalf mein unintended requests generate kar sakte hain, jinki wajah se user ke data ya actions compromise ho sakte hain.

Security Filter Chain ek concept hai jo web applications mein security measures implement karne mein madad karta hai. Ye chain various security filters ko include karta hai jo requests ko process karte hain, jaise ki authentication, authorization, validation, etc.

CSRF attack ke case mein, Security Filter Chain CSRF protection provide karta hai by:

CSRF Tokens: Ek common technique CSRF protection ke liye hai token-based approach. Isme, har request ke saath server-generated unique token bheja jata hai jo user ke session se associated hota hai. Jab user koi action perform karta hai, tab ye token validate hota hai to ensure ki request legitimate hai.

SameSite Cookies: SameSite attribute ko cookies mein set karke browser ko instruct kiya jata hai ki cookies sirf same origin requests ke saath hi bheje jayein, reducing CSRF risks.

Custom Headers: Some applications use custom headers for CSRF protection. Ye headers server dwara verify kiye jate hain, ensuring that the request originates from the expected source.

Security Filter Chain CSRF protection ko implement karke, applications CSRF attacks se bach sakte hain, preventing unauthorized actions and data leakage. Ye ek layer provide karta hai jo requests ko filter karta hai, allowing only legitimate requests to pass through.*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers("auth/register","auth/token","auth/validate").permitAll()
                .and()
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
