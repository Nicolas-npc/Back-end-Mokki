package com.example.demo.Config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Back-end Mokki",
                version = "1.0",
                description = "API REST para gestion de usuarios, productos, carrito, pedidos y pagos con autenticacion JWT.",
                contact = @Contact(name = "Equipo Mokki"),
                license = @License(name = "Uso academico")
        )
)
public class OpenApiConfig {
    
}
