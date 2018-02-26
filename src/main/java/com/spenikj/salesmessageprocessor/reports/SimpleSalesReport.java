package com.spenikj.salesmessageprocessor.reports;

import com.spenikj.salesmessageprocessor.sale.Sale;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import static java.util.stream.Collectors.*;

/**
 *
 * @author Spenik János <jspenik@indamail.hu>
 */
public class SimpleSalesReport extends Report {

    protected final Map<String, BigDecimal> salesByType;
    protected final BigDecimal total;

    public SimpleSalesReport(Collection<Sale> sales) {
        salesByType = sales.stream()
                .collect(
                        groupingBy(
                                sale -> sale.getProductType().getName(),
                                mapping(
                                        Sale::getValue,
                                        collectingAndThen(
                                                reducing(BigDecimal::add),
                                                Optional::get
                                        )
                                )
                        )
                );

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
