package com.dev.lembrete.security;


import com.dev.lembrete.repository.UserRepository;
import com.dev.lembrete.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception
    {
        return new UserService(userRepository);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/user/secure").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/test").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/item/**").permitAll()
                .antMatchers("/","/h2-console/**", "/h2/**").permitAll()
                .anyRequest().authenticated()//.and().formLogin().loginPage("/user/login").permitAll()
                .and()
                //.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/logout").permitAll();

                // filtra requisições de login
                .addFilter(new JWTLoginFilter(authenticationManager()))

                // filtra outras requisições para verificar a presença do JWT no header
                .addFilter(new JWTAuthorizationFilter(authenticationManager(),userService));
                httpSecurity
                .headers().frameOptions().sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*// cria uma conta default
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}password")
                .roles("ADMIN");*/

        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(new BCryptPasswordEncoder());

    }
}