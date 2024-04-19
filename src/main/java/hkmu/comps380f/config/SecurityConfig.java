package hkmu.comps380f.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/book/list/**").permitAll()
                        .requestMatchers("/book/view/**").permitAll()
                        .requestMatchers("/user/create").permitAll()
                        .requestMatchers("/user/edit/**").hasRole("USER")
                        .requestMatchers("/user/view/**").hasRole("USER")
                        .requestMatchers("/user/cart/**").hasRole("USER")
                        .requestMatchers("/user/orders/**").hasRole("USER")
                        .requestMatchers("/book/delete/**").hasRole("ADMIN")
                        .requestMatchers("/book/create/**").hasRole("ADMIN")
                        .requestMatchers("/book/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/user/personal").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/user/cart").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/user/favourite").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/home/**").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/book/list")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .rememberMe(remember -> remember
                        .key("uniqueAndSecret")
                        .tokenValiditySeconds(0)
                )
                .httpBasic(withDefaults());
        return http.build();
    }
}
