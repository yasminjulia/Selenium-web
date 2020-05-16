package Testes;
import Suport.Generator;
import Suport.Screenshot;
import Suport.Web;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")

public class InformacoesUsuarioTest {
    private WebDriver navegador;
    @Rule
    public TestName test = new TestName();
    @Before
    public void setUp(){
        navegador = Web.createChrome();

        navegador.findElement(By.linkText("Sign in")).click();
        //Formulario login
        WebElement formulaSignIn = navegador.findElement(By.id("signinbox"));
        formulaSignIn.findElement(By.name("login")).sendKeys("julio0001");
        formulaSignIn.findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.xpath("//div[@id=\"signinbox\"]/div[@class=\"modal-footer\"]/a[\"modal-action waves-effect waves-green btn-flat/[text(='Sign in')]\"]")).click();
        //div[@id="signinbox"]/div[@class="modal-footer"]/a["modal-action waves-effect waves-green btn-flat/[text(='Sign in')]"]
        navegador.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        //Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();
        //Clicar em um link que possui o texto "more data about you"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }
    @Test
    public void testAdicionarUmaInfoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String msgEsperada){
        //Clicar no botao atraves do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        //Identificar o popup onde esta o id addmoredata
        WebElement popupAddData = navegador.findElement(By.id("addmoredata"));
        //Escolher a opção "Phone"
        WebElement campoType = popupAddData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);
        //No campo contact digitar 66666
        popupAddData.findElement(By.name("contact")).sendKeys(contato);
        //Clicar no link de text SAVE q esta no popup
        popupAddData.findElement(By.linkText("SAVE")).click();
        //Na msg de id "toast-container" "Your contact has been added"
        WebElement msgToast = navegador.findElement(By.id("toast-container"));
        String msg = msgToast.getText();
        assertEquals(msgEsperada, msg);
    }
    @Test
    public void removerUmContato(){
        // Clicar no elemento xpath //span[text()="+557199999999"]/following-sibling::a
navegador.findElement(By.xpath("//span[text()=\"+557199999999\"]/following-sibling::a")).click();
        //Confirmar janela javascript
navegador.switchTo().alert().accept();
        //Validar msg Rest in peace, dear phone!
        WebElement msgToast = navegador.findElement(By.id("toast-container"));
        String msg = msgToast.getText();
        assertEquals("Rest in peace, dear phone!", msg);
        Screenshot.tirar(navegador,"D:\\Desktop\\Projeteis\\SeleniumWebDriver\\Foto"+ Generator.dataHora() + test.getMethodName() + ".png");
        //Aguardar 10 seg ate a janela desaparecer
        WebDriverWait aguardar = new WebDriverWait(navegador,10);
        aguardar.until(ExpectedConditions.stalenessOf(msgToast));
        //Fazer Logout
        navegador.findElement(By.linkText("Logout")).click();

    }
    @After
    public void tearDown(){
        navegador.quit();
    }

}
