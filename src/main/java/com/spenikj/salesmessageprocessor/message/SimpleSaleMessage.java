package com.spenikj.salesmessageprocessor.message;

import com.spenikj.salesmessageprocessor.sale.Sale;

public class SimpleSaleMessage extends AbstractMessage {

    protected final Sale sale;

    public SimpleSaleMessage(Sale sale) {
        this.sale = sale;
    }

    public Sale getSale() {
        return sale;
    }

}
