package com.spenikj.salesmessageprocessor.message;

public class SimpleSaleMessageProcessor extends AbstractMessageProcessor {

    @Override
    public boolean accept(AbstractMessage message) {
        return message instanceof SimpleSaleMessage;
    }

    @Override
    public void doProcess(AbstractMessage message) {
        SimpleSaleMessage simpleMessage = (SimpleSaleMessage) message;

        salesMessageProcessor.getSales().add(simpleMessage.getSale());
    }
}
