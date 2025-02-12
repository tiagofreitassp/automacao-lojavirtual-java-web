package br.com.ti.steps;

import br.com.ti.driver.DriverWeb;
import br.com.ti.pageobject.comprasPageObject;
import br.com.ti.utils.GeradorPDF;
import br.com.ti.variables.Variables;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class comprasSteps extends DriverWeb {
    public Variables v = new Variables();
    public comprasPageObject comprasPageObject;
    public GeradorPDF geradorPDF;

    public String chrome = "chrome";
    public String firefox = "firefox";
    public String edge = "edge";
    public String link = "http://www.automationpractice.pl/index.php";

    public String email;
    public String titulo;
    public String primeiroNome;
    public String ultimoNome;
    public String senha;
    public String companhia;
    public String endereco;
    public String cidade;
    public String cep;
    public String telefone;
    public String celular;
    public String email2;
    public String cpArEmailAddress;
    public String cpArPassword;

    private Scenario cenario;
    private String nomeDoCenario;

    public String navegador = chrome;

    @Before("@LojaVirtual")
    public void setUp(Scenario cenario) throws Exception {
        this.cenario = cenario;
        nomeDoCenario = this.cenario.getName();
        criarDriverWeb(navegador,link);
    }

    @After("@LojaVirtual")
    public void tearDown() throws Exception {
        comprasPageObject.fecharPDF();
        fecharDriverWeb();
    }

    @Dado("eu preencher todos os dados do formulario {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void euPreencherTodosOsDadosDoFormulario(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12) throws Exception {
        comprasPageObject = new comprasPageObject(getCurrentRunningDriver(),this.cenario,this.nomeDoCenario);
        comprasPageObject.euPreencherTodosOsDadosDoFormulario(string,string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12) ;
    }

    @Entao("o cadastro do novo cliente e criado")
    public void oCadastroDoNovoClienteECriado() throws Exception {
        comprasPageObject.oCadastroDoNovoClienteECriado();
    }

    @Dado("que efetuei a autenticacao de usuario com {string} e {string}")
    public void efetueiAAutenticacaoDeUsuarioComE(String string, String string2) throws Exception {
        comprasPageObject = new comprasPageObject(getCurrentRunningDriver(),this.cenario,this.nomeDoCenario);
        comprasPageObject.efetueiAAutenticacaoDeUsuarioComE(string, string2);
    }

    @Quando("escolhar um produto e concluir a compra")
    public void escolharUmProdutoEConcluirACompra() throws Exception {
        comprasPageObject.escolharUmProdutoEConcluirACompra();
    }

    @Entao("a compra e finalizada com sucesso")
    public void aCompraEFinalizadaComSucesso() throws Exception {
        comprasPageObject.aCompraEFinalizadaComSucesso();
    }

    @Dado("que efetuei a autenticacao de usuario com {string} incorreto e {string} valida")
    public void queEfetueiAAutenticacaoDeUsuarioComIncorretoEValida(String string, String string2) throws Exception {
        comprasPageObject = new comprasPageObject(getCurrentRunningDriver(),this.cenario,this.nomeDoCenario);
        comprasPageObject.inseriOsDadosDdeAutenticacao(string, string2);
    }

    @Dado("que efetuei a autenticacao de usuario com {string} valida e {string} invalida")
    public void queEfetueiAAutenticacaoDeUsuarioComValidaEInvalida(String string, String string2) throws Exception {
        comprasPageObject = new comprasPageObject(getCurrentRunningDriver(),this.cenario,this.nomeDoCenario);
        comprasPageObject.inseriOsDadosDdeAutenticacao(string, string2);
    }

    @Entao("uma mensagem de erro e exibida {string} e {string}")
    public void umaMensagemDeErroEExibidaE(String erro1, String erro2) throws Exception {
        comprasPageObject.validarMensagemDeErro_ThereIsError(erro1);
        comprasPageObject.validarMensagemDeErro_Autenticacao(erro2);
    }
}
