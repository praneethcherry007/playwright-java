import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class LoginTest {

    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

    @Test
    public void testLogin() {



        BrowserContext context = browser.newContext();

        /// Start and stop trace can help to see screenshot and snapshots for everystep
        /// Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        Page page = context.newPage();

        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println(page.title());


        page.locator("[name='username']").click();
        page.getByPlaceholder("Username").fill("Admin");
        /// To pause it in particular place for debug
        //page.pause();
        page.getByPlaceholder("Password").click();
        page.getByPlaceholder("Password").fill("admin123");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        /// Stop tracing and export it into a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        page.close();
        browser.close();
        playwright.close();


    }

}

