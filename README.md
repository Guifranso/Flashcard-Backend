# Flashcard-Backend

This project was created using the [Ktor Project Generator](https://start.ktor.io).

## Criação do banco de dados

Para criar o banco de dados é necessário criar um banco MongoDB local, para isso aconselho o uso da plataforma [MongoCompass](https://github.com/Guifranso/Flashcard-Backend). Passo a passo após instalar a plataforma:

1 - Crie uma nova conexão

2 - Crie um novo banco de dados com o nome de "Flashcard"

3 - Dentro do banco de dados crie uma tabela com o nome de "Baralho"

4 - Rode os endpoints da aplicação para popular o banco

5 - Pronto 🎉

## Building & Running

To build or run the project, use one of the following tasks:

| Task                          | Description                                                          |
| -------------------------------|---------------------------------------------------------------------- |
| `./gradlew test`              | Run the tests                                                        |
| `./gradlew build`             | Build everything                                                     |
| `buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `buildImage`                  | Build the docker image to use with the fat JAR                       |
| `publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `run`                         | Run the server                                                       |
| `runDocker`                   | Run using the local docker image                                     |

If the server starts successfully, you'll see the following output:

```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```

