package com.tobeto.hotel_reservation_pair_6.core.utilities.configurations.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    //Bu configürasyon, Swagger ui içerisinde Api ile birlikte JWT token gönderilmesine olanak tanıdık.
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .version("1.0")
                        .description("API documentation for your application"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("All API's \uD83D\uDCE2")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("Permitted API's \uD83D\uDEE1\uFE0F")
                .pathsToMatch("/api/auth/**", "/api/hotels/search",
                        "/api/rooms/available-rooms")
                .build();
    }

    @Bean
    public GroupedOpenApi managerSecureApi() {
        return GroupedOpenApi.builder()
                .group("Manager JWT Token API's \uD83D\uDD10")
                .pathsToMatch("/hotels/add", "/api/rooms/add", "/api/rooms/update",
                        "/api/reservations/getAllByHotelId", "/api/reservations/update-status",
                        "/api/reservations/getReportByHotelId", "/api/financial-reports/**",
                        "/api/hotel-images/hotel/{hotelId}", "/api/support-tickets/hotel/{hotelId}",
                        "/api/support-ticket-replies/reply", "/api/managers/update")
                .build();
    }

    @Bean
    public GroupedOpenApi guestSecureApi() {
        return GroupedOpenApi.builder()
                .group("Guest JWT Token API's \uD83D\uDD10")
                .pathsToMatch("/api/reservations/create", "/api/reservations/getAllByGuestId",
                        "/api/reservations/cancel", "/api/support-tickets/create",
                        "/api/support-tickets/guest/{guestId}", "/api/guests/update")
                .build();
    }
}
