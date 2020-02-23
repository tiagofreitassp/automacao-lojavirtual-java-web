package br.com.ti.steps;

import br.com.ti.base.BasePage;
import br.com.ti.variables.Variables;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class comprasSteps {
    public Variables v = new Variables();
    public BasePage page = new BasePage();

    public static String nomePasta;
    public File pastaEvidencias;

    public Boolean isPresent;
    public WebDriverWait wait;

    public String chrome = "chrome";
    public String firefox = "firefox";
    public String ie = "ie";
    public String edge = "edge";
    public String link = "http://automationpractice.com/index.php?";

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

    @Dado("que eu estou na pagina da loja virtual")
    public void queEuEstouNaPaginaDaLojaVirtual() throws InterruptedException {
        page.criarDriverWeb(chrome,link);
    }

    @Quando("eu preencher todos os dados do formulario {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void euPreencherTodosOsDadosDoFormulario(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12) throws MalformedURLException, InterruptedException {
        page.criarPastaEvidencia("Realizar Cadastro");

        this.email = string;
        this.titulo = string2;
        this.primeiroNome = string3;
        this.ultimoNome = string4;
        this.senha = string5;
        this.companhia = string6;
        this.endereco = string7;
        this.cidade = string8;
        this.cep = string9;
        this.telefone = string10;
        this.celular = string11;
        this.email2 = string12;

        page.clicar(By.xpath(v.btnSignIn));
        page.escrever(By.id(v.cpEmailAddress),email);
        page.clicar(By.xpath(v.btnCreateAnAccount));

        page.aguardarElemento(By.id(v.rdTitleMrs));

        if(titulo.equalsIgnoreCase("mr")){
            page.clicar(By.id(v.rdTitleMr));
        }else if (titulo.equalsIgnoreCase("mrs")){
            page.clicar(By.id(v.rdTitleMrs));
        }else {
            page.clicar(By.id(v.rdTitleMr));
        }

        page.escrever(By.id(v.cpFirstName),primeiroNome);
        page.escrever(By.id(v.cpLastName),ultimoNome);
        page.escrever(By.id(v.cpPassword),senha);
        page.esperar(1000);
        page.clicar(By.xpath(v.slDia));
        page.esperar(1000);
        page.clicar(By.xpath(v.lbDia));
        page.esperar(1000);
        page.clicar(By.xpath(v.slMes));
        page.esperar(1000);
        page.clicar(By.xpath(v.lbMes));
        page.esperar(1000);
        page.clicar(By.xpath(v.slAno));
        page.esperar(1000);
        page.clicar(By.xpath(v.lbAno));
        page.gerarScreenshot("Ev 1");

        page.scrollDown();

        page.escrever(By.id(v.cpCompany),companhia);
        page.escrever(By.id(v.cpAddress),endereco);
        page.escrever(By.id(v.cpCity),cidade);
        page.clicar(By.xpath(v.slState));
        page.clicar(By.xpath(v.lbState));
        page.gerarScreenshot("Ev 2");

        page.scrollDown();

        page.escrever(By.id(v.cpZip),cep);
        page.escrever(By.id(v.cpHomePhone),telefone);
        page.escrever(By.id(v.cpMobilePhone),celular);
        page.escrever(By.id(v.cpAlias),email2);

        page.gerarScreenshot("Ev 3");
        page.esperar(1000);

        page.clicar(By.xpath(v.btnRegister));
    }

    @Entao("o cadastro do novo cliente e criado")
    public void oCadastroDoNovoClienteECriado() throws IOException, InterruptedException, InvalidFormatException {
        page.aguardarElemento(By.xpath(v.txtBoasVindas));

        page.validarTexto(By.xpath(v.txtBoasVindas),"Welcome to your account. Here you can manage all of your personal information and orders.");
        page.gerarScreenshot("Ev 4");
        page.esperar(1000);

        page.gerarEvidenciaNoWord("Realizar cadastro de cliente","CT01","Cadastro de Cliente");
        page.fecharDriverWeb();
    }

    @Dado("efetuei a autenticacao de usuario com {string} e {string}")
    public void efetueiAAutenticacaoDeUsuarioComE(String string, String string2) throws MalformedURLException, InterruptedException {
        page.criarPastaEvidencia("Realizar compra");

        this.cpArEmailAddress=string;
        this.cpArPassword=string2;

        page.clicar(By.xpath(v.btnSignIn));
        page.escrever(By.id(v.cpArEmailAddress),cpArEmailAddress);
        page.escrever(By.id(v.cpArPassword),cpArPassword);
        page.gerarScreenshot("Ev 1");
        page.clicar(By.id(v.btnArSignin));

        page.aguardarElemento(By.xpath(v.txtBoasVindas));
        page.validarTexto(By.xpath(v.txtBoasVindas),"Welcome to your account. Here you can manage all of your personal information and orders.");
        page.gerarScreenshot("Ev 2");
    }

    @Quando("escolhar um produto e concluir a compra")
    public void escolharUmProdutoEConcluirACompra() throws MalformedURLException, InterruptedException {
        page.clicar(By.xpath(v.lbWomen));
        page.scrollDown();
        page.clicar(By.xpath(v.lbProduto1));
        page.esperar(2500);
        page.validarElementoExibido(By.xpath(v.txtFadedShortSleeveTshirts));
        page.clicar(By.xpath(v.btnAddToCart));

        page.validarElementoExibido(By.xpath(v.txtProductSuccessfullyAddedToYourShoppingCart));
        page.clicar(By.xpath(v.btnProceedToCheckout));

        page.validarElementoExibido(By.id(v.lblShoppingCartSummary));
        page.validarElementoExibido(By.xpath(v.txtProductSuccessfullyAddedToYourShoppingCart2));
        page.clicar(By.xpath(v.btnSCSProceedToCheckout));
        page.gerarScreenshot("Ev 3");

        page.scrollDown();
        page.clicar(By.name(v.btnAdProceedToCheckout));

        //validarElementoExibido(By.id(v.txtShipping));
        page.clicar(By.id(v.ckTermsOfService));
        page.clicar(By.name(v.btnShProceedToCheckout));

        page.validarElementoExibido(By.xpath(v.txtPleaseChooseYourPaymentMethod));
        page.validarTexto(By.id(v.lblTotalProducts),"$16.51");
        page.validarTexto(By.id(v.lblTotalShipping),"$2.00");
        page.validarTexto(By.id(v.lblTotal),"$18.51");
        page.clicar(By.xpath(v.btnPayByBankWire));

        page.validarElementoExibido(By.xpath(v.txtOrderSummary));
        page.validarElementoExibido(By.xpath(v.txtBankWirePayment));
        page.gerarScreenshot("Ev 4");
        page.clicar(By.xpath(v.btnIconfirmMyOrder));
    }

    @Entao("a compra e finalizada com sucesso")
    public void aCompraEFinalizadaComSucesso() throws IOException, InterruptedException, InvalidFormatException {
        page.validarElementoExibido(By.xpath(v.txtOrderConfirmation));
        page.validarElementoExibido(By.xpath(v.txtYourOrderOnMyStoreIsComplete));
        //validarTexto(By.id(v.txtYourOrderOnMyStoreIsComplete),"Your order on My Store is complete.");

        page.gerarScreenshot("Ev 5");
        page.esperar(1000);
        page.clicar(By.xpath(v.btnSignOut));
        page.gerarEvidenciaNoWord("Realizar compra de produto","CT02","Compra de produto");
        page.fecharDriverWeb();
    }
}
