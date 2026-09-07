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
        geradorPDF.evidenciaElemento("Etapa 1 cadastro");
        clicarNoBotaoSignup();

        escolherTitulo();
        inserirNovaSenha();
        inserirDataDeNascimento();
        geradorPDF.evidenciaElemento("Etapa 2 cadastro");

        page.scrollDown();
        inserirPrimeiroNome();
        inserirUltimoNome();
        inserirCompanhia();
        page.scrollDown();
        inserirEndereco();
        geradorPDF.evidenciaElemento("Etapa 3 cadastro");
        clicarNoBotaoCreateAccount();
    }

    public void oCadastroDoNovoClienteECriado() throws Exception {
        page.validarElementoExibido(By.xpath("//b[normalize-space(text())='Account Created!']"));
        page.validarElementoExibido(By.xpath("//p[contains(.,'Congratulations! Your new account has been successfully created!How-To, DIY & Expert Content')]"));
        page.validarElementoExibido(By.xpath("//p[contains(.,'You can now take advantage of member privileges to enhance your online  shopping experience with us.')]"));
        geradorPDF.evidenciaElemento("Fim do cadastro");
        page.clicar(By.xpath(v.btnContinue));
        clicarNoBotaoLogout();
    }

    public void preencherAddresses() throws Exception {
        page.escrever(By.id(v.cpAddress),endereco);
        escolherPais();
        page.escrever(By.id(v.cpEstado),estado);
        page.escrever(By.id(v.cpCity),cidade);
        page.escrever(By.id(v.cpZip),cep);
        page.escrever(By.id(v.cpMobilePhone),celular);
    }

    public void escolherPais() throws Exception {
        WebElement selectElement = driver.findElement(By.id(v.cpPais));
        Select select = new Select(selectElement);
        select.selectByValue(pais);
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
    }

    public void inserirNovoEmail(String email) throws Exception {
        page.escrever(By.xpath(v.cpEmailAddress),email);
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
    }

    public void inserirPrimeiroNome() throws Exception {
        page.escrever(By.id(v.cpFirstName),primeiroNome);
    }

    public void inserirUltimoNome() throws Exception {
        page.escrever(By.id(v.cpLastName),ultimoNome);
    }

    public void inserirCompanhia() throws Exception {
        page.escrever(By.id(v.cpCompany),companhia);
    }

    public void inserirNovaSenha() throws Exception {
        page.escrever(By.id(v.cpPassword),senha);
    }

    public void inserirDataDeNascimento() throws Exception {
        page.clicarSemEsperar(By.xpath(v.slDia));
        page.clicarSemEsperar(By.xpath(v.lbDia));
        page.clicarSemEsperar(By.xpath(v.slMes));
        page.clicarSemEsperar(By.xpath(v.lbMes));
        page.clicarSemEsperar(By.xpath(v.slAno));
        page.clicarSemEsperar(By.xpath(v.lbAno));
    }

    public void clicarNoBotaoLogout() throws Exception {
        page.clicar(By.xpath(v.btnLogout));
    }
}