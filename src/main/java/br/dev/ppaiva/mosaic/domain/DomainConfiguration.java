package br.dev.ppaiva.mosaic.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Configuração JPA para habilitar auditoria automática.
 * 
 * Esta classe será automaticamente importada pelos projetos que usarem
 * esta biblioteca através do component scanning do Spring Boot.
 * 
 * Para usar em outros projetos:
 * 1. Adicione @EntityScan("br.dev.ppaiva.mosaic.domain.models") 
 *    na sua classe Application
 * 2. A auditoria será habilitada automaticamente
 */
@Configuration
@EnableJpaAuditing
public class DomainConfiguration {

}
