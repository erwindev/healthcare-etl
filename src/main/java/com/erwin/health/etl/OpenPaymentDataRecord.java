package com.erwin.health.etl;

import java.io.Serializable;

public class OpenPaymentDataRecord implements Serializable {

    private String providerId;
    private String providerName;
    private String payerId;
    private String payerName;
    private String paymentAmount;

    public String getProviderId() {
        return providerId;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
    public String getProviderName() {
        return providerName;
    }
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
    public String getPayerId() {
        return payerId;
    }
    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }
    public String getPayerName() {
        return payerName;
    }
    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }
    public String getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
