# automacao-lojavirtual-java-web
Scripts de automação web em uma Loja Virtual desenvolvido com Java, jUnit e Selenium.

### Cobertura dos testes:  ###

* Realizar compra online

## Tecnologias:
* [Java JDK 8](https://www.oracle.com/br/java/technologies/javase-downloads.html)
* [Maven](https://maven.apache.org)
* [Maven dependencias](https://mvnrepository.com)
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
O projeto foi criado para executar no Windows. Mas pode receber adaptacoes para executar no MacOS e Linux caso nao execute bem fora do Windows.

Recomendado utilizar o Intellij Idea, mas pode usar o Eclipse IDE, Visual Studio Code ou Spring Tools Suite.

###  - Fluxo
*Descricao: Este script ira executar uma compra online, seguindo o fluxo desde a escolha do produto ate a etapa de confirmacao da compra.

###  - Massas
*Descricao: Antes de executar nao esqueca de trocar as massas por uma adequada. Faca isso para o CT01, para o CT02 nao ha 
problema em usar a massa disponivel na feature desde que verifique antes se o site nao apagou do banco de dados.

###  - Evidencias
*Descricao:
Ha duas classes para gerar evidencias, uma esta na BasePage que cria uma pasta, salva as imagens la dentro e depois gera o documento docx. Ou se preferir tem a classe GeradorPDF que gera das evidencias no formato pdf, aconselho usar ela pois é mais rapido e seguro.

Para visualizar as evidencias no documento pode usar o MS Office Word ou o LibreOffice

###  - Inicializar a automação
*Descricao: Classe para executar esta na pasta runner/RunnerTest