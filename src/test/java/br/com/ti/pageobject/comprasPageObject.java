package br.com.ti.pageobject;

import br.com.ti.base.BasePage;
import br.com.ti.utils.GeradorPDF;
import br.com.ti.variables.Variables;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class comprasPageObject {
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
    public String pais;
    public String estado;

    public GeradorPDF geradorPDF;
    public BasePage page;
    private WebDriver driver;

    public Variables v = new Variables();

    public comprasPageObject(WebDriver driver, Scenario cenario, String nomeTeste) {
        this.driver=driver;
        this.page = new BasePage(this.driver);
        this.geradorPDF = new GeradorPDF(this.driver,cenario, nomeTeste);
    }

    public void fecharPDF(){
        this.geradorPDF.finishPdf();
    }

    public void euPreencherTodosOsDadosDoFormulario(
            String email, String titulo, String primeiroNome, String ultimoNome,
            String senha, String companhia, String endereco, String cidade, String cep, String estado,
            String pais, String telefone,
            String celular) throws Exception {
        this.email = email;
        this.titulo = titulo;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.senha = senha;
        this.companhia = companhia;
        this.endereco = endereco;
        this.cidade = cidade;
        this.cep = cep;
        this.estado = estado;
        this.pais = pais;
        this.telefone = telefone;
        this.celular = celular;

        clicarNoBotaoSignIn();
        inserirNome(this.primeiroNome+" "+this.ultimoNome);
        inserirNovoEmail(this.email);
        clicarNoBotaoSignup();

        escolherTitulo();
        inserirNovaSenha();
        inserirDataDeNascimento();

        page.scrollDown();
        inserirPrimeiroNome();
        inserirUltimoNome();
        inserirCompanhia();
        page.scrollDown();
        inserirEndereco();
        clicarNoBotaoCreateAccount();
    }

    public void oCadastroDoNovoClienteECriado() throws Exception {
        page.validarElementoExibido(By.xpath("//b[normalize-space(text())='Account Created!']"));
        geradorPDF.evidenciaElemento("Fim do cadastro");
        page.clicar(By.xpath(v.btnContinue));
    }

    public void efetuarAutenticacaoComDadosInvalidos(String email, String senha) throws Exception {
        clicarNoBotaoSignIn();
        inserirEmailAdress(email);
        inserirPassword(senha);
        geradorPDF.evidenciaElemento("Autenticação de usuário");
        clicarNoBotaoLogin();
    }

    public void umaMensagemDeErroEExibida(String erro) throws Exception {
        validarMensagemDeErro(erro);
    }

    public void validarMensagemDeErro(String erro) throws Exception {
        page.validarElementoExibido(By.xpath(v.txtYourEmailOrPasswordIsIncorrect));
        geradorPDF.evidenciaElemento("Mensagem de erro exibida");
    }

    private void clicarNoBotaoLogin() throws Exception {
        page.clicar(By.xpath(v.cpLogin));
    }

    private void inserirEmailAdress(String email) throws Exception {
        page.escrever(By.xpath(v.cpEmailLogin),email);
        geradorPDF.evidenciaElemento("Inserir e-mail");
    }

    private void inserirPassword(String senha) throws Exception {
        page.escrever(By.xpath(v.cpPasswordLogin),senha);
        geradorPDF.evidenciaElemento("Inserir senha");
    }

    public void preencherAddresses() throws Exception {
        page.escrever(By.id(v.cpAddress),endereco);
        escolherPais();
        page.escrever(By.id(v.cpEstado),estado);
        page.escrever(By.id(v.cpCity),cidade);
        page.escrever(By.id(v.cpZip),cep);
        page.escrever(By.id(v.cpMobilePhone),celular);
        geradorPDF.evidenciaElemento("Preencher endereço");
    }

    public void escolherPais() throws Exception {
        WebElement selectElement = driver.findElement(By.id(v.cpPais));
        Select select = new Select(selectElement);
        select.selectByValue(pais);
        geradorPDF.evidenciaElemento("Escolher país");
    }

    public void clicarNoBotaoCreateAccount() throws Exception {
        page.clicar(By.xpath(v.btnCreateAccount));
    }

    public void inserirEndereco() throws Exception {
        preencherAddresses();
    }

    public void clicarNoBotaoSignIn() throws Exception {
        page.clicar(By.xpath(v.btnSignIn));
    }

    public void inserirNome(String nome) throws Exception {
        page.escrever(By.xpath(v.cpName),nome);
        geradorPDF.evidenciaElemento("Inserir nome");
    }

    public void inserirNovoEmail(String email) throws Exception {
        page.escrever(By.xpath(v.cpEmailAddress),email);
        geradorPDF.evidenciaElemento("Inserir novo e-mail");
    }

    public void clicarNoBotaoSignup() throws Exception {
        page.clicar(By.xpath(v.btnSignup));
    }

    public void escolherTitulo() throws Exception {
        page.aguardarElemento(By.id(v.lblTitle));

        if(titulo.equalsIgnoreCase("mr")){
            page.clicar(By.id(v.rdTitleMr));
        }

        if (titulo.equalsIgnoreCase("mrs")){
            page.clicar(By.id(v.rdTitleMrs));
        }

        geradorPDF.evidenciaElemento("Escolher título");
    }

    public void inserirPrimeiroNome() throws Exception {
        page.escrever(By.id(v.cpFirstName),primeiroNome);
        geradorPDF.evidenciaElemento("Inserir primeiro nome");
    }

    public void inserirUltimoNome() throws Exception {
        page.escrever(By.id(v.cpLastName),ultimoNome);
        geradorPDF.evidenciaElemento("Inserir último nome");
    }

    public void inserirCompanhia() throws Exception {
        page.escrever(By.id(v.cpCompany),companhia);
        geradorPDF.evidenciaElemento("Inserir companhia");
    }

    public void inserirNovaSenha() throws Exception {
        page.escrever(By.id(v.cpPassword),senha);
        geradorPDF.evidenciaElemento("Inserir nova senha");
    }

    public void inserirDataDeNascimento() throws Exception {
        page.clicarSemEsperar(By.xpath(v.slDia));
        page.clicarSemEsperar(By.xpath(v.lbDia));
        page.clicarSemEsperar(By.xpath(v.slMes));
        page.clicarSemEsperar(By.xpath(v.lbMes));
        page.clicarSemEsperar(By.xpath(v.slAno));
        page.clicarSemEsperar(By.xpath(v.lbAno));
        geradorPDF.evidenciaElemento("Inserir data de nascimento");
    }

    public void clicarNoBotaoLogout() throws Exception {
        page.clicar(By.xpath(v.btnLogout));
    }
}