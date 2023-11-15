package restTest.CurrencyTransactions.models;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MonetaryTransactions {
    private Date date;
    private String description;
    private double amount;
    private String currency;

    public MonetaryTransactions() {
    }

    public MonetaryTransactions(Date date, String description, double amount, String currency) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
