package com.exchange;

import java.math.BigDecimal;

public class ExchangeDTO {

    private BigDecimal currency;
    private String  sourceCode;
    private String  targetCode;

    public ExchangeDTO(){
    }

    public ExchangeDTO(BigDecimal currency, String  sourceCode, String  targetCode){
        this.currency = currency;
        this.sourceCode = sourceCode;
        this.targetCode = targetCode;
    }

    public BigDecimal getCurrency() {
        return currency;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setCurrency(BigDecimal currency) {
        this.currency = currency;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }
}
