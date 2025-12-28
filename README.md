# AmigoPet — Pet Adote (API Spring Boot)

🐾 Projeto AmigoPet (petAulas)

AmigoPet é uma API backend desenvolvida em Java com Spring Boot que serve como suporte para um sistema simples de adoção de animais. O projeto contém modelos, repositórios, serviços e controladores para gerenciar doadores, adotantes, pets, espécies e adoções.

📌 Funcionalidades principais
- Cadastro e gerenciamento de doadores (`/doadores`)
- Cadastro e gerenciamento de adotantes (`/adotantes`)
- Cadastro e gerenciamento de pets (`/pets`), incluindo filtros e listagem de disponíveis
- Cadastro e gerenciamento de adoções (`/adocoes`) com filtros por status, adotante, doador e pet
- Endpoints REST simples para criação, leitura, atualização e exclusão (CRUD)

🛠️ Tecnologias utilizadas
- Java 17+ (conforme seu JDK)
- Spring Boot
- Spring Data JPA (Hibernate)
- Maven (uso do wrapper `mvnw`)
- MySQL (via `docker-compose`) — há também um dump SQL em `Dump20251204.sql`

📁 Estrutura do projeto (resumida)
`petAulas/`

│── `api/` → código-fonte da API Spring Boot
│   └── `src/main/java/com/miaudopt/` → pacotes `controller`, `service`, `model`, `repository`, `config`
│   └── `src/main/resources/application.properties` → configuração da aplicação

│── `docker-compose.yml` → serviços para banco de dados (MySQL) e Adminer
│── `Dump20251204.sql` → dump do banco de dados

🎯 Objetivo do sistema
Criar uma API simples e prática para organizar informações de doadores, adotantes e pets disponíveis, facilitando o fluxo de adoção e servindo como projeto demonstrativo para aprendizado de backend com Spring Boot.

🔎 Endpoints principais (exemplos)
- `GET  /pets` — listar todos os pets
- `GET  /pets/disponiveis` — listar pets disponíveis
- `GET  /pets/{id}` — buscar pet por id
- `POST /pets` — criar novo pet
- `PUT  /pets/{id}` — atualizar pet
- `DELETE /pets/{id}` — deletar pet

- `GET  /adotantes` — listar adotantes
- `POST /adotantes` — criar adotante

- `GET  /adocoes` — listar adoções
- `POST /adocoes` — criar adoção

Observação: os controllers estão diretamente mapeados nas classes em `com.miaudopt.controller` (ex.: `PetController`, `AdotanteController`, `AdocaoController`). A porta padrão configurada é `9090`.

🚀 Como executar (modo rápido)
1. Iniciar o banco MySQL e Adminer com Docker Compose (na raiz `petAulas`):

```bash
docker-compose up -d
```

O `docker-compose.yml` já configura um serviço `mysql:8.0` e `adminer` (interface web em `http://localhost:8081`).

2. Ajuste (se necessário) as credenciais em `api/src/main/resources/application.properties` para combinar com seu container MySQL.

3. Executar a API com Maven (usar o wrapper para compatibilidade):

```bash
cd api
./mvnw spring-boot:run
```

ou empacotar e executar o jar:

```bash
cd api
./mvnw clean package -DskipTests
java -jar target/*.jar
```

🗄️ Sobre o banco de dados
- Por padrão `application.properties` aponta para `jdbc:mysql://172.18.0.2:3306/miaudopt` com usuário `root`/`root` e `spring.jpa.hibernate.ddl-auto=create`.
- Se você usar o `docker-compose.yml` presente, o serviço MySQL está mapeado para a porta `3306` do host e as variáveis definidas são `MYSQL_ROOT_PASSWORD=root` e também é criado um usuário `miaudopt` com senha `miau123`. Ajuste `application.properties` conforme sua preferência.
- Há um arquivo `Dump20251204.sql` com dados iniciais/importáveis.

👨‍🏫 Desenvolvimento
O projeto foi construído seguindo práticas comuns do Spring Boot: separação de camadas (controller, service, repository), uso de DTOs/entidades e repositórios JPA. É ideal para estudos e como base para evoluções (autenticação, frontend, testes automatizados, etc.).

📄 Licença
Este projeto destina-se a fins educacionais.

💡 Autor
Desenvolvido por Arthur-Conrado em colaboração com o Senai.

📌 Observações finais
- Verifique `application.properties` antes de rodar em produção.
- documentação Swagger/OpenAPI (já há `OpenApiConfig` no projeto) ou instruções para rodar com Dockerfile/imagem da API.

🧭 Documentação Swagger / OpenAPI
 - A aplicação já inclui configuração básica do OpenAPI via `OpenApiConfig`.
 - Com a aplicação em execução (porta padrão `9090`), acesse a interface Swagger UI em:
	 - `http://localhost:9090/swagger-ui.html` ou `http://localhost:9090/swagger-ui/index.html`
 - O JSON do OpenAPI está disponível em: `http://localhost:9090/v3/api-docs`
 - Para exportar o documento OpenAPI em JSON a partir da linha de comando:

```bash
curl -s http://localhost:9090/v3/api-docs -o openapi.json
```

Notas sobre dependências
 - Foi observada uma duplicação da dependência `springdoc-openapi-starter-webmvc-ui` no `pom.xml` com versões diferentes. Recomenda-se manter apenas a versão mais recente para evitar conflitos.
