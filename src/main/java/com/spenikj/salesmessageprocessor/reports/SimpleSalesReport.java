package com.spenikj.salesmessageprocessor.reports;

import com.spenikj.salesmessageprocessor.sale.Sale;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Spenik János <jspenik@indamail.hu>
 */
public class SimpleSalesReport extends Report {

    protected final Map<String, BigDecimal> salesByType;
    protected final BigDecimal total;

    public SimpleSalesReport(Collection<Sale> sales) {
        salesByType = new HashMap<>();
        sales.stream().forEach(sale -> {
            salesByType.compute(sale.getProductType().getName(), (key, value) -> value != null ? value.add(sale.getValue()) : sale.getValue());
        });

        total = salesByType.values().stream().reduce(BigDecimal::add).get();
    }

    public BigDecimal getSubTotalOf(String productType) {
        return salesByType.get(productType);
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public void printReport() {
        System.out.println("Summary report of sales:");
        System.out.println("---");
        salesByType.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
        System.out.println("---");

        System.out.println("Total: " + total);
    }
}
