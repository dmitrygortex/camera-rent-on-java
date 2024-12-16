//package org.example.camerarentweb.config;
//
//import org.example.camerarentweb.entities.UserRole;
//import org.example.camerarentweb.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.context.DelegatingSecurityContextRepository;
//import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
//import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
//import org.springframework.security.web.context.SecurityContextRepository;
//
//@Configuration
//public class AppSecurityConfiguration {
//    private UserRepository userRepository;
//
//    @Autowired
//    public AppSecurityConfiguration(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
//        http
//                .authorizeHttpRequests(
//                        authorizeHttpRequests ->
//                                authorizeHttpRequests.
//                                        requestMatchers(PathRequest.toStaticResources().atCommonLocations())
//                                        .permitAll()
//                                        .requestMatchers("/favicon.ico").permitAll()
//                                        .requestMatchers("/error").permitAll()
//                                        .requestMatchers("/", "/auth/login", "/auth/register", "auth/login-error")
//                                        .permitAll().
//                                        requestMatchers("/account/").authenticated().
//                                        requestMatchers("/admin-edit/", "/admin-panel").hasAnyRole(UserRole.ADMIN.name()).
//                                        anyRequest().authenticated()
//                )
//                .formLogin(
//                        (formLogin) ->
//                                formLogin.
//                                        loginPage("/auth/login").
//                                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
//                                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
//                                        defaultSuccessUrl("/").
//                                        failureForwardUrl("/auth/login-error")
//                )
//                .logout((logout) ->
//                        logout.logoutUrl("/auth/logout").
//                                logoutSuccessUrl("/").
//                                invalidateHttpSession(true)
//                ).securityContext(
//                        securityContext -> securityContext.
//                                securityContextRepository(securityContextRepository)
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public SecurityContextRepository securityContextRepository() {
//        return new DelegatingSecurityContextRepository(
//                new RequestAttributeSecurityContextRepository(),
//                new HttpSessionSecurityContextRepository()
//        );
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new Argon2PasswordEncoder(16, 32, 4, 16 * 1024, 3);
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() { return new AppUserDetailsService(userRepository); }
//}
