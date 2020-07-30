=== Automacao Loja Virtual ===

0.Objetivo
 .Primeiro teste realiza o cadastro de novo usuario.
 .Segundo teste e realizar uma pesquisa de produto e comprar produto ate o final do fluxo.

1.Requisitos:
 .Google Chrome Webdriver versao 84. O driver esta na pasta drivers (Para Windows e MacOS)
 .Java JDK 8
 .Intellij Idea, Eclipse IDE, Visual Studio Code ou Spring Tools Suite.
 .Dependencias Maven (Ja estao inseridas no arquivo pom.xml pronto para serem baixadas ao importar na IDE
  com as versoes estaveis).

2.Configuracoes:
 .O projeto foi criado para executar no Windows. Mas pode receber adaptacoes para executar no MacOS e Linux
 caso nao execute bem fora do Windows.
 .Os scripts gherkin estao na pasta features
 .Classe para executar esta na pasta runner/RunnerTest
 .Os metodos para criar o webdriver estao na classe DriverWeb na pasta Driver

3.Execucao:
 .Inserir as massas para teste no arquivo compras.feature na pasta features
 .Abrir a classe RunnerTest.java no Intellij Idea ou no Eclipse

4.Evidencias:
 .Apos a execucao as imagens de evidencias sao armazenadas na pasta evidencias.
 .Para visualizar as evidencias no documento pode usar o MS Office Word ou o LibreOffice

5.Massas:
 .Antes de executar nao esqueca de trocar as massas por uma adequada. Faca isso para o CT01, para o CT02 nao ha
  problema em usar a massa disponivel na feature desde que verifique antes se o site nao apagou do banco de dados.

6.Drivers:
 .Na pasta ./driver estao os arquivos para o Chrome e Firefox para o Windows e MacOS com as versoes recentes.
