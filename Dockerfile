# Define a imagem base
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Executa o comando para baixar as dependências do Maven
RUN mvn dependency:go-offline

# Copia todo o código fonte para o diretório de trabalho
COPY . .

# Executa o comando para compilar o projeto
RUN mvn package -DskipTests

# Define o comando de inicialização do container
CMD ["java", "-jar", "target/hub-0.0.1-SNAPSHOT.jar"]
