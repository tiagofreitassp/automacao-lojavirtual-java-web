package br.com.ti.pageobject;

import br.com.ti.base.BasePage;
import br.com.ti.utils.GeradorPDF;
import br.com.ti.variables.Variables;
import io.cucumber.java.Scenario;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;

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
    public String email2;
    public String cpArEmailAddress;
    public String cpArPassword;

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

    public void euPreencherTodosOsDadosDoFormulario(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12) throws MalformedURLException, InterruptedException {
        //page.criarPastaEvidencia(nomeDoCenario);

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

        geradorPDF.evidenciaElemento("Etapa 1 cadastro");
        //page.gerarScreenshot("Ev 1");

        page.scrollDown();

        page.escrever(By.id(v.cpCompany),companhia);
        page.escrever(By.id(v.cpAddress),endereco);
        page.escrever(By.id(v.cpCity),cidade);
        page.clicar(By.xpath(v.slState));
        page.clicar(By.xpath(v.lbState));

        geradorPDF.evidenciaElemento("Etapa 2 cadastro");
        //page.gerarScreenshot("Ev 2");

        page.scrollDown();

        page.escrever(By.id(v.cpZip),cep);
        page.escrever(By.id(v.cpHomePhone),telefone);
        page.escrever(By.id(v.cpMobilePhone),celular);
        page.escrever(By.id(v.cpAlias),email2);

        geradorPDF.evidenciaElemento("Etapa 3 cadastro");
        //page.gerarScreenshot("Ev 3");
        page.esperar(1000);

        page.clicar(By.xpath(v.btnRegister));
    }

    public void oCadastroDoNovoClienteECriado() throws IOException, InterruptedException, InvalidFormatException {
        page.aguardarElemento(By.xpath(v.txtBoasVindas));

        page.validarTexto(By.xpath(v.txtBoasVindas),"Welcome to your account. Here you can manage all of your personal information and orders.");

        geradorPDF.evidenciaElemento("Fim do cadastro");
        //page.gerarScreenshot("Ev 4");
        page.esperar(1000);

        //page.gerarEvidenciaNoWord(nomeDoCenario,"CT01","Cadastro de Cliente");
    }

    public void efetueiAAutenticacaoDeUsuarioComE(String string, String string2) throws MalformedURLException, InterruptedException {
        //page.criarPastaEvidencia(nomeDoCenario);

        this.cpArEmailAddress=string;
        this.cpArPassword=string2;

        page.clicar(By.xpath(v.btnSignIn));
        page.escrever(By.id(v.cpArEmailAddress),cpArEmailAddress);
        page.escrever(By.id(v.cpArPassword),cpArPassword);

        geradorPDF.evidenciaElemento("Autenticacao");
        //page.gerarScreenshot("Ev0");

        page.clicar(By.id(v.btnArSignin));

        page.validarTexto(By.xpath(v.txtBoasVindas),"Welcome to your account. Here you can manage all of your personal information and orders.");

        geradorPDF.evidenciaElemento("Validar tela inicial");
        //page.gerarScreenshot("Ev1");
    }

    public void escolharUmProdutoEConcluirACompra() throws MalformedURLException, InterruptedException {
        page.clicar(By.xpath(v.lbWomen));
        page.MoverParaElemento(By.xpath(v.lbProduto1));

        geradorPDF.evidenciaElemento("Escolher produto");
        //page.gerarScreenshot("Ev2");

        page.clicar(By.xpath(v.lbProduto1));

        page.esperar(3000);
        page.waitFrameAndSwitch(By.xpath(v.iFrame_FadedShortSleeveTshirts));

        page.validarElementoExibido(By.xpath(v.txtFadedShortSleeveTshirts));
        geradorPDF.evidenciaElemento("Clicar em Add to cart");
        //page.gerarScreenshot("Ev3");
        page.clicar(By.name(v.btnAddToCart));
        geradorPDF.evidenciaElemento("Clicado no botao Add to cart");
        page.esperar(9000);

        page.aguardarElemento(By.xpath(v.btnProceedToCheckout));
        //page.validarElementoExibido(By.xpath(v.txtProductSuccessfullyAddedToYourShoppingCart));
        geradorPDF.evidenciaElemento("Clicar em Proceed to checkout");
        //page.gerarScreenshot("Ev4");
        page.clicar(By.xpath(v.btnProceedToCheckout));

        page.validarElementoExibido(By.id(v.lblShoppingCartSummary));
        page.validarElementoExibido(By.xpath(v.txtProductSuccessfullyAddedToYourShoppingCart2));
        page.scrollDown();
        geradorPDF.evidenciaElemento("Clicar em SCS Proceed to checkout");
        //page.gerarScreenshot("Ev5");
        page.clicar(By.xpath(v.btnSCSProceedToCheckout));

        page.scrollDown();
        page.clicar(By.name(v.btnAdProceedToCheckout));

        //validarElementoExibido(By.id(v.txtShipping));
        page.clicar(By.id(v.ckTermsOfService));
        geradorPDF.evidenciaElemento("Clicar em Sh Proceed to checkout");
        //page.gerarScreenshot("Ev6");
        page.clicar(By.name(v.btnShProceedToCheckout));

        page.validarElementoExibido(By.xpath(v.txtPleaseChooseYourPaymentMethod));
        page.scrollDown();
        page.validarTexto(By.id(v.lblTotalProducts),"$16.51");
        page.validarTexto(By.id(v.lblTotalShipping),"$2.00");
        page.validarTexto(By.id(v.lblTotal),"$18.51");
        geradorPDF.evidenciaElemento("Clicar em Pay By Bank Wire");
        //page.gerarScreenshot("Ev7");
        page.clicar(By.xpath(v.btnPayByBankWire));

        page.validarElementoExibido(By.xpath(v.txtOrderSummary));
        page.validarElementoExibido(By.xpath(v.txtBankWirePayment));
        page.scrollDown();
        geradorPDF.evidenciaElemento("Clicar em I confirm my order");
        //page.gerarScreenshot("Ev8");
        page.clicar(By.xpath(v.btnIconfirmMyOrder));
    }

    public void aCompraEFinalizadaComSucesso() throws IOException, InterruptedException, InvalidFormatException {
        page.validarElementoExibido(By.xpath(v.txtOrderConfirmation));
        page.validarElementoExibido(By.xpath(v.txtYourOrderOnMyStoreIsComplete));
        page.validarTexto(By.xpath(v.txtYourOrderOnMyStoreIsComplete),"Your order on My Store is complete.");
        geradorPDF.evidenciaElemento("Validar texto 'Your order on My Store is complete.'");
        page.scrollDown();

        geradorPDF.evidenciaElemento("Clicar em Sign Out");
        //page.gerarScreenshot("Ev9");
        page.esperar(1000);
        page.clicar(By.xpath(v.btnSignOut));
        //page.gerarEvidenciaNoWord(nomeDoCenario,"CT02","Compra de produto");
    }
}
