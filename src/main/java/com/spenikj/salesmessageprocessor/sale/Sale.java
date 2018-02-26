package com.spenikj.salesmessageprocessor.sale;

import java.math.BigDecimal;

public class Sale {

    protected final ProductType productType;
    protected BigDecimal value;

    public Sale(ProductType productType, BigDecimal value) {
        this.productType = productType;
        this.value = value;
    }

    public ProductType getProductType() {
        return productType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
