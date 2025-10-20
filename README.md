# 📦 Mosaic Domain Library

Biblioteca compartilhada contendo as entidades de domínio do projeto Mosaic Resume App.

> **Autor:** Pablo Paiva  
> **Versão:** 0.0.1-SNAPSHOT  
> **Licença:** MIT

## 📚 Conteúdo

### Entidades (Models)

- **`AbstractEntity`**: Classe base para todas as entidades com:
  - `id` (UUID)
  - `createdAt` (timestamp automático)
  - `updatedAt` (timestamp automático)
  - `active` (soft delete)

- **`User`**: Entidade de usuário com:
  - `email` (único)
  - `name`
  - `roles` (lista de roles)
  - `profile` (relacionamento OneToOne com CandidateProfile)

- **`CandidateProfile`**: Perfil de candidato com:
  - `headline`
  - `location`
  - `skills` (lista de habilidades)
  - `seniority` (enum)

### Enums

- **`Seniority`**: Níveis de senioridade
  - `INTERN`
  - `JUNIOR`
  - `MID_LEVEL`
  - `SENIOR`
  - `SPECIALIST`

## 🚀 Publicação e Instalação

### 📤 Publicar no GitHub Packages (Recomendado)

```powershell
cd domain
.\publish-github.ps1
```

Ou manualmente:

```bash
mvn clean deploy
```

**📖 Guia completo:** [GITHUB-PACKAGES-SETUP.md](./GITHUB-PACKAGES-SETUP.md)

### 📥 Usar em outros projetos

Adicione no `pom.xml` do seu projeto:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/SEU_USUARIO_GITHUB/mosaic</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>br.dev.ppaiva.mosaic</groupId>
        <artifactId>domain</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

### 💻 Instalação Local (Desenvolvimento)

Para usar apenas na sua máquina:

```bash
cd domain
mvn clean install
```

## 🔧 Configuração no Projeto Consumidor

### 1. Application Class

Configure o scan de entidades:

```java
@SpringBootApplication
@EntityScan(basePackages = {
    "br.dev.ppaiva.mosaic.domain.models",  // Entidades da lib
    "com.seupackage.models"  // Entidades locais (se houver)
})
public class YourApplication {
    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }
}
```

### 2. Application Properties

```properties
# Hibernate não gerará as tabelas automaticamente
spring.jpa.hibernate.ddl-auto=none

# Use o script SQL em platform-data/postgres/init/01_init.sql
# para criar as tabelas manualmente
```

## 💡 Exemplo de Uso

```java
import br.dev.ppaiva.mosaic.domain.models.User;
import br.dev.ppaiva.mosaic.domain.models.CandidateProfile;
import br.dev.ppaiva.mosaic.domain.enums.Seniority;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User createCandidate(String email, String name) {
        // Criar perfil
        CandidateProfile profile = new CandidateProfile();
        profile.setHeadline("Senior Software Engineer");
        profile.setLocation("Natal, RN, Brazil");
        profile.setSeniority(Seniority.SENIOR);
        profile.setSkills(List.of("Java", "Spring Boot", "PostgreSQL", "Docker"));
        
        // Criar usuário
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setRoles(List.of("ROLE_CANDIDATE"));
        user.setProfile(profile);
        
        return userRepository.save(user);
    }
}
```

## 📦 Estrutura de Banco de Dados

As tabelas são criadas manualmente via SQL (ver `platform-data/postgres/init/01_init.sql`):

- `CANDIDATE_PROFILE`: Perfis de candidatos
- `USER`: Usuários do sistema
- `roles`: Roles dos usuários (ElementCollection)
- `skills`: Skills dos perfis (ElementCollection)

## 🔄 Auditoria Automática

A biblioteca inclui auditoria JPA automática para campos:
- `createdAt`: Preenchido automaticamente na criação
- `updatedAt`: Atualizado automaticamente em cada modificação

Isso é gerenciado pela classe `DomainConfiguration` com `@EnableJpaAuditing`.

## 📖 Documentação

- **[GITHUB-PACKAGES-SETUP.md](./GITHUB-PACKAGES-SETUP.md)** - Guia completo para publicar e usar no GitHub Packages
- **[EXEMPLO-USER-SERVICE.md](./EXEMPLO-USER-SERVICE.md)** - Exemplo prático de uso em um microserviço
- **[LIBRARY-USAGE.md](./LIBRARY-USAGE.md)** - Opções alternativas de publicação (Maven Central, etc.)

## 🎯 Próximos Passos

1. ✅ Substitua `SEU_USUARIO_GITHUB` no `pom.xml` pelo seu usuário do GitHub
2. ✅ Configure o GitHub Personal Access Token (veja [GITHUB-PACKAGES-SETUP.md](./GITHUB-PACKAGES-SETUP.md))
3. ✅ Execute `.\publish-github.ps1` para publicar
4. ✅ Use a biblioteca em seus microserviços (veja [EXEMPLO-USER-SERVICE.md](./EXEMPLO-USER-SERVICE.md))

## 🤝 Contribuindo

Ao adicionar novas entidades ou modificar existentes:

1. Atualize a versão no `pom.xml`
2. Execute `mvn clean deploy` para publicar
3. Atualize a versão nos projetos consumidores

## 📄 Licença

MIT License - Pablo Paiva

