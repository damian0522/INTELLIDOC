package com.umb.intellidoc.api.infrastructure.external.azure.openai;

import com.azure.core.credential.TokenCredential;
import com.azure.identity.AuthenticationUtil;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.credential.BearerTokenCredential;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AzureOpenAiProperties.class)
public class AzureOpenAiConfiguration {

    @Bean
    public TokenCredential azureCredential() {
        return new DefaultAzureCredentialBuilder()
                .build();
    }

    @Bean
    public OpenAIClient openAIClient(
            AzureOpenAiProperties properties,
            TokenCredential credential
    ) {

        return OpenAIOkHttpClient.builder()
                .baseUrl(properties.getEndpoint())
                .credential(
                        BearerTokenCredential.create(
                                AuthenticationUtil.getBearerTokenSupplier(
                                        credential,
                                        "https://ai.azure.com/.default"
                                )
                        )
                )
                .build();
    }
}