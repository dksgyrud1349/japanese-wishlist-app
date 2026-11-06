package com.project.japanwishlist.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI japanWishlistOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Japan Wishlist")
                        .description("일본 상품 검색 및 위시리스트 관리 API")
                        .version("v1"));
    }
}
