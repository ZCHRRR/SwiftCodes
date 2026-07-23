package com.swiftcodes.adapter.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI swiftCodesOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("SWIFT Codes API")
                        .version("1.0.0")
                        .description(
                                "Rejestr kodów SWIFT instytucji finansowych. "
                                        + "Umożliwia odczyt pojedynczego banku po kodzie SWIFT, "
                                        + "listowanie banków po kodzie kraju ISO2, "
                                        + "dodawanie i usuwanie rekordów."));
    }
}