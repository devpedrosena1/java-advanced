package br.com.fiap.tds.javaadv.Library.infrastructure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info( new Info()
                        .title("API de exemplo")
                        .version("1.0")
                        .description("API do cruso de Java Advanced 2tdsAgo.")
                );
    }

}
