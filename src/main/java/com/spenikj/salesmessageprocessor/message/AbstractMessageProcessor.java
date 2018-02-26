package com.spenikj.salesmessageprocessor.message;

import com.spenikj.salesmessageprocessor.SalesMessageProcessor;

public abstract class AbstractMessageProcessor {

    protected SalesMessageProcessor salesMessageProcessor;
    protected AbstractMessageProcessor nextProcessor;

    protected abstract void doProcess(AbstractMessage message);

    protected abstract boolean accept(AbstractMessage message);

    public void process(AbstractMessage message) {
        if (accept(message)) {
            salesMessageProcessor.getMessages().add(message);
            doProcess(message);
        } else if (nextProcessor != null) {
            nextProcessor.process(message);
        } else {
            System.out.println("No processor for message type " + message.getClass().getSimpleName());
        }
    }

    public void setSalesMessageProcessor(SalesMessageProcessor main) {
        this.salesMessageProcessor = main;
    }

    public void setNextProcessor(AbstractMessageProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }
}
