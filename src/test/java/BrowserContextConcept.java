import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class BrowserContextConcept {

    @Test
    public void testMultipleBrowsers() {


        Playwright playwright = Playwright.create();
        Browser browserCro = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Browser browserFir = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Browser browserWeb = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext browserContext1 = browserCro.newContext();
        Page page1 = browserContext1.newPage();
        page1.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println(page1.title());

        BrowserContext browserContext2 = browserFir.newContext();
        Page page2 = browserContext2.newPage();
        page2.navigate("https://www.youtube.com/");
        System.out.println(page2.title());

        BrowserContext browserContext3 = browserWeb.newContext();
        Page page3 = browserContext3.newPage();
        page3.navigate("https://www.netflix.com/in/");
        System.out.println(page3.title());
    }
}