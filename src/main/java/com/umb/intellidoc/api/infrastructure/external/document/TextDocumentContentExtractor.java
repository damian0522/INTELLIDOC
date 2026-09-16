package com.umb.intellidoc.api.infrastructure.external.document;

import com.umb.intellidoc.api.domain.ports.DocumentContentExtractorPort;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class TextDocumentContentExtractor
        implements DocumentContentExtractorPort {

    @Override
    public String extract(
            byte[] content,
            String contentType,
            String filename
    ) {

        if ("text/plain".equalsIgnoreCase(contentType)) {
            return new String(
                    content,
                    StandardCharsets.UTF_8
            );
        }

        throw new IllegalArgumentException(
                "Extracción de contenido no implementada para: "
                        + contentType
        );
    }
}