package br.com.ti.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverWeb {
    public WebDriver driver;

    public void criarDriverWeb(String browser, String url){
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
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void criarDriverFirefox(String url){
        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void criarDriverIE(String url) {
        System.setProperty("webdriver.ie.driver", ".//drivers//IEDriverServer.exe");
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void criarDriverEdge(String url) {
        System.setProperty("webdriver.edge.driver", ".//drivers//MicrosoftWebDriver.exe");
        driver = new EdgeDriver();
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
