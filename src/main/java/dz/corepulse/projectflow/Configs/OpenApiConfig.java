package dz.corepulse.projectflow.Configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(description = "Default server", url = "/")
        },
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "ProjectFlow API",
                version = "v1",
                description = "REST API for managing projects, sprints, tasks, stories, epics, and permissions within Corepulse ProjectFlow."
        )
)
public class OpenApiConfig {

    @Bean
    public OpenAPI projectFlowOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("ProjectFlow API")
                        .description("REST endpoints for tracking projects, sprints, stories, tasks and their analytics.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Corepulse Team")
                                .email("support@corepulse.dz")))
                .externalDocs(new ExternalDocumentation()
                        .description("ProjectFlow Wiki")
                        .url("https://example.com/docs"));
    }
}
