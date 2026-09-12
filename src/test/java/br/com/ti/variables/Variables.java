package br.com.ti.variables;

public class Variables {
    public String var = "";

    public String lbDia = "//div[@id='uniform-days']//option[2]";
    public String lbMes = "//option[contains(text(),'February')]";
    public String lbAno = "//option[contains(text(),'2000')]";
    public String lblTitle = "//label[contains(text(),'Title')]";

    public String slAno = "//select[@id='years']";
    public String slMes = "//select[@id='months']";
    public String slDia = "//select[@id='days']";

    public String cpName = "//input[@data-qa='signup-name']";
    public String cpPassword = "password";
    public String cpFirstName = "first_name";
    public String cpLastName = "last_name";
    public String cpCompany = "company";
    public String cpAddress = "address1";
    public String cpPais = "country";
    public String cpEstado = "state";
    public String cpCity = "city";
    public String cpZip = "zipcode";
    public String cpMobilePhone = "mobile_number";
    public String cpEmailAddress = "//input[@data-qa='signup-email']";
    public String cpEmailLogin = "//input[@data-qa='login-email']";
    public String cpPasswordLogin = "//input[@data-qa='login-password']";
    public String cpLogin = "//button[@data-qa='login-button']";

    public String btnLogout = "//a[normalize-space(text())='Logout']";
    public String btnContinue = "//a[@data-qa='continue-button']";
    public String btnCreateAccount = "//button[@data-qa='create-account']";
    public String btnSignIn = "//a[normalize-space(text())='Signup / Login']";
    public String btnSignup = "//button[@data-qa='signup-button']";

    public String rdTitleMr = "id_gender1";
    public String rdTitleMrs = "id_gender2";

    public String txtYourEmailOrPasswordIsIncorrect = "//p[normalize-space(text())='Your email or password is incorrect!']";
}
