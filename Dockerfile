# ==========================================
# ESTÁGIO 1: O Construtor (Build)
# ==========================================
FROM maven:3.9.5-eclipse-temurin-17-alpine AS builder

WORKDIR /build

# Copia os arquivos de configuração e o código-fonte
COPY pom.xml .
COPY src ./src

# Compila o projeto gerando o .jar (pulando os testes para agilizar)
RUN mvn clean package -DskipTests -Dfile.encoding=UTF-8

# ==========================================
# ESTÁGIO 2: O Executor (Run)
# ==========================================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Traz apenas o .jar gerado no estágio anterior, mantendo a imagem leve
COPY --from=builder /build/target/musicboxd-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]