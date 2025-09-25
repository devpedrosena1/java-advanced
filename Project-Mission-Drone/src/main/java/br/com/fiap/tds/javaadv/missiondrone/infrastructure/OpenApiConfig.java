package br.com.fiap.tds.javaadv.missiondrone.infrastructure;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info( new Info()
                        .title("Example API")
                        .version("1.0")
                        .description("API Checkpoint 2 - Java Advanced.")
                );
    }

}
