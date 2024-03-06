package br.com.peixoto.atacadista.configuration.springdoc;

import br.com.peixoto.atacadista.exception.ErrorMessage;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.tags.Tag;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("App Atacadista")
                        .version("v1")
                        .description("Aplicação para controle de Vendas da maior atacadista de Uberlândia-MG")
                ).externalDocs(new ExternalDocumentation()
                        .description("Acesse nosso Help")
                        .url("https://www.peixoto.com.br")
                ).tags(Arrays.asList(
                        new Tag().name("Categoria").description("Gerenciamento de de cadastro de categorias dos produtos."),
                        new Tag().name("Cliente").description("Gerenciamento de cadastro de clientes."),
                        new Tag().name("Pedido").description("Gerenciamento de cadastro de pedidos."),
                        new Tag().name("Produto").description("Gerenciamento de cadastro de Produtos.")
                )).components(new Components()
                        .schemas(gerarSchemas()));
    }
    private Map<String, Schema> gerarSchemas() {

        final Map<String, Schema> schemaMap = new HashMap<>();
        final Map<String, Schema> errorMessageSchema = ModelConverters.getInstance().read(ErrorMessage.class);

        schemaMap.putAll(errorMessageSchema);

        return schemaMap;
    }
}
