# MovieApp

**MovieApp** é um aplicativo Android desenvolvido em **Kotlin**, que consome a API do **The Movie Database (TMDb)** para exibir uma lista de filmes populares. O app segue os princípios de **Clean Architecture** e **SOLID**, usando **Jetpack Compose** para a UI.

## Índice

1. [Funcionalidades](#funcionalidades)
2. [Tecnologias e Bibliotecas Utilizadas](#tecnologias-e-bibliotecas-utilizadas)
3. [Configuração do Projeto](#configuração-do-projeto)
4. [Execução do Projeto](#execução-do-projeto)
5. [Testes](#testes)
6. [Melhorias Futuras](#melhorias-futuras)

## Funcionalidades

- **Lista de Filmes Populares** com imagens e detalhes.
- **Detalhes do Filme**: Sinopse e pôster.
- **Navegação** fluida usando **Jetpack Navigation**.
- **Carregamento Dinâmico de Imagens** com **Coil**.
- **Internacionalização**: Suporte a inglês e português, adaptando-se à configuração do dispositivo.

## Tecnologias e Bibliotecas Utilizadas

- **Kotlin**: Linguagem principal.
- **Clean Architecture & SOLID**: Modularidade e separação de responsabilidades.
- **Jetpack Compose**: Construção de UIs reativas.
- **Material Design 3**: UI moderna e responsiva.
- **Retrofit** e **OkHttp**: Comunicação HTTP.
- **Coil**: Carregamento de imagens.
- **Coroutines** e **Flow**: Concurrency e gerenciamento de estado.
- **Koin**: Injeção de dependências.
- **JUnit**, **Mockk**, **Espresso**: Testes unitários e de UI.

## Configuração do Projeto

### Requisitos

- Android Studio **Giraffe | 2023.3.1 Patch 1** ou superior.
- Kotlin **1.9.0**.
- Gradle **8.5.1**.
- Chave de API do TMDb para acessar os endpoints.

### Passos para Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/DaniellaSPaiva/MovieApp.git

2. Navegue até o diretório do projeto:
   ```bash
   cd MovieApp
   
3. Abra o projeto no Android Studio.

4. Adicione a sua chave de API do TMDb. Crie um arquivo local.properties (não rastreado pelo Git) com a seguinte linha:
   ```bash
   TMDB_API_KEY=your_api_key_here

5. Sincronize o projeto com as dependências do Gradle.

## Execução do Projeto
1. Após configurar e sincronizar o projeto no Android Studio, escolha um dispositivo ou emulador Android para rodar o app.
2. Clique em "Run" ou use o atalho Shift + F10 para compilar e rodar o aplicativo.

O aplicativo deve ser executado e exibir a lista de filmes populares. Clicando em um filme, você verá a tela de detalhes.

## Testes

O projeto inclui testes unitários para componentes-chave, garantindo a funcionalidade adequada e a manutenção do código:

- **Testes de Repositório**: Validam a comunicação correta com a API utilizando o **MockWebServer** para simular respostas reais da API.
- **Testes de ViewModel**: Garantem que as ViewModels lidam corretamente com o carregamento de dados de filmes populares e detalhes de filmes, com injeção de dependências feita via **KoinTest**.
- **Testes de Casos de Uso**: Verificam se os casos de uso, como `GetPopularMoviesUseCase` e `GetMovieDetailsUseCase`, interagem corretamente com o repositório e retornam os resultados esperados.

Melhorias futuras focarão em expandir os testes de integração para cobrir fluxos completos e garantir que o aplicativo funcione corretamente em diversos cenários.

## Melhorias Futuras

- **Ambientes de Desenvolvimento e Produção**: No momento, o aplicativo não possui ambientes de desenvolvimento e produção separados. A criação de ambientes distintos será considerada em uma versão futura para facilitar o teste de funcionalidades específicas, como logs de depuração e URLs de API, sem afetar a versão de produção.
- **Extração da API Key do código**: A chave da API atualmente embutida diretamente no código deve ser extraída e armazenada de forma mais segura. Para produção, recomenda-se o uso de uma solução como **Firebase Remote Config**.
- **SplashScreen Melhorada**: Implementar animações e personalizações visuais, além de garantir que a SplashScreen cubra o carregamento inicial de dados, como a listagem de filmes.

Contribuições são bem-vindas! Se você encontrar algum bug ou tiver sugestões, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto é distribuído sob a licença MIT. Para mais detalhes, consulte o arquivo LICENSE.
