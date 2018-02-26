package com.spenikj.salesmessageprocessor.message;

import java.math.BigDecimal;

public class SalesOperationMessage extends AbstractMessage {

    protected final String productTypeName;
    protected final BigDecimal amount;
    protected final Operation operation;

    public SalesOperationMessage(String productTypeName, BigDecimal amount, Operation operation) {
        this.productTypeName = productTypeName;
        this.amount = amount;
        this.operation = operation;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Operation getOperation() {
        return operation;
    }

    public enum Operation {

        ADD {
            @Override
            public BigDecimal execute(BigDecimal value, BigDecimal amount) {
                return value.add(value);
            }
        }, SUBSTRUCT {
            @Override
            public BigDecimal execute(BigDecimal value, BigDecimal amount) {
                return value.subtract(amount);
            }
        }, MULTIPLE {
            @Override
            public BigDecimal execute(BigDecimal value, BigDecimal amount) {
                return value.multiply(amount);
            }
        };

        public abstract BigDecimal execute(BigDecimal value, BigDecimal amount);
    }
}
