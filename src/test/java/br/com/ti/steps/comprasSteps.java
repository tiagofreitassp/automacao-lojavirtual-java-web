package br.com.ti.steps;

import br.com.ti.driver.DriverWeb;
import br.com.ti.pageobject.comprasPageObject;
import br.com.ti.utils.GeradorPDF;
import br.com.ti.enums.NavegadoresEnums;
import br.com.ti.variables.Variables;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;

public class comprasSteps extends DriverWeb {
    public Variables v = new Variables();
    public comprasPageObject comprasPageObject;
    public GeradorPDF geradorPDF;
    private Scenario cenario;
    private String nomeDoCenario;
    private final String navegador = NavegadoresEnums.edge.getValue();

    @Before("@LojaVirtual")
    public void setUp(Scenario cenario) throws Exception {
        this.cenario = cenario;
        nomeDoCenario = this.cenario.getName();
        criarDriverWeb(navegador);
    }

    @After("@LojaVirtual")
    public void tearDown() throws Exception {
        comprasPageObject.fecharPDF();
        fecharDriverWeb();
    }

    @Dado("eu preencher todos os dados do formulario {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void euPreencherTodosOsDadosDoFormulario(String email, String titulo, String primeiroNome, String ultimoNome, String senha, String companhia, String endereco, String cidade, String cep, String estado, String pais, String telefone, String celular) throws Exception {
        comprasPageObject = new comprasPageObject(getCurrentRunningDriver(),this.cenario,this.nomeDoCenario);
        comprasPageObject.euPreencherTodosOsDadosDoFormulario(email,titulo,primeiroNome,ultimoNome,senha,companhia,endereco,cidade,cep,estado,pais,telefone,celular);
    }

    @Entao("o cadastro do novo cliente e criado")
    public void oCadastroDoNovoClienteECriado() throws Exception {
        comprasPageObject.oCadastroDoNovoClienteECriado();
    }

    @Dado("que efetuei a autenticacao de usuario com {string} incorreto e {string} valida")
    public void queEfetueiAAutenticacaoDeUsuarioComIncorretoEValida(String email, String senha) throws Exception {
        comprasPageObject = new comprasPageObject(getCurrentRunningDriver(),this.cenario,this.nomeDoCenario);
        comprasPageObject.efetuarAutenticacaoComDadosInvalidos(email,senha);
    }

    @Entao("uma mensagem de erro e exibida {string}")
    public void umaMensagemDeErroEExibida(String erro) throws Exception {
        comprasPageObject.umaMensagemDeErroEExibida(erro);
    }

    @Dado("que efetuei a autenticacao de usuario com {string} valida e {string} invalida")
    public void queEfetueiAAutenticacaoDeUsuarioComValidaEInvalida(String email, String senha) throws Exception {
        comprasPageObject = new comprasPageObject(getCurrentRunningDriver(),this.cenario,this.nomeDoCenario);
        comprasPageObject.efetuarAutenticacaoComDadosInvalidos(email,senha);
    }
}
