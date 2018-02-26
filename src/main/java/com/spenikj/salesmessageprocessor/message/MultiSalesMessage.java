package com.spenikj.salesmessageprocessor.message;

import com.spenikj.salesmessageprocessor.sale.ProductType;
import com.spenikj.salesmessageprocessor.sale.Sale;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class MultiSalesMessage extends AbstractMessage {

    protected final Collection<Sale> sales;

    public MultiSalesMessage(String productTypeName, BigDecimal value, long occurencies) {
        sales = LongStream.rangeClosed(1, occurencies).boxed().map(l -> new Sale(new ProductType(productTypeName), value)).collect(Collectors.toList());
    }

    public Collection<Sale> getSales() {
        return sales;
    }
}
