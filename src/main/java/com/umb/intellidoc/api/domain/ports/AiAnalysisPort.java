package com.umb.intellidoc.api.domain.ports;

import com.umb.intellidoc.api.domain.model.AnalysisResult;
import com.umb.intellidoc.api.domain.model.AnalysisTemplate;

public interface AiAnalysisPort {

    AnalysisResult analyze(
            String documentText,
            AnalysisTemplate template
    );
}