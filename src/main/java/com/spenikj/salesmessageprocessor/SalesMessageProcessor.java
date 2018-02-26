package com.spenikj.salesmessageprocessor;

import com.spenikj.salesmessageprocessor.sale.Sale;
import com.spenikj.salesmessageprocessor.message.AbstractMessage;
import com.spenikj.salesmessageprocessor.message.AbstractMessageProcessor;
import com.spenikj.salesmessageprocessor.reports.SalesOperationsReport;
import com.spenikj.salesmessageprocessor.reports.SimpleSalesReport;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

/**
 *
 * @author Spenik János <jspenik@indamail.hu>
 */
public class SalesMessageProcessor {

    protected static final Logger LOGGER = Logger.getLogger(SalesMessageProcessor.class.getSimpleName());

    protected final Collection<AbstractMessage> messages = new ArrayDeque<>();
    protected final Collection<Sale> sales = new ArrayList<>();
    protected boolean acceptMessages = true;
    protected AbstractMessageProcessor defaultProcessor;

    public void addMessage(AbstractMessage message) {
        if (acceptMessages) {
            defaultProcessor.process(message);

            if (messages.size() % 50 == 0) {
                acceptMessages = false;
                LOGGER.info("Pausing...");
                new SalesOperationsReport(messages).printReport();
            } else if (messages.size() % 10 == 0) {
                new SimpleSalesReport(sales).printReport();
            }
        } else {
            LOGGER.severe("The application currently does not accept messages!");
        }
    }

    public Collection<AbstractMessage> getMessages() {
        return messages;
    }

    public Collection<Sale> getSales() {
        return sales;
    }

    public boolean isAcceptMessages() {
        return acceptMessages;
    }

    public AbstractMessageProcessor getDefaultProcessor() {
        return defaultProcessor;
    }

    public void setDefaultProcessor(AbstractMessageProcessor defaultProcessor) {
        this.defaultProcessor = defaultProcessor;
    }
}
