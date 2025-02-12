package br.com.ti.pageobject;

import br.com.ti.base.BasePage;
import br.com.ti.utils.GeradorPDF;
import br.com.ti.variables.Variables;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void euPreencherTodosOsDadosDoFormulario(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12) throws Exception {
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

        clicarNoBotaoSignIn();
        inserirNovoEmail(this.email);
        clicarNoBotaoCreateAnAccount();

        escolherTitulo();

        inserirPrimeiroNome();
        inserirSobrenome();
        inserirNovaSenha();

        page.scrollDown();

        inserirDataDeNascimento();

        geradorPDF.evidenciaElemento("Etapa 1 cadastro");

        clicarNoBotaoregister();

        inserirEndereco();

        page.scrollDown();

        inserirCEP();
        inserirTelefone();
        inserirCelular();
        inserirAlias();

        geradorPDF.evidenciaElemento("Etapa 3 cadastro");

        clicarNoBotaoSubmitAddress();
    }

    public void oCadastroDoNovoClienteECriado() throws Exception {
        page.validarElementoExibido(By.xpath("//h1[contains(text(),'My addresses')]"));

        geradorPDF.evidenciaElemento("Fim do cadastro");
    }

    public void efetueiAAutenticacaoDeUsuarioComE(String string, String string2) throws Exception {
        this.cpArEmailAddress=string;
        this.cpArPassword=string2;

        clicarNoBotaoSignIn();
        inserirEmail();
        inserirSenha();

        geradorPDF.evidenciaElemento("Autenticacao");

        clicarNoBotaoSignInSubmit();
        validarTexto_BoasVindas();

        geradorPDF.evidenciaElemento("Validar tela inicial");
    }

    public void inseriOsDadosDdeAutenticacao(String string, String string2) throws Exception {
        this.cpArEmailAddress=string;
        this.cpArPassword=string2;

        clicarNoBotaoSignIn();
        inserirEmail();
        inserirSenha();

        geradorPDF.evidenciaElemento("Autenticacao");

        clicarNoBotaoSignInSubmit();

        geradorPDF.evidenciaElemento("Validar tela inicial");
    }

    public void escolharUmProdutoEConcluirACompra() throws Exception {
        clicarNoMenuWomen();

        //Escolher produto Printed Chiffon Dress
        escolherProduto_PrintedChiffonDress();
        escolherTamanho("M");
        escolherCor_Verde();
        clicarNoBotaoAddToCart();

        clicarNoBotaoContinueShopping();

        //Escolher produto Printed Dress
        escolherProduto_PrintedDress();
        escolherTamanho("M");
        escolherCor_Bege();
        clicarNoBotaoAddToCart();

        clicarNoBotaoContinueShopping();

        //Escolher produto Faded Short Sleeve T-shirts
        escolherProduto_FadedShortSleeveTshirts();
        escolherTamanho("L");
        escolherCor_Laranja();
        clicarNoBotaoAddToCart();

        clicarNoBotaoProceedToCheckout();

        validarShoppingCartSummary();

        clicarNoBotaoCheckout();

        clicarNoTermoDeServico();

        escolherPagamento();

        validarOrder();
        validarBankWire();
        clicarNoBotaoIconfirmMyOrder();
    }

    public void aCompraEFinalizadaComSucesso() throws Exception {
        page.validarElementoExibido(By.xpath(v.txtOrderConfirmation));
        page.validarElementoExibido(By.xpath(v.txtYourOrderOnMyStoreIsComplete));
        geradorPDF.evidenciaElemento("Validar tela 'Your order on My Store is complete.'");

        logout();
    }

    public void clicarEmMyAddresses() throws Exception {
        page.clicar(By.xpath("//span[contains(text(),'My addresses')]"));
    }

    public void clicarEmAddaNewAddresses() throws Exception {
        page.clicar(By.xpath("//*[@id=\"center_column\"]/div/a"));
    }

    public void preencherAddresses() throws Exception {
        page.escrever(By.id(v.cpCompany),companhia);
        page.escrever(By.id(v.cpAddress),endereco);
        page.escrever(By.id(v.cpCity),cidade);
        page.clicar(By.xpath(v.slState));
        page.clicar(By.xpath(v.lbState));

        geradorPDF.evidenciaElemento("Etapa 2 cadastro");
    }

    public void clicarNoBotaoSubmitAddress() throws Exception {
        page.clicar(By.xpath("//button[@id='submitAddress']"));
    }

    public void inserirCEP() throws Exception {
        page.escrever(By.id(v.cpZip),cep);
    }

    public void inserirTelefone() throws Exception {
        page.escrever(By.id(v.cpHomePhone),telefone);
    }

    public void inserirCelular() throws Exception {
        page.escrever(By.id(v.cpMobilePhone),celular);
    }

    public void inserirAlias() throws Exception {
        page.escrever(By.id(v.cpAlias),email2);
    }

    public void inserirEndereco() throws Exception {
        page.aguardarElemento(By.xpath("//p[contains(text(),'Your account has been created.')]"));
        clicarEmMyAddresses();
        clicarEmAddaNewAddresses();
        preencherAddresses();
    }

    public void clicarNoBotaoregister() throws Exception {
        page.clicar(By.xpath(v.btnRegister));
    }

    public void clicarNoBotaoSignIn() throws Exception {
        page.clicar(By.xpath(v.btnSignIn));
    }

    public void inserirNovoEmail(String email) throws Exception {
        page.escrever(By.id(v.cpEmailAddress),email);
    }

    public void clicarNoBotaoCreateAnAccount() throws Exception {
        page.clicar(By.xpath(v.btnCreateAnAccount));
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

    public void inserirSobrenome() throws Exception {
        page.escrever(By.id(v.cpLastName),ultimoNome);
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

    public void inserirEmail() throws Exception {
        page.escrever(By.id(v.cpArEmailAddress),cpArEmailAddress);
    }

    public void inserirSenha() throws Exception {
        page.escrever(By.id(v.cpArPassword),cpArPassword);
    }

    public void clicarNoBotaoSignInSubmit() throws Exception {
        page.clicar(By.id(v.btnArSignin));
    }

    public void validarTexto_BoasVindas() throws Exception {
        page.validarTexto(By.xpath(v.txtBoasVindas),"Welcome to your account. Here you can manage all of your personal information and orders.");
    }

    public void clicarNoBotaoProceedToCheckout () throws Exception {
        geradorPDF.evidenciaElemento("Clicar em Proceed to checkout");
        page.clicar(By.xpath(v.btnProceedToCheckout));
    }

    public void clicarNoBotaoContinueShopping () throws Exception {
        geradorPDF.evidenciaElemento("Clicar em Continue Shopping");
        page.clicar(By.xpath(v.btnContinueShopping));
    }

    public void clicarNoMenuWomen() throws Exception {
        page.clicar(By.xpath(v.lbWomen));
    }

    public void escolherProduto_PrintedChiffonDress() throws Exception {
        page.moverParaElemento(By.xpath(v.txtProduto_PrintedChiffonDress));

        geradorPDF.evidenciaElemento("Escolher produto Printed Chiffon Dress");

        page.clicar(By.xpath(v.txtProduto_PrintedChiffonDress));

        page.esperar(4000);
        page.waitFrameAndSwitch(By.xpath(v.iFrame_FadedShortSleeveTshirts));
        page.validarElementoExibido(By.xpath(v.txtPrintedChiffonDress));
    }

    public void escolherProduto_Blouse() throws Exception {
        page.scroll(-1800);
        page.moverParaElemento(By.xpath(v.txtProduto_Blouse));

        geradorPDF.evidenciaElemento("Escolher produto Blouse");

        page.clicar(By.xpath(v.txtProduto_Blouse));

        page.esperar(4000);
        page.waitFrameAndSwitch(By.xpath(v.iFrame_FadedShortSleeveTshirts));
        page.validarElementoExibido(By.xpath("//h1[contains(text(),'Blouse')]"));
    }

    public void escolherProduto_PrintedDress() throws Exception {
        page.scroll(-1800);
        page.moverParaElemento(By.xpath(v.txtProduto_PrintedDress));

        geradorPDF.evidenciaElemento("Escolher produto Printed Dress");

        page.clicar(By.xpath(v.txtProduto_PrintedDress));

        page.esperar(4000);
        page.waitFrameAndSwitch(By.xpath(v.iFrame_FadedShortSleeveTshirts));
        page.validarElementoExibido(By.xpath("//h1[contains(text(),'Printed Dress')]"));
    }

    public void escolherProduto_FadedShortSleeveTshirts() throws Exception {
        page.scroll(-1800);
        page.moverParaElemento(By.xpath(v.txtFadedShortSleeveTshirts));

        geradorPDF.evidenciaElemento("Escolher produto Faded Short Sleeve T-shirts ");

        page.clicar(By.xpath(v.txtFadedShortSleeveTshirts));

        page.esperar(4000);
        page.waitFrameAndSwitch(By.xpath(v.iFrame_FadedShortSleeveTshirts));
        page.validarElementoExibido(By.xpath("//h1[contains(text(),'Faded Short Sleeve T-shirts')]"));
    }

    public void escolherTamanho(String size) throws Exception {
        page.clicar(By.id(v.selectSize));

        String tm = "//option[contains(text(),'"+size+"')]";
        page.clicar(By.xpath(tm));
    }

    public void escolherCor_Laranja() throws Exception {
        page.clicar(By.name("Orange"));
    }

    public void escolherCor_Verde() throws Exception {
        page.clicar(By.name("Green"));
    }

    public void escolherCor_Preta() throws Exception {
        page.clicar(By.name("Black"));
    }

    public void escolherCor_Bege() throws Exception {
        page.clicar(By.name("Beige"));
    }

    public void escolherCor_Rosa() throws Exception {
        page.clicar(By.name("Pink"));
    }

    public void escolherCor_Azul() throws Exception {
        page.clicar(By.name("Blue"));
    }

    public void escolherCor_Amarela() throws Exception {
        page.clicar(By.name("Yellow"));
    }

    public void clicarNoBotaoAddToCart() throws Exception {
        geradorPDF.evidenciaElemento("Clicar em Add to cart");

        page.clicar(By.name(v.btnAddToCart));
        geradorPDF.evidenciaElemento("Clicado no botao Add to cart");
        page.switchToDefault();
    }

    public void validarShoppingCartSummary() throws Exception {
        page.validarElementoExibido(By.id(v.lblShoppingCartSummary));
        page.validarElementoExibido(By.xpath(v.txtProductSuccessfullyAddedToYourShoppingCart2));
        page.scrollDown();
        geradorPDF.evidenciaElemento("Clicar em SCS Proceed to checkout");
        page.clicar(By.xpath(v.btnSCSProceedToCheckout));
    }

    public void clicarNoBotaoCheckout() throws Exception {
        page.scrollDown();
        page.clicar(By.name(v.btnAdProceedToCheckout));
    }

    public void clicarNoTermoDeServico() throws Exception {
        page.clicar(By.id(v.ckTermsOfService));
        geradorPDF.evidenciaElemento("Clicar em Sh Proceed to checkout");
        page.clicar(By.name(v.btnShProceedToCheckout));
    }

    public void escolherPagamento() throws Exception {
        page.validarElementoExibido(By.xpath(v.txtPleaseChooseYourPaymentMethod));
        page.scrollDown();
        page.validarTexto(By.id(v.lblTotalProducts),"$84");
        page.validarTexto(By.id(v.lblTotalShipping),"$7");
        page.validarTexto(By.id(v.lblTotal),"$91");
        page.scrollDown();
        geradorPDF.evidenciaElemento("Clicar em Pay By Bank Wire");
        page.clicar(By.xpath(v.btnPayByBankWire));
    }

    public void validarOrder() throws Exception {
        page.validarElementoExibido(By.xpath(v.txtOrderSummary));
    }

    public void validarBankWire() throws Exception{
        page.validarElementoExibido(By.xpath(v.txtBankWirePayment));
    }

    public void clicarNoBotaoIconfirmMyOrder() throws Exception {
        page.scrollDown();
        geradorPDF.evidenciaElemento("Clicar em I confirm my order");
        page.clicar(By.xpath(v.btnIconfirmMyOrder));
    }

    public void logout() throws Exception {
        page.scrollDown();
        geradorPDF.evidenciaElemento("Clicar em Sign Out");
        page.esperar(1000);
        page.clicar(By.xpath(v.btnSignOut));
    }

    public void validarMensagemDeErro_ThereIsError(String msg) throws Exception {
        page.validarElementoExibido(By.xpath("//p[normalize-space()='"+msg+"']"));
        geradorPDF.evidenciaElemento("Validar mensagem de erro There is Error");
    }

    public void validarMensagemDeErro_Autenticacao(String msg) throws Exception {
        page.validarElementoExibido(By.xpath("//li[normalize-space()='"+msg+"']"));
        geradorPDF.evidenciaElemento("Validar mensagem de erro na autenticação");
    }
}