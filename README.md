# automacao-lojavirtual-java-web

Scripts de automação web em uma Loja Virtual desenvolvido com Java, jUnit e Selenium.

### Cobertura dos testes:  ###

* Realizar cadastro de um novo usuario
* Realizar compra online

## Tecnologias:

* [Java JDK 8+](https://www.oracle.com/br/java/technologies/javase-downloads.html)
* [Maven](https://maven.apache.org)
* [Maven dependency](https://mvnrepository.com)
* [WebDriver Manager](https://github.com/bonigarcia/webdrivermanager)
* [Selenium Webdriver](https://www.selenium.dev/projects/)
* [Intellij Idea](https://www.jetbrains.com/pt-br/idea/)
* [jUnit](https://junit.org/junit5/)
* [Cucumber](https://cucumber.io)
* [ItextPDF](https://itextpdf.com/en)

## Dependências:

* Selenium-java
* jUnit 4
* Commons-io
* Cucumber-junit
* Cucumber-java
* Cucumber-picocontainer
* Jxl
* Poi-ooxml
* Itextpdf

## Instruções de execução:

###  - Plataforma

*Importante: 

Recomendado utilizar o Intellij Idea, mas pode usar o Eclipse IDE ou Visual Studio Code.

###  - Fluxo

*Descricao: Este script ira executar uma compra online, seguindo o fluxo desde a escolha do produto ate a etapa de confirmacao da compra.

###  - Massas

*Descricao: 

Antes de executar nao esqueca de trocar as massas por uma adequada. Faca isso para o CT01. E para o CT02 nao ha 
problema em usar a massa disponivel na feature desde que verifique antes se o site nao apagou do banco de dados.

###  - Evidencias

*Descricao:
As evidencias no final do teste com resultado Positivo ou Negativo são salvas na pasta evidencias no formato pdf.

Para visualizar as evidencias no documento pode usar o MS Office Word, WPS Office ou o LibreOffice

###  - Inicializar a automação

*Descricao: 
Classe para executar esta na pasta runner/RunnerTest.

Não é necessário baixar o WebDriver para cada navegador, o projeto utiliza o WebDriver Manager que faz o download adequado independente do Sistema Operacional ou versão do Navegador. Porém, é preciso que o navegador esteja instalado na máquina.

O webdriver esta setado para o Chrome, para os outros navegadores nao esqueca de alterar na variavel navegador na classe comprasSteps.java.
