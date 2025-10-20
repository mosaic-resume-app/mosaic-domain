# Script para publicar a biblioteca domain no GitHub Packages
# Uso: .\publish-github.ps1

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Mosaic Domain - GitHub Packages" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Verifica se está na pasta correta
if (!(Test-Path "pom.xml")) {
    Write-Host "❌ Erro: Execute este script na pasta domain/" -ForegroundColor Red
    Write-Host "   cd domain" -ForegroundColor Yellow
    Write-Host "   .\publish-github.ps1" -ForegroundColor Yellow
    exit 1
}

# Verifica se o Maven está instalado
try {
    $mvnVersion = mvn --version 2>&1 | Select-Object -First 1
    Write-Host "✅ Maven encontrado: $mvnVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Maven não encontrado. Instale o Maven primeiro." -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "⚠️  IMPORTANTE: Antes de continuar, certifique-se de:" -ForegroundColor Yellow
Write-Host "   1. Ter criado um Personal Access Token no GitHub" -ForegroundColor White
Write-Host "   2. Ter configurado o settings.xml em ~/.m2/" -ForegroundColor White
Write-Host "   3. Ter substituído SEU_USUARIO_GITHUB no pom.xml" -ForegroundColor White
Write-Host ""

$continue = Read-Host "Deseja continuar? (s/n)"
if ($continue -ne "s" -and $continue -ne "S") {
    Write-Host "Operação cancelada." -ForegroundColor Yellow
    exit 0
}

Write-Host ""
Write-Host "🔨 Limpando builds anteriores..." -ForegroundColor Cyan
mvn clean

if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Falha ao limpar o projeto" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "📦 Compilando e empacotando..." -ForegroundColor Cyan
mvn package -DskipTests

if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Falha ao compilar o projeto" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "🚀 Publicando no GitHub Packages..." -ForegroundColor Cyan
mvn deploy -DskipTests

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "❌ Falha ao publicar no GitHub Packages" -ForegroundColor Red
    Write-Host ""
    Write-Host "Possíveis causas:" -ForegroundColor Yellow
    Write-Host "  - Token do GitHub inválido ou expirado" -ForegroundColor White
    Write-Host "  - settings.xml não configurado corretamente" -ForegroundColor White
    Write-Host "  - URL no pom.xml incorreta" -ForegroundColor White
    Write-Host "  - Permissões insuficientes no repositório" -ForegroundColor White
    Write-Host ""
    Write-Host "Consulte: GITHUB-PACKAGES-SETUP.md" -ForegroundColor Cyan
    exit 1
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Green
Write-Host "  ✅ Publicação concluída com sucesso!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""
Write-Host "📦 Biblioteca publicada:" -ForegroundColor Cyan
Write-Host "   GroupId: br.dev.ppaiva.mosaic" -ForegroundColor White
Write-Host "   ArtifactId: domain" -ForegroundColor White

# Extrai a versão do pom.xml
$pomContent = Get-Content "pom.xml" -Raw
if ($pomContent -match '<version>([^<]+)</version>') {
    $version = $matches[1]
    Write-Host "   Version: $version" -ForegroundColor White
}

Write-Host ""
Write-Host "🔗 Verifique em:" -ForegroundColor Cyan
Write-Host "   https://github.com/SEU_USUARIO_GITHUB/mosaic/packages" -ForegroundColor White
Write-Host ""
Write-Host "📖 Para usar em outros projetos, consulte:" -ForegroundColor Cyan
Write-Host "   - GITHUB-PACKAGES-SETUP.md (configuração)" -ForegroundColor White
Write-Host "   - EXEMPLO-USER-SERVICE.md (exemplo de uso)" -ForegroundColor White
Write-Host ""

