package br.com.ti.driver;

import br.com.ti.utils.InfraUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
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
        }else{
            criarDriverChrome(url);
        }
    }

    public void criarDriverChrome(String url){
        String os = InfraUtils.getOsName();
        System.out.println("Sistema Operacional: "+os);

        if (os.equalsIgnoreCase("Mac") || os.equalsIgnoreCase("Unix")) {
            System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
        }else if (os.equalsIgnoreCase("Windows")){
            System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void criarDriverFirefox(String url){
        String os = InfraUtils.getOsName();
        System.out.println("Sistema Operacional: "+os);

        if (os.equalsIgnoreCase("Mac") || os.equalsIgnoreCase("Unix")) {
            System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver");
        }else if (os.equalsIgnoreCase("Windows")){
            System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
        }
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void criarDriverIE(String url) throws Exception {
        String os = InfraUtils.getOsName();
        System.out.println("Sistema Operacional: "+os);

        if (os.equalsIgnoreCase("Mac") || os.equalsIgnoreCase("Unix")) {
            throw new Exception("Não há webdriver do Internet Explorar para SO diferente do Windows!");
        }else if (os.equalsIgnoreCase("Windows")){
            System.setProperty("webdriver.ie.driver", ".//drivers//IEDriverServer.exe");
        }else {
            System.setProperty("webdriver.ie.driver", ".//drivers//IEDriverServer.exe");
        }
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void criarDriverEdge(String url) throws Exception {
        String os = InfraUtils.getOsName();
        System.out.println("Sistema Operacional: "+os);

        if (os.equalsIgnoreCase("Mac") || os.equalsIgnoreCase("Unix")) {
            System.setProperty("webdriver.edge.driver", ".//drivers//MicrosoftWebDriver");
        }else if (os.equalsIgnoreCase("Windows")){
            System.setProperty("webdriver.edge.driver", ".//drivers//MicrosoftWebDriver.exe");
        }else {
            System.setProperty("webdriver.edge.driver", ".//drivers//MicrosoftWebDriver.exe");
        }
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void criarDriverSafari(String url){
        SafariOptions options = new SafariOptions();
        driver = new SafariDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void fecharDriverWeb(){
        if (driver != null){
            driver.quit();
            System.out.println("Driver encerrado com sucesso!");
        }
    }
}