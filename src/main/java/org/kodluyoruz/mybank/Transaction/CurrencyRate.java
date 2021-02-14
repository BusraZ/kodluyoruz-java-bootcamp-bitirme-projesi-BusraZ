package org.kodluyoruz.mybank.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class CurrencyRate {
    private Map<String,Double> rates;
    private String base;
    private Date date;

    public CurrencyRate(Map<String, Double>  rates, String base, Date date) {
        this.rates = rates;
        this.base=base;
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
