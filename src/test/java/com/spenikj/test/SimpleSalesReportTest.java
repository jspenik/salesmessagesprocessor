package com.spenikj.test;

import com.spenikj.salesmessageprocessor.sale.ProductType;
import com.spenikj.salesmessageprocessor.sale.Sale;
import com.spenikj.salesmessageprocessor.reports.SimpleSalesReport;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimpleSalesReportTest {

    @Test
    public void printReportTest() {
        new SimpleSalesReport(
                Stream.of(
                        new Sale(new ProductType("apple"), BigDecimal.valueOf(10l)),
                        new Sale(new ProductType("orange"), BigDecimal.valueOf(10l))
                ).collect(Collectors.toList())
        ).printReport();
    }

    @Test
    public void reportSumTest() {
        SimpleSalesReport report = new SimpleSalesReport(
                Stream.of(
                        new Sale(new ProductType("apple"), BigDecimal.valueOf(10l)),
                        new Sale(new ProductType("apple"), BigDecimal.valueOf(30l)),
                        new Sale(new ProductType("orange"), BigDecimal.valueOf(10l))
                ).collect(Collectors.toList())
        );
        
        Assert.assertEquals(report.getSubTotalOf("apple"), BigDecimal.valueOf(40l));
        Assert.assertEquals(report.getSubTotalOf("orange"), BigDecimal.valueOf(10l));

        Assert.assertEquals(report.getTotal(), BigDecimal.valueOf(50l));
    }

}
