package com.umb.intellidoc.api.domain.ports;

public interface DocumentContentExtractorPort {

    String extract(
            byte[] content,
            String contentType,
            String filename
    );
}