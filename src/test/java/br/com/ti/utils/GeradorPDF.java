package br.com.ti.utils;

import br.com.ti.base.BasePage;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class GeradorPDF{
    private BasePage page = new BasePage();

    private Document document;
    private int contador;
    private Scenario cenario;
    private String arqEvidencia;

    public GeradorPDF(Scenario cenario, String nomeTeste){
        this.document = new Document();
        this.cenario = cenario;
        contador = 0;
        gerar(nomeTeste);
        addExternalImage("src/test/resources/utils/selenium_java.png", Element.ALIGN_JUSTIFIED);
        addFormatedText("Cenário: " + cenario.getName().toUpperCase(), FontFactory.HELVETICA, 20, Font.BOLDITALIC,
                BaseColor.BLUE, Element.ALIGN_CENTER);
        textoInicial();
        document.newPage();
    }

    private void gerar(String nomeTeste) {
        try {
            this.arqEvidencia = "./evidencias/" + nomeTeste +" " + DateTimeUtils.getDtHr() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(arqEvidencia));
            document.open();
            document.newPage();
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    public void addExternalImage(String path, int align) {
        try {
            Image image = Image.getInstance(path);
            image.scaleToFit(320f, 320f);
            image.setAlignment(align);
            document.add(image);
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    public void addExternalImage2(String path) {
        try {
            Image image = Image.getInstance(path);
            image.scaleToFit(520f, 520f);
            image.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(image);
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    private void insertPrint(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] imagem = ts.getScreenshotAs(OutputType.BYTES);

        try {
            Image image = Image.getInstance(imagem);
            image.scaleToFit(520f, 520f);
            document.add(image);
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    public void print(String passo,WebDriver driver) {
        if (contador == 2) {
            document.newPage();
            contador = 0;
        }
        addText(passo);
        insertPrint(driver);
        contador++;
    }

    public void addText(String text) {
        try {
            document.add(new Paragraph(text));
        } catch (DocumentException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    private void addFormatedText(String text, String nameFont, float size, int style, BaseColor color, int align) {
        try {
            Font font = FontFactory.getFont(nameFont, size, style, color);
            Paragraph paragraph = new Paragraph(text, font);
            paragraph.setAlignment(align);
            document.add(paragraph);
        } catch (DocumentException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    public void addFormatedText(String text, String nameFont, float size, int style, BaseColor color) {
        addFormatedText(text, nameFont, size, style, color, 0);
    }

    public void addFalhaDeValidacao(String text) {
        addFormatedText(text, FontFactory.HELVETICA_BOLD, 15f, 1, BaseColor.RED);
    }

    public void addException(Exception ex, String passo, WebDriver driver) {
        try {
            addFormatedText("Foi lançada uma exceção durante a execução do passo: " + passo, FontFactory.COURIER_BOLD, 16f,
                    1, BaseColor.RED);
            print("---------ScreenShoot do passo---------",driver);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            addFormatedText(sw.toString(), FontFactory.HELVETICA, 5f, 1, BaseColor.RED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void textoInicial() {
        addText("Data atual: " + DateTimeUtils.getDiaAtual());
        addText("Hora atual: " + DateTimeUtils.getHoraAtual());
        addText("Endereço IP: " + InfraUtils.getIpClient());
        addText("Usuário Atual ou de Rede: " + InfraUtils.getWindowsUser());
        addText("Nome do computador de execução: " + InfraUtils.getPcName());
        document.newPage();
    }

    public boolean finishPdf() {
        BaseColor cor = BaseColor.GREEN;
        String resultado;
        String novoNome;
        if (cenario.getStatus().toString().equals("FAILED")) {
            cor = BaseColor.RED;
            resultado = "__FAILED";
        } else {
            resultado = "__PASSED";
        }
        addFormatedText("STATUS DO CENARIO: " + cenario.getStatus(), FontFactory.TIMES_BOLDITALIC, 16f, 1, cor);
        document.close();
        novoNome = arqEvidencia.replace(".pdf", resultado + ".pdf");
        return new File(arqEvidencia).renameTo(new File(novoNome));
    }

    public void evidenciaElemento(String passo, WebDriver driver) {
        print(passo,driver);
    }
}
