package br.com.ti.driver;

import br.com.ti.utils.InfraUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.logging.Logger;

public class DriverWeb {
    public WebDriver driver;

    public WebDriver getCurrentRunningDriver() {
        return driver;
    }

    public void criarDriverWeb(String browser, String url) throws Exception {
        if(browser.equalsIgnoreCase("chrome")){
            criarDriverChrome(url);
        }else if (browser.equalsIgnoreCase("firefox")){
            criarDriverFirefox(url);
        }else if (browser.equalsIgnoreCase("ie")){
            criarDriverIE(url);
        }else if (browser.equalsIgnoreCase("edge")){
            criarDriverEdge(url);
        }else if (browser.equalsIgnoreCase("safari")){
            criarDriverSafari(url);
        }else{
            criarDriverChrome(url);
        }

        driver.manage().window().maximize();
        driver.get(url);
    }

    public void criarDriverChrome(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void criarDriverFirefox(String url){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    public void criarDriverIE(String url) throws Exception {
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
    }

    public void criarDriverEdge(String url) throws Exception {
        WebDriverManager.edgedriver().setup();
        //O Driver abaixo e do MS Edge Chromium e nao da versao anterior dele
        EdgeOptions edgeOptions = new EdgeOptions();
        driver = new EdgeDriver(edgeOptions);
    }

    public void criarDriverSafari(String url) throws Exception {
        try{
            SafariOptions options = new SafariOptions();
            driver = new SafariDriver(options);
        }catch (Exception e){
            System.out.println("Não há webdriver do Safari para SO diferente do MacOS!");
        }
    }

    public void fecharDriverWeb(){
        driver.quit();
        System.out.println("Driver encerrado com sucesso!");
    }
}
