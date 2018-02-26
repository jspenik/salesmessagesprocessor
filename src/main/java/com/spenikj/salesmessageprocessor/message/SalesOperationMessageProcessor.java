package com.spenikj.salesmessageprocessor.message;

public class SalesOperationMessageProcessor extends AbstractMessageProcessor {

    @Override
    protected void doProcess(AbstractMessage message) {
        SalesOperationMessage salesOperationMessage = (SalesOperationMessage) message;

        salesMessageProcessor.getSales().stream().filter(sale -> sale.getProductType().getName().equals(salesOperationMessage.productTypeName)).forEach(sale -> {
            sale.setValue(salesOperationMessage.operation.execute(sale.getValue(), salesOperationMessage.amount));
        });
    }

    @Override
    public boolean accept(AbstractMessage message) {
        return message instanceof SalesOperationMessage;
    }

}
