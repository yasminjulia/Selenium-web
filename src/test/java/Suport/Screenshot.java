package Suport;

        import com.sun.jna.platform.FileUtils;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.TakesScreenshot;
        import org.openqa.selenium.WebDriver;
        import java.io.File;
        import static com.sun.deploy.cache.Cache.copyFile;
        import static com.sun.jna.platform.FileUtils.*;

public class Screenshot {
    public static void tirar (WebDriver navegador, String arquivo){
        File screenshot = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);
        try {
            copyFile(screenshot, new File(arquivo));
        } catch (Exception e){
            System.out.println("Houveram problemas ao copiar o arquivo para a pasta:" + e.getMessage());
        }

    }
}
