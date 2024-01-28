package br.com.ti.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //Para executar todas as features
        features = "src/test/java/br/com/ti/features/compras.feature",

        glue = "br.com.ti.steps",
        tags = "@LojaVirtual",

        //Executa no jUnit exibindo no console os mesmos resultados do cucumber
        plugin = {"pretty", "html:evidencias/html/evidence.html"},

        //Ele nao tenta colocar caracteres coloridos e especiais
        monochrome = true,

        //Ele tira o _ e coloca as letras maiusculas de cada palavra
        snippets = CucumberOptions.SnippetType.CAMELCASE,

        //dryRun = true Valida se o mapeamento esta correto e nao executa o teste
        dryRun = false

        //strict = true
)
public class RunnerTest {
}
