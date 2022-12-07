package projectJEE.sw;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] EVERYONE_LIST_URLS = {
            "/", "/index", "/register", "/login", "/bestiary"
    };

    private static final String[] USER_LIST_URLS = {
            "/monsters", "/runes", "/artifacts", "/uploadJSON"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(EVERYONE_LIST_URLS)
                .permitAll()
                .antMatchers(USER_LIST_URLS)
                .hasAuthority("SUMMONER").anyRequest()
                .permitAll().and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll().and()
                .logout().permitAll();
        return http.build();
    }
}