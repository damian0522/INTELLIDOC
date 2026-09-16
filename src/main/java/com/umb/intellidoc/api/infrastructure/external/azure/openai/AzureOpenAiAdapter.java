package com.umb.intellidoc.api.infrastructure.external.azure.openai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umb.intellidoc.api.domain.model.AnalysisResult;
import com.umb.intellidoc.api.domain.model.AnalysisTemplate;
import com.umb.intellidoc.api.domain.ports.AiAnalysisPort;
import com.openai.client.OpenAIClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import org.springframework.stereotype.Component;

@Component
public class AzureOpenAiAdapter implements AiAnalysisPort {

    private final OpenAIClient openAIClient;
    private final AzureOpenAiProperties properties;
    private final ObjectMapper objectMapper;

    public AzureOpenAiAdapter(
            OpenAIClient openAIClient,
            AzureOpenAiProperties properties,
            ObjectMapper objectMapper
    ) {
        this.openAIClient = openAIClient;
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Override
    public AnalysisResult analyze(
            String documentText,
            AnalysisTemplate template
    ) {

        String prompt = buildPrompt(documentText, template);

        ResponseCreateParams params =
                ResponseCreateParams.builder()
                        .model(properties.getDeployment())
                        .input(prompt)
                        .maxOutputTokens(3000)
                        .build();

        Response response = openAIClient
                .responses()
                .create(params);

        String outPutText = response.output().stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(outputText -> outputText.text())
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Azure OpenAI no devolvió contenido de texto"
                        )
                );

        try {
            return objectMapper.readValue(
                    outPutText,
                    AnalysisResult.class
            );
        } catch (Exception exception) {
            throw new IllegalStateException(
                    "Azure OpenAI devolvió una respuesta que no pudo convertirse "
                            + "en AnalysisResult: " + outPutText,
                    exception
            );
        }
    }

    private String buildPrompt(
            String documentText,
            AnalysisTemplate template
    ) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                Eres el motor de análisis documental de IntelliDoc.

                Tu función es extraer información estructurada de un documento.

                Debes analizar exclusivamente el contenido proporcionado.
                Nunca inventes información.

                Tipo de documento:
                """)
                .append(template.documentType())
                .append("\n\n");

        prompt.append("Objetivo del análisis:\n")
                .append(template.objective())
                .append("\n\n");

        prompt.append("Campos solicitados:\n");

        template.fields().forEach(field -> {

            prompt.append("- ")
                    .append(field.name())
                    .append(": ")
                    .append(field.description())
                    .append(" | tipo: ")
                    .append(field.expectedType())
                    .append(" | obligatorio: ")
                    .append(field.required());

            if (field.pattern() != null) {
                prompt.append(" | patrón: ")
                        .append(field.pattern());
            }

            prompt.append("\n");
        });

        prompt.append("""
                
                Reglas de extracción:

                1. Utiliza únicamente información presente en el documento.
                2. No inventes valores.
                3. Si un campo no aparece, utiliza null.
                4. La información debe corresponder al documento analizado.
                5. Respeta el tipo de dato solicitado.
                6. Identifica información adicional relevante.
                7. Responde exclusivamente con JSON válido.
                8. No incluyas Markdown.
                9. No incluyas explicaciones fuera del JSON.

                Estructura esperada:

                {
                  "documentType": "...",
                  "requestedFields": {
                    "nombreCampo": {
                      "value": "...",
                      "confidence": 0.0,
                      "sourceText": "..."
                    }
                  },
                  "additionalFindings": {}
                }

                Documento:
                --------------------
                """);

        prompt.append(documentText);

        prompt.append("""
                
                --------------------
                Fin del documento.
                """);

        return prompt.toString();
    }
}