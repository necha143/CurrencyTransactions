package restTest.CurrencyTransactions.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int id;
    @Column(name = "date")
    private Date date;
    @Column(name = "id_currency")
    private String idCurrency;
    @Column(name = "name_currency")
    private String nameCurrency;
    @Column(name = "val")
    private double value;

    public ExchangeRate() {
    }

    public ExchangeRate(Date date, String idCurrency, String nameCurrency, double value) {
        this.date = date;
        this.idCurrency = idCurrency;
        this.nameCurrency = nameCurrency;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(String idCurrency) {
        this.idCurrency = idCurrency;
    }

    public String getNameCurrency() {
        return nameCurrency;
    }

    public void setNameCurrency(String nameCurrency) {
        this.nameCurrency = nameCurrency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "date=" + date +
                ", idCurrency=" + idCurrency +
                ", nameCurrency='" + nameCurrency + '\'' +
                ", value=" + value +
                '}';
    }
}
