package com.PASTRACK.PASTRACK.Security;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.PASTRACK.PASTRACK.Security.jwt.AuthEntryPointJwt;
import com.PASTRACK.PASTRACK.Security.jwt.AuthTokenFilter;



/**
 * @author Get Arrays (https://www.getarrays.io/)
 * @version 1.0
 * @since 7/10/2021
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthEntryPointJwt jwtAuthEntryPoint;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthTokenFilter jwtRequestFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                // .antMatcher("/api/v1/**").cors().and()
                .authorizeRequests().antMatchers("/api/login").permitAll().and()
                .authorizeRequests().antMatchers("/api/logout").permitAll().and()
                // .authorizeRequests().antMatchers("/api/register/**").hasAnyAuthority("ADMIN").and()
                // .authorizeRequests().antMatchers("/api/user/**").
                .authorizeRequests().antMatchers("/api/register/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/user/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/matpel/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/postingan/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/kelas/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/semester/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/angkatan/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/dashboard/guru/**").permitAll().and()

                .authorizeRequests().antMatchers("/api/peminatan/**").permitAll()
                .anyRequest().authenticated().and()
                // .antMatchers("/api/v1/pasien/register").permitAll()
                // .antMatchers("/api/v1/pasien/").hasAuthority("PASIEN").and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

} 

