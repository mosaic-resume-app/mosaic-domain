package br.dev.ppaiva.mosaic.domain.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SendEmailRequest(
    @NotBlank @Email String to,
    @NotBlank String subject,
    String htmlBody,
    String messageKey
) {}
