package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage{
    public MePage(WebDriver navegador) {
        super(navegador);
    }
    public MePage moreDataAbout(){
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
        return this;
    }
    public AddContactPage clicarBotaoAddMoreData(){
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        return new AddContactPage(navegador);
    }
}
