package com.spenikj.salesmessageprocessor.reports;

import com.spenikj.salesmessageprocessor.message.AbstractMessage;
import com.spenikj.salesmessageprocessor.message.SalesOperationMessage;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

public class SalesOperationsReport extends Report {

    protected final Map<String, List<SalesOperationMessage>> operationMessages;

    public SalesOperationsReport(Collection<AbstractMessage> messages) {
        this.operationMessages = messages
                .stream()
                .filter(message -> message instanceof SalesOperationMessage)
                .map(SalesOperationMessage.class::cast)
                .collect(
                        groupingBy(SalesOperationMessage::getProductTypeName)
                );
    }

    @Override
    public void printReport() {

        System.out.println("Adjustment report\n----");

        operationMessages.keySet().forEach(key -> {
            System.out.println("\n" + key + ":\n----");
            operationMessages.get(key).
                    forEach(message -> System.out.println(String.format("Operation: %s, amount: %s", message.getOperation(), message.getAmount())));
        });
    }
}
