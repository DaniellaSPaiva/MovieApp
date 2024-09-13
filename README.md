# MovieApp

MovieApp é um aplicativo Android desenvolvido em **Kotlin** que consome a API do The Movie Database (**TMDb**) para exibir uma lista de filmes populares. Ele foi construído usando **Clean Architecture**, princípios **SOLID** e tecnologias modernas como **Jetpack Compose** para a interface de usuário.

## Demonstração do MovieApp

Veja uma demonstração rápida do MovieApp em ação:

![MovieApp Demo](https://github.com/user-attachments/assets/680e4481-0f32-48cb-a7d0-2fa09fbfd512)

## Índice

1. [Funcionalidades](#funcionalidades)
2. [Tecnologias e Bibliotecas Utilizadas](#tecnologias-e-bibliotecas-utilizadas)
3. [Configuração do Projeto](#configuração-do-projeto)
4. [Execução do Projeto](#execução-do-projeto)
5. [Melhorias Futuras](#melhorias-futuras)
6. [Testes] (#testes)

## Funcionalidades

- **Listagem de filmes populares**: Exibe uma lista de filmes populares, com imagens e detalhes.
- **Detalhes do filme**: Cada filme da lista pode ser clicado para ver informações detalhadas, incluindo a sinopse e o pôster.
- **Navegação fluida**: Uso de Jetpack Navigation para navegação entre a lista de filmes e a tela de detalhes.
- **Carregamento dinâmico de imagens**: As imagens dos filmes são carregadas dinamicamente com **Coil**.
- **Internacionalização**: O idioma do aplicativo se adapta automaticamente ao idioma configurado no dispositivo, suportando inglês e português.

## Tecnologias e Bibliotecas Utilizadas

### Linguagem e Arquitetura
- **Kotlin**: Linguagem principal do desenvolvimento Android.
- **Clean Architecture**: Arquitetura modular e escalável, seguindo princípios SOLID para melhor manutenção e testes.
- **MVVM (Model-View-ViewModel)**: Padrão arquitetural usado para separar responsabilidades entre camadas.

### Interface de Usuário
- **Jetpack Compose**: Framework moderno para construção de UIs reativas no Android.
- **Material Design 3**: Utilização dos componentes modernos de Material Design para garantir uma UI limpa e responsiva.

### Rede e Consumo de API
- **Retrofit**: Biblioteca para realizar requisições HTTP de maneira simples e eficiente.
- **OkHttp Logging Interceptor**: Utilizado para logar requisições e respostas HTTP no Logcat.
- **Coil**: Biblioteca para carregamento de imagens via URL diretamente no Jetpack Compose.

### Gerenciamento de Estado e Concurrency
- **Coroutines e Flow**: Utilizados para lidar com operações assíncronas, como chamadas de API, de forma eficiente.
- **LiveData / StateFlow**: Gerenciamento reativo de estados na camada de ViewModel.

### Injeção de Dependências
- **Koin**: Biblioteca para injeção de dependências, garantindo modularidade e separação clara de responsabilidades.

### Testes
- **JUnit**: Framework principal para testes unitários.
- **Mockk**: Biblioteca utilizada para mockar dependências e simular comportamentos em testes.
- **Espresso**: Framework para testes de interface de usuário.
- **Robolectric**: Simula o ambiente Android para testes de unidade.

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

## Melhorias Futuras
- **Ambientes de Desenvolvimento e Produção**: No momento, o aplicativo não possui ambientes de desenvolvimento e produção separados. A criação de ambientes distintos será considerada em uma versão futura para facilitar o teste de funcionalidades específicas, como logs de depuração e URLs de API, sem afetar a versão de produção. Isso permitirá uma melhor gestão de configurações, simplificando o processo de deploy e o controle de comportamento do app em diferentes contextos.
- **Extração da API Key do código**: A chave da API que está atualmente embutida diretamente no código deve ser extraída e armazenada de forma mais segura. Para ambientes de desenvolvimento, recomenda-se o uso do arquivo `local.properties`, onde a chave pode ser definida e não será versionada no controle de versão. Para ambientes de produção, o uso de uma solução mais segura, como o **Firebase Remote Config**, seria uma opção adequada para gerenciar a API key dinamicamente e com maior segurança.
- **SplashScreen**: A SplashScreen atual utiliza a implementação padrão do Android com um logo simples e um fundo estático. Possíveis melhorias incluem:
  - **Personalização Visual**: Alterar a SplashScreen para refletir melhor a identidade visual do aplicativo, utilizando o tema e cores do MovieApp.
  - **Carregamento Dinâmico**: Adicionar carregamento dinâmico para a listagem de filmes, garantindo que a SplashScreen seja exibida até que as informações iniciais sejam carregadas, como configurações ou autenticação do usuário.
  - **Transições Suaves**: Implementar animações para tornar a transição entre a SplashScreen e a tela principal mais fluida e visualmente agradável.
  - **Animações Personalizadas**: Adicionar animações ao logo ou outros elementos visuais para criar uma experiência mais envolvente e profissional.

Contribuições são bem-vindas! Se você encontrar algum bug ou tiver sugestões, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Testes
O projeto inclui testes unitários para componentes-chave, garantindo a funcionalidade adequada e a manutenção do código:

- **Testes de Repositório**: Validam a comunicação correta com a API utilizando o MockWebServer para simular respostas reais da API.
- **Testes de ViewModel**: Garantem que as ViewModels lidam corretamente com o carregamento de dados de filmes populares e detalhes de filmes, com injeção de dependências feita via KoinTest.
- **Testes de Casos de Uso**: Verificam se os casos de uso, como GetPopularMoviesUseCase e GetMovieDetailsUseCase, interagem corretamente com o repositório e retornam os resultados esperados.
  
Melhorias futuras focarão em expandir os testes de integração para cobrir fluxos completos e garantir que o aplicativo funcione corretamente em diversos cenários.

## Licença
Este projeto é distribuído sob a licença MIT. Para mais detalhes, consulte o arquivo LICENSE.
