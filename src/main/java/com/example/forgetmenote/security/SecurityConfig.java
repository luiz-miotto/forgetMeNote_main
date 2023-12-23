package com.example.forgetmenote.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;
///IMPORTANT NOTE ABOUT CODE THAT IS COMMENTED OUT:
//the heavy use of comments on this file is to remove the JWT authentication component of this app
//JWT removal is temporary and only in place so as to not impede my progress with the rest of the app
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
    @Autowired
    UserDetailsServiceImpl userDetailsService

*/

    /*
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;



     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(authorizeConfig ->{
                    authorizeConfig.requestMatchers("/").permitAll();
                    authorizeConfig.requestMatchers("/home").permitAll();
                    authorizeConfig.requestMatchers("/signUpForm").permitAll();
                    authorizeConfig.requestMatchers("/login").permitAll();
                    authorizeConfig.requestMatchers("/ex/foos").permitAll();
                    authorizeConfig.requestMatchers("/api/v1/publish").permitAll();
                    authorizeConfig.requestMatchers("/emailing/sendEmail").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
                .formLogin().loginPage("/login")
                .and()
                .csrf().ignoringRequestMatchers("/emailing/sendEmail")
                .and()
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user")
                        .password("{noop}Pass123")
                        .authorities("ROLE_user")
                        .build()
        );
    }

    /*
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    */


    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/**").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/signUpForm").permitAll()
                                .requestMatchers("/createUser").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/api/auth/signin").permitAll()
                                .requestMatchers("/signin").permitAll()
                                .requestMatchers("/createEvent").permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin().loginPage("/login").loginProcessingUrl("/auth/api/signin").permitAll();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    */






}
