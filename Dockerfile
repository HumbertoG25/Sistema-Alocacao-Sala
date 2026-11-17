# ================================================================
# Dockerfile - Sistema SLA (Spring Boot + JSF/JoinFaces)
# Build em 2 etapas: a 1ª compila o projeto, a 2ª só executa o .jar
# (assim a imagem final fica pequena, sem Maven e sem código-fonte)
# ================================================================

# ---------- ETAPA 1: BUILD ----------
# Imagem com Maven + Java 17 (mesma versão definida no pom.xml)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia primeiro só o pom.xml e baixa as dependências.
# O Docker guarda isso em cache: se só o código mudar, não baixa tudo de novo.
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# Copia o código-fonte e gera o .jar (sem rodar os testes, para agilizar o deploy)
COPY src ./src
RUN mvn -B -q clean package -DskipTests

# ---------- ETAPA 2: EXECUÇÃO ----------
# Imagem leve, só com o Java Runtime (JRE)
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia apenas o .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Porta padrão da aplicação (o Render informa a porta real pela variável PORT)
EXPOSE 8080

# Limita a memória da JVM a 75% do que o container tiver (importante no plano free do Render)
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

# Inicia a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
