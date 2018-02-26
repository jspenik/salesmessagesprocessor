package com.spenikj.salesmessageprocessor.message;

public class MultiSalesMessageProcessor extends AbstractMessageProcessor {

    @Override
    protected void doProcess(AbstractMessage message) {
        MultiSalesMessage multiSalesMessage = (MultiSalesMessage) message;

        salesMessageProcessor.getSales().addAll(multiSalesMessage.getSales());
    }

    @Override
    protected boolean accept(AbstractMessage message) {
        return message instanceof MultiSalesMessage;
    }
}
