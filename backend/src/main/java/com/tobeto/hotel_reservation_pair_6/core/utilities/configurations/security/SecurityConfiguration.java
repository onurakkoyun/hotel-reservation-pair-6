package com.tobeto.hotel_reservation_pair_6.core.utilities.configurations.security;

import com.tobeto.hotel_reservation_pair_6.core.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.tobeto.hotel_reservation_pair_6.entities.enums.Role.*;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private final AuthEntryPointJwt unauthorizedHandler;

    private static final String[] WHITE_LIST_URL = {
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/api/rooms/available-rooms",
            "/api/hotels/search",
            "/api/hotels/get-all",
            "/api/auth/**",
            "/api/reviews/getByHotelId/{hotelId}",
            //  "/api/**"
            "/actuator/**"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler))
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers(PUT,  "/api/managers/update").hasRole(MANAGER.name())
                                .requestMatchers(PUT,  "/api/guests/update").hasRole(GUEST.name())
                                .requestMatchers(POST, "/api/hotels/add").hasRole(MANAGER.name())
                                .requestMatchers(POST, "/api/rooms/add").hasRole(MANAGER.name())
                                .requestMatchers(POST, "/api/rooms/update").hasRole(MANAGER.name())
                                .requestMatchers(POST, "/api/reservations/create").hasRole(GUEST.name())
                                .requestMatchers(GET,  "/api/reservations/getAllByHotelId").hasRole(MANAGER.name())
                                .requestMatchers(GET,  "/api/reservations/getAllByGuestId").hasRole(GUEST.name())
                                .requestMatchers(PUT,  "/api/reservations/update-status").hasRole(MANAGER.name())
                                .requestMatchers(PUT,  "/api/reservations/report").hasRole(MANAGER.name())
                                .requestMatchers(PUT,  "/api/reservations/cancel").hasRole(GUEST.name())
                                .requestMatchers(POST, "/api/hotel-images/hotel/{hotelId}").hasRole(MANAGER.name())
                                .requestMatchers(GET,  "/api/financial-reports/net-income").hasRole(MANAGER.name())
                                .requestMatchers(GET,  "/api/support-tickets/hotel/{hotelId}").hasRole(MANAGER.name())
                                .requestMatchers(GET,  "/api/support-ticket-replies/reply").hasRole(MANAGER.name())
                                .requestMatchers(GET,  "/api/support-tickets/create").hasRole(GUEST.name())
                                .requestMatchers(GET,  "/api/support-tickets/guest/{guestId}").hasRole(GUEST.name())
                                .requestMatchers(POST, "/api/reviews/add").hasRole(GUEST.name())
                                .requestMatchers(GET, "/api/users/get-by-email").hasAnyRole(GUEST.name(), MANAGER.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );
        return http.build();
    }
}
