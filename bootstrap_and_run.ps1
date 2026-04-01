$ErrorActionPreference = "Stop"
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12

$toolsDir = Join-Path $PWD ".tools"
if (!(Test-Path $toolsDir)) { New-Item -ItemType Directory -Force -Path $toolsDir | Out-Null }

# Añadimos .tools al gitignore para no ensuciar el repo
$gitignorePath = Join-Path $PWD ".gitignore"
if (-not (Get-Content $gitignorePath | Select-String -Pattern "^\.tools/")) {
    Add-Content -Path $gitignorePath -Value "`n.tools/"
}

$jdkUrl = "https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.10%2B7/OpenJDK17U-jdk_x64_windows_hotspot_17.0.10_7.zip"
$jdkZip = Join-Path $toolsDir "jdk.zip"
$jdkExtractDir = Join-Path $toolsDir "jdk"

if (!(Test-Path $jdkExtractDir)) {
    Write-Host "Descargando kit de desarrollo Java (JDK 17) de forma autonoma..."
    Invoke-WebRequest -Uri $jdkUrl -OutFile $jdkZip
    Write-Host "Extrayendo JDK..."
    Expand-Archive -Path $jdkZip -DestinationPath $toolsDir -Force
    Rename-Item -Path (Join-Path $toolsDir "jdk-17.0.10+7") -NewName "jdk"
}

$mvnUrl = "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip"
$mvnZip = Join-Path $toolsDir "mvn.zip"
$mvnExtractDir = Join-Path $toolsDir "mvn"

if (!(Test-Path $mvnExtractDir)) {
    Write-Host "Descargando Maven de forma autonoma..."
    Invoke-WebRequest -Uri $mvnUrl -OutFile $mvnZip
    Write-Host "Extrayendo Maven..."
    Expand-Archive -Path $mvnZip -DestinationPath $toolsDir -Force
    Rename-Item -Path (Join-Path $toolsDir "apache-maven-3.9.6") -NewName "mvn"
}

# Inyectamos en las variables de entorno para esta sesion
$env:JAVA_HOME = $jdkExtractDir
$env:PATH = "$jdkExtractDir\bin;$mvnExtractDir\bin;" + $env:PATH

Write-Host "--- Entorno Configurado Exitosamente ---"
java -version
mvn -version

Write-Host "Compilando todo el codigo fuente..."
mvn clean compile

if ($LASTEXITCODE -eq 0 -or $?) {
    Write-Host "Levantando el Sistema Grafico (Swing) SynchroMed..."
    # Ejecutamos sin bloquear la terminal
    Start-Process -NoNewWindow -FilePath "mvn" -ArgumentList "exec:java -Dexec.mainClass=com.synchromed.view.MainFrame"
    Write-Host "¡Ventana inicializada enviada a tu pantalla principal!"
} else {
    Write-Host "Error en la compilación. Verifica el Output de Maven."
}
