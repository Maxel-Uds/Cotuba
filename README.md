# Cotuba

Gerador de ebooks escritos no formato Markdown (`.md`) para os formatos EPUB e PDF.

Projeto para estudo de Design Patterns, SOLID e melhores práticas de código

Projeto baseado e estudado no livro **Desbravando o SOLID** de Alexandre Aquiles (2022)

Implementado em Java.

Pré-requisitos:

- Java 17
- Maven 3.8+

Para gerar um arquivo `.zip` com os JARs e os scripts necessários e buildar o super modulo, execute:

```
mvn clean install
```

## Gerar Ebook Pelo CMD

O `.zip` será gerado na pasta `cotuba-cli/target/`.

Descompacte esse `.zip` em um diretório qualquer (será criado um diretório chamado `libs` e um arquivo executável `cotuba.sh`).

Entre nas respectivas pastas do plugins que são `plugins/estatisticas-ebook` e `plugins/tema-paradizo` e execute:

```
mvn clean package
```

Após isso, copie os JARs que foram gerados em cada plugin para dentro do diretório `libs`, que foi gerado anteriormente, execute:

```
cp target/*-jar-with-dependencies.jar -d /PATH_DO_LOCAL_DA_PASTA/libs
```

Rode os comandos abaixo dentro do diretório da descompactação.

Se desejar, use os arquivos `.md` do diretório `livro-exemplo`.

### PDF

Para gerar um PDF, faça:

```
./cotuba.sh -d diretorio/do/livro -f pdf
```

Deverá ser gerado um arquivo chamado `book.pdf`.

### EPUB

Para gerar um EPUB, faça:

```
./cotuba.sh -d diretorio/do/livro -f epub
```

Deverá ser gerado um arquivo chamado `book.epub`.

## Gerar Ebook Pela Web

Para iniciar a aplicação web, execute:

```
java -jar cotuba-web/target/cotuba-web-0.0.1-SNAPSHOT.jar
```

Se tudo ocorrer bem, a aplicação terá subido e você poderá acessa-lá em `http://localhost:8080/`