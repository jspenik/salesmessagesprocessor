package com.spenikj.test;

import com.spenikj.salesmessageprocessor.SalesMessageProcessor;
import com.spenikj.salesmessageprocessor.message.AbstractMessageProcessor;
import com.spenikj.salesmessageprocessor.message.MessagesFactory;
import com.spenikj.salesmessageprocessor.message.MultiSalesMessageProcessor;
import com.spenikj.salesmessageprocessor.message.SalesOperationMessage;
import com.spenikj.salesmessageprocessor.message.SalesOperationMessageProcessor;
import com.spenikj.salesmessageprocessor.message.SimpleSaleMessageProcessor;
import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MessagesTest {

    @Test
    public void everyTenthMessages() {
        SalesMessageProcessor processor = initProcessorMain();

        Stream.of(
                MessagesFactory.multipleSalesMessage("apple", BigDecimal.valueOf(10l), 10),
                MessagesFactory.multipleSalesMessage("apple", BigDecimal.valueOf(10l), 10),
                MessagesFactory.multipleSalesMessage("apple", BigDecimal.valueOf(10l), 10),
                MessagesFactory.multipleSalesMessage("peach", BigDecimal.valueOf(10l), 10),
                MessagesFactory.multipleSalesMessage("orange", BigDecimal.valueOf(10l), 10),
                MessagesFactory.multipleSalesMessage("potato", BigDecimal.valueOf(10l), 10),
                MessagesFactory.multipleSalesMessage("potato", BigDecimal.valueOf(20l), 10),
                MessagesFactory.multipleSalesMessage("apple", BigDecimal.valueOf(10l), 10),
                MessagesFactory.multipleSalesMessage("cucumber", BigDecimal.valueOf(10l), 10),
                MessagesFactory.multipleSalesMessage("cucumber", BigDecimal.valueOf(10l), 10)
        ).forEach(processor::addMessage);
    }

    @Test
    public void fiftyMessages() {
        SalesMessageProcessor processor = initProcessorMain();

        Stream.concat(
                IntStream.rangeClosed(1, 49).boxed().map(i -> {
                    return MessagesFactory.multipleSalesMessage("cucumber", BigDecimal.valueOf(10l), i);
                }),
                Stream.of(
                        MessagesFactory.operationSalesMessage("cucumber", BigDecimal.ONE, SalesOperationMessage.Operation.ADD)
                )
        )
                .forEach(processor::addMessage);

        Assert.assertEquals(processor.isAcceptMessages(), false);
    }

    private SalesMessageProcessor initProcessorMain() {
        SalesMessageProcessor processor = new SalesMessageProcessor();

        AbstractMessageProcessor salesOperationProcessor = new SalesOperationMessageProcessor();
        salesOperationProcessor.setSalesMessageProcessor(processor);

        AbstractMessageProcessor multiSalesProcessor = new MultiSalesMessageProcessor();
        multiSalesProcessor.setSalesMessageProcessor(processor);
        multiSalesProcessor.setNextProcessor(salesOperationProcessor);

        AbstractMessageProcessor simpleProcessor = new SimpleSaleMessageProcessor();
        simpleProcessor.setSalesMessageProcessor(processor);
        simpleProcessor.setNextProcessor(multiSalesProcessor);

        processor.setDefaultProcessor(simpleProcessor);

        return processor;
    }
}
