package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeiaPage {
    private WebDriver site;
    public SeiaPage(WebDriver site){
        this.site = site;
    }
    public LoginFormPage clickSignin(){
        site.findElement(By.linkText("Sign in")).click();
        return new LoginFormPage(site);
    }
}
