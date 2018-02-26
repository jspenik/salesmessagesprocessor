package com.spenikj.salesmessageprocessor.reports;

import com.spenikj.salesmessageprocessor.message.AbstractMessage;
import com.spenikj.salesmessageprocessor.message.SalesOperationMessage;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Spenik János <jspenik@indamail.hu>
 */
public class SalesOperationsReport extends Report {

    protected Collection<AbstractMessage> messages;

    public SalesOperationsReport(Collection<AbstractMessage> messages) {
        this.messages = messages;
    }

    @Override
    public void printReport() {
        Map<String, Collection<SalesOperationMessage>> filteredMessages = new HashMap<>();

        messages
                .stream()
                .filter(message -> message instanceof SalesOperationMessage)
                .map(SalesOperationMessage.class::cast)
                .forEach(message -> filteredMessages.compute(message.getProductTypeName(), (k, v) -> {
            if (v != null) {
                v.add(message);

                return v;
            } else {
                return Arrays.asList(message);
            }
        }));

        System.out.println("Adjustment report");
        filteredMessages.keySet().forEach(key -> {
            System.out.println(key + ":");
            System.out.println("----");
            Collection<SalesOperationMessage> m = filteredMessages.get(key);
            m.forEach(m1 -> System.out.println(String.format("Operation: %s, amount: %s", m1.getOperation(), m1.getAmount())));
        });
    }

}
