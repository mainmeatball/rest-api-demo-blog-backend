package helloblog.security;

import helloblog.security.jwt.JwtConfigurer;
import helloblog.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private TokenProvider tokenProvider;
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, TokenProvider tokenProvider, UnauthorizedEntryPoint unauthorizedEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.unauthorizedEntryPoint = unauthorizedEntryPoint;
    }

    // TODO: implement auto-login after registration

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()

            .csrf().disable()

            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

            .authorizeRequests()
                .antMatchers("/login", "/sign_up", "/messages", "/who").permitAll()
                .antMatchers("/messages/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()

            .exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint)
                .and()

            .apply(securityConfigurerAdapter());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private JwtConfigurer securityConfigurerAdapter() {
        return new JwtConfigurer(tokenProvider);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
