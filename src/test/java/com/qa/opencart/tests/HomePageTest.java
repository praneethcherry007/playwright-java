package com.qa.opencart.tests;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTest {

    PlaywrightFactory pf;
    Page page;
    HomePage homepage;

    @BeforeTest
    public void setUp() throws IOException {
        pf = new PlaywrightFactory();
        page= pf.initBrowser(null);
        homepage = new HomePage(page);
    }

    @Test
    public void homePageTittleTest(){
        String actualTitle =homepage.getHomePageTittle();
        Assert.assertEquals(actualTitle,"Your Store");
        homepage.getHomePageUrl();
    }
    @AfterTest
    public void tearDown(){
        page.context().browser().close();
    }
}
