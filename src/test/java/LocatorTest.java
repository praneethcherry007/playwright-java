import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class LocatorTest {

    static Page page;

    @Test
    public void testLocators() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println(page.title());
        Locator userName = page.locator("[name = 'username']");
        userName.click();
        userName.fill("Admin");
        Locator passWord = page.locator("[name = 'password']");
        passWord.click();
        passWord.fill("admin123");
        Locator LoginButton = page.locator("//button[normalize-space()='Login']");
        LoginButton.click();
    }

    @Test
    public void selectorHub() {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        BrowserContext context = browser.newContext();
        /// Start and stop trace can help to see screenshot and snapshots for everystep
        /// Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();
        page.navigate("https://selectorshub.com/xpath-practice-page/");
        page.getByLabel("", new Page.GetByLabelOptions().setExact(true)).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Practice Page")).click();
//        page.pause();
//        page.locator("input[type='checkbox']:left-of(:text('Garry.white'))").first().click();
//        page.pause();
        selectCheckbox("Garry.white");
        selectCheckbox("Jasmine.Morgan");
        getUserRole("Garry.white");
        getUserRole("Jasmine.Morgan");
        /// Stop tracing and export it into a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
    }

    public void selectCheckbox(String userName) {
        page.locator("input[type='checkbox']:left-of(:text('" + userName + "'))").first().click();

    }

    public String getUserRole(String userName) {
        return page.locator("td:right-of(:text('" + userName + "'))").first().textContent();

    }

}