# Cliente API

## Descrição
Este projeto é uma API REST desenvolvida com **Spring Boot** para gerenciamento de clientes. Ele permite criar, listar, atualizar e excluir clientes armazenados em um banco de dados.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Maven**
- **Banco de Dados H2 (ou outro configurado no `application.properties`)**
- **Swagger para documentação da API**

## Estrutura do Projeto
```
clientes/
|-- src/main/java/com/example/clientes/
|   |-- config/          # Configuração do Swagger
|   |-- controller/      # Controladores REST
|   |-- model/           # Modelos de dados
|   |-- repository/      # Repositórios JPA
|   |-- service/         # Regras de negócio
|   |-- ClientesApplication.java  # Classe principal do Spring Boot
|
|-- src/main/resources/
|   |-- application.properties  # Configurações do Spring Boot
|
|-- pom.xml  # Gerenciamento de dependências com Maven
```

## Como Executar o Projeto
1. Certifique-se de ter **Java 17+** e **Maven** instalados.
2. Clone o repositório:
   ```sh
   git clone <URL_DO_REPOSITORIO>
   ```
3. Acesse a pasta do projeto:
   ```sh
   cd clientes
   ```
4. Compile e execute a aplicação:
   ```sh
   mvn spring-boot:run
   ```

## Endpoints Principais
A documentação completa pode ser acessada via Swagger em:
```
http://localhost:8080/swagger-ui.html
```

| Método | Endpoint | Descrição |
|---------|----------|-------------|
| GET | `/clientes` | Lista todos os clientes |
| GET | `/clientes/{id}` | Busca um cliente por ID |
| POST | `/clientes` | Adiciona um novo cliente |
| PUT | `/clientes/{id}` | Atualiza um cliente existente |
| DELETE | `/clientes/{id}` | Remove um cliente |

## Testes
Para executar os testes automatizados:
```sh
mvn test
```

## Licença
Este projeto está licenciado sob a **MIT License**. Consulte o arquivo `LICENSE` para mais detalhes.

