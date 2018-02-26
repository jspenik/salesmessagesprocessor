package com.spenikj.salesmessageprocessor.message;

import com.spenikj.salesmessageprocessor.sale.ProductType;
import com.spenikj.salesmessageprocessor.sale.Sale;
import java.math.BigDecimal;

public final class MessagesFactory {

    private MessagesFactory() {
        throw new UnsupportedOperationException();
    }

    public static SimpleSaleMessage simpleSaleMessage(String productTypeName, BigDecimal value) {
        return new SimpleSaleMessage(new Sale(new ProductType(productTypeName), value));
    }

    public static MultiSalesMessage multipleSalesMessage(String productTypeName, BigDecimal value, long occurencies) {
        return new MultiSalesMessage(productTypeName, value, occurencies);
    }

    public static SalesOperationMessage operationSalesMessage(String productTypeName, BigDecimal amount, SalesOperationMessage.Operation operation) {
        return new SalesOperationMessage(productTypeName, amount, operation);
    }
}
