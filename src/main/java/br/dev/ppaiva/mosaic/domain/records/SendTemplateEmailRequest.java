package br.dev.ppaiva.mosaic.domain.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;

public record SendTemplateEmailRequest(
    @NotBlank @Email String to,
    @NotBlank String templateId,
    Map<String, Object> templateData,
    String messageKey
) {}

