package br.com.ti.base;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasePage {
    public Boolean isPresent;
    public WebDriverWait wait;
    private static String nomePasta;
    private File pastaEvidencias;
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clicarRadioButton(int posicao) throws InterruptedException {
        esperar(1000);
        //Monta uma lista com todos os elementos de nome radio
        List<WebElement> radio = this.driver.findElements(By.name("radio"));
        //Pega a posicao 2 da lista (Equivalente a palavra Boa) e clicar nela
        radio.get(posicao).click();
    }

    public void clicarCheckBox(int posicao) throws InterruptedException {
        esperar(1000);
        //Monta uma lista com todos os elementos de nome checkbox
        List<WebElement> checkbox = this.driver.findElements(By.cssSelector("input[type='checkbox']"));
        //Pega a posicao 2 da lista (Equivalente a palavra Boa) e clicar nela
        checkbox.get(posicao).click();
    }

    public void clicarViewBox(int posicao, By by) throws InterruptedException {
        esperar(1000);
        //Monta uma lista com todos os elementos de nome svg
        List<WebElement> svg = this.driver.findElements(by);
        //Pega a posicao 2 da lista (Equivalente a palavra Boa) e clicar nela
        svg.get(posicao).click();
    }

    public void clicarSemEsperar(By by) throws MalformedURLException, InterruptedException {
        aguardarElemento(by);
        this.driver.findElement(by).click();
    }

    public void escrever(By by, String texto) throws MalformedURLException, InterruptedException {
        aguardarElemento(by);
        selecionarElemento(by);
        this.driver.findElement(by).sendKeys(texto);
    }

    public void clicar(By by) throws MalformedURLException, InterruptedException {
        aguardarElemento(by);
        selecionarElemento(by);
        this.driver.findElement(by).click();
    }

    public void esperar(long tempo) throws InterruptedException {
        Thread.sleep(tempo);
    }

    public void validarElementoExibido(By by) throws InterruptedException {
        aguardarElemento(by);
        selecionarElemento(by);
        this.driver.findElement(by).isDisplayed();
    }

    public void MoverParaElemento(By by){
        WebElement elemento = this.driver.findElement(by);
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", elemento);
    }

    public void validarTexto(By by, String texto) throws MalformedURLException, InterruptedException {
        aguardarElemento(by);
        selecionarElemento(by);
        Assert.assertEquals(texto, obterTexto(by));
    }

    public void waitUntilPageLoadComplete() {
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete'"));
    }

    public WebDriver waitFrameAndSwitch(By frame) throws InterruptedException {
        aguardarElemento(frame);
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public void switchToFrame(By by) throws InterruptedException {
        aguardarElemento(by);
        WebElement el = this.driver.findElement(by);
        this.driver.switchTo().frame(el);
    }

    public String obterTexto(By by) throws MalformedURLException, InterruptedException {
        aguardarElemento(by);
        return this.driver.findElement(by).getText();
    }

    public void aguardarElemento(By by) throws InterruptedException {
        esperar(1000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement waitAndFindElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void limparCampo(By by) throws MalformedURLException, InterruptedException {
        aguardarElemento(by);
        driver.findElement(by).sendKeys(Keys.CONTROL+"a");
        driver.findElement(by).sendKeys(Keys.DELETE);
    }

    public Object executarJS(String cmd, Object... param) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(cmd, param);
    }

    public void scrollUp() throws InterruptedException {
        esperar(1000);
        JavascriptExecutor jse1 = (JavascriptExecutor)driver;
        jse1.executeScript("window.scrollBy(0,-200)");
        esperar(1000);
    }

    public void scrollDown() throws InterruptedException {
        esperar(1000);
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("window.scrollBy(0,200)");
        esperar(1000);
    }

    public void scroll(long t) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("window.scrollBy(0,"+t+")");
    }

    public void scrollDownClick(By by) throws MalformedURLException, InterruptedException {
        isPresent = driver.findElements(by).size() > 0;
        System.out.println("SIZE FORA DO WHILE:" + isPresent);
        while (isPresent == false) {
            JavascriptExecutor jse2 = (JavascriptExecutor)driver;
            jse2.executeScript("window.scrollBy(0,30)");
            System.out.println("SIZE DENTRO DO WHILE:" + isPresent);
            isPresent = driver.findElements(by).size() > 0;
        }
        clicar(by);
    }

    public void voltarPaginaBrowser() {
        driver.navigate().back();
    }

    public void atualizarPaginaBrowser() {
        driver.navigate().refresh();
    }

    public void abrirPaginaBrowser(String site) {
        driver.navigate().to(site);
    }

    public void abrirNovaAbaNavegador() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
    }

    public void alterarAbaNavegador(int aba) {
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(aba));
    }

    public void selecionarElemento(By by){
        WebElement elemento = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border=arguments[1]", elemento, "solid 4px red");
    }

    //////////////////////////////////// Metodos para geracao de evidencias ///////////////////////////////

    public void criarPastaEvidencia(String nPasta) throws InterruptedException {
        Date dataAtual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        nomePasta = sdf.format(dataAtual);

        pastaEvidencias = new File("./evidencias/"+nPasta+" "+nomePasta);
        pastaEvidencias.mkdir();
    }

    public void gerarScreenshot(String nomeImagem) throws InterruptedException {
        try {
            TakesScreenshot ts = (TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            org.apache.commons.io.FileUtils.copyFile(source, new File(pastaEvidencias+"\\"+nomeImagem+".png"));

            System.out.println("Screenshot capturado de "+nomeImagem+"!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        esperar(1000);
    }

    public void gerarEvidenciaNoWord(String cenario, String id, String titulo) throws IOException, InvalidFormatException, IOException {
        XWPFDocument doc = new XWPFDocument(new FileInputStream(new File("Template.docx")));
        XWPFParagraph par = doc.createParagraph();
        XWPFRun run1 = par.createRun();
        XWPFRun run2 = par.createRun();
        XWPFRun run3 = par.createRun();

        run1.setText("ID " + String.valueOf(id) + "-");
        run1.setText(cenario);
        run1.setFontSize(11);
        run1.setColor("595959");
        run1.setFontFamily("Calibri Light");

        run2.addBreak();
        run2.addBreak();
        run2.setText("3. EVIDENCIAS DOS CASOS DE TESTE");
        run2.setBold(true);
        run2.setFontSize(11);
        run2.setColor("595959");
        run2.setFontFamily("Calibri Light");

        String[] paths = pastaEvidencias.list();

        for (String path : paths) {
            String imagem = pastaEvidencias + "\\" + path;
            FileInputStream is = new FileInputStream(imagem);

            run3.addBreak();
            run3.setText(imagem);
            run3.setFontSize(11);
            run3.setColor("595959");
            run3.setFontFamily("Calibri Light");
            run3.addBreak();
            run3.addPicture(is, Document.PICTURE_TYPE_PNG, imagem, Units.toEMU(513), Units.toEMU(313));
            run3.addBreak();
            is.close();

            String documento = pastaEvidencias + "\\" + "ID - " + id + " - " + titulo +".docx";
            FileOutputStream fos = new FileOutputStream(documento);
            doc.write(fos);
            fos.close();
        }
    }
}
