package com.spenikj.salesmessageprocessor.message;

public class SimpleSaleMessageProcessor extends AbstractMessageProcessor {

    @Override
    protected boolean accept(AbstractMessage message) {
        return message instanceof SimpleSaleMessage;
    }

    @Override
    protected void doProcess(AbstractMessage message) {
        SimpleSaleMessage simpleMessage = (SimpleSaleMessage) message;

        salesMessageProcessor.getSales().add(simpleMessage.getSale());
    }
}
