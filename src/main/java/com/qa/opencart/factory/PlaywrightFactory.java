package com.qa.opencart.factory;

import com.microsoft.playwright.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    public Page initBrowser(String browserName) throws IOException {
        prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }
            prop.load(input);
        }

        String browserType = browserName != null ? browserName : prop.getProperty("browser");
        String url = prop.getProperty("url");
        boolean headless = Boolean.parseBoolean(prop.getProperty("headless"));

        playwright = Playwright.create();
        System.out.println("browser name is:" + browserType);
        switch (browserType.toLowerCase()){
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
                break;
            case "msedge":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless));
                break;
            default:
                System.out.println("please pass the right browser name");
                break;
        }
        browserContext =browser.newContext();
        page =browserContext.newPage();
        page.navigate(url);

    return page;
    }
}
