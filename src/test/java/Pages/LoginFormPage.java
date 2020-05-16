package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage{

    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage typeLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
        return this;
    }
    public LoginFormPage typePassword(String password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
        return this;
    }
    public SecretaPage ClickSignIn(){
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new SecretaPage(navegador);
    }
    public SecretaPage fazerLogin(String login, String senha){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(senha);
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new SecretaPage(navegador);
    }

}
