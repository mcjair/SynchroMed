$env:JAVA_HOME = "$PWD\.tools\jdk"
$env:PATH = "$PWD\.tools\jdk\bin;$PWD\.tools\mvn\bin;" + $env:PATH
mvn compile spring-boot:run
