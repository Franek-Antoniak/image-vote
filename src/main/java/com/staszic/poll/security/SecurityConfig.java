package com.staszic.poll.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    List<String> users = IntStream.rangeClosed(1, 30).mapToObj(Integer::toString)
            .map(x -> "user" + x)
            .collect(Collectors.toList());
    List<String> passwords = List.of("uf8t3$Qa", "JQx$fr38", "T!7p3Dy%", "X92Vfs#N", "yr&kS3T!", "sZpD8#C5", "W!&Na7HS",
            "S#qnNk6g", "sJRD$5eB", "c#hrL38G", "Qvh#5N4!", "Lq7VS!5R", "F!mV8c9A", "xB3#9!t7", "DbA!xe98", "S%4zgMPF",
            "ge8Bn$C5", "Jf%#zP2L", "yL2p#P6f", "Wbpx%H69", "fA3K&h$G", "SW5N!wrq", "g$2UN#%5", "t#KTx3u6", "mT8NP%b$",
            "uMS&7KwA", "r#yT2mZs", "kW5!qay$", "S#aGgV57", "Tc!2&r$J");

    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        for (int index = 0; index < users.size(); index++) {
            String username = users.get(index);
            String password = passwords.get(index);
            String authorities = "USER";
            auth.inMemoryAuthentication().withUser(username).password(passwordEncoder().encode(password)).roles(authorities);
        }
        auth.inMemoryAuthentication().withUser("admin77").password(passwordEncoder().encode("s1t@3sz$ic")).roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login.html?error=true")
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }


}
