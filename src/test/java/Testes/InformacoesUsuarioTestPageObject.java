package Testes;

import Pages.LoginPage;
import Suport.Web;
import org.apache.log4j.BasicConfigurator;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestPageObject.csv")
public class InformacoesUsuarioTestPageObject {
    private WebDriver navegador;
    @Before
    public void setUp(){
    navegador = Web.createBrowserStack();
    BasicConfigurator.configure();

    }
    @Test
    public void testAdicionarUmaInfoUsuario( @Param(name = "login")String login,
                                             @Param(name = "senha")String senha,
                                             @Param(name = "tipo")String tipo,
                                             @Param(name = "contato")String contato,
                                             @Param(name = "mensagem")String msgEsperada){
               String textoToast = new LoginPage(navegador)
                .clickSignin()
                .fazerLogin(login, senha)
                .clicarMe()
                .moreDataAbout()
                .clicarBotaoAddMoreData()
                .addContato(tipo, contato)
                .capturarTextToast();

        assertEquals(msgEsperada, textoToast);

    }
    @After
    public void tearDown(){
        navegador.quit();
    }
}
