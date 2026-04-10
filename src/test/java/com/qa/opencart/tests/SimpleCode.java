package com.qa.opencart.tests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SimpleCode {

    @DataProvider(name = "order1")
    public Object[][] order() {
        return new Object[][]{{"app", "B"}};
    }

    private EOPlaceOrder buildOrder(String symbol, String action) {
        return EOPlaceOrder.builder()
                .symbol(symbol)
                .action(action)
                .build();
    }

    @Test(dataProvider = "order1")
    public void orderPlace(String symbol, String action ){
        EOPlaceOrder order = buildOrder(symbol, action);
        System.out.println("Order created: " + order);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EOPlaceOrder {
        @Builder.Default
        private String symbol="";
        @Builder.Default
        private String action="";
        @Builder.Default
        private Integer quantity=0;
        @Builder.Default
        private String account="900090";
        @Builder.Default
        private Integer value=10;
    }
}


