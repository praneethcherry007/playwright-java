package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
Page page;
    //locaters
    private String serach = "input[name='search']";
    //constructor
 public HomePage(Page page){
     this.page =page;
 }
    //methods
    public String  getHomePageTittle(){
        String title = page.title();
        System.out.println("page tittle:"+title);
     return title;
    }

    public String  getHomePageUrl() {
        String url = page.url();
        System.out.println("page url:" + url);
        return url;
    }
}
