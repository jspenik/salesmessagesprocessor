package com.spenikj.test;

import com.spenikj.salesmessageprocessor.message.MessagesFactory;
import com.spenikj.salesmessageprocessor.message.SalesOperationMessage;
import com.spenikj.salesmessageprocessor.reports.SalesOperationsReport;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SalesOperationsReportTest {
    
    @Test
    public void printReportTest() {
        new SalesOperationsReport(
                Stream.of(
                        MessagesFactory.simpleSaleMessage("orange", BigDecimal.valueOf(100l)),
                        MessagesFactory.operationSalesMessage("apple", BigDecimal.valueOf(10l), SalesOperationMessage.Operation.SUBSTRUCT),
                        MessagesFactory.operationSalesMessage("apple", BigDecimal.valueOf(20l), SalesOperationMessage.Operation.ADD),

                        MessagesFactory.operationSalesMessage("grape", BigDecimal.valueOf(25l), SalesOperationMessage.Operation.MULTIPLE)
                ).collect(Collectors.toList())
        ).printReport();
    }
}
