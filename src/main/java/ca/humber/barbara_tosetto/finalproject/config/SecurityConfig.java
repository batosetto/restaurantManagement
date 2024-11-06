package ca.humber.barbara_tosetto.finalproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UserDetailsService myUserDetailService;
    public SecurityConfig(UserDetailsService myUserDetailService){
        this.myUserDetailService = myUserDetailService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorize) -> authorize

                .requestMatchers("/restaurant/home", "/", "/login/**", "/register/**").permitAll()
                .requestMatchers("/restaurant/menu/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/restaurant/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(customLogin -> {
                    customLogin.loginPage("/login").defaultSuccessUrl("/restaurant/home", true).permitAll();
                }
        ).logout(l -> l
                .logoutUrl("/logout")
                .permitAll()
        );
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    // Password encrypting
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer ignoreResources(){
        return (webSecurity) -> webSecurity
                .ignoring()
                .requestMatchers("/css/**","/h2-console/**");
    }
}

