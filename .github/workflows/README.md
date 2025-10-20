# GitHub Actions Workflows

Este diretório contém os workflows de CI/CD para o projeto Mosaic.

## Workflows Disponíveis

### 📦 `publish-domain.yml`
**Propósito:** Publica a biblioteca `domain` no GitHub Packages

**Quando executa:**
- Push para `main` ou `master` com mudanças em `domain/**`
- Criação de release
- Manualmente via GitHub Actions UI

**O que faz:**
1. Compila o código
2. Executa testes
3. Publica no GitHub Packages
4. Faz upload dos JARs como artifacts

### 🔨 `build-domain.yml`
**Propósito:** Valida builds em PRs e branches de desenvolvimento

**Quando executa:**
- Pull Requests que modificam `domain/**`
- Push para branches (exceto `main/master`)

**O que faz:**
1. Compila o código
2. Executa testes
3. Faz upload dos resultados de teste

## Como adicionar novos workflows

1. Crie um arquivo `.yml` neste diretório
2. Use a sintaxe do GitHub Actions
3. Commit e push

## Documentação

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Maven GitHub Packages](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry)

