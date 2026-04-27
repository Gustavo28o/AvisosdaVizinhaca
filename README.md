Avisos da vizinhança

Sistema desenvolvido em Java com arquitetura MVC que permite a comunicação entre moradores de uma comunidade por meio de avisos classificados por nível de urgência.

 Objetivo

O projeto tem como objetivo resolver a falta de comunicação eficiente entre vizinhos, permitindo:

- Compartilhamento rápido de informações importantes
- Alertas de segurança, saúde e infraestrutura
- Organização de avisos por nível de urgência
- Visualização e acompanhamento de eventos na comunidade


 Tecnologias Utilizadas

- Java (POO)
- Arquitetura MVC
- Collections (`ArrayList`)
- Java Time (`LocalDateTime`)


 Estrutura do Projeto
src/
│
├── controller/
│ └── SistemaController.java
│
├── model/
│ ├── Morador.java
│ ├── MoradorComum.java
│ ├── LiderComunitario.java
│ ├── Aviso.java
│ ├── AvisoSeguranca.java
│ ├── AvisoSaude.java
│ ├── AvisoInfraestrutura.java
│ ├── ConfirmacaoVisualizacao.java
│ ├── Rua.java
│ └── Bairro.java
│
├── service/
│ ├── MoradorService.java
│ ├── AvisoService.java
│ └── EstatisticaService.java
│
├── view/
│ └── ConsoleView.java
│
└── Main.java


 Funcionalidades

Moradores
Cadastro de moradores
Identificação por ID automático
Diferenciação por tipo:
Morador comum
Líder comunitário

Avisos
 Envio de avisos com:
   Título
   Descrição
   Local
   Nível de urgência:
     Verde (informativo)
     Amarelo (atenção)
     Vermelho (urgente)

 Listagem de avisos
 Listagem de avisos por morador


Filtros
 Filtrar avisos por urgência


 Estatísticas
Contagem de avisos por nível de urgência


 **Conceitos de POO Aplicados**

 **Encapsulamento**
 
 **Herança**
 
   Morador → MoradorComum, LiderComunitario
   
   Aviso → AvisoSeguranca,AvisoSaude , AvisoInfraestrutura
   
 **Polimorfismo**
 
Método exibirAlerta() com comportamentos diferentes
   
 **Abstração**

  Classes abstratas (Morado, Aviso)

## Como executar


1. Clone o repositório:
   git clone https://github.com/Gustavo28o/AvisosdaVizinhaca.git

2. Acesse a pasta do projeto:
   cd AvisosdaVizinhaca

3. Compile o projeto (gerando os arquivos `.class` na pasta `bin`):
   javac -d bin src/view/*.java src/controller/*.java src/model/*.java src/service/*.java

4. Execute o programa:
   java -cp bin view.Main

## Observações importantes

* O projeto utiliza packages, por isso não funciona usar comandos como `javac src/*.java` ou `java src/Main`
* A pasta `bin` é usada como destino dos arquivos compilados (`.class`)

## Autores- 
Gustavo Cruz - RA: 12526162328
Vinicius Lúcio Pourrat - RA: 12525218535 


=== Vizinhança Conectada ===
1 - Ver avisos recentes
2 - Enviar novo aviso
3 - Cadastrar Morador
4 - Meus avisos
5 - Estatísticas
6 - Sair
