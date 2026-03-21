# 🐾 desafioCadastro2.0

Sistema de cadastro de pets desenvolvido com Java e Spring Boot, conectado ao MongoDB. O projeto implementa um CRUD completo com validações de negócio, tratamento de exceções e testes unitários.

 Projeto desenvolvido como desafio prático de estudo criado por Lucas Carrilho - [@devmagro](https://www.linkedin.com/in/karilho/), aplicando conceitos de Orientação a Objetos, arquitetura em camadas e boas práticas de desenvolvimento.

 YouTube: [@devmagro](https://www.youtube.com/@devmagro) 🎥

 Desafio: [Link do README](https://github.com/karilho/desafioCadastro)

---



## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Data MongoDB
- MongoDB
- Lombok
- Validation
- JUnit 5
- Mockito
- AssertJ

---

## 📋 Funcionalidades

- ✅ Cadastrar um novo pet
- ✅ Buscar pet por ID
- ✅ Listar todos os pets cadastrados
- ✅ Atualizar dados de um pet
- ✅ Deletar um pet
- ✅ Validações de negócio (peso, idade, nome, raça)
- ✅ Testes unitários da camada de serviço

---

## ✅ Regras de negócio

- Nome é obrigatório e deve conter apenas letras
- Raça deve conter apenas letras (sem números ou caracteres especiais)
- Peso deve estar entre **0.5kg** e **60kg**
- Idade deve ser no máximo **20 anos**
- Campos não informados (raça, endereço) são salvos como `NAO INFORMADO`
- Tipo (`CACHORRO` / `GATO`) e sexo (`MACHO` / `FEMEA`) são definidos por Enum

---

## ⚙️ Como rodar o projeto

### Pré-requisitos

- Java 17+
- Maven
- MongoDB rodando localmente na porta padrão `27017`

### Configuração

No arquivo `src/main/resources/application.yml`, configure sua conexão com o MongoDB:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/petsdb
```

### Executando

```bash
# Clone o repositório
git clone https://github.com/Users/desafioCadastro2.0.git

# Entre na pasta do projeto
cd desafioCadastro2.0

# Rode o projeto
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## 📌 Endpoints

### Pets

| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/pets` | Cadastrar um novo pet |
| `GET` | `/pets` | Listar todos os pets |
| `GET` | `/pets/{id}` | Buscar pet por ID |
| `PUT` | `/pets/{id}` | Atualizar dados do pet |
| `DELETE` | `/pets/{id}` | Deletar um pet |

### Exemplo para `POST /pets`

```json
{
  "name": "Nala",
  "age": 5.0,
  "weight": 12.5,
  "race": "Maltez",
  "type": "CACHORRO",
  "sex": "FEMEA",
  "address": {
    "street": "Rua Ribeiro de Almeida",
    "number": "11",
    "neighborhood": "Laranjeiras",
    "city": "Rio de Janeiro"
  }
}
```

---

## 🧪 Testes Unitários

Os testes cobrem a camada de serviço (`PetService`) com os seguintes cenários:

- Salvar pet com sucesso
- Atualizar pet com sucesso
- Deletar pet com sucesso
- Buscar pet por ID
- Listar todos os pets
- Exceção ao buscar pet inexistente
- Exceção ao informar idade maior que 20 anos
- Exceção ao informar peso maior que 60kg
- Exceção ao informar peso menor que 0.5kg

---

## 👨‍💻 Autor

Desenvolvido por **Gustavo Leal** como parte da trilha Java do básico ao Jr.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/gustavolealp/)
